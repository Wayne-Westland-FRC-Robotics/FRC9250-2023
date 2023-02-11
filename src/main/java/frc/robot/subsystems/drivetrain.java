// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// packages
package frc.robot.subsystems;

// imports
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class drivetrain extends SubsystemBase {
  
  // variables
  private final CANSparkMax leftCan = new CANSparkMax(DrivetrainConstants.LEFT_CAN_MOTOR_ID, MotorType.kBrushed);
  private final CANSparkMax rightCan = new CANSparkMax(DrivetrainConstants.RIGHT_CAN_MOTOR_ID, MotorType.kBrushed);
  private final WPI_VictorSPX leftSpx = new WPI_VictorSPX(DrivetrainConstants.LEFT_SPX_MOTOR_ID);
  private final WPI_VictorSPX rightSpx = new WPI_VictorSPX(DrivetrainConstants.RIGHT_SPX_MOTOR_ID);

  private final MotorControllerGroup leftMotors = new MotorControllerGroup(leftCan, leftSpx);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(rightCan, rightSpx);
  /** Creates a new drivetrain. */
  public drivetrain() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
