package Assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void getType() {
        Car car = new Car("West", 1, 0, 0);
        car.intiDir();
        assertEquals("Car", car.getType());
        assertEquals(-2, car.getXpos());
    }

}