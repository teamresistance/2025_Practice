// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HdwConstants;
import frc.robot.Constants.RobotConstants;

public class ElevatorSubsys extends SubsystemBase {

    //Hardware
    private final Solenoid elevStg1SV = new Solenoid(RobotConstants.kModType, HdwConstants.kElevStg1SVChnl);
    private final Solenoid elevStg2SV = new Solenoid(RobotConstants.kModType, HdwConstants.kElevStg2SVChnl);

    //Variables
    private static int elevLvlRequest = 0;  //Elevator level to be raised to when executed

    /** Creates a new Subsystem. */
    public ElevatorSubsys() {
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Elev/Level Req", elevLvlRequest);
        // This method will be called once per scheduler run
    }

    public void setElevator(int level){
        switch(level){
            case 0: //Retrieve
            case 1: //Level 1
            case 2: //Level 2
            elevStg1SV.set(false);  //Low
            elevStg2SV.set(false);
            System.out.println("Exec level: " + level);
            break;
            case 3: //Level 2
            elevStg1SV.set(true);   //Mid
            elevStg2SV.set(false);
            System.out.println("Exec level: " + level);
            break;
            case 4: //Level 2
            elevStg1SV.set(true);   //High
            elevStg2SV.set(true);
            System.out.println("Exec level: " + level);
            break;
            default:
            elevStg1SV.set(true);
            elevStg2SV.set(true);
            System.out.println("Error: illegal elevator stage level: " + level);
        }
    }

    public void setElevRq(int level){ elevLvlRequest = level; }
    public int getElevRq(){ return elevLvlRequest; }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
