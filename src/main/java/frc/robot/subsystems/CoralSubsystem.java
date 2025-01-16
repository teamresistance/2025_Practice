// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralSubsystem extends SubsystemBase {
  /** Creates a new CoralSubsystem. */
  public CoralSubsystem() {}

  private Joystick js = new Joystick(0);

  public void initSelectLocation(int loc){
    if (js.getRawAxis(3) == 1){
      selectLocation(loc+6);
    }
    else if (js.getRawAxis(3) == -1){
      selectLocation(loc);
    }
  }

  public void selectLocation(int loc){
    System.out.println("Imagine the robot moved to location " + loc);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
