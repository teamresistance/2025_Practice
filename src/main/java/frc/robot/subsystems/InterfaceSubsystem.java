// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;
import java.util.ArrayList;

public class InterfaceSubsystem extends SubsystemBase {
  public SendableChooser<String> reefBranchChooser = new SendableChooser<String>();
  public SendableChooser<String> reefLevelChooser = new SendableChooser<String>();
  public ArrayList<String[]> reefBranchCombinations = new ArrayList<>();

  /** Creates a new ExampleSubsystem. */
  public InterfaceSubsystem() {
    reefBranchChooserInitialize();
    reefLevelChooserInitialize();
  }

  public void reefBranchChooserInitialize() {
    reefBranchChooser.addOption("A", "A");
    reefBranchChooser.addOption("B", "B");
    reefBranchChooser.addOption("C", "C");
    reefBranchChooser.addOption("D", "D");
    reefBranchChooser.addOption("E", "E");
    reefBranchChooser.addOption("F", "F");
    reefBranchChooser.addOption("G", "G");
    reefBranchChooser.addOption("H", "H");
    reefBranchChooser.addOption("I", "I");
    reefBranchChooser.addOption("J", "J");
    reefBranchChooser.addOption("K", "K");
    reefBranchChooser.addOption("L", "L");
    reefBranchChooser.setDefaultOption("A", "A");
    SmartDashboard.putData("Reef Branch Chooser", reefBranchChooser);
  }

  public void reefLevelChooserInitialize() {
    reefLevelChooser.addOption("1", "1");
    reefLevelChooser.addOption("2", "2");
    reefLevelChooser.addOption("3", "3");
    reefLevelChooser.addOption("4", "4");
    reefLevelChooser.setDefaultOption("1", "1");
    SmartDashboard.putData("Reef Level Chooser", reefLevelChooser);
  }

  @Override
  public void periodic() {
    SmartDashboard.putData("Reef Branch Chooser", reefBranchChooser);
    SmartDashboard.putData("Reef Level Chooser", reefLevelChooser);
  }

  @Override
  public void simulationPeriodic() {
    SmartDashboard.putData("Reef Branch Chooser", reefBranchChooser);
    SmartDashboard.putData("Reef Level Chooser", reefLevelChooser);
  }
}
