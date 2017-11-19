import java.awt.*;

/**
 * <h1>Car</h1>
 * Subclass to vehicle, includes length of cars used for transporting and number of doors.
 */

public abstract class Car extends Vehicle {

    /**
     * Specific variable to Car, containing amount of doors on car.
     */
    private int nrDoors; // Number of doors on the car
    /**
     * Boolean case for checking if car is loaded onto transport.
     */
    private boolean isLoaded = false;

    /**
     * Specific variable to car, containing length of car, used when being transported inside Transport.
     */
    private double carLength;

    /**
     * Constructor for car
     * @param enginePower Car's engine power
     * @param color       Car's color
     * @param modelName   Car's model name
     * @param nrDoors     Car's number of doors
     * @param carLength   Car's length
     */
    public Car(double enginePower, Color color, String modelName, int nrDoors, double carLength) {
        super(enginePower, color, modelName, true);
        this.nrDoors = nrDoors;
        this.carLength = carLength;
    }

    /**
     * Return number of doors on car
     *
     * @return Numbers of doors on car
     */
    public int getNrDoors() {
        return nrDoors;
    }

    /**
     * Returns length of car
     * @return length of car
     */
    public double getCarLength() {
        return carLength;
    }

    /**
     * Sets value of isLoaded
     * @param loaded isLoaded´s new value
     */
    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    /**
     * Checks value of isLoaded
     * @return value of isLoaded
     */
    public boolean isLoaded() {
        return isLoaded;
    }
}

