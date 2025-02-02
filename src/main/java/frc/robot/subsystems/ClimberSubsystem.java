package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.HdwConstants;
import frc.robot.Constants.RobotConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClimberSubsystem extends SubsystemBase{
    
    //Hardware
    private final Solenoid climberSV = new Solenoid(RobotConstants.kModType, HdwConstants.kClimberSVChnl);

    //Variables


    public ClimberSubsystem(){
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("Climber is Activated:", climberSV.get());
    }

    public void climberActivate(boolean state){
        climberSV.set(state);
        System.out.println("Climber is Activated: " + climberSV.get());
    }
    public void simulationPerioic(){
        //This method will be called once per scheduler run
    }
}
