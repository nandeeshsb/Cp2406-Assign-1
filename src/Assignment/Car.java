package Assignment;

public class Car extends Vehicle {
    /*Car vehicle type  */
    public Car(String direction, int speed, int x, int y) {
        super(direction, speed, x, y);
        setLength(getCarlength());
        setType("Car");
    }

    public String getType() {
        return "Car";
    }
}
