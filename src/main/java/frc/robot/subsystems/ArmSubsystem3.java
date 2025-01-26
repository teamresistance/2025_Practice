// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem3 extends SubsystemBase {
  private Solenoid arm = new Solenoid(PneumaticsModuleType.CTREPCM, 2);

  /** Creates a new ArmSubsystem. */
  public ArmSubsystem3() {}

    /** Command to toggle the elevator's rotating coral holder */
  public void ElevatorToggle(){
    arm.set(!arm.get()); //When solenoids are added change this to a .toggle()
  }
  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Arm Holder", arm.get());
    // This method will be called once per scheduler run
  }
}
