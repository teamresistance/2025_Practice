// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.FlipperSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** Command to move the flipper into scoring position and release the coral. */
public class FlipperScoreCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final FlipperSubsystem m_subsystem;

  /**
   * Creates a new FlipperScoringCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public FlipperScoreCommand(FlipperSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    System.out.println("Claw is getting ready to score!");
  }

  @Override
  public void execute() {
    m_subsystem.flipperReadyToScore();
    m_subsystem.letGo();
    m_subsystem.score();
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Scoring of coral on branch is complete!");
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}