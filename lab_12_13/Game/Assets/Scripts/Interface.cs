using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class Interface : MonoBehaviour {

    public CarScript cs;
    private SmoothCamera sc;
    public Text coinsCount;
    public int coinsTarget3 = 10;
    public int coinsTarget2 = 6;
    public int coinsTarget1 = 3;
    public string keyName = "S";
    public Image[] stars;
    public GameObject[] cars;
    private bool isPaused = false;
    public GameObject PausePanel;

    void Start()
    {
        sc = GetComponent<SmoothCamera>();
        cs = cars[PlayerPrefs.GetInt("c")].GetComponent<CarScript>();
        cars[PlayerPrefs.GetInt("c")].SetActive(true);
        sc.target = cars[PlayerPrefs.GetInt("c")].transform;
        sc.sc = cars[PlayerPrefs.GetInt("c")].GetComponent<WheelJoint2D>();
    }

    void Update ()
    {
        if (cs.fp.activeSelf)
        {
            for (int i = 0; i < cs.controlCar.Length; i++)
            {
                cs.controlCar[i].clickedIs = false;
                cs.controlCar[i].gameObject.SetActive(false);
            }
            coinsCount.text = "Собрано монет: " + cs.coinsInt.ToString();
            if (Input.GetMouseButtonDown(0))
            {
                SceneManager.LoadScene(0);
            }
            if (cs.coinsInt == coinsTarget3)
            {
                stars[0].color = new Color(stars[0].color.r, stars[0].color.g, stars[0].color.b, 255);
                stars[1].color = new Color(stars[1].color.r, stars[1].color.g, stars[1].color.b, 255);
                stars[2].color = new Color(stars[2].color.r, stars[2].color.g, stars[2].color.b, 255);
                PlayerPrefs.SetInt(keyName, 3);
                PlayerPrefs.Save();
            }
            else if (cs.coinsInt >= coinsTarget2 && cs.coinsInt != coinsTarget3)
            {
                stars[0].color = new Color(stars[0].color.r, stars[0].color.g, stars[0].color.b, 255);
                stars[1].color = new Color(stars[1].color.r, stars[1].color.g, stars[1].color.b, 255);
                if (PlayerPrefs.GetInt(keyName) != 3)
                {
                    PlayerPrefs.SetInt(keyName, 2);
                    PlayerPrefs.Save();
                }
            }
            else if (cs.coinsInt >= coinsTarget1 && cs.coinsInt != coinsTarget2)
            {
                stars[0].color = new Color(stars[0].color.r, stars[0].color.g, stars[0].color.b, 255);
                if (PlayerPrefs.GetInt(keyName) != 3 && PlayerPrefs.GetInt(keyName) != 2)
                {
                    PlayerPrefs.SetInt(keyName, 1);
                    PlayerPrefs.Save();
                }
            }
        }
        if (Input.GetKeyDown(KeyCode.Escape) && !isPaused && !cs.fp.activeSelf)
        {
            PausePanel.SetActive(true);
            Time.timeScale = 0;
            isPaused = true;
        }
        else if (Input.GetKeyDown(KeyCode.Escape) && isPaused)
        {
            PausePanel.SetActive(false);
            Time.timeScale = 1;
            isPaused = false;
        }
	}

    public void pauseOn()
    {
        PausePanel.SetActive(true);
        Time.timeScale = 0;
        isPaused = true;
    }

    public void _continue()
    {
        PausePanel.SetActive(false);
        Time.timeScale = 1;
        isPaused = false;
    }

    public void goToMenu()
    {
        Time.timeScale = 1;
        SceneManager.LoadScene("menu");
    }

}
