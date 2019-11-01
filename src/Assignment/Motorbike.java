package Assignment;

public class Motorbike extends Vehicle {
    /*Motorbike vehicle type*/
    public Motorbike(String direction, int speed, int x, int y) {
        super(direction, speed, x, y);
        setLength(getCarlength() / 2);
        setType("Motorbike");
    }

    public String getType() {
        return "Motorbike";
    }
}
