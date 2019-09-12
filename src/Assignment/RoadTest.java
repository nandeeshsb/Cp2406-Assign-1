
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


/*Tests for previos versions of road  */
 /*


          @Test
           void testonroad() {
               Road road = new Road(24, 10, "North", 0, 15);
               road.makeRoad();
               road.addVehicle("Motorbike", "North", 10, 0, 50);
               road.addTrafficLight();
               road.addVehicle("Motorbike", "North", 10, 0, 25);

           }


           @Test
           void trafficLighttest() {
               Road road = new Road(30, 10, "North", 0, 0);
               road.makeRoad();
               road.addTrafficLight("End");
               road.trafficLights.get(0).setStatus(false);
               road.addMotorbike(10);
               road.moveVehicles();
               assertEquals(11, road.getVehicleOnRoad(0).getYpos());
               road.moveVehicles();
               assertEquals(21, road.getVehicleOnRoad(0).getYpos());
               road.moveVehicles();
               assertEquals(29, road.getVehicleOnRoad(0).getYpos());
               road.moveVehicles();
               road.moveVehicles();
               assertEquals(29, road.getVehicleOnRoad(0).getYpos());
           }


           @Test
           void testaddtrafficlight() {
               Road road = new Road(25, 10, "North", 0, 0);
               road.makeRoad();
               road.addTrafficLight("End");
               assertEquals(25, road.getTrafficlight(0).getYpos());
               Road road2 = new Road(25, 10, "South", 0, 0);
               road2.makeRoad();
               road2.addTrafficLight("End");
               assertEquals(-25, road2.getTrafficlight(0).getYpos());
           }

           @Test
           void testcollisions() {
               Road road = new Road(25, 10, "North", 0, 0);
               road.makeRoad();
               road.addTrafficLight("End");
               road.getTrafficlight(0).setStatus(false);
               road.addCar( 24);
               road.moveVehicles();
               road.moveVehicles();
               assertEquals(24, road.getVehicleOnRoad(0).getYpos());
               road.addCar( 15);
               road.moveVehicles();
               road.moveVehicles();
               assertEquals(21, road.getVehicleOnRoad(1).getYpos());

               Road road2 = new Road(25, 10, "West", 0, 0);
               road2.makeRoad();
               road2.addBus( 4);
               road2.moveVehicles();
               road2.moveVehicles();
               assertEquals(-8, road2.getVehicleOnRoad(0).getXrear());
               road2.addCar( 15);
               road2.moveVehicles();
               assertEquals(-18, road2.getVehicleOnRoad(0).getXpos());
               assertEquals(-11, road2.getVehicleOnRoad(1).getXpos());

               Road road3 = new Road(50, 10, "North", 0, 0);
               road3.makeRoad();
               road3.addBus(2);
               road3.moveVehicles();
               road3.moveVehicles();
               road3.moveVehicles();
               road3.addCar( 5);
               road3.moveVehicles();
               assertEquals(8, road3.getVehicleOnRoad(0).getYrear());
               assertEquals(7, road3.getVehicleOnRoad(1).getYpos());

               road3.moveVehicles();
               assertEquals(2, road3.getVehicleOnRoad(1).getSpeed());
               road3.moveVehicles();
               assertEquals(11, road3.getVehicleOnRoad(1).getYpos());
               road3.moveVehicles();
               assertEquals(20, road3.getVehicleOnRoad(0).getYpos());
               assertEquals(13, road3.getVehicleOnRoad(1).getYpos());

           }

           @Test
           void testonroad() {
               Road road = new Road(15, 10, "North", 0, 0);
               road.makeRoad();
               road.addCar( 5);
               road.moveVehicles();
               road.moveVehicles();
               assertEquals(0, road.vehicles.get(0).getRoad_ref());
               assertEquals(12, road.getVehicleOnRoad(0).getYpos());
               road.moveVehicles();
               assertEquals(1, road.vehicles_off_road.get(0).getRoad_ref());
               road.moveVehicles();
               road.moveVehicles();


           }
       }
       */