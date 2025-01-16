package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// Commands
import frc.robot.commands.ElevatorRaiseFirstStageCommand;
import frc.robot.commands.ElevatorLowerFirstStageCommand;
import frc.robot.commands.ArmGetIntoScoringPositionAndScoreCommand;
import frc.robot.commands.ArmRetractCommand;

// Subsystems
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ArmSubsystem;

// Create a sequential command group for Level 3
public class Level3CommandGroup extends SequentialCommandGroup {
    public Level3CommandGroup(ElevatorSubsystem elevatorSubsystem, ArmSubsystem armSubsystem) {
        addCommands(
            new ElevatorRaiseFirstStageCommand(elevatorSubsystem),
            new ArmGetIntoScoringPositionAndScoreCommand(armSubsystem),
            new ArmRetractCommand(armSubsystem),
            new ElevatorLowerFirstStageCommand(elevatorSubsystem)
        );
    }
}