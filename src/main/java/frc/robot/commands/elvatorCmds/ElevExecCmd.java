// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elvatorCmds;

import frc.robot.subsystems.ElevatorSubsys;

import edu.wpi.first.wpilibj2.command.Command;

/** Elevator command to execute the value set in Elevator subsystem. */
public class ElevExecCmd extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

    private final ElevatorSubsys m_elevSubsys;

    /**
     * Create command to execute the value set in Elevator subsystem.
     *
     * @param m_elevsubsys The subsystem used by this command.
     */
    public ElevExecCmd(ElevatorSubsys m_elevSubsys) {
        this.m_elevSubsys = m_elevSubsys;
        addRequirements(m_elevSubsys);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_elevSubsys.setElevator(m_elevSubsys.getElevRq());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
