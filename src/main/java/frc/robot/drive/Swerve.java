// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive;

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
import frc.robot.drive.SwerveModule;
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
public class Swerve extends SubsystemBase {
    Trajectory trajectory;
    Trajectory.State theStateOfTheTrajectory;
    Pose2d BlueATarget= new Pose2d(4.073, 3.306, new Rotation2d(Math.atan(3.306/4.073)));
    Pose2d BlueBTarget= new Pose2d(4.064, 2.959, new Rotation2d(Math.atan(2.959/4.064)));
    Pose2d BlueCTarget= new Pose2d(5.199, 3.125, new Rotation2d(Math.atan(3.125/5.199)));
    Pose2d BlueDTarget= new Pose2d(5.0152, 3.86, new Rotation2d(Math.atan(3.86/5.0152)));
    Pose2d BlueKTarget= new Pose2d(4.9261, 12.347, new Rotation2d(Math.tan(12.347/4.9261)));
    Pose2d BlueLTarget= new Pose2d(12.5308, 4.19, new Rotation2d(Math.tan(4.19/12.5308)));
    Pose2d currentPose2d= new Pose2d(0,0,new Rotation2d(0));
    TrajectoryConfig trajectoryConfiguration= new TrajectoryConfig(1,1);
    Spline.ControlVector controlVectorStart = new Spline.ControlVector(new double[]{0, 0, 0}, new double[]{0, 0, 0});
    Spline.ControlVector controlVectorEnd = new Spline.ControlVector(new double[]{0, 0, 0}, new double[]{0, 0, 0});
    Trajectory BlueAtrajectory;
    TrajectoryGenerator genny;
    QuinticHermiteSpline curvedPathway;
    Spline[] arrayOfSplines;
    SwerveModule frontLeft= new SwerveModule(1,2);
    SwerveModule frontRight= new SwerveModule(3,4);
    SwerveModule backLeft= new SwerveModule(5,6);
    SwerveModule backRight= new SwerveModule(7,8);
    SwerveModule[] swerveModules= new SwerveModule[4];{

    
    swerveModules[0]=frontLeft;
    swerveModules[1]=frontRight;
    swerveModules[2]= backLeft;
    swerveModules[3]= backRight;
}
  /** Creates a new FlipperSubsystem. */
  public Swerve() {
  }
  public void moveToTargetA(){
    controlVectorStart.x= new double[]{currentPose2d.getX(),1, 0};
    controlVectorStart.y= new double[]{currentPose2d.getY(),1, 0};
    controlVectorEnd.x= new double[]{BlueATarget.getX(), 0, 0};
    controlVectorEnd.y= new double[]{BlueATarget.getY(), 0, 0};
    curvedPathway= new QuinticHermiteSpline(controlVectorStart.x, controlVectorStart.y,controlVectorEnd.x,controlVectorEnd.y);
    Spline[] arrayOfSplines= {curvedPathway};
    List<PoseWithCurvature> pathway= TrajectoryGenerator.splinePointsFromSplines(arrayOfSplines);
    List<Pose2d> twoDimensionalPoints= new ArrayList<>();
    for(PoseWithCurvature curvedPose: pathway){
      twoDimensionalPoints.add(curvedPose.poseMeters);
    }
    trajectory= TrajectoryGenerator.generateTrajectory(twoDimensionalPoints, trajectoryConfiguration);
    swerveModules[0].setDesiredState(theStateOfTheTrajectory);
    swerveModules[1].setDesiredState(theStateOfTheTrajectory);
    swerveModules[2].setDesiredState(theStateOfTheTrajectory);
    swerveModules[3].setDesiredState(theStateOfTheTrajectory);
  }
  public void moveToTargetB(){
    controlVectorStart.x= new double[]{currentPose2d.getX(),1, 0};
    controlVectorStart.y= new double[]{currentPose2d.getY(),1, 0};
    controlVectorEnd.x= new double[]{BlueBTarget.getX(), 0, 0};
    controlVectorEnd.y= new double[]{BlueBTarget.getY(), 0, 0};
    curvedPathway= new QuinticHermiteSpline(controlVectorStart.x, controlVectorStart.y,controlVectorEnd.x,controlVectorEnd.y);
    Spline[] arrayOfSplines= {curvedPathway};
    List<PoseWithCurvature> pathway= TrajectoryGenerator.splinePointsFromSplines(arrayOfSplines);
    List<Pose2d> twoDimensionalPoints= new ArrayList<>();
    for(PoseWithCurvature curvedPose: pathway){
      twoDimensionalPoints.add(curvedPose.poseMeters);
    }
    trajectory= TrajectoryGenerator.generateTrajectory(twoDimensionalPoints, trajectoryConfiguration);
    swerveModules[0].setDesiredState(theStateOfTheTrajectory);
    swerveModules[1].setDesiredState(theStateOfTheTrajectory);
    swerveModules[2].setDesiredState(theStateOfTheTrajectory);
    swerveModules[3].setDesiredState(theStateOfTheTrajectory);
  }
  public void moveToTargetC(){
    controlVectorStart.x= new double[]{currentPose2d.getX(),1, 0};
    controlVectorStart.y= new double[]{currentPose2d.getY(),1, 0};
    controlVectorEnd.x= new double[]{BlueCTarget.getX(), 0, 0};
    controlVectorEnd.y= new double[]{BlueCTarget.getY(), 0, 0};
    curvedPathway= new QuinticHermiteSpline(controlVectorStart.x, controlVectorStart.y,controlVectorEnd.x,controlVectorEnd.y);
    Spline[] arrayOfSplines= {curvedPathway};
    List<PoseWithCurvature> pathway= TrajectoryGenerator.splinePointsFromSplines(arrayOfSplines);
    List<Pose2d> twoDimensionalPoints= new ArrayList<>();
    for(PoseWithCurvature curvedPose: pathway){
      twoDimensionalPoints.add(curvedPose.poseMeters);
    }
    trajectory= TrajectoryGenerator.generateTrajectory(twoDimensionalPoints, trajectoryConfiguration);
    swerveModules[0].setDesiredState(theStateOfTheTrajectory);
    swerveModules[1].setDesiredState(theStateOfTheTrajectory);
    swerveModules[2].setDesiredState(theStateOfTheTrajectory);
    swerveModules[3].setDesiredState(theStateOfTheTrajectory);
  }
  public void moveToTargetD(){
    controlVectorStart.x= new double[]{currentPose2d.getX(),1, 0};
    controlVectorStart.y= new double[]{currentPose2d.getY(),1, 0};
    controlVectorEnd.x= new double[]{BlueDTarget.getX(), 0, 0};
    controlVectorEnd.y= new double[]{BlueDTarget.getY(), 0, 0};
    curvedPathway= new QuinticHermiteSpline(controlVectorStart.x, controlVectorStart.y,controlVectorEnd.x,controlVectorEnd.y);
    Spline[] arrayOfSplines= {curvedPathway};
    List<PoseWithCurvature> pathway= TrajectoryGenerator.splinePointsFromSplines(arrayOfSplines);
    List<Pose2d> twoDimensionalPoints= new ArrayList<>();
    for(PoseWithCurvature curvedPose: pathway){
      twoDimensionalPoints.add(curvedPose.poseMeters);
    }
    trajectory= TrajectoryGenerator.generateTrajectory(twoDimensionalPoints, trajectoryConfiguration);
    swerveModules[0].setDesiredState(theStateOfTheTrajectory);
    swerveModules[1].setDesiredState(theStateOfTheTrajectory);
    swerveModules[2].setDesiredState(theStateOfTheTrajectory);
    swerveModules[3].setDesiredState(theStateOfTheTrajectory);
  }
  public void moveToTargetK(){
    controlVectorStart.x= new double[]{currentPose2d.getX(),1, 0};
    controlVectorStart.y= new double[]{currentPose2d.getY(),1, 0};
    controlVectorEnd.x= new double[]{BlueKTarget.getX(), 0, 0};
    controlVectorEnd.y= new double[]{BlueKTarget.getY(), 0, 0};
    curvedPathway= new QuinticHermiteSpline(controlVectorStart.x, controlVectorStart.y,controlVectorEnd.x,controlVectorEnd.y);
    Spline[] arrayOfSplines= {curvedPathway};
    List<PoseWithCurvature> pathway= TrajectoryGenerator.splinePointsFromSplines(arrayOfSplines);
    List<Pose2d> twoDimensionalPoints= new ArrayList<>();
    for(PoseWithCurvature curvedPose: pathway){
      twoDimensionalPoints.add(curvedPose.poseMeters);
    }
    trajectory= TrajectoryGenerator.generateTrajectory(twoDimensionalPoints, trajectoryConfiguration);
    swerveModules[0].setDesiredState(theStateOfTheTrajectory);
    swerveModules[1].setDesiredState(theStateOfTheTrajectory);
    swerveModules[2].setDesiredState(theStateOfTheTrajectory);
    swerveModules[3].setDesiredState(theStateOfTheTrajectory);
  }
  public void moveToTargetL(){
    controlVectorStart.x= new double[]{currentPose2d.getX(),1, 0};
    controlVectorStart.y= new double[]{currentPose2d.getY(),1, 0};
    controlVectorEnd.x= new double[]{BlueLTarget.getX(), 0, 0};
    controlVectorEnd.y= new double[]{BlueLTarget.getY(), 0, 0};
    curvedPathway= new QuinticHermiteSpline(controlVectorStart.x, controlVectorStart.y,controlVectorEnd.x,controlVectorEnd.y);
    Spline[] arrayOfSplines= {curvedPathway};
    List<PoseWithCurvature> pathway= TrajectoryGenerator.splinePointsFromSplines(arrayOfSplines);
    List<Pose2d> twoDimensionalPoints= new ArrayList<>();
    for(PoseWithCurvature curvedPose: pathway){
      twoDimensionalPoints.add(curvedPose.poseMeters);
    }
    trajectory= TrajectoryGenerator.generateTrajectory(twoDimensionalPoints, trajectoryConfiguration);
    swerveModules[0].setDesiredState(theStateOfTheTrajectory);
    swerveModules[1].setDesiredState(theStateOfTheTrajectory);
    swerveModules[2].setDesiredState(theStateOfTheTrajectory);
    swerveModules[3].setDesiredState(theStateOfTheTrajectory);
  }

  @Override
  public void periodic() {
    
    

    
  }
  @Override
  public void simulationPeriodic() {
    double millisecondsTime= System.currentTimeMillis()/1000;
      theStateOfTheTrajectory= trajectory.sample(millisecondsTime);
    
  }
}