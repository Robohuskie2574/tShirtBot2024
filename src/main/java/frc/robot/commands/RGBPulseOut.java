package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RGBControl;

public class RGBPulseOut extends Command {
    RGBControl RGBStrip;
    Timer Time = new Timer();
    double TBtwn = 10;
    int middle = 50;
    Boolean AutoEnd; // does nothing
    Boolean ReturnMode;
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    public RGBPulseOut(RGBControl RGBStrip,double Speed,Boolean AutoEnd,Boolean ReturnMode){
        this.RGBStrip=RGBStrip;
        this.TBtwn=Speed;
        this.AutoEnd = AutoEnd;
        this.ReturnMode=ReturnMode;
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
            Boolean thisdiff=((Math.abs(middle-i))<Time.get()*TBtwn);
            thisdiff=ReturnMode?!thisdiff:thisdiff;
            RGBStrip.Set(i,thisdiff?255:0,0,thisdiff?0:255);
        }
    }

    @Override
    public void end(boolean interrupted) {} 

    @Override
    public boolean isFinished() {
        return AutoEnd?(int)(Time.get()*TBtwn)>108:false;
    }
}
