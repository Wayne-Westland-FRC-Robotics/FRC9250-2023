// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// packages
package frc.robot;

// imports
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.OnlyEngage;
import frc.robot.commands.OnlyMobility;
import frc.robot.commands.ScoreWithEngage;
import frc.robot.commands.ScoreWithMobility;
import frc.robot.commands.arcadeDriveCommand;
import frc.robot.commands.driveCommand;
import frc.robot.commands.extendArmCommand;
import frc.robot.commands.halfSpeedDrive;
import frc.robot.commands.pullIntakeCommand;
import frc.robot.commands.pushIntakeCommand;
import frc.robot.commands.retractArmCommand;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.intake;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
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
  private final Robot m_robot = new Robot();
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);
  private final CommandJoystick m_driverJoystickR = 
      new CommandJoystick(OperatorConstants.kDriverJoystickPortR);
  private final CommandJoystick m_driverJoystickL =
      new CommandJoystick(OperatorConstants.kDriverJoystickPortL);




  // commands for the flightsticks and xbox controllers


  private final Command m_flightDrive = new driveCommand(m_driverJoystickL::getY, m_driverJoystickR::getY, m_drivetrain);
  private final Command m_xboxDrive = new driveCommand(m_driverController::getLeftY, m_driverController::getRightY, m_drivetrain);
  private final Command m_xboxArcadeDrive = new arcadeDriveCommand(m_driverController::getLeftY, m_driverController::getLeftX, m_drivetrain);
  private final Command m_joystickArcadeDrive = new arcadeDriveCommand(m_driverJoystickR::getY, m_driverJoystickR::getX, m_drivetrain);


  // commands for autonomous period

  private final Command m_mobility = new ScoreWithMobility(m_drivetrain, m_arm, m_intake);
  private final Command m_engage = new ScoreWithEngage(m_drivetrain, m_arm, m_intake, m_robot);
  private final Command m_noScoreM = new OnlyMobility(m_drivetrain);
  private final Command m_noScoreE = new OnlyEngage(m_drivetrain, m_robot);


  SendableChooser<Command> m_controlType = new SendableChooser<>();
  SendableChooser<Command> m_chooser1 = new SendableChooser<>();
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
   
    // control type options

    m_controlType.setDefaultOption("Xbox Controller Tank", m_xboxDrive);
    m_controlType.addOption("Flightstick Tank", m_flightDrive);
    m_controlType.addOption("Xbox Controller Arcade", m_xboxArcadeDrive);
    m_controlType.addOption("Flightstick Arcade", m_joystickArcadeDrive);

    // autonomous options

    m_chooser1.setDefaultOption("Score With Mobility Bonus", m_mobility);
    m_chooser1.addOption("Score with Engagement Bonus", m_engage);   
    m_chooser1.addOption("Mobility Without Scoring", m_noScoreM);
    m_chooser1.addOption("Engagement Without Scoring", m_noScoreE); 

    Shuffleboard.getTab("Control").add(m_controlType);
    Shuffleboard.getTab("Autonomous Path").add(m_chooser1);

   
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
    
    // everything for the operator xbox controller
    
    m_operatorController.povUp().whileTrue(new extendArmCommand(m_arm));
    m_operatorController.povDown().whileTrue(new retractArmCommand(m_arm));
    m_operatorController.leftBumper().whileTrue(new pushIntakeCommand(m_intake));
    m_operatorController.rightBumper().whileTrue(new pullIntakeCommand(m_intake));
    m_driverController.rightBumper().whileTrue(new halfSpeedDrive(m_driverController::getLeftY, m_driverController::getRightY, m_drivetrain));

    // everything for the flightstick operation

    m_driverJoystickL.button(3).whileTrue(new extendArmCommand(m_arm));
    m_driverJoystickL.button(4).whileTrue(new retractArmCommand(m_arm));
    m_driverJoystickR.button(3).whileTrue(new pushIntakeCommand(m_intake));
    m_driverJoystickR.button(4).whileTrue(new pullIntakeCommand(m_intake));
    m_driverJoystickL.button(5).whileTrue(new halfSpeedDrive(m_driverJoystickL::getY, m_driverJoystickR::getY, m_drivetrain));

  }

  /**
   * get the control type chosen by the sendable chooser
   * @return robot control type
   */
  public Command getControlTypeChooser() {
    return m_controlType.getSelected();
  }

  /**
   * get the drivetrain of the robot.
   * the drivetrain subsysten code is located in {@link drivetrain}
   * @return drivetrain
   */
  public drivetrain getDrivetrain() {
    return m_drivetrain;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_chooser1.getSelected();
    
  }
}