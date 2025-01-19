// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HdwConstants;
import frc.robot.Constants.RobotConstants;

public class GripSubsys extends SubsystemBase {

    //Hardware
    private final Solenoid gripCloseSV = new Solenoid(RobotConstants.kModType, HdwConstants.kGripClsSVChnl);

    //Variables


    /** Creates a new Subsystem. */
    public GripSubsys() {
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Grip/Gripper is closed:", gripCloseSV.get());
        // This method will be called once per scheduler run
    }

    public void toggleGrip(){
        gripCloseSV.set(!gripCloseSV.get());
        System.out.println("Gripper is: " + gripCloseSV.get());
    }

}
