package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.LimelightHelpers;
import frc.robot.Constants.RobotConstants;

public class LimelightSubsystem extends SubsystemBase {
    
    public LimelightSubsystem() {}

    @Override
    public void periodic() {
        double tx = LimelightHelpers.getTX(RobotConstants.limelightName);
        double ty = LimelightHelpers.getTY(RobotConstants.limelightName);
        double ta = LimelightHelpers.getTA(RobotConstants.limelightName);

        SmartDashboard.putNumber("X-Offset in degrees", tx);
        SmartDashboard.putNumber("Y-Offset in degrees", ty);
        SmartDashboard.putNumber("Area of target in %", ta);
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
