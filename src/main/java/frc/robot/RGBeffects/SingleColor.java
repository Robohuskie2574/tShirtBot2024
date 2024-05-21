package frc.robot.RGBeffects;
import java.util.ArrayList;

import frc.robot.Constants;

import java.awt.Color;
public class SingleColor extends BaseLEDEffect{
    int r,g,b;
    public SingleColor(int r,int g,int b){
        this.r=r;
        this.g=g;
        this.b=b;
    }
    int sLen=frc.robot.Constants.LEDlen;
    @Override
    ArrayList<Color> GetEffect(Boolean Triggered){
        ArrayList<Color> RetList = new ArrayList<Color>();
        for(int i = 0; i < sLen; i++){
            RetList.add(new Color(r,g,b));
        }
        return RetList;
    }
}
