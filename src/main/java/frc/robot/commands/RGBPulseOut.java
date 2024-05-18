package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RGBControl;

public class RGBPulseOut extends Command {
    RGBControl RGBStrip;
    Timer Time;
    float TBtwn = 0.1;
    int middle = 50;
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    public RGBPulseOut(RGBControl RGBStrip){
        this.RGBStrip=RGBStrip;
        addRequirements(this.RGBStrip);
    }

    @Override
    public boolean runsWhenDisabled()
    {
        return true;
    }

    @Override
    public void initialize() {
        Time.restart();
    }

    @Override
    public void execute(){
        for (int i = 0; i < 108; i++) {
            //int rb=255*(((int)(Time.get()*TBtwn)-Math.abs(middle-i))>0);
            int rb=0;
            RGBStrip.Set(i,0,0,255);
        }
    }

    @Override
    public void end(boolean interrupted) {} 

    @Override
    public boolean isFinished() {
        return (Time.get()*TBtwn)>108;
    }
}
