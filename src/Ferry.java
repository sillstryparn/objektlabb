import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *<h1>Ferry</h1>
 * Subclass to Transport, a ferry capable of loading cars onto its ramp and moving them around.
 */
public class Ferry extends Transport {

    /**
     * List of cars loaded onto Ferry's ramp
     */
    List<Car> carList = new ArrayList<>();

    /**
     * Constructor for ferry
     */
    public Ferry() {
        super(1000, Color.white, "Ferry", 100);
    }

    /**
     * incrementSpeed method specific to ferry, determines value of next currentSpeed.
     * @param amount value between 0 and 1
     */
    @Override
    protected void incrementSpeed(double amount) {
        double newCurrentSpeed = getCurrentSpeed() + speedFactor() * amount;
        setCurrentSpeed(getCheckedIncrementSpeed(newCurrentSpeed));
    }

    /**
     * decrementSpeed method specific to ferry, determines value of next currentSpeed.
     * @param amount value between 0 and 1
     */
    @Override
    protected void decrementSpeed(double amount) {
        double newCurrentSpeed = getCurrentSpeed() - speedFactor() * amount;
        setCurrentSpeed(getCheckedDecrementSpeed(newCurrentSpeed));
    }

    /**
     * Adds car into Ferry's arrayList
     * @param car current car
     */
    @Override
    public void addCar(Car car) {
        carList.add(car);
    }

    /**
     * Removes car for Ferry's arrayList if it is not empty.
     * @return car being removed, if no cars inside list returns null
     */
    @Override
    public Car removeCar() {
        if (!carList.isEmpty()) {
            return carList.get(0);
        } else {
            return null;
        }
    }

    /**
     * Checks if car is of valid type for loading
     * @param car current car
     * @return true for smaller cars, false for trucks
     */
    @Override
    public boolean isValidCarType(Car car) {
        return true;
    }

    /**
     * Method for moving car with Ferry, managing cars in ferry's arrayList.
     */
    @Override
    public void moveCarWithTransport() {
        for (Car c : carList){
            c.setxPosition(getxPosition());
            c.setyPosition(getyPosition());
        }
    }

    /**
     * Speedfactor for Ferry
     *
     * @return 1% of Ferry's enginePower
     */
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }

}
