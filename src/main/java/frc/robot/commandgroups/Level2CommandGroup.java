package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// Commands
import frc.robot.commands.ArmGetIntoScoringPositionAndScoreCommand;
import frc.robot.commands.ArmRetractCommand;

// Subsystems
import frc.robot.subsystems.ArmSubsystem;

// Create a sequential command group for Level 4
public class Level2CommandGroup extends SequentialCommandGroup {
    public Level2CommandGroup(ArmSubsystem armSubsystem) {
        addCommands(
            new ArmGetIntoScoringPositionAndScoreCommand(armSubsystem),
            new ArmRetractCommand(armSubsystem)
        );
    }
}