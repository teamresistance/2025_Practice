// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elvatorCmds;

import frc.robot.subsystems.ElevatorSubsys;

<<<<<<<< HEAD:src/main/java/frc/robot/commands/ElevatorRaiseFirstStageCommand.java
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ElevatorRaiseFirstStageCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ElevatorSubsystem m_subsystem;
========
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ElevMidCmd extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final ElevatorSubsys m_elevSubsys;
  private final int level;
>>>>>>>> Practice_JCH:src/main/java/frc/robot/commands/elvatorCmds/ElevMidCmd.java

  /**
   * Creates a new ExampleCommand.
   *
   * @param m_elevsubsys The subsystem used by this command.
   */
<<<<<<<< HEAD:src/main/java/frc/robot/commands/ElevatorRaiseFirstStageCommand.java
  public ElevatorRaiseFirstStageCommand(ElevatorSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem); 
========
  public ElevMidCmd(ElevatorSubsys m_elevSubsys, int level) {
    this.m_elevSubsys = m_elevSubsys;
    this.level = level;
    addRequirements(m_elevSubsys);
>>>>>>>> Practice_JCH:src/main/java/frc/robot/commands/elvatorCmds/ElevMidCmd.java
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Raising first stage...");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
<<<<<<<< HEAD:src/main/java/frc/robot/commands/ElevatorRaiseFirstStageCommand.java
    if (!m_subsystem.firstStageSolenoidUp) {
      m_subsystem.raiseFirstStage();
    }
========
    m_elevSubsys.setElevator(level);
>>>>>>>> Practice_JCH:src/main/java/frc/robot/commands/elvatorCmds/ElevMidCmd.java
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
<<<<<<<< HEAD:src/main/java/frc/robot/commands/ElevatorRaiseFirstStageCommand.java
    System.out.println("Raised first stage!");
========
>>>>>>>> Practice_JCH:src/main/java/frc/robot/commands/elvatorCmds/ElevMidCmd.java
    return true;
  }
}
