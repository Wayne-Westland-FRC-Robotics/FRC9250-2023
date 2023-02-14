// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.autoBluePath;
import frc.robot.commands.autoGreenPath;
import frc.robot.commands.driveCommand;
import frc.robot.commands.extendArmCommand;
import frc.robot.commands.pullIntakeCommand;
import frc.robot.commands.pushIntakeCommand;
import frc.robot.commands.retractArmCommand;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.intake;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final drivetrain m_drivetrain = new drivetrain();
  private final arm m_arm = new arm();
  private final intake m_intake = new intake();
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  private final Command m_greenPath = new autoGreenPath(m_drivetrain, m_arm, m_intake);
  private final Command m_bluePath = new autoBluePath(m_drivetrain, m_arm, m_intake);

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_drivetrain.setDefaultCommand(
      new driveCommand(m_driverController::getLeftY, m_driverController::getRightY, m_drivetrain)
    );

    m_chooser.setDefaultOption("Green Path", m_greenPath);

    m_chooser.addOption("Blue Path", m_bluePath);

    Shuffleboard.getTab("Autonomous").add(m_chooser);
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    m_operatorController.povUp().whileTrue(new extendArmCommand(m_arm));
    m_operatorController.povDown().whileTrue(new retractArmCommand(m_arm));
    m_operatorController.leftBumper().whileTrue(new pushIntakeCommand(m_intake));
    m_operatorController.rightBumper().whileTrue(new pullIntakeCommand(m_intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_chooser.getSelected();
  }
}
