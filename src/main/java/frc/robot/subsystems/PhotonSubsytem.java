package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.wpi.first.units.Units.Rotation;

import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.proto.Photon;
import org.photonvision.targeting.PhotonPipelineResult;
import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;

import java.util.Map;
import java.util.Optional;
import java.util.HashMap;



public class PhotonSubsytem extends SubsystemBase {
    // PhotonVision cameras
    PhotonCamera FRcam = new PhotonCamera("front_right");
    PhotonCamera FLcam = new PhotonCamera("front_left");
    PhotonCamera BRcam = new PhotonCamera("back_right");
    PhotonCamera BLcam = new PhotonCamera("back_left");
    PhotonCamera FrontCam = new PhotonCamera("front");
    PhotonCamera BackCam = new PhotonCamera("back");

    // AprilTag Field Layout
    AprilTagFieldLayout aprilTagLayout = AprilTagFieldLayout.loadField(AprilTagFields.k2025ReefscapeWelded);

    // Map of camera transforms (X, Y in inches, converted to meters)
    Map<PhotonCamera, double[]> cameraTransforms = Map.of(
        FRcam, new double[]{12.0, 6.0},  // Example values in inches
        FLcam, new double[]{12.0, -6.0},
        BRcam, new double[]{-12.0, 6.0},
        BLcam, new double[]{-12.0, -6.0},
        FrontCam, new double[]{16.0, 0.0},
        BackCam, new double[]{-16.0, 0.0}
    );

    public PhotonSubsytem() {}

    public Pose2d CameraToPose(PhotonCamera camera, double[] transform) {
        // Convert inches to meters
        double transformX = transform[0] * 0.0254;
        double transformY = transform[1] * 0.0254;

        // Transform representing the camera’s offset from the robot center
        Transform3d robotToCam = new Transform3d(
            new Translation3d(transformX, transformY, 0.5),  // Assuming 0.5m height
            new Rotation3d(0, 0, 0)  // Assuming no tilt
        );

        // Create PhotonPoseEstimator
        PhotonPoseEstimator photonPoseEstimator = new PhotonPoseEstimator(
            aprilTagLayout, PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, camera, robotToCam
        );

        // Get latest camera result
        PhotonPipelineResult PPLR = camera.getLatestResult();
        Optional<EstimatedRobotPose> estimatedPose = photonPoseEstimator.update(PPLR);

        // Get Pose3d and convert to Pose2d
        return estimatedPose.map(EstimatedRobotPose::estimatedPose).map(Pose3d::toPose2d).orElse(new Pose2d());
    }

    public Pose2d AveragePose() {
        double avgX = 0, avgY = 0, sumCos = 0, sumSin = 0;
        int validPoseCount = 0;

        // Loop through all cameras and transforms
        for (Map.Entry<PhotonCamera, double[]> entry : cameraTransforms.entrySet()) {
            Pose2d pose = CameraToPose(entry.getKey(), entry.getValue());

            if (pose.getX() != 0 || pose.getY() != 0) { // Ensure the pose is valid
                avgX += pose.getX();
                avgY += pose.getY();
                sumCos += pose.getRotation().getCos();
                sumSin += pose.getRotation().getSin();
                validPoseCount++;
            }
        }

        if (validPoseCount == 0) return new Pose2d(); // No valid poses

        avgX /= validPoseCount;
        avgY /= validPoseCount;
        Rotation2d avgRot = new Rotation2d(Math.atan2(sumSin, sumCos));

        return new Pose2d(avgX, avgY, avgRot);
    }

    @Override
    public void periodic() {
        Pose2d avgPose = AveragePose();
        SmartDashboard.putNumber("Average Pose X", avgPose.getX());
        SmartDashboard.putNumber("Average Pose Y", avgPose.getY());
        SmartDashboard.putNumber("Average Rotation (Degrees)", avgPose.getRotation().getDegrees());
    }
}
