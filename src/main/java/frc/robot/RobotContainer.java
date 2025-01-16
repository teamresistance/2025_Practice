// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;

import frc.robot.commands.ChangeBooleanCommand;
import frc.robot.commands.GoToReefLocationCmd;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ChangeBooleanSubsystem;
import frc.robot.subsystems.CoralSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.PositionElevatorCmd;
import frc.robot.commands.ToggleArmCmd;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ChangeBooleanSubsystem m_changeBooleanSubsystem = new ChangeBooleanSubsystem();
  private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
  private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
  private final CoralSubsystem m_coralSubsystem = new CoralSubsystem();

  private final ChangeBooleanCommand m_changeBooleanCommand = new ChangeBooleanCommand(m_changeBooleanSubsystem);

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
    new JoystickButton(js, 5).onTrue(new PositionElevatorCmd(m_elevatorSubsystem, 1));
    new JoystickButton(js, 3).onTrue(new PositionElevatorCmd(m_elevatorSubsystem, 2));
    new JoystickButton(js, 4).onTrue(new PositionElevatorCmd(m_elevatorSubsystem, 3));
    new JoystickButton(js, 6).onTrue(new PositionElevatorCmd(m_elevatorSubsystem, 4));
    //Arm
    new JoystickButton(js, 1).onTrue(new ToggleArmCmd(m_armSubsystem));
    //Reef
    new JoystickButton(js, 7).onTrue(new GoToReefLocationCmd(m_coralSubsystem, 1));
    new JoystickButton(js, 8).onTrue(new GoToReefLocationCmd(m_coralSubsystem, 2));
    new JoystickButton(js, 9).onTrue(new GoToReefLocationCmd(m_coralSubsystem, 3));
    new JoystickButton(js, 10).onTrue(new GoToReefLocationCmd(m_coralSubsystem, 4));
    new JoystickButton(js, 11).onTrue(new GoToReefLocationCmd(m_coralSubsystem, 5));
    new JoystickButton(js, 12).onTrue(new GoToReefLocationCmd(m_coralSubsystem, 6));
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    changeBooleanButton.onTrue(m_changeBooleanCommand);
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
  public Command getChangeBooleanCommand() {
    // An example command will be run in autonomous
    return new ChangeBooleanCommand(m_changeBooleanSubsystem);
  }
}
