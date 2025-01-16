// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.reef;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {}

  private boolean valve1 = false; //Update these later to actual solenoids
  private boolean valve2 = false;
  private boolean valve3 = false; //This is the rotate solenoid

  /** Command to change elevator stage. Level 0 is coral station receiving. */
  public void ElevatorPosition(int level){
    if (level == 1 || level == 2 || level == 0){
      valve1 = false;
      valve2 = false;
    }
    else if (level== 3){
      valve1 = true;
      valve2 = false;
    }
    else if (level == 4){
      valve1 = true;
      valve2 = true;
    }
  }
  /** Command to toggle the elevator's rotating coral holder */
  public void ElevatorToggle(){
    valve3 = !valve3; //When solenoids are added change this to a .toggle()
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Elevator Solenoid 1", valve1);
    SmartDashboard.putBoolean("Elevator Solenoid 2", valve2);
    SmartDashboard.putBoolean("Elevator Solenoid 3", valve3);
    // This method will be called once per scheduler run
  }
}
