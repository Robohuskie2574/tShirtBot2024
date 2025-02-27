package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.ArrayList;
import frc.robot.Color;
import frc.robot.Rgbdyncfg;
import frc.robot.Rgbmgr;

public class RGBControl extends SubsystemBase{
    AddressableLED CannonStrip;
    AddressableLEDBuffer Colors;
    private ShuffleboardTab ConfigTab = Shuffleboard.getTab("Config");
    private GenericEntry BrightnessMultiplier;
    public int StripSize = 108;
    private boolean StrictAlpha = false;
    private int RgbErrors = 0;
    public RGBControl(){
        BrightnessMultiplier = ConfigTab
        .add("LED Brightness",1)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 1))
        .getEntry();
        CannonStrip = new AddressableLED(4);
        Colors = new AddressableLEDBuffer(StripSize);
        CannonStrip.setLength(Colors.getLength());
        for (int i = 0; i < StripSize; i++) {
            Colors.setRGB(i,0,0,0);
        }
        for (int i = 0; i < StripSize; i++) {
            Colors.setRGB(i,0,0,255);
        }
        CannonStrip.setData(Colors);
        CannonStrip.start();
        Rgbdyncfg.RGBShiftTimer.start();
    }

    public void DoStuff(){
        ArrayList<Color> ColorList = Rgbmgr.Root();
        try{
            if(StripSize!=ColorList.size()){
                String ErrorString = String.format("The size of the list of colors (%d) and the size of the strip (%d) mismatch!", ColorList.size(),StripSize);
                throw new Exception(ErrorString);
            }
            for(int i = 0; i < StripSize; i++){
                Color ThisColor = ColorList.get(i);
                if(0 > ThisColor.r || ThisColor.r > 1){
                    throw new Exception(String.format("Index %d violates range check on channel r. Value of %f not between 0 and 1.",i,ThisColor.r));
                }
                if(0 > ThisColor.g || ThisColor.g > 1){
                    throw new Exception(String.format("Index %d violates range check on channel g. Value of %f not between 0 and 1.",i,ThisColor.g));
                }
                if(0 > ThisColor.b || ThisColor.b > 1){
                    throw new Exception(String.format("Index %d violates range check on channel b. Value of %f not between 0 and 1.",i,ThisColor.b));
                }
                if(StrictAlpha && (ThisColor.a!=1)){
                    throw new Exception(String.format("Index %d violates range check on channel a. Value of %f not equal to 1. Strict alpha is enabled",i,ThisColor.a));
                }else if(0 > ThisColor.a || ThisColor.a > 1){
                    throw new Exception(String.format("Index %d violates range check on channel a. Value of %f is not between 0 and 1. Strict alpha is disabled",i,ThisColor.a));
                }
            }
        }catch(Exception e){
            ColorList = Rgbmgr.AllOneColor(new Color(0,0,0));
            RgbErrors++;
            System.err.printf("A critcal error has occured in the RGB code. RGB has been turned set to black. This is critical error number %d.\n",RgbErrors);
            System.err.printf("Current Config: %s\n",Rgbdyncfg.cfgstr());
            e.printStackTrace();
        }
        float b = BrightnessMultiplier.getFloat(1);
        for(int i = 0; i < StripSize; i++){
            Colors.setRGB(i,(int)(ColorList.get(i).r*255*b),(int)(ColorList.get(i).g*255*b),(int)(ColorList.get(i).b*255*b));
        }
    }

    @Override
    public void periodic() {
        DoStuff();
        CannonStrip.setData(Colors);
    }

    @Override
    public void simulationPeriodic() {
    }    
}
