package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.LimelightHelpers;
import frc.robot.Constants.RobotConstants;
import frc.robot.Constants.FieldConstants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.apriltag.AprilTagFieldLayout;

public class LimelightSubsystem extends SubsystemBase {
    
    public LimelightSubsystem() {}

    public double getAngleOfAprilTag (int id) {
        AprilTagFieldLayout field = AprilTagFieldLayout.loadField(
            AprilTagFields.k2025Reefscape
        );

        return field.getTagPose(id).get().getRotation().getZ();
    }

    /**
     * 
     * @param reefXY Array of length 2 that stores the x and y coordinates of the reef branch from the alliance origin.
     * @param aprilTagPoseAngleRadians The angle, in radians, of the AprilTag on the nearest side of the reef.
     * @param cameraToCenterOffset Array of length 2 that stores the x and y offset of the camera with respect to the center of the robot.
     * The +y direction is forward with the flipper side in front.
     * Tje +x direction is rotated -90 degrees from the +y direction.
     * @param flipperToCenterOffset Array of length 2 that stores the x and y offset of the flipper with respect to the center of the robot.
     * The +y direction is forward with the flipper side in front.
     * Tje +x direction is rotated -90 degrees from the +y direction.
     * @return The ideal pose of the robot to score on the branch expressed in reefXY.
     */
     public Pose2d getAlignedPose(double[] reefXY, 
        double aprilTagPoseAngleRadians, 
        double[] cameraToCenterOffset, 
        double[] flipperToCenterOffset)
    {
        Rotation2d idealPoseAngle = new Rotation2d((aprilTagPoseAngleRadians + Math.PI) % (2 * Math.PI));

        double idealVector = cameraToCenterOffset[0]
            + FieldConstants.kReefBranchInsetInches
            - flipperToCenterOffset[0];
        
        double idealX = reefXY[0] + idealVector * Math.cos(aprilTagPoseAngleRadians);
        double idealY = reefXY[1] + idealVector * Math.sin(aprilTagPoseAngleRadians);

        return new Pose2d(idealX, idealY, idealPoseAngle);
    }

    @Override
    public void periodic() {
        /*
        double tx = LimelightHelpers.getTX(RobotConstants.limelightName);
        double ty = LimelightHelpers.getTY(RobotConstants.limelightName);
        double ta = LimelightHelpers.getTA(RobotConstants.limelightName);

        double perceivedBranchWidthPixels = 
        LimelightHelpers.getT2DArray(
            RobotConstants.limelightName)[13];

        double forwardDistanceToBranchInches = 
            (RobotConstants.kLimelightWindowResolutionWidthPixels)
            * (FieldConstants.kReefBranchWidthInches)
            / (perceivedBranchWidthPixels
            * Math.sin(
                Math.toRadians(
                    RobotConstants.kLimelightHorizontalFOVdegrees
                    / 2))
            * 2);

        double horizontalOffsetToBranchInches = 
            forwardDistanceToBranchInches
            * Math.tan(
                Math.toRadians(
                    tx
                ));

        SmartDashboard.putNumber("X-Offset in degrees", tx);
        SmartDashboard.putNumber("Y-Offset in degrees", ty);
        SmartDashboard.putNumber("Area of target in %", ta);
        */
    }

    @Override
    public void simulationPeriodic() {
    }
}