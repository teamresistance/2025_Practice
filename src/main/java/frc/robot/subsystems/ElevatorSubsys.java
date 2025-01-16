// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevConstants;
import frc.robot.Constants.RobotConstants;

public class ElevatorSubsys extends SubsystemBase {

    //Hardware
    private final Solenoid stg1SV = new Solenoid(RobotConstants.kModType, ElevConstants.kStg1SVChnl);
    private final Solenoid stg2SV = new Solenoid(RobotConstants.kModType, ElevConstants.kStg2SVChnl);
    private static int elevLvlRequest = 0;  //Elevator level to be raised to when executed

    /** Creates a new Subsystem. */
    public ElevatorSubsys() {
    }

    /**
     * Example command factory method.
     *
     * @return a command
     */
    public Command exampleMethodCommand() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
                () -> {
                    /* one-time action goes here */
                });
    }

    /**
     * An example method querying a boolean state of the subsystem (for example, a
     * digital sensor).
     *
     * @return value of some boolean subsystem state, such as a digital sensor.
     */
    public boolean exampleCondition() {
        // Query some boolean state, such as a digital sensor.
        return false;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Elev/Level Req", elevLvlRequest);
        // This method will be called once per scheduler run
    }

    public void setEvelRq(int level){
        
    }

    public void setElevator(int level){
        switch(level){
            case 0: //Retrieve
            case 1: //Level 1
            case 2: //Level 2
            stg1SV.set(false);  //Low
            stg2SV.set(false);
            System.out.println("Exec level: " + level);
            break;
            case 3: //Level 2
            stg1SV.set(true);   //Mid
            stg2SV.set(false);
            System.out.println("Exec level: " + level);
            break;
            case 4: //Level 2
            stg1SV.set(true);   //High
            stg2SV.set(true);
            System.out.println("Exec level: " + level);
            break;
            default:
            stg1SV.set(true);
            stg2SV.set(true);
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
