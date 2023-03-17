// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class intake extends SubsystemBase {
  
  // variables
  private final CANSparkMax intake = new CANSparkMax(ArmConstants.INTAKE_CAN_MOTOR_ID, MotorType.kBrushless);
  
  /** Creates a new intake. */
  public intake() {
    intake.setInverted(false);
    intake.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * set the intake motor to a certain value
   * @param percent the percent of the motor speed
   * @param amps the limit of the motor in amps
   */
  public void setIntakeMotor(double percent, int amps) {
    intake.set(percent);
    intake.setSmartCurrentLimit(amps);
  }

  /**
   * pull an item into the intake
   */
  public void pullItem() {
    intake.set(ArmConstants.INTAKE_OUTPUT_POWER);
    intake.setSmartCurrentLimit(ArmConstants.INTAKE_CURRENT_LIMIT_A);
  }

  /**
   * push an item out of the intake
   */
  public void pushItem() {
    intake.set(-ArmConstants.INTAKE_OUTPUT_POWER);
    intake.setSmartCurrentLimit(ArmConstants.INTAKE_CURRENT_LIMIT_A);
  }

  /**
   * set the intake to idle
   */
  public void idle() {
    intake.set(0);
    intake.setSmartCurrentLimit(0);
  }
}
