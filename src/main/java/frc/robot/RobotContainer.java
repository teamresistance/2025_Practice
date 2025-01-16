// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ElevConstants;
import frc.robot.Constants.OperatorConstants;

import frc.robot.commands.Autos;
import frc.robot.commands.elvatorCmds.ElevExecCmd;
import frc.robot.commands.elvatorCmds.ElevHighCmd;
import frc.robot.commands.elvatorCmds.ElevLowCmd;
import frc.robot.commands.elvatorCmds.ElevLvl1Cmd;
import frc.robot.commands.elvatorCmds.ElevLvl2Cmd;
import frc.robot.commands.elvatorCmds.ElevLvl3Cmd;
import frc.robot.commands.elvatorCmds.ElevLvl4Cmd;
import frc.robot.commands.elvatorCmds.ElevLvlCmd;
import frc.robot.commands.elvatorCmds.ElevMidCmd;
import frc.robot.subsystems.ElevatorSubsys;

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
    private final ElevatorSubsys m_elevSubsys = new ElevatorSubsys();
    public static Joystick coDriverJS = new Joystick(2);
    public static int elevLevelReqest = 0;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button trigger bindings
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        // Create a JSB for each level
        new JoystickButton(coDriverJS, ElevConstants.kElevRcvrBtn).onTrue(new ElevLvlCmd(m_elevSubsys, 0));
        new JoystickButton(coDriverJS, ElevConstants.kElevLvl1Btn).onTrue(new ElevLvlCmd(m_elevSubsys, 1));
        new JoystickButton(coDriverJS, ElevConstants.kElevLvl2Btn).onTrue(new ElevLvlCmd(m_elevSubsys, 2));
        new JoystickButton(coDriverJS, ElevConstants.kElevLvl3Btn).onTrue(new ElevLvlCmd(m_elevSubsys, 3));
        new JoystickButton(coDriverJS, ElevConstants.kElevLvl4Btn).onTrue(new ElevLvlCmd(m_elevSubsys, 4));

        // Create a JSB for the 3 levels
        // new JoystickButton(coDriverJS, ElevConstants.kElevLowBtn).onTrue(new ElevLowCmd(m_elevSubsys, 1));
        // new JoystickButton(coDriverJS, ElevConstants.kElevMidBtn).onTrue(new ElevMidCmd(m_elevSubsys, 3));
        // new JoystickButton(coDriverJS, ElevConstants.kElevHighBtn).onTrue(new ElevHighCmd(m_elevSubsys, 4));

        // Work in progress.  Need to select the level then execute when ready(?).
        // new JoystickButton(coDriverJS, ElevConstants.kElevExecBtn).onTrue(new ElevExecCmd(m_elevSubsys, 0));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLowBtn).onTrue( () -> (this.setElevRq(0)));
        // new JoystickButton(coDriverJS, ElevConstants.kElevMidBtn).onTrue(new ElevMidCmd(m_elevSubsys, 3));
        // new JoystickButton(coDriverJS, ElevConstants.kElevHighBtn).onTrue(new ElevHighCmd(m_elevSubsys, 4));
    }

    private void setElevRq(int level){
        this.elevLevelReqest = level;
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
        // new Trigger(m_elevatorSubsys::exampleCondition)
        //         .onTrue(new ElevCommand(m_elevatorSubsys));

        // Schedule `exampleMethodCommand` when the Xbox controller's B button is
        // pressed,
        // cancelling on release.
        // m_driverController.b().whileTrue(m_elevatorSubsys.exampleMethodCommand());

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        return Autos.exampleAuto(m_elevSubsys);
    }

}
