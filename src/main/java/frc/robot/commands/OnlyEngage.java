// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class OnlyEngage extends SequentialCommandGroup {
  
  // variables
  private final drivetrain m_drivetrain;
  private final Robot m_robot;
  
  /** Creates a new OnlyEngage. */
  public OnlyEngage(drivetrain drive, Robot robot) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    m_drivetrain = drive;
    m_robot = robot;
    addRequirements(m_drivetrain);
    
    addCommands(
      new driveAuto(-0.5, -0.5, m_drivetrain).withTimeout(2),
      new BalanceNoUltra(m_drivetrain, m_robot).withTimeout(3)
    );
  }
}
