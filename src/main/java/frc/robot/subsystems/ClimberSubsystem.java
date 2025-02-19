
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.HardwareConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClimberSubsystem extends SubsystemBase {

    // Hardware
    private final Solenoid climberSV = new Solenoid(HardwareConstants.pneumaticsModuleType,
            HardwareConstants.kClimberSolenoid_portNumber);

    // Variables
    private boolean isActivated = false;

    public ClimberSubsystem() {
    }

    public void activateClimber(boolean state) {
        climberSV.setPulseDuration(1);
        climberSV.set(state);
        System.out.println("Climber is Activated: " + climberSV.get());
        isActivated = climberSV.get();
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Climber is Activated:", climberSV.get());
    }

    public void simulationPeriodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putBoolean("Climber is Activated:", climberSV.get());

        Logger.recordOutput("Climber/Activated", isActivated);
    }
}