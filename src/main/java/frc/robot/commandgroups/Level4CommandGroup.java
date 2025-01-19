package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// Commands
import frc.robot.commands.ElevatorRaiseFirstStageCommand;
import frc.robot.commands.ElevatorLowerFirstStageCommand;
import frc.robot.commands.ArmGetIntoScoringPositionAndScoreCommand;
import frc.robot.commands.ArmRetractCommand;
import frc.robot.commands.ElevatorRaiseSecondStageCommand;
import frc.robot.commands.ElevatorLowerSecondStageCommand;

// Subsystems
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ArmSubsystem;

// Create a sequential command group for Level 4
public class Level4CommandGroup extends SequentialCommandGroup {
    public Level4CommandGroup(ElevatorSubsystem elevatorSubsystem, ArmSubsystem armSubsystem) {
        addCommands(
            new ElevatorRaiseFirstStageCommand(elevatorSubsystem),
            new ElevatorRaiseSecondStageCommand(elevatorSubsystem),
            new ArmGetIntoScoringPositionAndScoreCommand(armSubsystem),
            new ArmRetractCommand(armSubsystem),
            new ElevatorLowerSecondStageCommand(elevatorSubsystem),
            new ElevatorLowerFirstStageCommand(elevatorSubsystem)
        );
    }
}