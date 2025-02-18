package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PhotonPipelineResult;
import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;

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

    // Camera transform map
    Map<PhotonCamera, double[]> cameraTransforms = new HashMap<>();

    public PhotonSubsytem() {
        cameraTransforms.put(FRcam, new double[] { 12.0, 6.0 });
        cameraTransforms.put(FLcam, new double[] { 12.0, -6.0 });
        cameraTransforms.put(BRcam, new double[] { -12.0, 6.0 });
        cameraTransforms.put(BLcam, new double[] { -12.0, -6.0 });
        cameraTransforms.put(FrontCam, new double[] { 16.0, 0.0 });
        cameraTransforms.put(BackCam, new double[] { -16.0, 0.0 });
    }

    public Pose2d CameraToPose(PhotonCamera camera, double[] transform) {
        if (aprilTagLayout == null)
            return new Pose2d(); // Handle error case

        // Convert inches to meters
        double transformX = transform[0] * 0.0254;
        double transformY = transform[1] * 0.0254;

        // Camera offset from robot center
        Transform3d robotToCam = new Transform3d(
                new Translation3d(transformX, transformY, 0.5),
                new Rotation3d(0, 0, 0));

        // Create PhotonPoseEstimator
        PhotonPoseEstimator photonPoseEstimator = new PhotonPoseEstimator(
                aprilTagLayout, PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, robotToCam); // Constructor is in the
                                                                                        // following format:
                                                                                        // AprilTagFieldLayout
                                                                                        // fieldTags, PoseStrategy
                                                                                        // strategy, Transform3d
                                                                                        // robotToCamera.

        // Get latest camera result
        PhotonPipelineResult PPLR = camera.getLatestResult();
        Optional<EstimatedRobotPose> estimatedPose = photonPoseEstimator.update(PPLR);

        return estimatedPose.map(p -> p.estimatedPose)
                .map(Pose3d::toPose2d)
                .orElse(new Pose2d());

    }

    public Pose2d AveragePose() {
        double avgX = 0, avgY = 0, sumCos = 0, sumSin = 0;
        int validPoseCount = 0;

        for (Map.Entry<PhotonCamera, double[]> entry : cameraTransforms.entrySet()) {
            Pose2d pose = CameraToPose(entry.getKey(), entry.getValue());

            if (pose.getX() != 0 || pose.getY() != 0) { // Ensure valid pose
                avgX += pose.getX();
                avgY += pose.getY();
                sumCos += pose.getRotation().getCos();
                sumSin += pose.getRotation().getSin();
                validPoseCount++;
            }
        }

        if (validPoseCount == 0)
            return new Pose2d(); // No valid poses

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
