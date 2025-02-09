package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.LimelightHelpers;
import frc.robot.Constants.RobotConstants;
import frc.robot.Constants.FieldConstants;

public class LimelightSubsystem extends SubsystemBase {
    
    public LimelightSubsystem() {}

    @Override
    public void periodic() {
        double tx = LimelightHelpers.getTX(RobotConstants.limelightName);
        double ty = LimelightHelpers.getTY(RobotConstants.limelightName);
        double ta = LimelightHelpers.getTA(RobotConstants.limelightName);

        double perceivedBranchWidthPixels = 
        LimelightHelpers.getT2DArray(
            RobotConstants.limelightName)[13];

        double forwardDistanceToBranchMeters = 
            (RobotConstants.kLimelightWindowResolutionWidthPixels)
            * (FieldConstants.kReefBranchWidthMeters)
            / (perceivedBranchWidthPixels
            * Math.sin(
                Math.toRadians(
                    RobotConstants.kLimelightHorizontalFOVdegrees
                    / 2))
            * 2);
        double horizontalOffsetToBranchMeters = 
            forwardDistanceToBranchMeters
            * Math.tan(
                Math.toRadians(
                    tx));
        
        SmartDashboard.putNumber("Forward Distance to Branch in Meters", forwardDistanceToBranchMeters);
        SmartDashboard.putNumber("Horizontal Offset to Branch in Meters", horizontalOffsetToBranchMeters);

    }

    @Override
    public void simulationPeriodic() {
      periodic();
    }
}