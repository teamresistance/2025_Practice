// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
// Command based imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Logging imports
import edu.wpi.first.wpilibj.smartdashboard.*;
import org.littletonrobotics.junction.Logger;

// Hardware Imports
import frc.robot.Constants.HardwareConstants;
import com.fasterxml.jackson.core.base.GeneratorBase;
import edu.wpi.first.wpilibj.Solenoid;

public class ElevatorSubsystem extends SubsystemBase {
  //Variables

  /*
  * This solenoid  controls the first (lower) stage of the elevator.
  * It must be set to true before {@code secondStageSolenoid} is.
  * If it is on, L3 scoring is enabled.
  */
  public Solenoid firstStageSolenoid = new Solenoid(
      HardwareConstants.pneumaticsModuleType,
      HardwareConstants.kSolenoid_firstStage_portNumber);
  
  /*
  * This solenoid  controls the first (lower) stage of the elevator.
  * It must be set to true ONLY IF {@code firstStageSolenoid} is.
  * If it is on, then the first stage solenoid must also be on. Therefore, L4 scoring is enabled.
  */
  public Solenoid secondStageSolenoid = new Solenoid(
      HardwareConstants.pneumaticsModuleType,
      HardwareConstants.kSolenoid_secondStage_portNumber);
  
  /*
  * This variable records the state of the first stage solenoid.
  * It should be changed when its corresponding solenoid's state is changed.
  */
  public boolean firstStageSolenoidUp = false;

  /*
  * This variable records the state of the second stage solenoid.
  * It should be changed when its corresponding solenoid's state is changed.
  */
  public boolean secondStageSolenoidUp = false;

  /** Creates a new ExampleSubsystem. */
  public ElevatorSubsystem() {
  }

  /**
   * This method sets the first stage's solenoid to the up position.
   * It also sets the corresponding boolean variable {@code firstStageSolenoidUp) accordingly.
   * @return Void
   */
  public void raiseFirstStage() {
    firstStageSolenoid.setPulseDuration(
        HardwareConstants.kFirstStagePulseDurationSeconds);
    firstStageSolenoid.set(true);

    firstStageSolenoidUp = true;
  }
  
  /**
   * This method sets the second stage's solenoid to the up position.
   * It also sets the corresponding boolean variable {@code secondStageSolenoidUp) accordingly.
   * @return Void
   */
  public void raiseSecondStage() {
    secondStageSolenoid.setPulseDuration(
        HardwareConstants.kSecondStagePulseDurationSeconds);
    firstStageSolenoid.set(true);

    firstStageSolenoidUp = true;
  }
  
  /**
   * This method sets the first stage's solenoid to the down position.
   * It also sets the corresponding boolean variable {@code firstStageSolenoidUp) accordingly.
   * @return Void
   */
  public void lowerFirstStage() {
    firstStageSolenoid.set(false);

    firstStageSolenoidUp = false;
  }
  
  /**
   * This method sets the  second stage's solenoid to the down position.
   * It also sets the corresponding boolean variable {@code secondStageSolenoidUp) accordingly.
   * @return Void
   */
  public void lowerSecondStage() {
    secondStageSolenoid.set(false);

    firstStageSolenoidUp = false;
  }

  /**
  * This method returns whether the first stage solenoid is up.
  * By consequence, this also returns whether any elevator solenoid is active,
  * because the second stage can only be up if the first is already up.
  */
  public boolean getFirstStageSolenoidUp() {
    return firstStageSolenoidUp;
  }

  @Override
  public void periodic() {
    // Reporting
    SmartDashboard.putBoolean("First Stage Up?", firstStageSolenoidUp);
    SmartDashboard.putBoolean("Second Stage Up?", secondStageSolenoidUp);
    
    // Logging
    Logger.recordOutput("Elevator/First Stage Up", firstStageSolenoidUp);
    Logger.recordOutput("Elevator/Second Stage Up", secondStageSolenoidUp);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
