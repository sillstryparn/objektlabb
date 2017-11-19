import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * <h1>Tests</h1>
 * Various tests, checking every method and its usability.
 */
public class VehicleTest {
    @Test
    public void correctSpeedFactorVolvo() {
        Volvo240 car = new Volvo240();
        Assert.assertEquals(1.25, car.speedFactor(), 0.001);
    }

    @Test
    public void correctGasIncreaseVolvo() {
        Vehicle vehicle = new Volvo240();
        vehicle.gas(0.5);
        Assert.assertEquals(0.625, vehicle.getCurrentSpeed(), 0.001);
    }

    @Test
    public void correctBrakeDecreaseVolvo() {
        Vehicle vehicle = new Volvo240();
        vehicle.gas(0.5);
        vehicle.brake(0.25);
        Assert.assertEquals(0.3125, vehicle.getCurrentSpeed(), 0.0001);
    }

    @Test
    public void gasGreaterThan1Volvo() {
        Vehicle vehicle = new Volvo240();
        try {
            vehicle.gas(100);
            Assert.fail("Check doesn't work.");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals(Vehicle.OUTSIDE_RANGE, ex.getMessage());
        }
    }

    @Test
    public void gasLessThan0Volvo() {
        Vehicle vehicle = new Volvo240();
        try {
            vehicle.gas(-50);
            Assert.fail("Check doesn't work.");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals(Vehicle.OUTSIDE_RANGE, ex.getMessage());
        }
    }

    @Test
    public void brakeGreaterThan1Volvo() {
        Vehicle vehicle = new Volvo240();
        try {
            vehicle.brake(100);
            Assert.fail("Check doesn't work.");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals(Vehicle.OUTSIDE_RANGE, ex.getMessage());
        }
    }

    @Test
    public void brakeLessThan0Volvo() {
        Vehicle vehicle = new Volvo240();
        try {
            vehicle.brake(-50);
            Assert.fail("Check doesn't work.");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals(Vehicle.OUTSIDE_RANGE, ex.getMessage());
        }
    }

    @Test
    public void isTurboOnSaab() {
        Saab95 car = new Saab95();
        car.setTurboOn();
        Assert.assertTrue(car.isTurboOn());
    }

    @Test
    public void isTurboOffSaab() {
        Saab95 car = new Saab95();
        car.setTurboOff();
        Assert.assertFalse(car.isTurboOn());
    }

    @Test
    public void correctGasIncreaseSaab() {
        Vehicle vehicle = new Saab95();
        vehicle.gas(0.5);
        Assert.assertEquals(0.625, vehicle.getCurrentSpeed(), 0.001);
    }

    @Test
    public void correctBrakeDecreaseSaab() {
        Vehicle vehicle = new Saab95();
        vehicle.gas(0.5);
        vehicle.brake(0.25);
        Assert.assertEquals(0.3125, vehicle.getCurrentSpeed(), 0.0001);
    }

    @Test
    public void gasGreaterThan1Saab() throws Exception {
        Vehicle vehicle = new Saab95();
        try {
            vehicle.gas(100);
            throw new Exception("Check doesn't work.");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void gasLessThan0Saab() throws Exception {
        Vehicle vehicle = new Saab95();
        try {
            vehicle.gas(-50);
            throw new Exception("Check doesn't work.");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void turnLeft() {
        Vehicle vehicle = new Volvo240();
        vehicle.turnLeft();
        Assert.assertEquals(Vehicle.Direction.LEFT, vehicle.getDirection());
        vehicle.turnLeft();
        Assert.assertEquals(Vehicle.Direction.DOWN, vehicle.getDirection());
        vehicle.turnLeft();
        Assert.assertEquals(Vehicle.Direction.RIGHT, vehicle.getDirection());
        vehicle.turnLeft();
        Assert.assertEquals(Vehicle.Direction.UP, vehicle.getDirection());
    }

    @Test
    public void turnRight() {
        Vehicle vehicle = new Volvo240();
        vehicle.turnRight();
        Assert.assertEquals(Vehicle.Direction.RIGHT, vehicle.getDirection());
        vehicle.turnRight();
        Assert.assertEquals(Vehicle.Direction.DOWN, vehicle.getDirection());
        vehicle.turnRight();
        Assert.assertEquals(Vehicle.Direction.LEFT, vehicle.getDirection());
        vehicle.turnRight();
        Assert.assertEquals(Vehicle.Direction.UP, vehicle.getDirection());
    }

    @Test
    public void moveYPosition() {
        Vehicle vehicle = new Volvo240();
        vehicle.setCurrentSpeed(50);
        vehicle.move();
        Assert.assertEquals(50, vehicle.getyPosition(), 0.001);
        vehicle.setDirection(Vehicle.Direction.DOWN);
        vehicle.setCurrentSpeed(100);
        vehicle.move();
        Assert.assertEquals(-50, vehicle.getyPosition(), 0.001);
    }

    @Test
    public void moveXDirection() {
        Vehicle vehicle = new Volvo240();
        vehicle.setDirection(Vehicle.Direction.RIGHT);
        vehicle.setCurrentSpeed(25);
        vehicle.move();
        Assert.assertEquals(25, vehicle.getxPosition(), 0.001);
        vehicle.setDirection(Vehicle.Direction.LEFT);
        vehicle.setCurrentSpeed(50);
        vehicle.move();
        Assert.assertEquals(-25, vehicle.getxPosition(), 0.001);
    }

    @Test
    public void startAndStopEngine() {
        Vehicle vehicle = new Volvo240();
        vehicle.startEngine();
        Assert.assertEquals(0.1, vehicle.getCurrentSpeed(), 0.001);
        vehicle.stopEngine();
        Assert.assertEquals(0, vehicle.getCurrentSpeed(), 0.001);
    }

    @Test
    public void correctNumberOfDoors() {
        Car volvo = new Volvo240();
        Assert.assertEquals(4, volvo.getNrDoors());
        Car saab = new Saab95();
        Assert.assertEquals(2, saab.getNrDoors());
    }

    @Test
    public void correctColor() {
        Vehicle volvo = new Volvo240();
        Assert.assertEquals(Color.black, volvo.getColor());
        Vehicle saab = new Saab95();
        Assert.assertEquals(Color.red, saab.getColor());
    }

    @Test
    public void correctModelName() {
        Vehicle volvo = new Volvo240();
        Assert.assertEquals("Volvo240", volvo.getModelName());
        Vehicle saab = new Saab95();
        Assert.assertEquals("Saab95", saab.getModelName());
    }

    @Test
    public void moveScaniaRamp() {
        Scania scania = new Scania();
        scania.raiseRamp(50);
        Assert.assertEquals(50, scania.getRampDegree(), 0.001);
        scania.raiseRamp(30);
        Assert.assertEquals(70, scania.getRampDegree(), 0.001);
        scania.lowerRamp(40);
        Assert.assertEquals(30, scania.getRampDegree(), 0.001);
        scania.lowerRamp(70);
        Assert.assertEquals(0, scania.getRampDegree(), 0.001);
    }

    @Test
    public void moveWithTrailer() {
        Transport trailer = new Trailer();
        Car volvo = new Volvo240();

        trailer.lowerRamp();
        trailer.loadCar(volvo);
        trailer.raiseRamp();

        trailer.setCurrentSpeed(300);
        trailer.move();

        Assert.assertEquals(300, volvo.getyPosition(), 0.001);
    }

    @Test
    public void moveWithFerry() {
        Transport ferry = new Ferry();
        Car saab = new Saab95();

        ferry.lowerRamp();
        ferry.loadCar(saab);
        ferry.raiseRamp();

        ferry.setCurrentSpeed(300);
        ferry.move();

        Assert.assertEquals(300, saab.getyPosition(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void unloadWhileRampIsDownFerry() {
        Transport ferry = new Ferry();
        Car volvo = new Volvo240();
        ferry.loadCar(volvo);
        ferry.raiseRamp();
        ferry.unloadCar();
    }

    @Test(expected = IllegalArgumentException.class)
    public void unloadWhileRampIsDownTrailer() {
        Transport trailer = new Trailer();
        Car volvo = new Volvo240();
        trailer.loadCar(volvo);
        trailer.raiseRamp();
        trailer.unloadCar();
    }

}
