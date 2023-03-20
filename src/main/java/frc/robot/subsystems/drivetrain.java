// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// packages
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
// imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class drivetrain extends SubsystemBase {
  private final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(DrivetrainConstants.LEFT_CAN_MOTOR_ID);
  private final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(DrivetrainConstants.RIGHT_CAN_MOTOR_ID);
  private final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(DrivetrainConstants.LEFT_SPX_MOTOR_ID);
  private final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(DrivetrainConstants.RIGHT_SPX_MOTOR_ID);

  private final MotorControllerGroup leftMotors = new MotorControllerGroup(leftMotor1, leftMotor2);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(rightMotor1, rightMotor2);

  private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotors, rightMotors);

  public drivetrain() {
    startBrake();
  }

  /**
   * set the speed and rotation for arcade driving
   * @param forwardSpeed the speed at which the robot moves forward
   * @param rotation the speed of rotation
   */
  public void arcadeDrive(Double forwardSpeed, Double rotation) {
    diffDrive.arcadeDrive(forwardSpeed, rotation);
  }

  /**
   * set the speed of the motors for tank driving
   * @param leftSpeed the speed of the left motors
   * @param rightSpeed the speed of the right motors
   */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    diffDrive.tankDrive(-leftSpeed, rightSpeed);
  }

  /**
   * start the brakes for the drivetrain
   */
  public void startBrake() {
    leftMotor1.setNeutralMode(NeutralMode.Brake);
    leftMotor2.setNeutralMode(NeutralMode.Brake);
    rightMotor1.setNeutralMode(NeutralMode.Brake);
    rightMotor2.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * stop the brakes for the drivetrain
   */
  public void stopBrake() {
    leftMotor1.setNeutralMode(NeutralMode.Coast);
    leftMotor2.setNeutralMode(NeutralMode.Coast);
    rightMotor1.setNeutralMode(NeutralMode.Coast);
    rightMotor2.setNeutralMode(NeutralMode.Coast);
  }

}
