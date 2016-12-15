using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class MenuScript : MonoBehaviour {

    public GameObject levelChanger;
    public GameObject exitPanel;
    public GameObject garagePanel;
    public Image[] cars;
    public Button[] buttons;
    public Text[] carsText;
    public string[] levels;

    void Start()
    {
        //PlayerPrefs.DeleteAll();
        cars[PlayerPrefs.GetInt("c")].color = Color.white;
        if (PlayerPrefs.GetInt(levels[0]) == 3)
        {
            buttons[0].interactable = true;
            carsText[0].gameObject.SetActive(false);
        }
    }

    void Update()
    {
        if (garagePanel.activeSelf == true && Input.GetKeyDown(KeyCode.Escape))
        {
            garagePanel.SetActive(false);
        }
        else if (levelChanger.activeSelf == true && Input.GetKeyDown(KeyCode.Escape))
        {
            levelChanger.SetActive(false);
        }
        else if (exitPanel.activeSelf == false && Input.GetKeyDown(KeyCode.Escape))
        {
            exitPanel.SetActive(true);
        }
        else if (Input.GetKeyDown(KeyCode.Escape))
        {
            exitPanel.SetActive(false);
        }
    }

	public void OnClickStart()
    {
        levelChanger.SetActive(true);
    }

    public void OnClickExit()
    {
        Application.Quit();
    }

    public void levelButtons(int level)
    {
        SceneManager.LoadScene(level);
    }

    public void carChanger(int car)
    {
        PlayerPrefs.SetInt("c", car);
        PlayerPrefs.Save();
        for (int i = 0; i < cars.Length; i++)
        {
            cars[i].color = Color.black;
            cars[car].color = Color.white;
        }
    }
}
