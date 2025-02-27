package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.*;
import java.util.Map;

public class DriveTeleOp extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveTrainSubsystem DriveTrain;
    private final Joystick Joystick = new Joystick(0);
    private ShuffleboardTab ConfigTab = Shuffleboard.getTab("Config");
    private GenericEntry FineControlRampUpRate;
    private GenericEntry FineControlModeSpeedSelector;
    private double mul = 1.0;
    private Timer ButtonReleaseTimer = new Timer();
    public DriveTeleOp(DriveTrainSubsystem InputDriveTrain) {
        FineControlModeSpeedSelector = ConfigTab
        .add("Fine Control Mode Speed Multiplier",.75)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 1))
        .getEntry();
        FineControlRampUpRate = ConfigTab
        .add("Fine Control Disable Ramp Up Rate",1)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 2.5))
        .getEntry();
        ButtonReleaseTimer.start();
        DriveTrain = InputDriveTrain;
        addRequirements(DriveTrain);
    }


    @Override
    public void execute(){
        Boolean SlowDrive = Joystick.getRawButton(2);
        Double SlowSpeed=FineControlModeSpeedSelector.getDouble(.75);
        Double RampRate=FineControlRampUpRate.getDouble(1);
        if(SlowDrive){
            ButtonReleaseTimer.restart();
            mul = SlowSpeed;
            System.out.println(mul);
        }else{
            mul=Math.min(SlowSpeed+ButtonReleaseTimer.get()*RampRate,1);
        }
        DriveTrain.Drive(Joystick.getY()*mul,Joystick.getTwist());
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
