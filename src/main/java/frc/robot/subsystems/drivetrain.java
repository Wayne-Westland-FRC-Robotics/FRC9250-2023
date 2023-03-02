// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// packages
package frc.robot.subsystems;

// imports
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class drivetrain extends SubsystemBase {
  private final CANSparkMax driveLeftSpark = new CANSparkMax(DrivetrainConstants.LEFT_CAN_MOTOR_ID, MotorType.kBrushless);
  private final CANSparkMax driveRightSpark = new CANSparkMax(DrivetrainConstants.RIGHT_CAN_MOTOR_ID, MotorType.kBrushless);
  private final WPI_VictorSPX driveLeftVictor = new WPI_VictorSPX(DrivetrainConstants.LEFT_SPX_MOTOR_ID);
  private final WPI_VictorSPX driveRightVictor = new WPI_VictorSPX(DrivetrainConstants.RIGHT_SPX_MOTOR_ID);

  private final MotorControllerGroup leftMotors = new MotorControllerGroup(driveLeftSpark, driveLeftVictor);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(driveRightSpark, driveRightVictor);

  private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotors, rightMotors);

  public void arcadeDrive(Double forwardSpeed, Double rotation) {
    diffDrive.arcadeDrive(forwardSpeed, rotation);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    diffDrive.tankDrive(leftSpeed, rightSpeed);
  }

}
