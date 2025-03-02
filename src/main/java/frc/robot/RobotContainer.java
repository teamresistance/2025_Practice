// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//  ooooooooo.              .o8                     .     .oooooo.                             .              o8o                                 
//  `888   `Y88.           "888                   .o8    d8P'  `Y8b                          .o8              `"'                                 
//   888   .d88'  .ooooo.   888oooo.   .ooooo.  .o888oo 888           .ooooo.  ooo. .oo.   .o888oo  .oooo.   oooo  ooo. .oo.    .ooooo.  oooo d8b 
//   888ooo88P'  d88' `88b  d88' `88b d88' `88b   888   888          d88' `88b `888P"Y88b    888   `P  )88b  `888  `888P"Y88b  d88' `88b `888""8P 
//   888`88b.    888   888  888   888 888   888   888   888          888   888  888   888    888    .oP"888   888   888   888  888ooo888  888     
//   888  `88b.  888   888  888   888 888   888   888 . `88b    ooo  888   888  888   888    888 . d8(  888   888   888   888  888    .o  888     
//  o888o  o888o `Y8bod8P'  `Y8bod8P' `Y8bod8P'   "888"  `Y8bood8P'  `Y8bod8P' o888o o888o   "888" `Y888""8o o888o o888o o888o `Y8bod8P' d888b    

package frc.robot;

// Constants
import frc.robot.Constants.OperatorConstants.JoystickType;
import frc.robot.Constants.HardwareConstants.LedMode;
import frc.robot.Constants.OperatorConstants;

// Operator Input
import frc.robot.OperatorInput;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;

//  .oooooo.                                                                             .o8           
// d8P'  `Y8b                                                                           "888           
//888           .ooooo.  ooo. .oo.  .oo.   ooo. .oo.  .oo.    .oooo.   ooo. .oo.    .oooo888   .oooo.o 
//888          d88' `88b `888P"Y88bP"Y88b  `888P"Y88bP"Y88b  `P  )88b  `888P"Y88b  d88' `888  d88(  "8 
//888          888   888  888   888   888   888   888   888   .oP"888   888   888  888   888  `"Y88b.  
//`88b    ooo  888   888  888   888   888   888   888   888  d8(  888   888   888  888   888  o.  )88b 
// `Y8bood8P'  `Y8bod8P' o888o o888o o888o o888o o888o o888o `Y888""8o o888o o888o `Y8bod88P" 8""888P' 

// Command Groups
import frc.robot.commandgroups.Level2CommandGroup;
import frc.robot.commandgroups.Level3CommandGroup;
import frc.robot.commandgroups.Level4CommandGroup;

// Elevator Commands
// NONE YET!

// Arm Commands
// NONE YET!

// Interface Commands
import frc.robot.commands.InterfaceToggleLeftRightCommand;
import frc.robot.commands.InterfaceBranchIDCommand;
import frc.robot.commands.InterfaceBranchLevelCommand;

// Climber Command(s)
import frc.robot.commands.ActivateClimberCommand;

//   .oooooo..o              .o8                                         .                                        
//  d8P'    `Y8             "888                                       .o8                                        
//  Y88bo.      oooo  oooo   888oooo.   .oooo.o oooo    ooo  .oooo.o .o888oo  .ooooo.  ooo. .oo.  .oo.    .oooo.o 
//   `"Y8888o.  `888  `888   d88' `88b d88(  "8  `88.  .8'  d88(  "8   888   d88' `88b `888P"Y88bP"Y88b  d88(  "8 
//       `"Y88b  888   888   888   888 `"Y88b.    `88..8'   `"Y88b.    888   888ooo888  888   888   888  `"Y88b.  
//  oo     .d8P  888   888   888   888 o.  )88b    `888'    o.  )88b   888 . 888    .o  888   888   888  o.  )88b 
//  8""88888P'   `V88V"V8P'  `Y8bod8P' 8""888P'     .8'     8""888P'   "888" `Y8bod8P' o888o o888o o888o 8""888P' 
//                                              .o..P'                                                            
//                                              `Y8P'                                                             

import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FlipperSubsystem;
import frc.robot.subsystems.InterfaceSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.PhotonSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.drive.Swerve;
import frc.robot.subsystems.LedSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

// Drive
import frc.robot.drive.Swerve;

//    .oooooo.   ooooo 
//   d8P'  `Y8b  `888' 
//  888      888  888  
//  888      888  888  
//  888      888  888  
//  `88b    d88'  888  
//   `Y8bood8P'  o888o 
//                    

// Necessary stuff
// import edu.wpi.first.wpilibj2.command.Command;  <------------- Uncomment this if you are using a command without a group
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
        // The robot's subsystems and commands are defined here...
        private final OperatorInput m_operatorInput = new OperatorInput(JoystickType.k3Joysticks);

        private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
        private final FlipperSubsystem m_flipperSubsystem = new FlipperSubsystem();
        private final InterfaceSubsystem m_interfaceSubsystem = new InterfaceSubsystem();
        private final LimelightSubsystem m_limelightSubsystem = new LimelightSubsystem();
        private final PhotonSubsystem m_photonSubsystem = new PhotonSubsystem();
        private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
        private final LedSubsystem m_ledSubsystem = new LedSubsystem();

        // private final Swerve drive = new Swerve();

        private final Level2CommandGroup m_level2CommandGroup = new Level2CommandGroup(m_flipperSubsystem);
        private final Level3CommandGroup m_level3CommandGroup = new Level3CommandGroup(m_elevatorSubsystem,
                        m_flipperSubsystem);
        private final Level4CommandGroup m_level4CommandGroup = new Level4CommandGroup(m_elevatorSubsystem,
                        m_flipperSubsystem);
        private final ActivateClimberCommand m_ActivateClimberCommand = new ActivateClimberCommand(m_climberSubsystem);

        // Button box
        CommandGenericHID button_box = new CommandGenericHID(OperatorConstants.kButtonBox_portNumber);

        /**
         * The container for the robot. Contains subsystems, OI devices, and commands.
         */
        public RobotContainer() {

                // Configure the trigger bindings
                configureBindings();
        }

        // Logitech Extreme 3D Pro

        // Test: Keyboard

        /**
         * Use this method to define your trigger->command mappings. Triggers can be
         * created via the
         * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
         * an arbitrary
         * predicate, or via the named factories in {@link
         * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
         * {@link
         * CommandXboxController
         * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
         * PS4} controllers or
         * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
         * joysticks}.
         */
        private void configureBindings() {
                Trigger[] buttonBoxTriggers = new Trigger[16];
                for (int i = 0; i < 12; i++) {
                        Trigger trigger = new Trigger(button_box.button(i)).onTrue(
                                new InterfaceBranchIDCommand(m_interfaceSubsystem, (char) (i + 65))
                        );

                        buttonBoxTriggers[i] = trigger;
                }
                for (int i = 11; i < 16; i++) {
                        Trigger trigger = new Trigger(button_box.button(i)).onTrue(
                                new InterfaceBranchLevelCommand(m_interfaceSubsystem, (i - 10))
                        );

                        buttonBoxTriggers[i] = trigger;
                }

                m_operatorInput.climbButton.onTrue(m_ActivateClimberCommand);

                // ------------------Non-button Triggers for Subsystem Interaction

                // If interface subsystem finalizes a branch combination, limelight subsystem
                // will take this combination and begin seeking alignment.
                new Trigger(m_interfaceSubsystem::branchCombinationExists)
                                .onTrue(new InstantCommand(
                                                () -> {
                                                        m_limelightSubsystem.reefBranchCombinations = m_interfaceSubsystem
                                                                        .getBranchCombination();
                                                        m_limelightSubsystem.setSeekingAlignment(true);
                                                }));

                // If flipper subsystem detects coral, LED subsystem will strobe.
                // If flipper subsystem is ready to score, LED subsystem will glow solid.
                Trigger ledComplexTrigger = new Trigger(() -> m_flipperSubsystem.getHasCoral()
                                && (!m_elevatorSubsystem.getFirstStageSolenoidUp()));

                ledComplexTrigger.onTrue(new InstantCommand(
                                () -> {
                                        m_ledSubsystem.setMode(LedMode.kSTROBE);
                                }));

                ledComplexTrigger.onFalse(new InstantCommand(
                                () -> {
                                        m_ledSubsystem.setMode(LedMode.kSOLID);
                                }));
                // If PhotonVision has a current pose, set drive's currentPose to that pose.
                /*
                new Trigger(() -> m_photonSubsystem.avgPose.isPresent()).onTrue(new InstantCommand(
                                () -> {
                                        drive.setCurrentPose(m_photonSubsystem.avgPose.get());
                                }));
                */
                // If drive is in the ideal pose, set isSeekingAlignment to false.
                new Trigger(() -> m_limelightSubsystem.isWithinErrorThreshold()).onTrue(new InstantCommand(
                                () -> {
                                        m_limelightSubsystem.setSeekingAlignment(false);
                                }));

        }
        /**
         * Use this to pass the boolean changer command to the main {@link Robot} class.
         *
         * @return the command to run in teleopPeriodic
         */
        // public Command getChangeBooleanCommand() {
        // An example command will be run in autonomous
        // return new ChangeBooleanCommand(m_changeBooleanSubsystem);
        // }
}