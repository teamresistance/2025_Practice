// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
    }

    public static class RobotConstants {
        public static final PneumaticsModuleType kModType = PneumaticsModuleType.CTREPCM;
        public static final int kModID = 2; // CAN adr, ID, of PDH
    }

    public static class ElevConstants {
        //Hardware
        public static final int kStg1SVChnl = 0;
        public static final int kStg2SVChnl = 1;
        //JS Buttons
        public static final int kElevRcvrBtn = 6;
        public static final int kElevLvl1Btn = 1;
        public static final int kElevLvl2Btn = 2;
        public static final int kElevLvl3Btn = 3;
        public static final int kElevLvl4Btn = 4;

        public static final int kElevExecBtn = 5;
        public static final int kElevLowBtn = 1;
        public static final int kElevMidBtn = 2;
        public static final int kElevHighBtn = 3;
    }

}
