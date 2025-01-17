// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.Constants.RobotConstants;


public class ArmSubsystem extends SubsystemBase {
  public boolean hasCoral = false;
  public boolean inScoringPosition = false;

  /** Creates a new ExampleSubsystem. */
  public ArmSubsystem() {}

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return the opposite of the value of said boolean state.
   */
  public void getCoral() {
    hasCoral = true;
  }

  public void rotateArmAxis() {
  }

  public void rotateArmWrist() {
  }
  public void getInScoringPosition() {
    rotateArmAxis();
    rotateArmWrist();
    inScoringPosition = true;
  }

  public void score() {
    try {
      Thread.sleep(RobotConstants.kScoreTimeoutSeconds);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    hasCoral = false;
  }

  public void getBackToReceivingPosition() {
    inScoringPosition = false;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Has Coral?", hasCoral);
    SmartDashboard.putBoolean("In Scoring Position?", inScoringPosition);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
