package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class Rgbdyncfg{
    public static float RainbowSecSquish = 10;
    public static float RainbowSecShiftSpeed = 100;
    public static Timer RGBShiftTimer = new Timer();
    public static String cfgstr(){
        String DataString = new String();
        DataString+=String.format("RainbowSecSquish:%f, ",RainbowSecSquish);
        DataString+=String.format("RainbowSecShiftSpeed:%f, ",RainbowSecShiftSpeed);
        DataString+=String.format("Timer:%f, ",RGBShiftTimer.get());
        return DataString;
    }
}