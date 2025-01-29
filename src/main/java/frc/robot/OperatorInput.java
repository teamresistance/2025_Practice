// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**Class to configure possible joystick types and buttons. */
public class OperatorInput {
    // Joysticks possible
    public static Joystick leftDrvrJS = new Joystick(0);
    public static Joystick rightDrvrJS = new Joystick(1);
    public static Joystick coDriverJS = new Joystick(2);
    public static Joystick neoJS = new Joystick(3);
    public static Joystick kybd1JS = new Joystick(4);
    public static Joystick kybd2JS = new Joystick(5);
    // JoystickButtons
    public JoystickButton lvl2Button;
    public JoystickButton lvl3Button;
    public JoystickButton lvl4Button;
    public JoystickButton selectBranchAndAddButton;

    /**
     * Constructor to configure buttons for joystick type passed
     * 
     * @param js_Type 0=Normal 3 JS's, 1=Neopad, 2=Keyboard
     */
    public OperatorInput (int js_Type) {
        // Configure the button trigger bindings
        int defaultConfigJS = js_Type; //2; // 0=Normal 3 JS's, 1=Neopad, 2=Keyboard
        switch (defaultConfigJS) {
            case 0:
                config3Joysticks();
                break;
            case 1:
                configNeo();
                break;
            case 2:
                configKybd();
                break;
            default:
                config3Joysticks();
                System.out.println("Bad joystick configuration: " + defaultConfigJS);
        }
    }

    /**Configue all buttons defined for 3 JS's */
    private void config3Joysticks() {
        // Create a JSB for each level
        lvl2Button = new JoystickButton(coDriverJS, OperatorConstants.klvl2Button_3Joysticks_ID);
        lvl3Button = new JoystickButton(coDriverJS, OperatorConstants.klvl3Button_3Joysticks_ID);
        lvl4Button = new JoystickButton(coDriverJS, OperatorConstants.klvl4Button_3Joysticks_ID);
        selectBranchAndAddButton = new JoystickButton(coDriverJS, OperatorConstants.selectBranchAndAddButton_3Joysticks_ID);
    }

    /**Configue all buttons defined for a Niteno Pad */
    private void configNeo() {
        // Create a JSB for each level
        lvl2Button = new JoystickButton(neoJS, OperatorConstants.klvl2Button_Neo_ID);
        lvl3Button = new JoystickButton(neoJS, OperatorConstants.klvl3Button_Neo_ID);
        lvl4Button = new JoystickButton(neoJS, OperatorConstants.klvl4Button_Neo_ID);
        selectBranchAndAddButton = new JoystickButton(neoJS, OperatorConstants.selectBranchAndAddButton_Neo_ID);
    }

    /**Configue all buttons defined to use the keyboard */
    private void configKybd() {
        // Create a JSB for each level
        lvl2Button = new JoystickButton(kybd1JS, OperatorConstants.klvl2Button_Kybd1_ID);
        lvl3Button = new JoystickButton(kybd1JS, OperatorConstants.selectBranchAndAddButton_Kybd1_ID);
        lvl4Button = new JoystickButton(kybd1JS, OperatorConstants.klvl4Button_Kybd1_ID);
        selectBranchAndAddButton = new JoystickButton(kybd1JS, OperatorConstants.klvl3Button_Kybd1_ID);
    }
}

