package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RGBControl extends SubsystemBase{
    AddressableLED CannonStrip;
    AddressableLEDBuffer Colors;
    private ShuffleboardTab ConfigTab = Shuffleboard.getTab("Config");
    private GenericEntry BrightnessMultiplier;
    public RGBControl(){
        BrightnessMultiplier = ConfigTab
        .add("LED Brightness",.5)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 1))
        .getEntry();
        CannonStrip = new AddressableLED(4);
        Colors = new AddressableLEDBuffer(108);
        CannonStrip.setLength(Colors.getLength());
        for (int i = 0; i < 108; i++) {
            Colors.setRGB(i,0,0,0);
        }
        for (int i = 0; i < 30; i++) {
            Colors.setRGB(i,0,0,255);
        }
        CannonStrip.setData(Colors);
        CannonStrip.start();
    }

    public void Set(int i,int r,int g,int b){
        Colors.setRGB(i, (int)(r*BrightnessMultiplier.getDouble(.5)), (int)(g*BrightnessMultiplier.getDouble(.5)), (int)(b*BrightnessMultiplier.getDouble(.5))); //*multiplies your brightness*/
    }
    
    @Override
    public void periodic() {
        CannonStrip.setData(Colors);
    }

    @Override
    public void simulationPeriodic() {
    }    
}
