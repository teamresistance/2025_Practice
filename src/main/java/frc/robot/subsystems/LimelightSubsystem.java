package frc.robot.subsystems;

// Command based imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// Hardware Imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Limelight Helpers
import frc.robot.LimelightHelpers;

// Constants
import frc.robot.Constants.RobotConstants;
import frc.robot.Constants.FieldConstants;

// Logging and Testing Imports
import org.littletonrobotics.junction.Logger;

// Geometry Imports
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
// AprilTag Imports
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.apriltag.AprilTagFieldLayout;

//NetworkTable Imports
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;


public class LimelightSubsystem extends SubsystemBase {
    // Variables
    
    // BOTH APRILTAG BASED and REEF BRANCH BASED variables
    public Pose2d currentPose = new Pose2d(4, 4, new Rotation2d(4));
    public Pose2d alignedPose = new Pose2d(4, 4, new Rotation2d(4));;
    public boolean isSeekingAlignment = false;
    public Object[] reefBranchCombinations = { "", "", 0 };
    String limelightName = RobotConstants.limelightName;
    // REEF BRANCH BASED variables
    double perceivedBranchWidthPixels = 0.0;
    double forwardDistanceToBranchInches = 0.0;
    double horizontalOffsetToBranchInches = 0.0;
    double tX;
    double tShort;
    NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable(limelightName);

    /** Creates a new LimelightSubsystem. */
    public LimelightSubsystem() {
        if (limelightName == null || limelightName.isEmpty()) {
            throw new IllegalArgumentException("Limelight name is not set in RobotConstants.");
        }

        // Set the Limelight's pipeline to 0
        LimelightHelpers.setPipelineIndex(limelightName, 0);;
    }

    /**
     * Sets the seeking alignment state.
     * 
     * @param changeTo the new state of seeking alignment
     */
    public void setSeekingAlignment(boolean changeTo) {
        this.isSeekingAlignment = changeTo;
    }

    /**
     * Checks if the current pose is within the error threshold of the aligned pose.
     * 
     * @return true if within the error threshold, false otherwise
     */
    public boolean isWithinErrorThreshold() {
        boolean inXThreshold = Math
                .abs(currentPose.getX() - alignedPose.getX()) < RobotConstants.kXdirectionErrorThresholdInches;
        boolean inYThreshold = Math
                .abs(currentPose.getX() - alignedPose.getX()) < RobotConstants.kYdirectionErrorThresholdInches;

        return inXThreshold && inYThreshold;
    }

    // APRILTAG BASED STRATEGY


    public int getNearestVisibleAprilTagID() { // Should only be called when target is visible
        return (int) LimelightHelpers.getT2DArray(limelightName)[9];
    }

    public double getAngleOfAprilTag(int id) {
        AprilTagFieldLayout field = AprilTagFieldLayout.loadField(
                AprilTagFields.k2025ReefscapeWelded);

        return field.getTagPose(id).get().getRotation().getZ();
    }

    /**
     * Gets the reef XY coordinates based on the branch ID.
     * 
     * @param id the branch ID
     * @return the XY coordinates of the reef
     */
    public double[] getReefXY(String id) {
        if (!id.equals("M")) {
            switch (id) {
                case "A":
                    return FieldConstants.branchAposition;
                case "B":
                    return FieldConstants.branchBposition;
                case "C":
                    return FieldConstants.branchCposition;
                case "D":
                    return FieldConstants.branchDposition;
                case "E":
                    return FieldConstants.branchEposition;
                case "F":
                    return FieldConstants.branchFposition;
                case "G":
                    return FieldConstants.branchGposition;
                case "H":
                    return FieldConstants.branchHposition;
                case "I":
                    return FieldConstants.branchIposition;
                case "J":
                    return FieldConstants.branchJposition;
                case "K":
                    return FieldConstants.branchKposition;
                case "L":
                    return FieldConstants.branchLposition;
                default:
                    throw new IllegalArgumentException("Invalid branch ID: " + id);
            }
        } else {
            if (id.matches("A|B")) {
                return FieldConstants.reefABmidpoint;
            } else if (id.matches("C|D")) {
                return FieldConstants.reefCDmidpoint;
            } else if (id.matches("E|F")) {
                return FieldConstants.reefEFmidpoint;
            } else if (id.matches("G|H")) {
                return FieldConstants.reefGHmidpoint;
            } else if (id.matches("I|J")) {
                return FieldConstants.reefIJmidpoint;
            } else if (id.matches("K|L")) {
                return FieldConstants.reefKLmidpoint;
            } else {
                throw new IllegalArgumentException("Invalid branch ID: " + id);
            }
        }
    }

    /**
     * 
     * @param reefXY                   Array of length 2 that stores the x and y
     *                                 coordinates of the reef branch from the
     *                                 alliance origin.
     * @param aprilTagPoseAngleRadians The angle, in radians, of the AprilTag on the
     *                                 nearest side of the reef.
     * @param cameraToCenterOffset     Array of length 2 that stores the x and y
     *                                 offset of the camera with respect to the
     *                                 center of the robot.
     *                                 The +y direction is forward with the flipper
     *                                 side in front.
     *                                 Tje +x direction is rotated -90 degrees from
     *                                 the +y direction.
     * @param flipperToCenterOffset    Array of length 2 that stores the x and y
     *                                 offset of the flipper with respect to the
     *                                 center of the robot.
     *                                 The +y direction is forward with the flipper
     *                                 side in front.
     *                                 Tje +x direction is rotated -90 degrees from
     *                                 the +y direction.
     * @return The ideal pose of the robot to score on the branch expressed in
     *         reefXY.
     */
    public Pose2d getAlignedPose(double[] reefXY,
            double aprilTagPoseAngleRadians,
            double[] cameraToCenterOffset,
            double[] flipperToCenterOffset) {
        Rotation2d idealPoseAngle = new Rotation2d((aprilTagPoseAngleRadians + Math.PI) % (2 * Math.PI));

        double idealVector = cameraToCenterOffset[0]
                + FieldConstants.kReefBranchInsetInches
                - flipperToCenterOffset[0];

        double idealX = reefXY[0] + idealVector * Math.cos(aprilTagPoseAngleRadians);
        double idealY = reefXY[1] + idealVector * Math.sin(aprilTagPoseAngleRadians);

        return new Pose2d(idealX, idealY, idealPoseAngle);
    }

    // REEF BRANCH BASED STRATEGY

    /**
     * Updates the perceived branch width in pixels.
     */
    public void setPBWP() {
        perceivedBranchWidthPixels = tShort;
    }

    /**
     * Updates the forward distance to the branch in inches.
     */
    public void setFDTBI() {
        forwardDistanceToBranchInches = (RobotConstants.kLimelightWindowResolutionWidthPixels)
                * (FieldConstants.kReefBranchWidthInches)
                / (perceivedBranchWidthPixels
                        * Math.sin(
                                Math.toRadians(
                                        RobotConstants.kLimelightHorizontalFOVdegrees
                                                / 2))
                        * 2);
    }

    /**
     * Updates the horizontal offset to the branch in inches.
     */
    public void setHOTBI() {
        horizontalOffsetToBranchInches = forwardDistanceToBranchInches
                * Math.tan(
                        Math.toRadians(
                                tX));
    }

    /**
     * 
     * @param reefPose                 Pose of the reef branch. ONLY USED IN THE
     *                                 REEF BRANCH BASED APPROACH.
     * @param aprilTagPoseAngleRadians The angle, in radians, of the AprilTag on the
     *                                 nearest side of the reef.
     * @param cameraToCenterOffset     Array of length 2 that stores the x and y
     *                                 offset of the camera with respect to the
     *                                 center of the robot.
     *                                 The +y direction is forward with the flipper
     *                                 side in front.
     *                                 Tje +x direction is rotated -90 degrees from
     *                                 the +y direction.
     * @param flipperToCenterOffset    Array of length 2 that stores the x and y
     *                                 offset of the flipper with respect to the
     *                                 center of the robot.
     *                                 The +y direction is forward with the flipper
     *                                 side in front.
     *                                 Tje +x direction is rotated -90 degrees from
     *                                 the +y direction.
     * @return The ideal pose of the robot to score on the branch expressed in
     *         reefXY.
     */
    public Pose2d getAlignedPose(Pose2d reefPose,
            Pose2d currentPose,
            double[] cameraToCenterOffset,
            double[] flipperToCenterOffset) {
        Rotation2d idealPoseAngle = new Rotation2d(reefPose.getRotation().getRadians());

        double idealVector = cameraToCenterOffset[0]
                + FieldConstants.kReefBranchInsetInches
                - flipperToCenterOffset[0];

        double idealX = currentPose.getX() + idealVector * Math.cos(reefPose.getRotation().getRadians());
        double idealY = currentPose.getY() + idealVector * Math.sin(reefPose.getRotation().getRadians());

        return new Pose2d(idealX, idealY, idealPoseAngle);
    }

    @Override
    public void periodic() {
        // APRILTAG BASED STRATEGY
        // We want to keep isSeekingAlignment as true until the robot's pose matches the
        // alignedPose.
        if (isSeekingAlignment && LimelightHelpers.getTV(limelightName)) {
            alignedPose = getAlignedPose(
                    getReefXY(reefBranchCombinations[0].toString()),
                    getAngleOfAprilTag(getNearestVisibleAprilTagID()),
                    RobotConstants.kCameraToCenterOffsetInches,
                    RobotConstants.kFlipperToCenterOffsetInches);
        }

        // REEF BRANCH BASED STRATEGY
    boolean targetVisible = limelightTable.getEntry("tv").getDouble(0) == 1.0;
    tShort = limelightTable.getEntry("tshort").getDouble(0);
    tX = limelightTable.getEntry("tx").getDouble(0);

    if (targetVisible) {
        setPBWP();
        setFDTBI();
        setHOTBI();
    }

        // Debug
        System.out.println(perceivedBranchWidthPixels + " width in pixels");
        System.out.println(forwardDistanceToBranchInches + " distance to branch inches");
        System.out.println(horizontalOffsetToBranchInches + " horizontal to branch inches");

        // Logging
        Logger.recordOutput("Limelight/Aligned Pose", alignedPose);
        Logger.recordOutput("Limelight/Current Pose", currentPose);
        Logger.recordOutput("Limelight/Reef Branch Combinations", reefBranchCombinations.toString());
    }

    @Override
    public void simulationPeriodic() {
    }
}