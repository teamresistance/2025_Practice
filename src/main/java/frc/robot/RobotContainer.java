// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;

import frc.robot.commands.GoToReefLocationCmd;
import frc.robot.commands.InterfaceChooseCmd;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.InterfaceSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.ArmSubsystem3;
import frc.robot.subsystems.CoralSubsystem;
import frc.robot.subsystems.ElevatorSubsystem3;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.PositionElevatorCmd3;
import frc.robot.commands.ToggleArmCmd3;
import frc.robot.commands.VisionCmd;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ElevatorSubsystem3 m_elevatorSubsystem3 = new ElevatorSubsystem3();
  private final ArmSubsystem3 m_armSubsystem3 = new ArmSubsystem3();
  private final CoralSubsystem m_coralSubsystem = new CoralSubsystem();
  private final InterfaceSubsystem m_interfaceSubsystem = new InterfaceSubsystem();
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();


  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final Joystick js = new Joystick(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the trigger bindings
    configureBindings();
  }

  public static Joystick driverJoystick = new Joystick(0);
  public static JoystickButton changeBooleanButton = new JoystickButton(driverJoystick, 7);

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //Levels
    int mode = 2;
    if (mode == 1){
      new JoystickButton(js, 5).onTrue(new PositionElevatorCmd3(m_elevatorSubsystem3, 1));
      new JoystickButton(js, 3).onTrue(new PositionElevatorCmd3(m_elevatorSubsystem3, 2));
      new JoystickButton(js, 4).onTrue(new PositionElevatorCmd3(m_elevatorSubsystem3, 3));
      new JoystickButton(js, 6).onTrue(new PositionElevatorCmd3(m_elevatorSubsystem3, 4));
      //Arm
      new JoystickButton(js, 1).onTrue(new ToggleArmCmd3(m_armSubsystem3));
      //Reef
      new JoystickButton(js, 7).onTrue(new GoToReefLocationCmd(m_coralSubsystem, 1));
      new JoystickButton(js, 8).onTrue(new GoToReefLocationCmd(m_coralSubsystem, 2));
      new JoystickButton(js, 9).onTrue(new GoToReefLocationCmd(m_coralSubsystem, 3));
      new JoystickButton(js, 10).onTrue(new GoToReefLocationCmd(m_coralSubsystem, 4));
      new JoystickButton(js, 11).onTrue(new GoToReefLocationCmd(m_coralSubsystem, 5));
      new JoystickButton(js, 12).onTrue(new GoToReefLocationCmd(m_coralSubsystem, 6));
    }
    if (mode == 2){
      new JoystickButton(js, 1).onTrue(new VisionCmd(m_visionSubsystem));
    }
    if (mode == 3){
      new JoystickButton(js, 1).onTrue(new InterfaceChooseCmd(m_interfaceSubsystem));
    }
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  /**
   * Use this to pass the boolean changer command to the main {@link Robot} class.
   *
   * @return the command to run in teleopPeriodic
   */
}
