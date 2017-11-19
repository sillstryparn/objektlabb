/**
 * <h1>Movable</h1>
 * Interface used for enabling all Vehicles to move around, specified in Vehicle.
 */
public interface Movable {
    /**
     * Moves car with currentSpeed in x- or y-direction depending on its current direction
     */
    void move();

    /**
     * Turn car 90 degrees right
     */
    void turnRight();

    /**
     * Turn car 90 degrees left
     */
    void turnLeft();
}
