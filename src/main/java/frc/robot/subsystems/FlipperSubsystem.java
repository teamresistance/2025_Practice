
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//  Command based imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Hardware Imports
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.HardwareConstants;
import frc.robot.Constants.RobotConstants;

// Logging and Testing Imports
import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FlipperSubsystem extends SubsystemBase {
  // Variables

  /**
  *  This solenoid controls the gripper mechanism on the robot.
  * it directly interacts with the coral, and locks it in place with pressure applied to it.
  */
  private final Solenoid gripper = new Solenoid(HardwareConstants.pneumaticsModuleType, HardwareConstants.kSolenoid_gripper_portNumber);

  /**
  * This solenoid controls the flipper. The flipper swivels out to prime the gripper to score or
  * receive coral.
  */
  private final Solenoid flipper = new Solenoid(HardwareConstants.pneumaticsModuleType, HardwareConstants.kSolenoid_flipper_portNumber);

  /**
  * This solenoid controls the centering mechanism. If a coral enters the gripper with some part of it outside the domain of the grippr,
  * the centerer will push the coral fully into the gripper.
  */
  private final Solenoid centerer = new Solenoid(HardwareConstants.pneumaticsModuleType, HardwareConstants.kSolenoid_centerer_portNumber);

  /**
  * This is a banner sensor which detects coral. when placed in the gripper, 
  * coral detected by the sensor will set its output to {@code true}.
  */
  private final DigitalInput coralDetector = new DigitalInput(HardwareConstants.kCoralDetector_portNumber);

  /**
  * This boolean variable is set to {@code true} when a coral is detected by the banner sensor
  * and {@code false} if not. This value is updated in {@code periodic()}.
  */
  public boolean hasCoral = false;

  /**
  * This boolean variable is set according to the state of the gripper solenoid.
  */
  private boolean isGripped = false;

  /**
  * This boolean variable is set according to the state of the flipper solenoid.
  */
  private boolean isInScoringPosition = false;

  /**
  * This boolean variable is set according to the state of the centerer solenoid.
  */
  private boolean centered = false;

  /** Creates a new FlipperSubsystem. */
  public FlipperSubsystem() {
  }

  /**
  * Sets the gripper solenoid to the on position and sets {@code isGripped} accordingly.
  */
  public void grip() {
    gripper.setPulseDuration(1.0);
    hasCoral = coralDetector.get();
    gripper.set(hasCoral);
    isGripped = gripper.get();
  }
  
  /**
  * Called in periodic. This method returns the output of the banner sensor;
  * true if a coral is detected, and false if not.
  */
  public boolean updateHasCoral() {
    hasCoral = coralDetector.get();
    return hasCoral;
  }

  /**
  * Sets the gripper solenoid to the off position and sets {@code isGripped} accordingly.
  */
  public void letGo() {
    gripper.setPulseDuration(1.0);
    gripper.set(false);
    isGripped = false;
  }
  
  /**
  * Sets the flipper solenoid to the on position and sets {@code isInScoringPosition} accordingly.
  */
  public void extend() {
    flipper.setPulseDuration(1.0);
    flipper.set(true);
    isInScoringPosition = true;
  }

  /**
  * Sets the flipper solenoid to the off position and sets {@code isInScoringPosition} accordingly.
  */
  public void retract() {
    flipper.setPulseDuration(1.0);
    flipper.set(false);
    isInScoringPosition = false;
  }

  /**
  * Waits for the delay as specified by {@code RobotConstants.kScoreTimeoutMilliseconds}
  * to allow a groped coral to drop.
  */
  public void score() {
    try {
      Thread.sleep(RobotConstants.kScoreTimeoutMilliseconds);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    hasCoral = false;
  }

  /**
  * Returns {@code FlipperSubsystem.hasCoral}.
  */
  public boolean getHasCoral() {
    return hasCoral;
  }

  @Override
  public void periodic() {
    // Scan for coral using DigitalInput.
    // If coral found and is not gripped nor centered, center it.
    if ((gripper.get() == false)
        && (coralDetector.get() == true)
        && (centered == false)) {
        centerer.set(true);
        centered = true;
    }

    // Scan for coral using DigitalInput.
    // If coral found and is centered, grip it after kGripperDelayMilliseconds milliseconds.
    // Then, release the centering solenoid.
    if ((gripper.get() == false)
        && (coralDetector.get() == true)
        && (centered == true)) {
        try {
          Thread.sleep(RobotConstants.kGripperDelayMilliseconds);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        gripper.set(true);
        centerer.set(false);
    }

    updateHasCoral();

    // Reporting
    SmartDashboard.putBoolean("Has Coral?", hasCoral);
    SmartDashboard.putBoolean("Is Gripped?", isGripped);
    SmartDashboard.putBoolean("In Scoring Position?", isInScoringPosition);

    // Logging
    Logger.recordOutput("Flipper/Has Coral", hasCoral);
    Logger.recordOutput("Flipper/Is Gripped", isGripped);
    Logger.recordOutput("Flipper/In Scoring Position", isInScoringPosition);
  }

  @Override
  public void simulationPeriodic() {
    // Scan for coral using DigitalInput.
    // If coral found, grip it after 200ms.
    if (gripper.get() == false) {
      if (coralDetector.get() == true) {
        try {
          Thread.sleep(RobotConstants.kGripperDelayMilliseconds);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        gripper.set(true);
      }
    }

    SmartDashboard.putBoolean("Has Coral?", hasCoral);
    SmartDashboard.putBoolean("Is Gripped?", isGripped);
    SmartDashboard.putBoolean("In Scoring Position?", isInScoringPosition);
  }
}
