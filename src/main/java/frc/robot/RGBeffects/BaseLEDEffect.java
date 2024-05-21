package frc.robot.RGBeffects;
import java.util.ArrayList;

import frc.robot.Constants;

import java.awt.Color;
public class BaseLEDEffect {
    int sLen=frc.robot.Constants.LEDlen;
    ArrayList<Color> GetEffect(Boolean Triggered){
        ArrayList<Color> RetList = new ArrayList<Color>();
        for(int i = 0; i < sLen; i++){
            RetList.add(new Color(0,0,0,0));
        }
        return RetList;
    }
}
