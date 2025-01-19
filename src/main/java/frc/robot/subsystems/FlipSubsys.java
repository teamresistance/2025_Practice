// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HdwConstants;
import frc.robot.Constants.RobotConstants;

public class FlipSubsys extends SubsystemBase {

    //Hardware
    private final Solenoid flipOutSV = new Solenoid(RobotConstants.kModType, HdwConstants.kFlipOutSVChnl);
    private final Solenoid flipUpSV = new Solenoid(RobotConstants.kModType, HdwConstants.kFlipUpSVChnl);

    //Variables


    /** Creates a new Subsystem. */
    public FlipSubsys() {
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Flip/Flipper is out:", flipOutSV.get());
        SmartDashboard.putBoolean("Flip/Flipper is up:", flipUpSV.get());
        // This method will be called once per scheduler run
    }

    public void flipperOut(boolean out){
        flipOutSV.set(out);
        System.out.println("Flipper rotate is: " + flipOutSV.get());
    }
    public void flipperUp(boolean up){
        flipUpSV.set(up);
        System.out.println("Flipper tilt is: " + flipUpSV.get());
    }

}
