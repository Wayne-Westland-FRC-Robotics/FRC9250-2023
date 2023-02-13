// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// packages
package frc.robot.subsystems;

// imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class drivetrain extends SubsystemBase {
  private final CANSparkMax driveLeftSpark = new CANSparkMax(DrivetrainConstants.LEFT_CAN_MOTOR_ID, MotorType.kBrushless);
  private final CANSparkMax driveRightSpark = new CANSparkMax(DrivetrainConstants.RIGHT_CAN_MOTOR_ID, MotorType.kBrushless);
  private final VictorSPX driveLeftVictor = new VictorSPX(DrivetrainConstants.LEFT_SPX_MOTOR_ID);
  private final VictorSPX driveRightVictor = new VictorSPX(DrivetrainConstants.RIGHT_SPX_MOTOR_ID);

  public void drive(Double leftSpeed, Double rightSpeed) {
    driveLeftSpark.set(leftSpeed);
    driveLeftVictor.set(ControlMode.PercentOutput, leftSpeed);
    driveRightSpark.set(rightSpeed);
    driveRightVictor.set(ControlMode.PercentOutput, rightSpeed);
  }


}
