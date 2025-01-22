package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RollerSubsystem;

public class RollerOutputCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final RollerSubsystem m_subsystem;

    public RollerOutputCommand(RollerSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        // Initialization code here
        System.out.println("Output started");
    }

    @Override
    public void execute() {

      m_subsystem.startRollersReverse();

    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Output ended");
        m_subsystem.stopRollers();
    }

    @Override
    public boolean isFinished() {

        return true; // Change this condition as needed
    }
}