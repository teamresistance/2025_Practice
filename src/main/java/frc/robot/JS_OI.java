// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.JSConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**Class to configure possible joystick types and buttons. */
public class JS_OI {
    // Joysticks possible
    public Joystick leftDrvrJS = new Joystick(0);
    public Joystick rightDrvrJS = new Joystick(1);
    public Joystick coDriverJS = new Joystick(2);
    public Joystick neoJS = new Joystick(3);
    public Joystick kybd0JS = new Joystick(4);
    // public Joystick kybd1JS = new Joystick(5);
    // JoystickButtons
    public JoystickButton elevLowBtn;    //Request Low level when execute
    public JoystickButton elevMidBtn;    //Request Mid level when execute
    public JoystickButton elevHighBtn;   //Request High level when execute
    public JoystickButton elevExecBtn;   //Execute
    public JoystickButton armToggleBtn;  //Rotate arm to place in scoring/recieving position -OR-
    public JoystickButton flipScoreBtn;  //Spin & flip to be in score position
    public JoystickButton gripToggleBtn; //Grip/release coral
    //Variables
    private SendableChooser<String> chsr = new SendableChooser<String>();
    private final String[] chsrDesc = {"3-Joysticks", "Nintendo", "Keyboard"};
    // private static int jsConfig;
    private String prvJSAssign;
    private int defaultConfigJS;


    /**
     * Constructor to configure buttons for joystick type passed
     * 
     * @param js_Type 0=Normal 3 JS's, 1=Neopad, 2=Keyboard
     */
    public JS_OI(int js_Type) {
        // Configure the button trigger bindings
        defaultConfigJS = js_Type; //2; // 0=Normal 3 JS's, 1=Neopad, 2=Keyboard
        chooserInit();
        configJS();
    }

    /**Configure a new JS assignment */
    public void configJS() { // Configure JS controller assignments
        configClearAll();          //Clear exisitng jsConfig

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
        // configButtonBindings();
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
        elevExecBtn = new JoystickButton(kybd0JS, JSConstants.kElevExecKybd0Btn);
        elevLowBtn = new JoystickButton(kybd0JS, JSConstants.kElevLowKybd0Btn);
        elevMidBtn = new JoystickButton(kybd0JS, JSConstants.kElevMidKybd0Btn);
        elevHighBtn = new JoystickButton(kybd0JS, JSConstants.kElevHighKybd0Btn);
        armToggleBtn = new JoystickButton(kybd0JS, JSConstants.kArmTglKybd0Btn);
        gripToggleBtn = new JoystickButton(kybd0JS, JSConstants.kGripTglKybd0Btn);
        // armToggleBtn = new JoystickButton(kybd0JS, JSConstants.kArmTglKybd1Btn);
        // gripToggleBtn = new JoystickButton(kybd0JS, JSConstants.kGripTglKybd1Btn);
    }

    /**Configue all buttons defined to use the keyboard */
    private void configClearAll() {
        // Create a JSB for each level
        elevExecBtn = null;
        elevLowBtn = null;
        elevMidBtn = null;
        elevHighBtn = null;
        armToggleBtn = null;
        gripToggleBtn = null;
    }

    //---- Joystick controller chooser ----
    /** Setup the JS Chooser and place on Smartdashboard */
    private void chooserInit(){
        for(int i = 0; i < chsrDesc.length; i++){
            chsr.addOption(chsrDesc[i], chsrDesc[i]);
        }
        chsr.setDefaultOption(chsrDesc[defaultConfigJS], chsrDesc[defaultConfigJS]);    //Chg index to select chsrDesc[] for default
        SmartDashboard.putData("JS/Choice", chsr);
        ChooserUpd();   //Update the JS assignments
    }

    /**Check sdb to see if it has change and update if it has. */
    public boolean ChooserUpd() { // Chk for Joystick configuration
        // System.out.println("Prv JS Assn: " + prvJSAssign + " =? "+ chsr.getSelected());
        if (prvJSAssign != (chsr.getSelected() == null ? chsrDesc[0] : chsr.getSelected())) {
            prvJSAssign = chsr.getSelected();
            SmartDashboard.putString("JS/Choosen", chsr.getSelected());   //Put selected on sdb
            System.out.println("JS Chsn: " + chsr.getSelected());
            defaultConfigJS = chsrStr2Int(prvJSAssign);
            configJS();         //then assign new jsConfig
            return true;
        }
        return false;
    }

    /**
     * Get an int for a string array.
     * Need to convert to Enum,
     * @param str
     * @return
     */
    public int chsrStr2Int(String str){
        int i = 0;
        for( ; i < chsrDesc.length - 1; i++ ){
            if(prvJSAssign == chsrDesc[i]) break;
        }
        return i;
    }

}
