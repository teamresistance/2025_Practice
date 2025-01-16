// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Constants
import frc.robot.Constants.OperatorConstants;


//  .oooooo.                                                                             .o8           
// d8P'  `Y8b                                                                           "888           
//888           .ooooo.  ooo. .oo.  .oo.   ooo. .oo.  .oo.    .oooo.   ooo. .oo.    .oooo888   .oooo.o 
//888          d88' `88b `888P"Y88bP"Y88b  `888P"Y88bP"Y88b  `P  )88b  `888P"Y88b  d88' `888  d88(  "8 
//888          888   888  888   888   888   888   888   888   .oP"888   888   888  888   888  `"Y88b.  
//`88b    ooo  888   888  888   888   888   888   888   888  d8(  888   888   888  888   888  o.  )88b 
// `Y8bood8P'  `Y8bod8P' o888o o888o o888o o888o o888o o888o `Y888""8o o888o o888o `Y8bod88P" 8""888P' 

// Robot-wide Commands
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ChangeBooleanCommand;

// Elevator Commands
import frc.robot.commands.ElevatorRaiseFirstStageCommand;
import frc.robot.commands.ElevatorLowerFirstStageCommand;
import frc.robot.commands.ElevatorRaiseSecondStageCommand;
import frc.robot.commands.ElevatorLowerSecondStageCommand;

// Arm Commands
import frc.robot.commands.ArmGetIntoScoringPositionAndScoreCommand;
import frc.robot.commands.ArmRetractCommand;

//   .oooooo..o              .o8                                         .                                        
//  d8P'    `Y8             "888                                       .o8                                        
//  Y88bo.      oooo  oooo   888oooo.   .oooo.o oooo    ooo  .oooo.o .o888oo  .ooooo.  ooo. .oo.  .oo.    .oooo.o 
//   `"Y8888o.  `888  `888   d88' `88b d88(  "8  `88.  .8'  d88(  "8   888   d88' `88b `888P"Y88bP"Y88b  d88(  "8 
//       `"Y88b  888   888   888   888 `"Y88b.    `88..8'   `"Y88b.    888   888ooo888  888   888   888  `"Y88b.  
//  oo     .d8P  888   888   888   888 o.  )88b    `888'    o.  )88b   888 . 888    .o  888   888   888  o.  )88b 
//  8""88888P'   `V88V"V8P'  `Y8bod8P' 8""888P'     .8'     8""888P'   "888" `Y8bod8P' o888o o888o o888o 8""888P' 
//                                              .o..P'                                                            
//                                              `Y8P'                                                             

// Robot-wide Subsystems
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ChangeBooleanSubsystem;

// Delicious Subsystem Imports
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ArmSubsystem;

//    .oooooo.   ooooo 
//   d8P'  `Y8b  `888' 
//  888      888  888  
//  888      888  888  
//  888      888  888  
//  `88b    d88'  888  
//   `Y8bood8P'  o888o 
//                    

// Operator Input Imports
// ----------------------Logitech Extreme 3D Pro
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// ----------------------Xbox Controller
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

// Necessary stuff
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;

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

  private final ChangeBooleanCommand m_changeBooleanCommand = new ChangeBooleanCommand(m_changeBooleanSubsystem);

  private final ElevatorRaiseFirstStageCommand m_elevatorRaiseFirstStageCommand = new ElevatorRaiseFirstStageCommand(m_elevatorSubsystem);
  private final ElevatorLowerFirstStageCommand m_elevatorLowerFirstStageCommand = new ElevatorLowerFirstStageCommand(m_elevatorSubsystem);
  private final ElevatorRaiseSecondStageCommand m_elevatorRaiseSecondStageCommand = new ElevatorRaiseSecondStageCommand(m_elevatorSubsystem);
  private final ElevatorLowerSecondStageCommand m_elevatorLowerSecondStageCommand = new ElevatorLowerSecondStageCommand(m_elevatorSubsystem);

  private final ArmGetIntoScoringPositionAndScoreCommand m_armGetIntoScoringPositionAndScoreCommand = new ArmGetIntoScoringPositionAndScoreCommand(m_armSubsystem);
  private final ArmRetractCommand m_armRetractCommand = new ArmRetractCommand(m_armSubsystem);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the trigger bindings
    configureBindings();
  }

  public static Joystick driverJoystick = new Joystick(0);
  public static JoystickButton changeBooleanButton = new JoystickButton(driverJoystick, 7);
  public static JoystickButton lvl2Button = new JoystickButton(driverJoystick, 3);
  public static JoystickButton lvl3Button = new JoystickButton(driverJoystick, 4);
  public static JoystickButton lvl4Button = new JoystickButton(driverJoystick, 6);

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
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
    
    //Level 2
    lvl2Button.onTrue(m_armGetIntoScoringPositionAndScoreCommand.andThen(
      m_armRetractCommand)
    );
    //Level 3
    lvl3Button.onTrue(m_elevatorRaiseFirstStageCommand.andThen(
      m_armGetIntoScoringPositionAndScoreCommand.andThen(
        m_armRetractCommand.andThen(
          m_elevatorLowerFirstStageCommand)
          )
        )
      );
    //Level 4
    lvl4Button.onTrue(m_elevatorRaiseFirstStageCommand.andThen(
      m_elevatorRaiseSecondStageCommand.andThen(
        m_armGetIntoScoringPositionAndScoreCommand.andThen(
          m_armRetractCommand.andThen(
            m_elevatorLowerSecondStageCommand.andThen(
              m_elevatorLowerFirstStageCommand)
            )
          )
        )
      )
    );

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
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }

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
