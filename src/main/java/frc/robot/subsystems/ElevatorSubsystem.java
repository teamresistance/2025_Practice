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
  * If it is on, L2 scoring is enabled.
  */
  public Solenoid firstStageSolenoid = new Solenoid(
      HardwareConstants.pneumaticsModuleType,
      HardwareConstants.kSolenoid_firstStage_portNumber);

  public Solenoid secondStageSolenoid = new Solenoid(
      HardwareConstants.pneumaticsModuleType,
      HardwareConstants.kSolenoid_secondStage_portNumber);

  public boolean firstStageSolenoidUp = false;
  public boolean secondStageSolenoidUp = false;

  /** Creates a new ExampleSubsystem. */
  public ElevatorSubsystem() {
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a
   * digital sensor).
   *
   * @return the opposite of the value of said boolean state.
   */
  public void raiseFirstStage() {
    firstStageSolenoid.setPulseDuration(
        HardwareConstants.kFirstStagePulseDurationSeconds);
    firstStageSolenoid.set(true);

    firstStageSolenoidUp = true;
  }

  public void raiseSecondStage() {
    secondStageSolenoid.setPulseDuration(
        HardwareConstants.kSecondStagePulseDurationSeconds);
    firstStageSolenoid.set(true);

    firstStageSolenoidUp = true;
  }

  public void lowerFirstStage() {
    firstStageSolenoid.set(false);

    firstStageSolenoidUp = false;
  }

  public void lowerSecondStage() {
    secondStageSolenoid.set(false);

    firstStageSolenoidUp = false;
  }

  public boolean getFirstStageSolenoidUp() {
    return firstStageSolenoidUp;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("First Stage Up?", firstStageSolenoidUp);
    SmartDashboard.putBoolean("Second Stage Up?", secondStageSolenoidUp);

    Logger.recordOutput("Elevator/First Stage Up", firstStageSolenoidUp);
    Logger.recordOutput("Elevator/Second Stage Up", secondStageSolenoidUp);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
