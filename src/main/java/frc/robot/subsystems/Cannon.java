package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

public class Cannon extends SubsystemBase{
    private Relay[] CannonRelays ={new Relay(0), new Relay(1), new Relay(2), new Relay(3)};
    private GenericEntry FireTimeSelector;
    public Cannon(GenericEntry FireTimeSelector){
        for(Relay ThisRelay : CannonRelays){
            ThisRelay.set(Value.kOff);
        }
        this.FireTimeSelector=FireTimeSelector;
    }
    public double GetFireTime(){
        return FireTimeSelector.getDouble(.2);
    }
    public void fire(int Num){
        CannonRelays[Num].set(Value.kForward);
    }
    public void stop(){
        for(Relay ThisRelay : CannonRelays){
            ThisRelay.set(Value.kOff);
        }
    }
    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
    }    
}
