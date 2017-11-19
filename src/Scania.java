import java.awt.*;

/**
 * <h1>Scania</h1>
 * Subclass to car, includes a ramp which can be raised and lowered between 0 and 70 degrees.
 */
public class Scania extends Car {

    /**
     * Unique variable for Scania, contains which degree ramp is positioned in.
     */
    private double rampDegree = 0;

    /**
     * Constructor
     */
    public Scania() {
        super(200, Color.red, "Scania", 2, 12);
    }

    /**
     * Method to raise Scania's ramp
     *
     * @param amount Degree to raise the ramp with
     */
    public void raiseRamp(double amount) {
        rampDegree += amount;
        if (rampDegree > 70) {
            rampDegree = 70;
        }
    }

    /**
     * Method to lower Scania's ramp
     *
     * @param amount Degree to lower the ramp with
     */
    public void lowerRamp(double amount) {
        rampDegree -= amount;
        if (rampDegree < 0) {
            rampDegree = 0;
        }
    }

    /**
     * Speedfactor for Scania
     *
     * @return 1% of Scania's enginepower
     */
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }

    /**
     * IncrementSpeed for Scania, determines value of next currentSpeed
     *
     * @param amount Value between 0 and 1
     */
    @Override
    protected void incrementSpeed(double amount) {
        double newCurrentSpeed = getCurrentSpeed() + speedFactor() * amount;
        setCurrentSpeed(getCheckedIncrementSpeed(newCurrentSpeed));
    }

    /**
     * DecrementSpeed for Scania, determines value of next currentSpeed
     *
     * @param amount value between 0 and 1
     */
    @Override
    protected void decrementSpeed(double amount) {
        double newCurrentSpeed = getCurrentSpeed() - speedFactor() * amount;
        setCurrentSpeed(getCheckedDecrementSpeed(newCurrentSpeed));
    }

    /**
     * Get the current degree of Scania's ramp
     *
     * @return Scania's ramp degree
     */
    public double getRampDegree() {
        return rampDegree;
    }

    /**
     * Set a degree for Scania's ramp
     *
     * @param rampDegree Degree for the ramp
     */
    public void setRampDegree(double rampDegree) {
        this.rampDegree = rampDegree;
    }
}
