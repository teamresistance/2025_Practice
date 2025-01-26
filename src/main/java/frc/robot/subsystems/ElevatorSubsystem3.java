// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem3 extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem3() {}

  private Solenoid elevSolenoid1 = new Solenoid(PneumaticsModuleType.CTREPCM, 0); //Update these later to actual solenoids
  private Solenoid elevSolenoid2 = new Solenoid(PneumaticsModuleType.CTREPCM, 1);

  /** Command to change elevator stage. Level 0 is coral station receiving. */
  public void PositionElevator(int level){
    if (level == 1 || level == 2 || level == 0){
      elevSolenoid1.set(false);
      elevSolenoid2.set(false);
    }
    else if (level== 3){
      elevSolenoid1.set(true);
      elevSolenoid2.set(false);
    }
    else if (level == 4){
      elevSolenoid1.set(true);
      elevSolenoid2.set(true);
    }
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Elevator Solenoid 1", elevSolenoid1.get());
    SmartDashboard.putBoolean("Elevator Solenoid 2", elevSolenoid2.get());
    // This method will be called once per scheduler run
  }
}
