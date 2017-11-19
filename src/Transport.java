import java.awt.*;

/**
 * <h1>Transport</h1>
 * Subclass to vehicle, capable of loading cars (excluding Scania) onto its ramp and moving them around.
 * Has 2 subclasses, Ferry and Trailer.
 */

public abstract class Transport extends Vehicle {

    /**
     * Unique variable to transport and its subclasses for checking if ramp is up or down, enabling other methods.
     */
    public boolean isRampUp;
    /**
     * Unique variable to transport and its subclasses, used for limiting amount of cars on one transport.
     */
    private double loadingCapacity;

    /**
     * VEHICLE_IS_MOVING - String for telling users that loading wont work while currentSpeed > 0
     * RAMP_IS_DOWN - String for telling users transport cant move while isRampUp = false
     * RAMP_IS_UP - String for telling users transport cant load cars while isRampUp = true
     */
    final static String VEHICLE_IS_MOVING = "Can't move loading platform while the vehicle is moving.";
    final static String  RAMP_IS_DOWN = "Can't move transport while the ramp is down.";
    final static String RAMP_IS_UP = "Can't load or unload while the ramp is up.";

    /**
     * Transport constructor
     *
     * @param enginePower     Transport's engine power
     * @param color           Transport's color
     * @param modelName       Transport's model name
     * @param loadingCapacity Transport's loading capacity
     */
    public Transport(double enginePower, Color color, String modelName, double loadingCapacity) {
        super(enginePower, color, modelName, true);
        this.loadingCapacity = loadingCapacity;
    }

    /**
     * Abstract method for adding car into transport
     * @param car current car
     */
    public abstract void addCar(Car car);

    /**
     * Abstract method for removing car from transport
     * @return car being removed
     */
    public abstract Car removeCar();

    /**
     * Abstract method for checking if car can be loaded, scania returns false
     * @param car current car
     * @return true for smaller cars, false for trucks
     */
    public abstract boolean isValidCarType(Car car);

    /**
     * Abstract method for moving car with its transport and both keeping same positions.
     */
    public abstract void moveCarWithTransport();

    /**
     * Specific move method for transports, needs true statement from isRampUp to execute.
     * Moves both the transport and the cars loaded inside it.
     * @throws IllegalArgumentException Thrown when isRampUp returns false
     */
    @Override
    public void move() {
        if (!isRampUp){
            throw new IllegalArgumentException("Can't move while ramp is down.");
        }
        super.move();
        moveCarWithTransport();
    }

    /**
     * Override method for gas, stops transport from moving if ramp is down.
     * @param amount Value between 0 and 1
     * @throws IllegalArgumentException Thrown when isRampUp = false
     */
    @Override
    public void gas(double amount) throws IllegalArgumentException {
        if (isRampUp)
        super.gas(amount);
        else throw new IllegalArgumentException(RAMP_IS_DOWN);
    }

    /**
     * Method for loading car into transport and updating loadCapacity
     * @param car current car
     */
    public void loadCar(Car car) {
        if (!isRampUp && isNearRamp(car) && !isEngineStarted() && isValidCarType(car) && !car.isLoaded()) {
            if (getLoadingCapacity() < car.getCarLength()) {
                throw new IllegalArgumentException("The trailer is full.");
            } else {
                addCar(car);
                setLoadingCapacity(getLoadingCapacity() - car.getCarLength());
                car.setValidKeyInserted(false);
                car.setLoaded(true);
            }
        }
    }

    /**
     * Method for unloading car and updating loadingCapacity
     * @return car being unloaded
     */
    public Car unloadCar() {
        if (!isEngineStarted() && !isRampUp) {
            Car car = removeCar();
            setLoadingCapacity(getLoadingCapacity() + car.getCarLength());
            car.setValidKeyInserted(true);
            unloadingDirection(car);
            car.setLoaded(false);
            return car;
        }
        else {
            throw new IllegalArgumentException(RAMP_IS_UP);
        }
    }

    /**
     * Method for raising ramp of a Transport, only executes when currentSpeed = 0
     * @throws IllegalArgumentException Thrown when currentSpeed is not 0
     */
    public void raiseRamp() {
        if (getCurrentSpeed() == 0) {
            isRampUp = true;
        } else {
            throw new IllegalArgumentException(VEHICLE_IS_MOVING);
        }
    }

    /**
     * Method for lowering ramp of a Transport, only executes when currentSpeed = 0
     * @throws IllegalArgumentException Thrown when currentSpeed is not 0
     */
    public void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            isRampUp = false;
        } else {
            throw new IllegalArgumentException(VEHICLE_IS_MOVING);
        }
    }

    /**
     * Boolean case for checking if car is near ramp, allowing it to be loaded into transport
     * @param car current car
     * @return true if difference between car and transports x and y directions <= 20, else false
     */
    private boolean isNearRamp(Car car) {
        double deltaX = car.getxPosition() - getxPosition();
        double deltaY = car.getyPosition() - getyPosition();

        if (Math.abs(deltaX) <= 20 && Math.abs(deltaY) <= 20) {
            return true;
        }
        return false;
    }

    /**
     * Method for determining positioning of car when it gets unloaded from transport.
     * @param car current car
     */
    private void unloadingDirection(Car car) {
        switch (getDirection()) {
            case UP:
                car.setyPosition(getyPosition() - 5);
            case DOWN:
                car.setyPosition(getyPosition() + 5);
            case RIGHT:
                car.setxPosition(getxPosition() - 5);
            case LEFT:
                car.setxPosition(getxPosition() + 5);
        }
    }

    /**
     * Returns loadingCapacity of transport
     * @return value of loadingCapacity
     */
    public double getLoadingCapacity() {
        return loadingCapacity;
    }

    /**
     * Sets value transports loadingCapacity
     * @param loadingCapacity value of new loadingCapacity
     */
    private void setLoadingCapacity(double loadingCapacity) {
        this.loadingCapacity = loadingCapacity;
    }
}
