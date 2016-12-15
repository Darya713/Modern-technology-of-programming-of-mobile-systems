using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class CarScript : MonoBehaviour {

    WheelJoint2D[] wheelJoints;
    JointMotor2D frontWheel;
    JointMotor2D backWheel;

    public float maxSpeed = -1000f;
    private float maxBackSpeed = 1500f;
    private float acceleration = 300f;
    private float deacceleration = -100f;
    public float brakeForce = 3000f;
    private float gravity = 9.8f;
    private float angleCar = 0;
    public float wheelSize = 0.116f;
    public bool ground = false;
    public LayerMask map;
    public Transform bwheel;
    public int coinsInt = 0;
    public Text coinsText;
    public GameObject fp;
    private AudioSource carSound;
    public AudioSource coinSound;

    public ClickScript[] controlCar;

	// Use this for initialization
	void Start () {
        wheelJoints = gameObject.GetComponents<WheelJoint2D>();
        backWheel = wheelJoints[1].motor;
        frontWheel = wheelJoints[0].motor;
        carSound = GetComponent<AudioSource>();
	}

    void Update()
    {
        coinsText.text = coinsInt.ToString();
        ground = Physics2D.OverlapCircle(bwheel.transform.position, wheelSize, map);
    }

    // Update is called once per frame
    void FixedUpdate()
    {

        frontWheel.motorSpeed = backWheel.motorSpeed;

        angleCar = transform.localEulerAngles.z;

        if (angleCar >= 180)
        {
            angleCar = angleCar - 360;
        }

        //if (ground == true)
        //{
        if (controlCar[0].clickedIs == true)
        {
            backWheel.motorSpeed = Mathf.Clamp(backWheel.motorSpeed - (acceleration - gravity * Mathf.PI * (angleCar / 180) * 100) * Time.deltaTime, maxSpeed, maxBackSpeed);
        }
        if ((controlCar[0].clickedIs == false && backWheel.motorSpeed < 0) || (controlCar[0].clickedIs == false && backWheel.motorSpeed == 0 && angleCar < 0))
        {
            backWheel.motorSpeed = Mathf.Clamp(backWheel.motorSpeed - (deacceleration - gravity * Mathf.PI * (angleCar / 180) * 100) * Time.deltaTime, maxSpeed, 0);
        }
        else if ((controlCar[0].clickedIs == false && backWheel.motorSpeed > 0) || (controlCar[0].clickedIs == false && backWheel.motorSpeed == 0 && angleCar > 0))
        {
            backWheel.motorSpeed = Mathf.Clamp(backWheel.motorSpeed - (-deacceleration - gravity * Mathf.PI * (angleCar / 180) * 100) * Time.deltaTime, 0, maxBackSpeed);
        }
        //}
        //else if (controlCar[0].clickedIs == false && backWheel.motorSpeed < 0)
        //{
        //    backWheel.motorSpeed = Mathf.Clamp(backWheel.motorSpeed - deacceleration * Time.deltaTime, maxSpeed, 0);
        //}
        //else if (controlCar[0].clickedIs == false && backWheel.motorSpeed > 0)
        //{
        //    backWheel.motorSpeed = Mathf.Clamp(backWheel.motorSpeed + deacceleration * Time.deltaTime, 0, maxBackSpeed);
        //}
        //if (controlCar[0].clickedIs == true && ground == false)
        //{
        //    backWheel.motorSpeed = Mathf.Clamp(backWheel.motorSpeed - (acceleration - gravity * Mathf.PI * (angleCar / 180) * 100) * Time.deltaTime, maxSpeed, maxBackSpeed);
        //}
        //

        if (controlCar[1].clickedIs == true && backWheel.motorSpeed > 0)
        {
            backWheel.motorSpeed = Mathf.Clamp(backWheel.motorSpeed - brakeForce * Time.deltaTime, 0, maxBackSpeed);
        }
        else if (controlCar[1].clickedIs == true && backWheel.motorSpeed < 0)
        {
            backWheel.motorSpeed = Mathf.Clamp(backWheel.motorSpeed + brakeForce * Time.deltaTime, maxSpeed, 0);
        }

        wheelJoints[1].motor = backWheel;
        wheelJoints[0].motor = frontWheel;

        carSound.pitch = Mathf.Clamp(-backWheel.motorSpeed / 1000, 0.3f, 3);
    }

    void OnTriggerEnter2D(Collider2D trigger)
    {
        if (trigger.gameObject.tag == "coins")
        {
            coinsInt++;
            coinSound.Play();
            Destroy(trigger.gameObject);
        }
        else if (trigger.gameObject.tag == "finish")
        {
            fp.SetActive(true);
        }
        
    }

    void OnDrawGizmos()
    {
        Gizmos.DrawWireSphere(bwheel.transform.position, wheelSize);
    }
}
