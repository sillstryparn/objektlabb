import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * <h1>Trailer</h1>
 * Subclass to transport, a trailer able to load and unload cars from its ramp and move them around.
 */

public class Trailer extends Transport {

    /**
     *Stack containing cars loaded unto Trailer.
     */
    Deque<Car> carStack = new ArrayDeque<>();

    /**
     * Constructor for Trailer
     */
    public Trailer() {
        super(300, Color.black, "Volvo Transporter", 25);
    }

    /**
     * Adds car into Trailer's carStack
     * @param car current car
     */
    @Override
    public void addCar(Car car) {
        carStack.push(car);
    }

    /**
     * Removes top (last) car from Trailer's carStack if not empty
     * @return car being removed, if no cars inside stack returns null
     */
    @Override
    public Car removeCar() {
        if (!carStack.isEmpty()) {
            return carStack.pop();
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
        if (car instanceof Volvo240 || car instanceof Saab95) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method for moving car with Trailer, managing cars in trailer's carStack.
     */
    @Override
    public void moveCarWithTransport() {
        List<Car> tempCarList = new ArrayList<>();
        while (!carStack.isEmpty()) {
            Car car = carStack.pop();
            car.setxPosition(getxPosition());
            car.setyPosition(getyPosition());
            tempCarList.add(car);
        }
        for (int i = tempCarList.size() - 1; i > 0; i--) {
            carStack.push(tempCarList.get(i));
        }
    }

    /**
     * incrementSpeed method specific to trailer, determines value of next currentSpeed.
     * @param amount value between 0 and 1
     */
    @Override
    protected void incrementSpeed(double amount) {
        double newCurrentSpeed = getCurrentSpeed() + speedFactor() * amount;
        setCurrentSpeed(getCheckedIncrementSpeed(newCurrentSpeed));
    }

    /**
     * decrementSpeed method specific to trailer, determines value of next currentSpeed.
     * @param amount value between 0 and 1
     */
    @Override
    protected void decrementSpeed(double amount) {
        double newCurrentSpeed = getCurrentSpeed() - speedFactor() * amount;
        setCurrentSpeed(getCheckedDecrementSpeed(newCurrentSpeed));
    }

    /**
     * Speedfactor for Trailer
     *
     * @return 1% of Trailer's enginePower
     */
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }

}
