
package frc.robot.subsystems;

// Commqnd-Based Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Logging and Testing Imports
import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Hardware Imports
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.HardwareConstants;

public class ClimberSubsystem extends SubsystemBase {

    // Hardware

    /**
    * This solenoid is placed on the climber to activate it during endgame.
    * It is meant to be activated once; hence, there are no methods
    * to deactivate it.
    */
    private final Solenoid climberSV = new Solenoid(HardwareConstants.pneumaticsModuleType,
            HardwareConstants.kClimberSolenoid_portNumber);

    // Variables
    /**
    * Used for reporting and logging whether the climber
    * is activated (robot should be up) or not. if
    * it is true, the climber is active. If false, it is not.
    */
    private boolean isActivated = false;

    /**
    * Constructor for this subsystem. used to create an object in RobotContainer.
    */
    public ClimberSubsystem() {
    }
    
    /**
    * Sets the climberSV solenoid to the positon expressed by {@code state}.
    * Also changes {@code isActivated} to that state.
    * @param state The intended state you want to set the climber solenoid.
    * @return void
    */
    public void activateClimber(boolean state) {
        climberSV.setPulseDuration(1);
        climberSV.set(state);
        System.out.println("Climber is Activated: " + climberSV.get());
        isActivated = climberSV.get();
    }
    
    @Override
    public void periodic() {
        // SmartDashboard reporting
        SmartDashboard.putBoolean("Climber is Activated:", climberSV.get());

        // Logging
        Logger.recordOutput("Climber/Activated", isActivated);
    }

    /*
    * This method is called every 20 ms in simulation.
    */
    public void simulationPeriodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putBoolean("Climber is Activated:", climberSV.get());
    }
}