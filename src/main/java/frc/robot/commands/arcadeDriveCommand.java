// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;

public class arcadeDriveCommand extends CommandBase {

  // variables
  private final DoubleSupplier forwardSpeed;
  private final DoubleSupplier rotation;
  private final drivetrain m_drivetrain;

  /** Creates a new arcadeDriveCommand. */
  public arcadeDriveCommand(DoubleSupplier FORWARD, DoubleSupplier ROTATE, drivetrain DRIVE) {
    // Use addRequirements() here to declare subsystem dependencies.
    forwardSpeed = FORWARD;
    rotation = ROTATE;
    m_drivetrain = DRIVE;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(forwardSpeed.getAsDouble(), rotation.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.arcadeDrive(0d, 0d);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
