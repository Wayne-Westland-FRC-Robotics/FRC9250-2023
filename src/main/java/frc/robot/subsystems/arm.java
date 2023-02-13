// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class arm extends SubsystemBase {
 
  // variables
  private final CANSparkMax motor = new CANSparkMax(ArmConstants.ARM_CAN_MOTOR_ID, MotorType.kBrushless);
  
  /** Creates a new arm. */
  public arm() {
    motor.setInverted(true);
    motor.setIdleMode(IdleMode.kBrake);
    motor.setSmartCurrentLimit(ArmConstants.ARM_CURRENT_LIMIT_A);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setArmMotor(double percent) {
    motor.set(percent);
  }

  public void extend() {
    motor.set(ArmConstants.ARM_OUTPUT_POWER);
  }

  public void retract() {
    motor.set(-ArmConstants.ARM_OUTPUT_POWER);
  }

  public void idle() {
    motor.set(0);
  }
}
