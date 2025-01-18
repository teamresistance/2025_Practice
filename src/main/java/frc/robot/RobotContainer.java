// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.JSConstants;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.GripConstants;
import frc.robot.Constants.OperatorConstants;

import frc.robot.commands.Autos;
import frc.robot.commands.elvatorCmds.ArmToggleCmd;
import frc.robot.commands.elvatorCmds.ElevExecCmd;
import frc.robot.commands.elvatorCmds.ElevHighCmd;
import frc.robot.commands.elvatorCmds.ElevLowCmd;
import frc.robot.commands.elvatorCmds.ElevLvlCmd;
import frc.robot.commands.elvatorCmds.ElevMidCmd;
import frc.robot.commands.elvatorCmds.ElevSetRqCmd;
import frc.robot.commands.elvatorCmds.GripToggleCmd;
import frc.robot.subsystems.ArmSubsys;
import frc.robot.subsystems.ElevatorSubsys;
import frc.robot.subsystems.GripSubsys;
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
    private final GripSubsys m_gripSubsys = new GripSubsys();
    private final ArmSubsys m_armSubsys = new ArmSubsys();
    //Joysticks possible
    public static Joystick leftDrvrJS = new Joystick(0);
    public static Joystick rightDrvrJS = new Joystick(1);
    public static Joystick coDriverJS = new Joystick(2);
    public static Joystick neoJS = new Joystick(3);
    public static Joystick kybd1JS = new Joystick(4);

    public static int elevLevelReqest = 0;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button trigger bindings
        int defaultConfigJS = 1;    //0=Normal 3 JS, 1=Neopad, 2=Keyboard 1
        switch( defaultConfigJS ){
            case 0:
            config3JSBtnBindings();
            break;
            case 1:
            configNeoBtnBindings();
            break;
            case 2:
            configKybd1BtnBindings();
            break;
            default:
            config3JSBtnBindings();
            System.out.println("Bad joystick configuration: " + defaultConfigJS);
        }
    }

    private void config3JSBtnBindings() {
        // Create a JSB for each level
        // new JoystickButton(coDriverJS, ElevConstants.kElevRcvrBtn).onTrue(new ElevLvlCmd(m_elevSubsys, 0));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLvl1Btn).onTrue(new ElevLvlCmd(m_elevSubsys, 1));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLvl2Btn).onTrue(new ElevLvlCmd(m_elevSubsys, 2));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLvl3Btn).onTrue(new ElevLvlCmd(m_elevSubsys, 3));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLvl4Btn).onTrue(new ElevLvlCmd(m_elevSubsys, 4));

        // Create a JSB for the 3 levels: low, mid, high
        // new JoystickButton(coDriverJS, ElevConstants.kElevHighBtn).onTrue(new ElevLvlCmd(m_elevSubsys, 1));
        // new JoystickButton(coDriverJS, ElevConstants.kElevMidBtn).onTrue(new ElevLvlCmd(m_elevSubsys, 2));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLowBtn).onTrue(new ElevLvlCmd(m_elevSubsys, 3));

        // Work in progress.  Need to select the level then execute when ready(?).
        new JoystickButton(coDriverJS, JSConstants.kElevExec3JSBtn).onTrue(new ElevExecCmd(m_elevSubsys));
        new JoystickButton(coDriverJS, JSConstants.kElevHigh3JSBtn).onTrue(new ElevSetRqCmd(m_elevSubsys, 4));
        new JoystickButton(coDriverJS, JSConstants.kElevMid3JSBtn).onTrue(new ElevSetRqCmd(m_elevSubsys, 3));
        new JoystickButton(coDriverJS, JSConstants.kElevLow4JSBtn).onTrue(new ElevSetRqCmd(m_elevSubsys, 0));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLowBtn).onTrue( () -> (this.setElevRq(0)));
    }

    private void configNeoBtnBindings() {
        // Create a JSB for each level
        // new JoystickButton(coDriverJS, ElevConstants.kElevRcvrBtn).onTrue(new ElevLvlCmd(m_elevSubsys, 0));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLvl1Btn).onTrue(new ElevLvlCmd(m_elevSubsys, 1));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLvl2Btn).onTrue(new ElevLvlCmd(m_elevSubsys, 2));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLvl3Btn).onTrue(new ElevLvlCmd(m_elevSubsys, 3));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLvl4Btn).onTrue(new ElevLvlCmd(m_elevSubsys, 4));

        // Create a JSB for the 3 levels: low, mid, high
        // new JoystickButton(coDriverJS, ElevConstants.kElevHighBtn).onTrue(new ElevLvlCmd(m_elevSubsys, 1));
        // new JoystickButton(coDriverJS, ElevConstants.kElevMidBtn).onTrue(new ElevLvlCmd(m_elevSubsys, 2));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLowBtn).onTrue(new ElevLvlCmd(m_elevSubsys, 3));

        // Work in progress.  Need to select the level then execute when ready(?).
        new JoystickButton(neoJS, JSConstants.kElevExecNeoBtn).onTrue(new ElevExecCmd(m_elevSubsys));
        new JoystickButton(neoJS, JSConstants.kElevHighNeoBtn).onTrue(new ElevSetRqCmd(m_elevSubsys, 4));
        new JoystickButton(neoJS, JSConstants.kElevMidNeoBtn).onTrue(new ElevSetRqCmd(m_elevSubsys, 3));
        new JoystickButton(neoJS, JSConstants.kElevLowNeoBtn).onTrue(new ElevSetRqCmd(m_elevSubsys, 0));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLowBtn).onTrue( () -> (this.setElevRq(0)));
        new JoystickButton(neoJS, ArmConstants.kArmTglNeoBtn).onTrue(new ArmToggleCmd(m_armSubsys));
        new JoystickButton(neoJS, GripConstants.kGripTglNeoBtn).onTrue(new GripToggleCmd(m_gripSubsys));

    }

    private void configKybd1BtnBindings() {
        // Work in progress.  Need to select the level then execute when ready(?).
        new JoystickButton(kybd1JS, JSConstants.kElevExecKybd1Btn).onTrue(new ElevExecCmd(m_elevSubsys));
        new JoystickButton(kybd1JS, JSConstants.kElevHighKybd1Btn).onTrue(new ElevSetRqCmd(m_elevSubsys, 4));
        new JoystickButton(kybd1JS,  JSConstants.kElevMidKybd1Btn).onTrue(new ElevSetRqCmd(m_elevSubsys, 3));
        new JoystickButton(kybd1JS,  JSConstants.kElevLowKybd1Btn).onTrue(new ElevSetRqCmd(m_elevSubsys, 0));
        // new JoystickButton(coDriverJS, ElevConstants.kElevLowBtn).onTrue( () -> (this.setElevRq(0)));
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
