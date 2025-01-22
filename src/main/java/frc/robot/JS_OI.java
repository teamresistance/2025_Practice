// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.JSConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**Class to configure possible joystick types and buttons. */
public class JS_OI {
    // Joysticks possible
    public static Joystick leftDrvrJS = new Joystick(0);
    public static Joystick rightDrvrJS = new Joystick(1);
    public static Joystick coDriverJS = new Joystick(2);
    public static Joystick neoJS = new Joystick(3);
    public static Joystick kybd1JS = new Joystick(4);
    public static Joystick kybd2JS = new Joystick(5);
    // JoystickButtons
    public JoystickButton elevLowBtn;    //Request Low level when execute
    public JoystickButton elevMidBtn;    //Request Mid level when execute
    public JoystickButton elevHighBtn;   //Request High level when execute
    public JoystickButton elevExecBtn;   //Execute
    public JoystickButton armToggleBtn;  //Rotate arm to place in scoring/recieving position -OR-
    public JoystickButton flipScoreBtn;  //Spin & flip to be in score position
    public JoystickButton gripToggleBtn; //Grip/release coral

    /**
     * Constructor to configure buttons for joystick type passed
     * 
     * @param js_Type 0=Normal 3 JS's, 1=Neopad, 2=Keyboard
     */
    public JS_OI(int js_Type) {
        // Configure the button trigger bindings
        int defaultConfigJS = js_Type; //2; // 0=Normal 3 JS's, 1=Neopad, 2=Keyboard
        switch (defaultConfigJS) {
            case 0:
                config3JS();
                break;
            case 1:
                configNeo();
                break;
            case 2:
                configKybd();
                break;
            default:
                config3JS();
                System.out.println("Bad joystick configuration: " + defaultConfigJS);
        }
    }

    /**Configue all buttons defined for 3 JS's */
    private void config3JS() {
        // Create a JSB for each level
        elevExecBtn = new JoystickButton(coDriverJS, JSConstants.kElevExec3JSBtn);
        elevLowBtn = new JoystickButton(coDriverJS, JSConstants.kElevHigh3JSBtn);
        elevMidBtn = new JoystickButton(coDriverJS, JSConstants.kElevMid3JSBtn);
        elevHighBtn = new JoystickButton(coDriverJS, JSConstants.kElevLow3JSBtn);
        armToggleBtn = new JoystickButton(coDriverJS, JSConstants.kArmTgl3JSBtn);
        gripToggleBtn = new JoystickButton(coDriverJS, JSConstants.kGripTgl3JSBtn);
    }

    /**Configue all buttons defined for a Niteno Pad */
    private void configNeo() {
        // Create a JSB for each level
        elevExecBtn = new JoystickButton(neoJS, JSConstants.kElevExecNeoBtn);
        elevLowBtn = new JoystickButton(neoJS, JSConstants.kElevHighNeoBtn);
        elevMidBtn = new JoystickButton(neoJS, JSConstants.kElevMidNeoBtn);
        elevHighBtn = new JoystickButton(neoJS, JSConstants.kElevLowNeoBtn);
        armToggleBtn = new JoystickButton(neoJS, JSConstants.kArmTglNeoBtn);
        gripToggleBtn = new JoystickButton(neoJS, JSConstants.kGripTglNeoBtn);
    }

    /**Configue all buttons defined to use the keyboard */
    private void configKybd() {
        // Create a JSB for each level
        elevExecBtn = new JoystickButton(kybd1JS, JSConstants.kElevExecKybd1Btn);
        elevLowBtn = new JoystickButton(kybd1JS, JSConstants.kElevLowKybd1Btn);
        elevMidBtn = new JoystickButton(kybd1JS, JSConstants.kElevMidKybd1Btn);
        elevHighBtn = new JoystickButton(kybd1JS, JSConstants.kElevHighKybd1Btn);
        armToggleBtn = new JoystickButton(kybd2JS, JSConstants.kArmTglKybd2Btn);
        gripToggleBtn = new JoystickButton(kybd2JS, JSConstants.kGripTglKybd2Btn);
    }
}
