package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class RollerSubsystem extends SubsystemBase {

        public boolean rollerActivated = false;
        public boolean rollerReverse = false;

        public RollerSubsystem() {}

        public void startRollersForward () {
            rollerActivated = true;
            rollerReverse = false;
        }
        // Method to stop the roller
        public void stopRollers() {
            rollerActivated = false;
        }
        public void startRollersReverse() {
            rollerReverse = true;
            rollerReverse = false;
        }

        @Override
        public void periodic() {
            SmartDashboard.putBoolean("Roller Activated?", rollerActivated);
            SmartDashboard.putBoolean("Roller Reversed?", rollerReverse);
        }
        @Override
        public void simulationPeriodic() {
            // This method will be called once per scheduler run during simulation
        }
    }
    
