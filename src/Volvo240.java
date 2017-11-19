import java.awt.*;

/**
 * Subclass to Car, always creates the same car with set amount of doors, power etc.
 */
public class Volvo240 extends Car {
    /**
     * Unique variable to subclass, used in speedFactor
     */
    private final static double trimFactor = 1.25;

    /**
     * Constructor
     */
    public Volvo240() {
        super(100, Color.black, "Volvo240", 4, 4.8);
    }

    /**
     * Determines value of speedFactor which is used during incrementSpeed, decrementSpeed
     *
     * @return speedFactor
     */
    public double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }

    /**
     * IncrementSpeed for volvo, uses Math.min to determine which value of currentSpeed to use for method in superclass
     *
     * @param amount Value between 0 and 1
     */
    protected void incrementSpeed(double amount) {
        double newCurrentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
        setCurrentSpeed(getCheckedIncrementSpeed(newCurrentSpeed));
    }

    /**
     * DecrementSpeed for volvo, uses Math.max to determine which value of currentSpeed to use for method in superclass
     *
     * @param amount value between 0 and 1
     */
    protected void decrementSpeed(double amount) {
        double newCurrentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        setCurrentSpeed(getCheckedDecrementSpeed(newCurrentSpeed));
    }


}
