// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.RobotConstants;
import java.util.Calendar;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.swerve.SwerveModule;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units; // Added import for Units
import edu.wpi.first.wpilibj.drive.SwerveDriveKinematics; // Added import for SwerveDriveKinematics
import edu.wpi.first.wpilibj.drive.SwerveDriveOdometry; // Added import for SwerveDriveOdometry
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX; // Added import for PWMTalonFX
import edu.wpi.first.wpilibj.trajectory.Trajectory; // Added import for Trajectory
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig; // Added import for TrajectoryConfig
import edu.wpi.first.math.controller.LTVUnicycleController;
import edu.wpi.first.math.controller.LTVUnicycleController;
public class SwerveSubsystem extends SubsystemBase {
    public Pose2d currentPose;

    Pose2d BlueATarget= new Pose2d(4.073, 3.306, new Rotation2d(Math.atan(3.306/4.073)));
    Pose2d BlueBTarget= new Pose2d(4.064, 2.959, new Rotation2d(Math.atan(2.959/4.064)));
    Pose2d BlueCTarget= new Pose2d(5.199, 3.125, new Rotation2d(Math.atan(3.125/5.199)));
    Pose2d BlueDTarget= new Pose2d(5.0152, 3.86, new Rotation2d(Math.atan(Math.atan(3.86/5.0152))));
    Pose2d BlueKTarget= new Pose2d(4.9261, 12.347, new Rotation2d(Math.tan(12.347/4.9261)));
    Pose2d BlueLTarget= new Pose2d(12.5308, 4.19, new Rotation2d(Math.tan(4.19/12.5308)));
    RamseteController ramseteController= new RamseteController();
    Trajectory robotPath= new Trajectory(currentPose, BlueATarget, config);
    TalonFX swerveMotor1= new TalonFX(1);
    TalonFX swerveMotor2= new TalonFX(2);
    TalonFX swerveMotor3= new TalonFX(3);
    TalonFX swerveMotor4= new TalonFX(4);
    SwerveModule[] swerveModule= new SwerveModule[4];
    swerveModule[0]= swerveMotor1;
    swerveModule[1]= swerveMotor2;
    swerveModule[2]= swerveMotor3;
    swerveModule[3]= swerveMotor4;
    Trajectory.State trajectoryState;
  /** Creates a new FlipperSubsystem. */

  public SwerveSubsystem() {}
  public void moveToTarget(){
    SwerveModuleState desiredState= ramsetteController.calculate(currentPose, trajectoryState);
    swerveModule[0].setDesiredState(desiredState);
    swerveModule[1].setDesiredState(desiredState);
    swerveModule[2].setDesiredState(desiredState);
    swerveModule[3].setDesiredState(desiredState);   
  }
    

  

  @Override
  public void periodic() {
    //Scan for coral using DigitalInput.
    //If coral found, grip it after 200ms.

    SmartDashboard.putBoolean("Has Coral?", hasCoral);
    SmartDashboard.putBoolean("Is Gripped?", isGripped);
    SmartDashboard.putBoolean("In Scoring Position?", isInScoringPosition);
  }

  @Override
  public void simulationPeriodic() {
    currentPose= cameraThingy.getEstimatedPose();
    double timeInSeconds= System.currentTimeMillis()/1000;
    trajectoryState= robotPath.sample(timeInSeconds);
    
    //Scan for coral using DigitalInput.
    //If coral found, grip it after 200ms.
    ///if (gripper.get() == false) {
      ///if (coralDetector.get() == true) {
        ///try {
            ///Thread.sleep(RobotConstants.kGripperDelayMilliseconds);
        ///} catch (InterruptedException e) {
            ///Thread.currentThread().interrupt();
        ///}
        ///gripper.set(true);
      

    SmartDashboard.putBoolean("Has Coral?", hasCoral);
    SmartDashboard.putBoolean("Is Gripped?", isGripped);
    SmartDashboard.putBoolean("In Scoring Position?", isInScoringPosition);
  }}