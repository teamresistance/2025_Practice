// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elvatorCmds;

import frc.robot.subsystems.ArmSubsys;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * This takes in a level from the JS button call
 * then pass it to elevator subsystem.
 * <p>The level is only set.  Another command needs 
 * to be called to execute the level to solenoids.
 */
public class ArmToggleCmd extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final ArmSubsys m_armSubsys;

  /**
   * Create command to set level in arm subsystem
   *
   * @param m_elevsubsys The subsystem used by this command.
   * @param level to set in subsystem.  0-Low, 3-Mid, 4-high
   */
  public ArmToggleCmd(ArmSubsys m_armSubsys) {
    this.m_armSubsys = m_armSubsys;
    addRequirements(m_armSubsys);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_armSubsys.toggleArm();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
