// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ScoreWithMobility extends SequentialCommandGroup {
 
  // variables
  private final drivetrain m_drivetrain;
  private final arm m_arm;
  private final intake m_intake;
 
  /** Creates a new ScoreWithMobility. */
  public ScoreWithMobility(drivetrain drive, arm arm, intake intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    m_drivetrain = drive;
    m_arm = arm;
    m_intake = intake;
    addRequirements(m_drivetrain, m_arm, m_intake);

    addCommands(
      new ParallelCommandGroup(
        new driveAuto(0.5, 0.5, m_drivetrain),
        new extendArmCommand(m_arm)
      .withTimeout(2)),
      new pushIntakeCommand(m_intake).withTimeout(0.5),
      new driveAuto(-0.5, -0.5, m_drivetrain)
    );
  }
}
