package frc.robot.subsystems;

// Command based imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Logging and Testing Imports
import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Hardware imports
import frc.robot.Constants.RobotConstants;
import frc.robot.Constants.HardwareConstants;
import frc.robot.Constants.HardwareConstants.LedMode;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LedSubsystem extends SubsystemBase {
    // Variables

    /**
    * An LED strip with length as specified by {@code RobotConstants.kLedLength}
    */
    AddressableLED ledstrip = new AddressableLED(HardwareConstants.kLed_portNumber);
    
    /**
    * The length of the LED strip
    */
    int length = RobotConstants.kLedLength;

    // These variables are necessary for the strobe animation to function.

    int animationFrame = 0;

    /**
    * The amount of time to  advance to the next strobe animation frame.
    * Can only be 20ms or greater multiples of 20ms.
    * A higher value means a slower animation.
    */
    int animationDelay = RobotConstants.kLedAnimationDelayMilliseconds;
    
    int delayTracker = 0;

    /**
    * An enum value specifying how the LED lights up.
    */
    public LedMode mode = HardwareConstants.LedMode.kOFF;

    public LedSubsystem() {
        ledstrip.setLength(length);
        ledstrip.start();
    }

    /**
    * This method sets all LEDs to an RGB value specified by the {@code int[] color}.
    * This array MUST be of length 3.
    * @param color An array starting the intended color in RGB format.
    * @return Void
    */
    public void setLEDColor(int[] color) {
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(length);
        for (var i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, color[0], color[1], color[2]);
        }
        ledstrip.setData(buffer);
    }
    
    /**
    * Creates an animated gradient effect between any number of distinct colors.
    * @param colors a bunch of int[]s of length 3, each a color in RGB format.
    * @return void
    */
    public void strobeBetween(int frame, int[]... colors) {
        int cycleLength = length / (colors.length - 1); // Avoid out-of-bounds
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(length);

        for (int i = 0; i < length; i++) {
            int shiftedIndex = (i + frame) % length; // Shift the gradient by 'frame' Leds

            int colorsBetween = shiftedIndex / cycleLength;
            int nextColor = Math.min(colorsBetween + 1, colors.length - 1); // Prevent out-of-bounds

            double ratio = (shiftedIndex % cycleLength) / (double) cycleLength; // Use double for smooth interpolation

            int red = (int) (colors[colorsBetween][0] * (1 - ratio) + colors[nextColor][0] * ratio);
            int grn = (int) (colors[colorsBetween][1] * (1 - ratio) + colors[nextColor][1] * ratio);
            int blu = (int) (colors[colorsBetween][2] * (1 - ratio) + colors[nextColor][2] * ratio);

            buffer.setRGB(i, red, grn, blu);
        }
    }
    
    /**
    * Turns off the led strip.
    */
    public void turnOff() {
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(length);
        for (var i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, 0, 0, 0);
        }
        ledstrip.setData(buffer);
    }

    /**
    *Changes LEDSubsystem.mode.
    * @param changeTo The intended mode to change to. Can only be kSOLID, kSTROBE, or kOFF.
    */
    public void setMode(LedMode changeTo) {
        mode = changeTo;
    }

    @Override
    public void periodic() {
        // Update animation frame
        delayTracker++;

        if (delayTracker * 20 >= animationDelay) {
            animationFrame = (animationFrame + 1) % length;
            delayTracker = 0;
        }

        // Color the LED strip according to the mode.
        switch (mode) {
            case kSOLID:
                setLEDColor(RobotConstants.kLedSolidColor);
                break;
            case kSTROBE:
                strobeBetween(animationFrame,
                        RobotConstants.kLedStrobeColor1,
                        RobotConstants.kLedStrobeColor2,
                        RobotConstants.kLedStrobeColor3);
                break;
            case kOFF:
                turnOff();
                break;
        }

        // Reporting
        SmartDashboard.putString("LED Mode", mode.toString());

        // Logging
        Logger.recordOutput("LED/Mode", mode.toString());

    }

    @Override
    public void simulationPeriodic() {

    }
}
