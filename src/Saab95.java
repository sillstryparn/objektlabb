import java.awt.*;

/**
 * Subclass to Car, always creates the same car, set amount of doors, power etc.
 */
public class Saab95 extends Car {
    /**
     * Unique variable for this subclass, used in speedFactor
     */
    private boolean turboOn;

    /**
     * Constructor
     */
    public Saab95() {
        super(125, Color.red, "Saab95", 2, 5);
        turboOn = false;
    }

    /**
     * Determines value of speedFactor, used in incrementSpeed and decrementSpeed
     *
     * @return speedFactor
     */
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

    /**
     * IncrementSpeed for saab, determines value of next currentSpeed
     *
     * @param amount Value between 0 and 1
     */
    protected void incrementSpeed(double amount) {
        double newCurrentSpeed = getCurrentSpeed() + speedFactor() * amount;
        setCurrentSpeed(getCheckedIncrementSpeed(newCurrentSpeed));
    }

    /**
     * DecrementSpeed for saab, determines value of next currentSpeed
     *
     * @param amount value between 0 and 1
     */
    protected void decrementSpeed(double amount) {
        double newCurrentSpeed = getCurrentSpeed() - speedFactor() * amount;
        setCurrentSpeed(getCheckedDecrementSpeed(newCurrentSpeed));
    }

    /**
     * Checks if turbo is enabled or disabled
     *
     * @return True or false, depending on state of turbo
     */
    public boolean isTurboOn() {
        return turboOn;
    }

    /**
     * Method for enabling turbo
     */
    public void setTurboOn() {
        turboOn = true;
    }

    /**
     * Method for disabling turbo
     */
    public void setTurboOff() {
        turboOn = false;
    }
}
