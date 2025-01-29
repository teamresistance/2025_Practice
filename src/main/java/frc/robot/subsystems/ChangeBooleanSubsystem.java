// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class ChangeBooleanSubsystem extends SubsystemBase {
  public boolean booleanToChange = true;

  /** Creates a new ExampleSubsystem. */
  public ChangeBooleanSubsystem() {}

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return the opposite of the value of said boolean state.
   */
  public void changeBoolean() {
    booleanToChange = !booleanToChange;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("My boolean", booleanToChange);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}