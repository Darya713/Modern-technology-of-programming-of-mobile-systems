using UnityEngine;
using System.Collections;

public class ClickScript : MonoBehaviour {

    public bool clickedIs = false;
    
    public void OnClick()
    {
        clickedIs = true;
    }

    public void OnStopClick()
    {
        clickedIs = false;
    }

}
