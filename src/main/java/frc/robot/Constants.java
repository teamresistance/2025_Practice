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
    public static final int kDriverControllerPort = 0;

    public static final int klvl2Button_3Joysticks_ID = 0;
    public static final int klvl3Button_3Joysticks_ID = 0;
    public static final int klvl4Button_3Joysticks_ID = 0;
    public static final int kSelectBranchAndAddButton_3Joysticks_ID = 0;

    public static final int klvl2Button_Neo_ID = 0;
    public static final int klvl3Button_Neo_ID = 0;
    public static final int klvl4Button_Neo_ID = 0;
    public static final int kSelectBranchAndAddButton_Neo_ID = 0;

    public static final int klvl2Button_Kybd1_ID = 0;
    public static final int klvl3Button_Kybd1_ID = 0;
    public static final int klvl4Button_Kybd1_ID = 0;
    public static final int kSelectBranchAndAddButton_Kybd1_ID = 0;

  }

  public static class RobotConstants {
    public static final int kScoreTimeoutMilliseconds = 3000; //Milliseconds
  }

  public static class HardwareConstants {
    public static final PneumaticsModuleType pneumaticsModuleType = PneumaticsModuleType.REVPH;
    
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
  }
    public static class OperatorConstants {
        public static final int kDriverControllerPort = 0;
    }

    public static class RobotConstants {
        public static final int kScoreTimeoutSeconds = 3000; // Milliseconds
        public static final PneumaticsModuleType kModType = PneumaticsModuleType.CTREPCM;
        public static final int kModID = 2; // CAN adr, ID, of PDH
    }

    public static class HdwConstants {
        // ----------- Hardware -------------------
        public static final int kElevStg1SVChnl = 0;
        public static final int kElevStg2SVChnl = 1;
        public static final int kArmDnSVChnl = 2;
        public static final int kGripClsSVChnl = 3;
        public static final int kFlipOutSVChnl = 4;
        public static final int kFlipUpSVChnl = 5;
    }

    public static class JSConstants {
        // -------------- JS Buttons for normal 3 joysticks -----------------
        // for all level select
        public static final int kElevRcvr3JSBtn = 6;
        public static final int kElevLvl13JSBtn = 1;
        public static final int kElevLvl23JSBtn = 2;
        public static final int kElevLvl33JSBtn = 3;
        public static final int kElevLvl43JSBtn = 4;
        // for request set then execute
        public static final int kElevExec3JSBtn = 6;
        public static final int kElevLow3JSBtn = 3;
        public static final int kElevMid3JSBtn = 2;
        public static final int kElevHigh3JSBtn = 1;
        // for Arm
        public static final int kArmTgl3JSBtn = 11;
        // for gripper
        public static final int kGripTgl3JSBtn = 12;

        // ----------- JS Buttons for Neopad -------------
        // for all level select
        public static final int kElevRcvrNeoBtn = 6;
        public static final int kElevLvl1NeoBtn = 1;
        public static final int kElevLvl2NeoBtn = 2;
        public static final int kElevLvl3NeoBtn = 3;
        public static final int kElevLvl4NeoBtn = 4;
        // for request set then execute
        public static final int kElevExecNeoBtn = 4;
        public static final int kElevLowNeoBtn = 1;
        public static final int kElevMidNeoBtn = 2;
        public static final int kElevHighNeoBtn = 3;
        // for Arm
        public static final int kArmTglNeoBtn = 5;
        // for gripper
        public static final int kGripTglNeoBtn = 6;

        // ----------------- JS Buttons for keyboard --------------
        // for request set then execute
        public static final int kElevExecKybd0Btn = 4;
        public static final int kElevLowKybd0Btn = 1;
        public static final int kElevMidKybd0Btn = 2;
        public static final int kElevHighKybd0Btn = 3;
        // for Arm
        public static final int kArmTglKybd0Btn = 5;
        public static final int kArmTglKybd1Btn = 2;
        // for gripper
        public static final int kGripTglKybd0Btn = 6;
        public static final int kGripTglKybd1Btn = 2;
    }

    public static class GripConstants {
        // JS Buttons for normal 3 joysticks
        public static final int kGripTgl3JSBtn = 1;
        // JS Buttons for Neopad
        public static final int kGripTglNeoBtn = 5;
        // JS Buttons for keyboard
        public static final int kGripTglkybd1Btn = 1;
    }

    public static class ArmConstants {
        // JS Buttons for normal 3 joysticks
        public static final int kArmTgl3JSBtn = 1;
        // JS Buttons for Neopad
        public static final int kArmTglNeoBtn = 6;
        // JS Buttons for keyboard
        public static final int kArmTglkybd1Btn = 1;
    }
}
