// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Jaguar;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrainSubsystem extends SubsystemBase {
    Jaguar drive_left_front = new Jaguar(0);
    Jaguar drive_right_front = new Jaguar(1);
    Jaguar drive_left_back = new Jaguar(2);
    Jaguar drive_right_back = new Jaguar(3);
    DifferentialDrive MainRobotDrive = new DifferentialDrive(drive_left_front,drive_right_front);
    public DriveTrainSubsystem() {
        drive_left_front.addFollower(drive_left_back);
        drive_right_front.addFollower(drive_right_back);
    }
    public void Drive(double move,double rotate){
        MainRobotDrive.arcadeDrive(rotate, move);
    }

/*
    public Command exampleMethodCommand() {
        return runOnce(
                () -> {
                });
    }
*/

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
    }
    
}
