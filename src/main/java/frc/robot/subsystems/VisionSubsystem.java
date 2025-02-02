// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {

  private PhotonCamera camera = new PhotonCamera("RPI3 PhotonVision");
  private boolean hasTargets;
  private PhotonTrackedTarget target;
  double yaw;
  double pitch;
  double area;
  double skew;
  int ID;

  public void outputResults(){
    System.out.println(yaw + " " + pitch + " " + area + " " + skew + " " + ID);
  }

  /** Creates a new VisionSubsystem. */
  public VisionSubsystem() {}

  @Override
  public void periodic() {
    var result = camera.getLatestResult();
    hasTargets = result.hasTargets();
    target = result.getBestTarget();

    if (hasTargets){
      yaw = target.getYaw();
      pitch = target.getPitch();
      area = target.getArea();
      skew = target.getSkew();
      ID = target.getFiducialId();
    }
    // This method will be called once per scheduler run
  }
}
