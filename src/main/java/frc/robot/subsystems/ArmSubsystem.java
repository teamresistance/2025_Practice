// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.Constants.RobotConstants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.sensors.PigeonIMU;



public class ArmSubsystem extends SubsystemBase {
  public boolean hasCoral = false;
  public boolean inScoringPosition = false;
  public boolean rotated=false;
  Solenoid wristRotator = new Solenoid(PneumaticsModuleType.REVPH, 2); //The pneumatics hub channels that we are using are 0, 2, and 5
  Solenoid armLifter = new Solenoid(PneumaticsModuleType.REVPH, 0);
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
  public void armExtension(){
    armLifter.setPulseDuration(1.0);
    armLifter.set(true);
    inScoringPosition = true;
  }
  public void armRetraction(){
    armLifter.setPulseDuration(1.0);
    armLifter.set(false);
    inScoringPosition = false;
  }
  public void armGetToScoringPosition() {
    armExtension();
    rotateArmWrist();
  }
  public void armAwayFromScoringPosition() {
    armRetraction();
  }
  public boolean inScoringPosition(){
    return inScoringPosition;
  }

  public void rotateArmWrist() {
    wristRotator.setPulseDuration(1.0);
    wristRotator.set(true);
    rotated = true;

  }
  public void UnrotateArmWrist() {
    wristRotator.set(false);
    rotated = false;
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
    UnrotateArmWrist();
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
