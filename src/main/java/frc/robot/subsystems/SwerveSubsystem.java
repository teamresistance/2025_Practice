
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.RobotConstants;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import java.util.List;
import static edu.wpi.first.units.Units.Newton;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.SwerveModule;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.spline.*;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.spline.Spline.ControlVector;
import java.util.ArrayList;
public class SwerveSubsystem extends SubsystemBase {
  

  /** Creates a new FlipperSubsystem. */
  public SwerveSubsystem() {
    Pose2d BlueATarget= new Pose2d(4.073, 3.306, new Rotation2d(Math.atan(3.306/4.073)));
    Pose2d BlueBTarget= new Pose2d(4.064, 2.959, new Rotation2d(Math.atan(2.959/4.064)));
    Pose2d BlueCTarget= new Pose2d(5.199, 3.125, new Rotation2d(Math.atan(3.125/5.199)));
    Pose2d BlueDTarget= new Pose2d(5.0152, 3.86, new Rotation2d(Math.atan(3.86/5.0152)));
    Pose2d BlueKTarget= new Pose2d(4.9261, 12.347, new Rotation2d(Math.tan(12.347/4.9261)));
    Pose2d BlueLTarget= new Pose2d(12.5308, 4.19, new Rotation2d(Math.tan(4.19/12.5308)));
    Pose2d currentPose2d= new Pose2d(0,0,new Rotation2d(0));
    Trajectory BlueAtrajectory;
    TrajectoryConfig trajectoryConfiguration= new TrajectoryConfig(1,1);
    Spline.ControlVector controlVectorStart = new Spline.ControlVector(new double[]{0, 0, 0}, new double[]{0, 0, 0});
    Spline.ControlVector controlVectorEnd = new Spline.ControlVector(new double[]{0, 0, 0}, new double[]{0, 0, 0});
    controlVectorStart.x= new double[]{currentPose2d.getX(),1, 0};
    controlVectorStart.y= new double[]{currentPose2d.getY(),1, 0};
    controlVectorEnd.x= new double[]{BlueATarget.getX(), 1, 0};
    controlVectorEnd.y= new double[]{BlueATarget.getY(), 0, 0};
    TrajectoryGenerator genny;
    QuinticHermiteSpline curvedPathway= new QuinticHermiteSpline(controlVectorStart.x, controlVectorStart.y,controlVectorEnd.x,controlVectorEnd.y);
    Spline[] arrayOfSplines= {curvedPathway};
    List<PoseWithCurvature> pathway= TrajectoryGenerator.splinePointsFromSplines(arrayOfSplines);
    List<Pose2d> twoDimensionalPoints= new ArrayList<>();
    for(PoseWithCurvature curvedPose: pathway){
      twoDimensionalPoints.add(curvedPose.poseMeters);
    }
    Trajectory trajectory= TrajectoryGenerator.generateTrajectory(twoDimensionalPoints, trajectoryConfiguration);
    SwerveModule frontLeft= new SwerveModule(1,2,3);
    SwerveModule frontRight= new SwerveModule(4,5,6);
    SwerveModule backLeft= new SwerveModule(7,8,9);
    SwerveModule backRight= new SwerveModule(10,11,12);
    SwerveModule swerveModules[]= new SwerveModule[4];
    swerveModules[0]=frontLeft;
    swerveModules[1]=frontRight;
    swerveModules[2]= backLeft;
    swerveModules[3]= backRight;
  }


  @Override
  public void periodic() {
    
    

    
  }
  @Override
  public void simulationPeriodic() {
    //Scan for coral using DigitalInput.
    //If coral found, grip it after 200ms.
    

    
  }
}





