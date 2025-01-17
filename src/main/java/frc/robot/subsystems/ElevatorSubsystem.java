// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class ElevatorSubsystem extends SubsystemBase {
  public boolean firstStageSolenoidUp = false;
  public boolean secondStageSolenoidUp = false;

  /** Creates a new ExampleSubsystem. */
  public ElevatorSubsystem() {}

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return the opposite of the value of said boolean state.
   */
  public void raiseFirstStage() {
    firstStageSolenoidUp = true;
  }
  public void raiseSecondStage() {
    firstStageSolenoidUp = true;
  }
  public void lowerFirstStage() {
    firstStageSolenoidUp = false;
  }
  public void lowerSecondStage() {
    firstStageSolenoidUp = false;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("First Stage Up?", firstStageSolenoidUp);
    SmartDashboard.putBoolean("Second Stage Up?", secondStageSolenoidUp);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
