package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// Commands
import frc.robot.commands.FlipperScoringCommand;
import frc.robot.commands.FlipperBackToReceivingCommand;
import frc.robot.commands.ElevatorRaiseSecondStageCommand;
import frc.robot.commands.ElevatorLowerSecondStageCommand;

// Subsystems
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FlipperSubsystem;

// Create a sequential command group for Level 4
public class Level4CommandGroup extends SequentialCommandGroup {
    public Level4CommandGroup(ElevatorSubsystem elevatorSubsystem, FlipperSubsystem flipperSubsystem) {
        addCommands(
            new ElevatorRaiseSecondStageCommand(elevatorSubsystem),  
            new Design4ClawScoringCommand(FlipperSubsystem),
            new Design4ClawBackToReceivingCommand(FlipperSubsystem),
            new ElevatorLowerSecondStageCommand(elevatorSubsystem)
        );
    }
}