// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// packages
package frc.robot.subsystems;

// imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class drivetrain extends SubsystemBase {
  private final WPI_TalonSRX driveLeftSpark = new WPI_TalonSRX(DrivetrainConstants.LEFT_CAN_MOTOR_ID);
  private final WPI_TalonSRX driveRightSpark = new WPI_TalonSRX(DrivetrainConstants.RIGHT_CAN_MOTOR_ID);
  private final WPI_TalonSRX driveLeftVictor = new WPI_TalonSRX(DrivetrainConstants.LEFT_SPX_MOTOR_ID);
  private final WPI_TalonSRX driveRightVictor = new WPI_TalonSRX(DrivetrainConstants.RIGHT_SPX_MOTOR_ID);

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
