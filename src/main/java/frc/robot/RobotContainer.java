// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import java.util.Map;


public class RobotContainer {
    ShuffleboardTab ConfigTab;
    GenericEntry FireTimeSelector;
    private final DriveTrainSubsystem RobotDriveTrainSubsystem = new DriveTrainSubsystem();
    private Cannon RobotCannon;
    private final Joystick ControlJoystick = new Joystick(0);
    private final JoystickButton SafetyButton = new JoystickButton(ControlJoystick,1);
    private final Trigger Cannon1 = new Trigger(new JoystickButton(ControlJoystick,5)).and(SafetyButton);
    private final Trigger Cannon2 = new Trigger(new JoystickButton(ControlJoystick,3)).and(SafetyButton);
    private final Trigger Cannon3 = new Trigger(new JoystickButton(ControlJoystick,4)).and(SafetyButton);
    private final Trigger Cannon4 = new Trigger(new JoystickButton(ControlJoystick,6)).and(SafetyButton);

    public RobotContainer() {
        ConfigTab=Shuffleboard.getTab("Config");
        FireTimeSelector = ConfigTab
        .add("Fire Time",.2)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", .3))
        .getEntry();
        RobotCannon = new Cannon(FireTimeSelector);
        configureBindings();
        RobotDriveTrainSubsystem.setDefaultCommand(new DriveTeleOp(RobotDriveTrainSubsystem));
    }

    private void configureBindings() {
        Cannon1.onTrue(new CannonControl(RobotCannon,0));
        Cannon2.onTrue(new CannonControl(RobotCannon,1));
        Cannon3.onTrue(new CannonControl(RobotCannon,2));
        Cannon4.onTrue(new CannonControl(RobotCannon,3));
    }

    public Command getAutonomousCommand() {
        return null;
    }
    public void dummy(){
    }
}
