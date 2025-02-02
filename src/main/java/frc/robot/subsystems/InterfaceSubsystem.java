// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class InterfaceSubsystem extends SubsystemBase {
  /** Creates a new InferfaceSubsystem. */
  public InterfaceSubsystem() {}

  public SendableChooser<String> interfaceChooser;
  public SendableChooser<String> interfaceChooserLvl;
  public void interfaceChooser(){
    //Pos
    interfaceChooser = new SendableChooser<>();
    interfaceChooser.addOption("1", "1");
    interfaceChooser.addOption("2", "2");
    interfaceChooser.addOption("3", "3");
    interfaceChooser.addOption("4", "4");
    interfaceChooser.addOption("5", "5");
    interfaceChooser.addOption("6", "6");
    interfaceChooser.addOption("7", "7");
    interfaceChooser.addOption("8", "8");
    interfaceChooser.addOption("9", "9");
    interfaceChooser.addOption("10", "10");
    interfaceChooser.addOption("11", "11");
    interfaceChooser.addOption("12", "12");
    interfaceChooser.setDefaultOption("1","1");
    //Level
    interfaceChooserLvl = new SendableChooser<>();
    interfaceChooserLvl.addOption("1", "1");
    interfaceChooserLvl.addOption("2", "2");
    interfaceChooserLvl.addOption("3", "3");
    interfaceChooserLvl.addOption("4", "4");
    interfaceChooserLvl.setDefaultOption("1","1"); 
    }

  public void goInterfaceLoc(){
    System.out.println("Pretend this went to location " + interfaceChooser.getSelected() + ", level " + interfaceChooserLvl.getSelected());
  }

  @Override
  public void periodic() {
    if (interfaceChooser == null){
      interfaceChooser();
    }
    else{
      SmartDashboard.putString("Reef Position", interfaceChooser.getSelected());
      SmartDashboard.putString("Reef Level", interfaceChooserLvl.getSelected());
      SmartDashboard.putData(interfaceChooser);
      SmartDashboard.putData(interfaceChooserLvl);
    }
    // This method will be called once per scheduler run
  }
}
