
package Assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoadMainTest {

    @Test
    void testnewRoads() {
        RoadMain roadMain = new RoadMain();

        roadMain.addnewRoad(20, 10, "North", 0, 0);
        roadMain.addConectingRoad(0, 20, "North");
        roadMain.addConectingRoad(0, 20, "East");
        roadMain.addConectingRoad(0, 20, "West");


        assertEquals(1, roadMain.roads.get(0).connectedRoads.get(0));
        assertEquals(2, roadMain.roads.get(0).connectedRoads.get(1));
        assertEquals(3, roadMain.roads.get(0).connectedRoads.get(2));
        roadMain.addConectingRoad(2, 20, "North");
        roadMain.addConectingRoad(2, 20, "South");

        assertEquals(4, roadMain.roads.get(2).connectedRoads.get(0));
        assertEquals(5, roadMain.roads.get(2).connectedRoads.get(1));


    }

    @Test
    void testTurns() {
        RoadMain roadMain = new RoadMain();

        roadMain.addnewRoad(25, 10, "North", 0, 0);
        roadMain.addConectingRoad(0, 25, "East");
        roadMain.addConectingRoad(0, 25, "North");
        assertEquals(1, roadMain.roads.get(0).connectedRoads.get(0));
        assertEquals(2, roadMain.roads.get(0).connectedRoads.get(1));
        roadMain.addVehicle("Car", 5, 0);
        roadMain.printPos();
        roadMain.moveVehicles();
        assertEquals(7, roadMain.roads.get(0).vehicles.get(0).getPos());
        roadMain.moveVehicles();
        assertEquals(12, roadMain.roads.get(0).vehicles.get(0).getPos());
        roadMain.moveVehicles();
        assertEquals(17, roadMain.roads.get(0).vehicles.get(0).getPos());
        roadMain.moveVehicles();
        assertEquals(22, roadMain.roads.get(0).vehicles.get(0).getPos());
        roadMain.moveVehicles();
        assertEquals(30, roadMain.roads.get(1).vehicles.get(0).getYpos());
        assertEquals(2, roadMain.roads.get(1).vehicles.get(0).getPos());
        roadMain.moveVehicles();
        assertEquals(30, roadMain.roads.get(1).vehicles.get(0).getYpos());
        assertEquals(7, roadMain.roads.get(1).vehicles.get(0).getPos());
        roadMain.moveVehicles();
        assertEquals(12, roadMain.roads.get(1).vehicles.get(0).getPos());
        roadMain.moveVehicles();
        roadMain.moveVehicles();
        assertEquals(22, roadMain.roads.get(1).vehicles.get(0).getPos());
        roadMain.moveVehicles();
        assertEquals(1, roadMain.roads.get(1).getNum_vehicles());
        roadMain.moveVehicles();


    }

    @Test
    void testIntersections() {
        RoadMain roadMain = new RoadMain();

        roadMain.addnewRoad(25, 10, "North", 0, 0);
        roadMain.addConectingRoad(0, 25, "East");
        roadMain.addConectingRoad(0, 25, "North");
        roadMain.addConectingRoad(0, 25, "West");
        assertEquals(1, roadMain.roads.get(0).connectedRoads.get(0));
        assertEquals(2, roadMain.roads.get(0).connectedRoads.get(1));
        assertEquals(3, roadMain.roads.get(0).connectedRoads.get(2));
        roadMain.addConectingRoad(2, 25, "North");
        roadMain.addConectingRoad(2, 25, "West");
        assertEquals(4, roadMain.roads.get(2).connectedRoads.get(0));
        assertEquals(5, roadMain.roads.get(2).connectedRoads.get(1));
        roadMain.addVehicle("Car", 5, 0);

        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.addVehicle("Bus", 7, 0);
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();

        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();

    }


    @Test
    void testTrafficLights() {
        RoadMain roadMain = new RoadMain();

        roadMain.addnewRoad(25, 10, "North", 0, 0);
        roadMain.addConectingRoad(0, 25, "East");
        roadMain.addConectingRoad(0, 25, "North");
        roadMain.addConectingRoad(0, 25, "West");
        roadMain.roads.get(0).addTrafficLight("End");
        roadMain.roads.get(0).setEndLight(false);
        assertEquals(1, roadMain.roads.get(0).connectedRoads.get(0));
        assertEquals(2, roadMain.roads.get(0).connectedRoads.get(1));
        assertEquals(3, roadMain.roads.get(0).connectedRoads.get(2));
        roadMain.addConectingRoad(2, 25, "North");
        roadMain.addConectingRoad(2, 25, "West");
        assertEquals(4, roadMain.roads.get(2).connectedRoads.get(0));
        assertEquals(5, roadMain.roads.get(2).connectedRoads.get(1));
        roadMain.addVehicle("Car", 5, 0);

        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.addVehicle("Bus", 7, 0);
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();

        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.roads.get(0).setEndLight(true);
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();
        roadMain.printPos();
        roadMain.moveVehicles();


    }
}

