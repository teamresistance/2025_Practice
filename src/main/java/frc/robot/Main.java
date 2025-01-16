// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


//   _____ ___  __  __ __   ___ ___  __  _   __ _____ __  __  _  ______   ___  ___  
//  |_   _| __|/  \|  V  | | _ \ __/' _/| |/' _/_   _/  \|  \| |/ _/ __| / _ \/ __| 
//    | | | _|| /\ | \_/ | | v / _|`._`.| |`._`. | || /\ | | ' | \_| _|  ) _ ( ,_ \ 
//    |_| |___|_||_|_| |_| |_|_\___|___/|_||___/ |_||_||_|_|\__|\__/___| \___/\___/ 

//  ooo        ooooo            o8o              
//  `88.       .888'            `"'              
//   888b     d'888   .oooo.   oooo  ooo. .oo.   
//   8 Y88. .P  888  `P  )88b  `888  `888P"Y88b  
//   8  `888'   888   .oP"888   888   888   888  
//   8    Y     888  d8(  888   888   888   888  
//  o8o        o888o `Y888""8o o888o o888o o888o 

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * Do NOT add any static variables to this class, or any initialization at all. Unless you know what
 * you are doing, do not modify this file except to change the parameter class to the startRobot
 * call.
 */
public final class Main {
  private Main() {}

  /**
   * Main initialization function. Do not perform any initialization here.
   *
   * <p>If you change your main robot class, change the parameter type.
   */
  public static void main(String... args) {
    RobotBase.startRobot(Robot::new);
  }
}
