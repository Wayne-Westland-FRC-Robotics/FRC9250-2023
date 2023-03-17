// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// packages
package frc.robot.commands;

// imports
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;

public class driveCommand extends CommandBase {
 
  // variables
  private final DoubleSupplier leftSpeed;
  private final DoubleSupplier rightSpeed;
  private final drivetrain m_drivetrain;
 
  /** Creates a new driveCommand. */
  public driveCommand(DoubleSupplier lSpeed, DoubleSupplier rSpeed, drivetrain Drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    leftSpeed = lSpeed;
    rightSpeed = rSpeed;
    m_drivetrain = Drivetrain;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.tankDrive(leftSpeed.getAsDouble(), rightSpeed.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.tankDrive(0d, 0d);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
