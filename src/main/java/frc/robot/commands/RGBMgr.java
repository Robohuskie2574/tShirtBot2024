package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RGBControl;
import frc.robot.Constants.*;
import frc.robot.RGBeffects.*;
import java.util.ArrayList;
import java.awt.Color;
public class RGBMgr extends Command {
    RGBControl RGBStrip;
    java.util.ArrayList<BaseLEDEffect> EffectStack = new ArrayList<BaseLEDEffect>();
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    public RGBMgr(RGBControl RGBStrip){
        this.RGBStrip=RGBStrip;
        addRequirements(this.RGBStrip);
        EffectStack.add(new SingleColor(0,0,255));
    }

    @Override
    public boolean runsWhenDisabled()
    {
        return true;
    }

    @Override
    public void execute(){
        for(BaseLEDEffect ThisEffect : EffectStack){
            
        }
        for (int i = 0; i < frc.robot.Constants.LEDlen; i++){
            int gMode = (24*i)%512;
            gMode = gMode>255?511-gMode:gMode;
            RGBStrip.Set(i,gMode,gMode,255);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public void initialize() {

    } 

    @Override
    public boolean isFinished() {
        return false;
    }
}
