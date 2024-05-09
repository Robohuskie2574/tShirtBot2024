package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Cannon;
import edu.wpi.first.wpilibj.Timer;

enum fStage{
    Firing,Done
}
public class CannonControl extends Command {
    private fStage FireStage;
    private Timer FireTimer = new Timer();
    private final Cannon Cannon;
    int Barrel = 0;
    double FireTime = .2;
    public CannonControl(Cannon inCannon, int inBarrel){
        Cannon = inCannon;
        Barrel = inBarrel;
        addRequirements(Cannon);
    }
    @Override
    public void initialize(){
        FireTime=Cannon.GetFireTime();
        FireStage=fStage.Firing;
        Cannon.fire(Barrel);
        FireTimer.restart();
    }
    @Override
    public void execute() {
        if(FireTimer.get()>=FireTime){
            FireStage = fStage.Done;
        }
    }
    @Override
    public void end(boolean interrupted) {
        Cannon.stop();
    }
    @Override
    public boolean isFinished() {
        return (FireStage==fStage.Done);
    }
    @Override
    public InterruptionBehavior getInterruptionBehavior(){
        return InterruptionBehavior.kCancelIncoming;
    }
}
