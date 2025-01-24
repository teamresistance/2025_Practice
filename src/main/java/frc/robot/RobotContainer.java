// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.elvatorCmds.ArmToggleCmd;
import frc.robot.commands.elvatorCmds.ElevExecCmd;
import frc.robot.commands.elvatorCmds.ElevSetRqCmd;
import frc.robot.commands.elvatorCmds.GripToggleCmd;
import frc.robot.subsystems.ArmSubsys;
import frc.robot.subsystems.ElevatorSubsys;
import frc.robot.subsystems.FlipSubsys;
import frc.robot.subsystems.GripSubsys;

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
    private final FlipSubsys m_flipSubsys = new FlipSubsys();

    private static final JS_OI jsbtn = new JS_OI(1);  // 0=Normal 3 JS, 1=Neopad, 2=Keyboard
    
    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        configButtonBindings();
    }

    public void configButtonBindings() {
        // Need to select the level then execute when ready(?).
        jsbtn.elevExecBtn.onTrue(new ElevExecCmd(m_elevSubsys));
        jsbtn.elevLowBtn.onTrue(new ElevSetRqCmd(m_elevSubsys, 0));
        jsbtn.elevMidBtn.onTrue(new ElevSetRqCmd(m_elevSubsys, 3));
        jsbtn.elevHighBtn.onTrue(new ElevSetRqCmd(m_elevSubsys, 4));
        //for arm toggle
        jsbtn.armToggleBtn.onTrue(new ArmToggleCmd(m_armSubsys));
        //for gripper
        jsbtn.gripToggleBtn.onTrue(new GripToggleCmd(m_gripSubsys));
    }

    public void update(){
        if(jsbtn.ChooserUpd()) configButtonBindings();;
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
    // private void configureBindings() {
        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
        // new Trigger(m_elevatorSubsys::exampleCondition)
        // .onTrue(new ElevCommand(m_elevatorSubsys));

        // Schedule `exampleMethodCommand` when the Xbox controller's B button is
        // pressed,
        // cancelling on release.
        // m_driverController.b().whileTrue(m_elevatorSubsys.exampleMethodCommand());

    // }

    // /**
    //  * Use this to pass the autonomous command to the main {@link Robot} class.
    //  *
    //  * @return the command to run in autonomous
    //  */
    // public Command getAutonomousCommand() {
    //     // An example command will be run in autonomous
    //     return Autos.exampleAuto(m_elevSubsys);
    // }

}
