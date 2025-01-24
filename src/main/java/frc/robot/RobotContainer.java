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

import frc.robot.Constants.OperatorConstants;

import frc.robot.subsystems.ElevatorSubsys;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ArmSubsys;
import frc.robot.subsystems.ChangeBooleanSubsystem;
// import frc.robot.subsystems.FlipSubsys;
import frc.robot.subsystems.GripSubsys;
import frc.robot.commands.elvatorCmds.ElevExecCmd;
import frc.robot.commands.elvatorCmds.ElevSetRqCmd;
import frc.robot.commands.elvatorCmds.ArmToggleCmd;
import frc.robot.commands.elvatorCmds.GripToggleCmd;

import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ChangeBooleanCommand;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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
    private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
    private final ChangeBooleanSubsystem m_changeBooleanSubsystem = new ChangeBooleanSubsystem();
    private final ChangeBooleanCommand m_changeBooleanCommand = new ChangeBooleanCommand(m_changeBooleanSubsystem);

    private final ElevatorSubsys m_elevSubsys = new ElevatorSubsys();
    private final GripSubsys m_gripSubsys = new GripSubsys();
    private final ArmSubsys m_armSubsys = new ArmSubsys();
    // private final FlipSubsys m_flipSubsys = new FlipSubsys();

    private static final JS_OI jsbtn = new JS_OI(1); // 0=Normal 3 JS, 1=Neopad, 2=Keyboard

    // The robot's subsystems and commands are defined here...

    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController m_driverController = new CommandXboxController(
            OperatorConstants.kDriverControllerPort);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {

        configButtonBindings();

        // Configure the trigger bindings
        configureBindings();
    }

    public static Joystick driverJoystick = new Joystick(0);
    public static JoystickButton changeBooleanButton = new JoystickButton(driverJoystick, 7);

    public void configButtonBindings() {
        // Need to select the level then execute when ready(?).
        jsbtn.elevExecBtn.onTrue(new ElevExecCmd(m_elevSubsys));
        jsbtn.elevLowBtn.onTrue(new ElevSetRqCmd(m_elevSubsys, 0));
        jsbtn.elevMidBtn.onTrue(new ElevSetRqCmd(m_elevSubsys, 3));
        jsbtn.elevHighBtn.onTrue(new ElevSetRqCmd(m_elevSubsys, 4));
        // for arm toggle
        jsbtn.armToggleBtn.onTrue(new ArmToggleCmd(m_armSubsys));
        // for gripper
        jsbtn.gripToggleBtn.onTrue(new GripToggleCmd(m_gripSubsys));
    }

    public void update() {
        if (jsbtn.ChooserUpd())
            configButtonBindings();
    }

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
        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
        // new Trigger(m_exampleSubsystem::exampleCondition)
        // .onTrue(new ExampleCommand(m_exampleSubsystem));

        // Schedule `exampleMethodCommand` when the Xbox controller's B button is
        // pressed,
        // cancelling on release.
        // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

        // changeBooleanButton.onTrue(m_changeBooleanCommand);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        return Autos.exampleAuto(m_exampleSubsystem);
    }

    /**
     * Use this to pass the boolean changer command to the main {@link Robot} class.
     *
     * @return the command to run in teleopPeriodic
     */
    // public Command getChangeBooleanCommand() {
    // // An example command will be run in autonomous
    // return new ChangeBooleanCommand(m_changeBooleanSubsystem);
    // }
}
