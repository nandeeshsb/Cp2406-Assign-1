
package Assignment;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoadTest {

    @Test
    void testMove() {
        Road road = new Road(24, 10, "East", 0, 0);
        road.makeRoad();
        assertEquals(24, road.getXeast());
        road.addBus(4);
        road.moveVehicles();
        assertEquals(10, road.vehicles.get(0).getXpos());
        road.addCar(3);
        road.moveVehicles();
        road.moveVehicles();
        assertEquals(18, road.vehicles.get(0).getXpos());
        assertEquals(8, road.vehicles.get(1).getXpos());
    }

    @Test
    void testCollisions() {
        Road road = new Road(25, 10, "North", 0, 0);
        road.makeRoad();
        road.addCar(3);
        road.moveVehicles();
        road.moveVehicles();
        assertEquals(8, road.vehicles.get(0).getPos());
        road.addMotorbike(8);

        road.moveVehicles();
        assertEquals(11, road.vehicles.get(0).getPos());
        assertEquals(9, road.vehicles.get(1).getPos());
        road.moveVehicles();
        assertEquals(14, road.vehicles.get(0).getPos());
        assertEquals(12, road.vehicles.get(1).getPos());
        assertEquals(2, road.getNum_vehicles());

    }

    @Test
    void testonRoad() {
        Road road = new Road(25, 10, "North", 0, 0);
        road.makeRoad();
        road.addCar(10);
        road.moveVehicles();
        road.moveVehicles();
        road.moveVehicles();
        assertEquals(1, road.vehiclesOffRoad.size());

    }



    @Test
    void testrandomthings() {
        Road road = new Road(15, 10, "North", 0, 0);
        road.addCar(15);
        road.vehiclesOffRoad.add(road.vehicles.get(0));
        assertEquals(15, road.vehiclesOffRoad.get(0).getSpeed());
    }

}


