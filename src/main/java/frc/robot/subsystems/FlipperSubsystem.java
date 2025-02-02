// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.Constants.RobotConstants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;


public class FlipperSubsystem extends SubsystemBase {
  Solenoid gripper = new Solenoid(PneumaticsModuleType.REVPH, 6); //The pneumatics hub channels that we are using are 0, 2, and 5
  Solenoid flipper= new Solenoid(PneumaticsModuleType.REVPH, 9);
  DigitalInput coralDetector= new DigitalInput(0);

  public boolean isInScoringPosition=false;
  public boolean isInReceivingPosition= false;
  public boolean isGripped = false;
  public boolean hasCoral = coralDetector.get();
  
  /** Creates a new ExampleSubsystem. */
  public FlipperSubsystem() {}

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return the opposite of the value of said boolean state.
   */
  public void grip() {
    gripper.setPulseDuration(1.0);
    hasCoral = coralDetector.get();
    gripper.set(hasCoral);
    isGripped = gripper.get();
   
  }
  public void letGo(){
    gripper.setPulseDuration(1.0);
    gripper.set(false);
  }
  ///public void clawSpinner(){
   // double motorTurnAmount= angleToEncoderTicks(90);
   /// rotator.set(ControlMode.position, motorTurnAmount);
  //}
  public void flipperReadyToScore() {
    flipper.setPulseDuration(1.0);
    flipper.set(true);
    isInScoringPosition = true;
  }
  public void flipperReadyToReceive(){
    flipper.setPulseDuration(1.0);
    flipper.set(false);
    isInReceivingPosition = true;
  }
  ///public void clawUnspinner(){
    ///rotator.set(ControlMode.Position, -motorTurnAmount);
  ///}

  public void score() {
    try {
      Thread.sleep(RobotConstants.kScoreTimeoutSeconds);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    hasCoral = false;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Has Coral?", hasCoral);
    SmartDashboard.putBoolean("Is Gripped?", isGripped);
    SmartDashboard.putBoolean("In Scoring Position?", isInScoringPosition);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
