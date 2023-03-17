// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  
  /**
   * constants for the robot container, mainly for controllers
   */
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
    public static final int kDriverJoystickPortR = 2;
    public static final int kDriverJoystickPortL = 3;
  }

  /**
   * constants for drivetrain properties, mainly motor IDs
   */
  public static class DrivetrainConstants {
    public static final int LEFT_CAN_MOTOR_ID = 2;
    public static final int RIGHT_CAN_MOTOR_ID = 4;
    public static final int LEFT_SPX_MOTOR_ID = 3;
    public static final int RIGHT_SPX_MOTOR_ID = 5;
    public static final double SPEED_REDUCTION = 2;
  }

  /**
   * constants for arm properties, mainly used by the arm and intake subsystems
   */
  public static class ArmConstants {
    public static final int ARM_CAN_MOTOR_ID = 5;
    public static final int INTAKE_CAN_MOTOR_ID = 6;
    public static final int ARM_CURRENT_LIMIT_A = 20;
    public static final double ARM_OUTPUT_POWER = 0.4;
    public static final int INTAKE_CURRENT_LIMIT_A = 25;
    public static final int INTAKE_HOLD_CURRENT_LIMIT_A = 5;
    public static final double INTAKE_OUTPUT_POWER = 1.0;
    public static final double INTAKE_HOLD_POWER = 0.07;
  }

  /**
   * constants for control systems
   */
  public static class ControlSystemConstants {
    public final static double BALANCE_TILT_LIMIT = 10;
    public final static double BALANCE_SPEED = 0.07;
    public final static double TILT_BOUND = 5;
  }
}
