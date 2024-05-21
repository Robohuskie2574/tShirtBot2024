package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RGBControl;
import frc.robot.Constants.*;

public class RGBRunning extends Command {
    RGBControl RGBStrip;
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    public RGBRunning(RGBControl RGBStrip){
        this.RGBStrip=RGBStrip;
        addRequirements(this.RGBStrip);
    }

    @Override
    public boolean runsWhenDisabled()
    {
        return true;
    }

    @Override
    public void execute(){
        for (int i = 0; i < frc.robot.Constants.LEDlen; i++) {
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
