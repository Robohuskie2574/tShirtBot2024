package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RGBControl;

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
        for (int i = 0; i < 108; i++) {
            RGBStrip.Set(i,0,0,255);
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
