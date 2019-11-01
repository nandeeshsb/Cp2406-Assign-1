package Assignment;

public class Bus extends Vehicle {
    /*Bus vehicle type  */
    public Bus(String direction, int speed, int x, int y) {
        super(direction, speed, x, y);
        setLength(getCarlength() * 3);
        setType("Bus");
    }

    public String getType() {
        return "Bus";
    }
}