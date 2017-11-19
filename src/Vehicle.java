import java.awt.*;

/**
 * <h1>Vehicle</h1>
 * The abstract Vehicle class is the superclass for the whole project, handling subclasses such as Transport and Car.
 * It handles the various identical variables and methods from both sides, shortening the code
 * and making it easier to implement a bigger range of car/transport models etc.
 * <p>
 *
 * @author Albin, Oscar
 * @version 1.1
 * @since 2017-11-02
 */

public abstract class Vehicle implements Movable {
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private double xPosition; // The position of the Vehicle pertaining to x
    private double yPosition; // The position of the Vehicle pertaining to y
    private Direction direction = Direction.UP; // Direction, of Vehicle, default UP
    final static String OUTSIDE_RANGE = "Gas and brake value must be between 0-1.";
    private final static String KEY_NOT_INSERTED = "The vehicle's key is not inserted.";
    private boolean validKeyInserted; // Boolean case for whether or not key is inserted into car
    private boolean engineStarted;  // Boolean case for checking if engine is turned on.

    /**
     * Enum that tells direction of car
     */
    public enum Direction {
        UP, DOWN, RIGHT, LEFT
    }

    /**
     * Vehicle constructor
     *
     * @param enginePower Vehicle's engine power
     * @param color       Vehicle's color
     * @param modelName   Vehicle's model name
     */
    public Vehicle(double enginePower, Color color, String modelName, boolean validKeyInserted) {
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.validKeyInserted = validKeyInserted;
    }

    /**
     * Abstract method for incrementing currentSpeed of Vehicle
     *
     * @param amount value between 0 and 1
     */
    protected abstract void incrementSpeed(double amount);

    /**
     * Abstract method for decrementing currentSpeed of Vehicle
     *
     * @param amount value between 0 and 1
     */

    protected abstract void decrementSpeed(double amount);


    /**
     * Method for moving a Vehicle depending on its direction, stationed inside the Movable interface.
     */
    @Override
    public void move() {
        switch (direction) {
            case UP:
                yPosition += currentSpeed;
                break;
            case DOWN:
                yPosition -= currentSpeed;
                break;
            case RIGHT:
                xPosition += currentSpeed;
                break;
            case LEFT:
                xPosition -= currentSpeed;
                break;
        }
    }

    /**
     * Method for turning Vehicles current direction to the right, its super stationed inside the Movable interface
     */
    @Override
    public void turnRight() {
        switch (direction) {
            case UP:
                direction = Direction.RIGHT;
                break;
            case DOWN:
                direction = Direction.LEFT;
                break;
            case RIGHT:
                direction = Direction.DOWN;
                break;
            case LEFT:
                direction = Direction.UP;
                break;
        }
    }

    /**
     * Method for turning Vehicles current direction to the left, its super stationed inside the Movable interface
     */
    @Override
    public void turnLeft() {
        switch (direction) {
            case UP:
                direction = Direction.LEFT;
                break;
            case DOWN:
                direction = Direction.RIGHT;
                break;
            case RIGHT:
                direction = Direction.UP;
                break;
            case LEFT:
                direction = Direction.DOWN;
                break;
        }
    }

    /**
     * Starts car engine if key is inserted, setting car's currentSpeed to 0.1 and engineStarted to true
     * @throws IllegalArgumentException Thrown when key is not inserted into car
     */

    public void startEngine() {
        if (validKeyInserted) {
            currentSpeed = 0.1;
            engineStarted = true;
        } else {
            throw new IllegalArgumentException(KEY_NOT_INSERTED);
        }
    }

    /**
     * Stops car's engine, setting currentSpeed to 0, also validKeyInserted and engineStarted to false
     */

    public void stopEngine() {
        currentSpeed = 0;
        validKeyInserted = false;
        engineStarted = false;
    }

    /**
     * Increases speed of car
     *
     * @param amount Value between 0 and 1
     * @throws IllegalArgumentException Thrown when amount is out of the interval
     * @throws IllegalArgumentException Thrown when validKeyInserted is false
     */
    public void gas(double amount) throws IllegalArgumentException {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException(OUTSIDE_RANGE);
        } else if (validKeyInserted) {
            incrementSpeed(amount);
        } else {
            throw new IllegalArgumentException(KEY_NOT_INSERTED);
        }
    }


    /**
     * Decreases speed of car
     *
     * @param amount Value between 0 and 1
     * @throws IllegalArgumentException Thrown when amount is out of the interval
     */
    public void brake(double amount) throws IllegalArgumentException {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException(OUTSIDE_RANGE);
        }
        decrementSpeed(amount);
    }

    /**
     * Checks value of engineStarted
     * @return case of engineStarted
     */
    public boolean isEngineStarted() {
        return engineStarted;
    }

    /**
     * Sets value of validKeyInserted
     * @param validKeyInserted true or false
     */
    public void setValidKeyInserted(boolean validKeyInserted) {
        this.validKeyInserted = validKeyInserted;
    }

    /**
     * Return car's enginePower
     *
     * @return Vehicle's enginePower
     */
    public double getEnginePower() {
        return enginePower;
    }

    /**
     * Returns the color of the car
     *
     * @return The color of the car
     */
    public Color getColor() {
        return color;
    }

    /**
     * Return the modelname of the car
     *
     * @return The modelname of the Vehicle
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Returns car's x-coordinate
     *
     * @return Vehicle's x-coordinate
     */
    public double getxPosition() {
        return xPosition;
    }

    /**
     * Returns car's y-coordinate
     *
     * @return Vehicle's y-coordinate
     */

    public double getyPosition() {
        return yPosition;
    }

    /**
     * Set vehicles x-position
     *
     * @param xPosition Vehicles new x-position
     */
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Set vehicles y-position
     *
     * @param yPosition Vehicles new y-position
     */
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * Returns current speed of car
     *
     * @return Current speed of car
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * Sets car's currentSpeed
     *
     * @param currentSpeed Set cars currentSpeed
     */

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
        if (currentSpeed > enginePower) {
            this.currentSpeed = enginePower;
        } else if (currentSpeed < 0) {
            this.currentSpeed = 0;
        }
    }

    /**
     * Returns the direction of the car
     *
     * @return Vehicle's direction
     */

    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets direction of the car
     *
     * @param direction Set direction of car
     */

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Makes sure currentSpeed does not exceed enginePower and that it cant decrease currentSpeed
     *
     * @param newSpeed Temporary variable, used if speed is increased
     * @return currentSpeed, set to value of enginePower if currentSpeed exceeds enginePower, otherwise set to newSpeed
     */
    public double getCheckedIncrementSpeed(double newSpeed) {
        if (currentSpeed > enginePower) {
            currentSpeed = enginePower;
        } else if (newSpeed > currentSpeed) {
            currentSpeed = newSpeed;
        }
        return currentSpeed;
    }

    /**
     * Makes sure currentSpeed does not go below 0 and that it cant increase currentSpeed
     *
     * @param newSpeed Temporary variable, used if speed is decreased
     * @return currentSpeed, set to 0 if currentSpeed comes below 0, otherwise set to newSpeed
     */
    public double getCheckedDecrementSpeed(double newSpeed) {
        if (currentSpeed < 0) {
            currentSpeed = 0;
        } else if (newSpeed < currentSpeed) {
            currentSpeed = newSpeed;
        }
        return currentSpeed;
    }
}
