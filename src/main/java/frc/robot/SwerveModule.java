package frc.robot;

public class SwerveModule {
    int driveMotor_ID;
    int turningMotor_ID;
    int turningEncoder_ID;
    public SwerveModule(int driveMotorID, int turningMotorID, int turningEncoderID) { 
        driveMotor_ID= driveMotorID; 
        turningMotor_ID = turningMotorID;
        turningEncoder_ID = turningEncoderID;    
    }
}
