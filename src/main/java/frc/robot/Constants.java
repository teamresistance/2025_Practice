// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


//Hardware imports
import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static enum JoystickType {
      k3Joysticks,
      k2JoysticksAndReefSelector,
      kNeo,
      kKybd1
    }

    public static final int klvl2Button_3Joysticks_ID = 3;
    public static final int klvl3Button_3Joysticks_ID = 4;
    public static final int klvl4Button_3Joysticks_ID = 6;
    public static final int kSelectBranchAndAddButton_3Joysticks_ID = 1;
    public static final int kclimbButton_3Joysticks_ID = 2;

    public static final int kclimbButton_2JoysticksAndReefSelector_ID = 2;

    public static final int klvl2Button_Neo_ID = 1;
    public static final int klvl3Button_Neo_ID = 2;
    public static final int klvl4Button_Neo_ID = 3;
    public static final int kSelectBranchAndAddButton_Neo_ID = 4;
    public static final int kclimbButton_Neo_ID = 5;

    public static final int klvl2Button_Kybd1_ID = 0;
    public static final int klvl3Button_Kybd1_ID = 0;
    public static final int klvl4Button_Kybd1_ID = 0;
    public static final int kSelectBranchAndAddButton_Kybd1_ID = 0;

  }

  public static class RobotConstants {
    public static final int kScoreTimeoutMilliseconds = 3000; //Milliseconds
    public static final int kGripperDelayMilliseconds = 200; //Milliseconds

    public static final String limelightName = "limelight";
    public static final double kLimelightWindowResolutionWidthPixels = 960;
    public static final double kLimelightHorizontalFOVdegrees = 62.5;
  }

  public static class HardwareConstants {
    public static final PneumaticsModuleType pneumaticsModuleType = PneumaticsModuleType.CTREPCM;
    
    //Arm
    public static final int kSolenoid_wristRotator_portNumber = 2;
    public static final int kWristRotatorPulseDurationSeconds = 1;
    public static final int kSolenoid_armLifter_portNumber = 0;
    public static final int kArmLifterPulseDurationSeconds = 1;

    //Elevator
    public static final int kSolenoid_firstStage_portNumber = 1;
    public static final int kFirstStagePulseDurationSeconds = 1;
    public static final int kSolenoid_secondStage_portNumber = 5;
    public static final int kSecondStagePulseDurationSeconds = 1;

    //Climber
    public static final int kClimberSolenoid_portNumber = 4;
  }

  public static class FieldConstants {
    public static final double kReefBranchWidthInches = 1.660;
  }

}