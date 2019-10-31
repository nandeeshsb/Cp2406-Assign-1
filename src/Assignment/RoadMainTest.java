

package Assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoadMainTest {

    @Test
    void testnewRoads() {
        RoadMain roadMain = new RoadMain();

        roadMain.addnewRoad(20, 10, "North", 0, 0);//0
        roadMain.addConectingRoad(0, 200, "North");//1
        roadMain.addConectingRoad(0, 200, "East");//2
        roadMain.addConectingRoad(0, 200, "West");//3
        roadMain.addConectingRoad(2, 200, "South");//4
        roadMain.addConectingRoad(2, 200, "North");//5
        roadMain.addConectingRoad(5, 200, "East");//6
        roadMain.addConectingRoad(5, 200, "West");//7

        assertEquals(1, roadMain.roads.get(0).connectedRoads.get(0));
        assertEquals(2, roadMain.roads.get(0).connectedRoads.get(1));
        assertEquals(3, roadMain.roads.get(0).connectedRoads.get(2));

        assertEquals(4, roadMain.roads.get(2).connectedRoads.get(0));
        assertEquals(5, roadMain.roads.get(2).connectedRoads.get(1));
       // assertEquals(3, roadMain.roads.get(0).connectedRoads.get(2));



        /*roadMain.addConectingRoad(2, 20, "North");
        roadMain.addConectingRoad(2, 20, "South");*/

        /*assertEquals(4, roadMain.roads.get(2).connectedRoads.get(0));
        assertEquals(5, roadMain.roads.get(2).connectedRoads.get(1));*/


    }

    /*@Test
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


    }*/
}
/*
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


*/
/*Tests for previos versions of roadMain  *//*

*/
/*@Test
    void testturning() {
        RoadMain roadMain = new RoadMain();
        roadMain.addnewRoad(20, 10, "East", 0, 0);
        roadMain.addConectingRoad(20, "North");
        roadMain.addConectingRoad(20, "West");
        roadMain.addLight(0, "End");
        roadMain.roads.get(0).getTrafficlight(0).setStatus(false);
        roadMain.addVehicle("Car", 5);
        roadMain.moveVehicles();
        assertEquals(7, roadMain.roads.get(0).vehicles.get(0).getXpos());
        roadMain.moveVehicles();
        roadMain.addVehicle("Motorbike", 4);
        roadMain.moveVehicles();
        roadMain.moveVehicles();
        assertEquals(19, roadMain.roads.get(0).vehicles.get(0).getXpos());
        assertEquals(9, roadMain.roads.get(0).vehicles.get(1).getXpos());
        roadMain.roads.get(0).getTrafficlight(0).setStatus(true);
        roadMain.moveVehicles();
        roadMain.moveVehicles();
        roadMain.moveVehicles();
        assertEquals(17, roadMain.roads.get(1).vehicles.get(0).getYpos());
        assertEquals(16, roadMain.roads.get(1).vehicles.get(0).getXpos());
      //  roadMain.roads.get(0).getTrafficlight(0).setStatus(true);
        roadMain.moveVehicles();
        assertEquals(7, roadMain.roads.get(1).vehicles.get(0).getzYpos());
        assertEquals(16, roadMain.roads.get(1).vehicles.get(1).getXpos());
        roadMain.moveVehicles();
        roadMain.moveVehicles();
        assertEquals(19, roadMain.roads.get(0).vehicles.get(0).getXpos());
        assertEquals(16, roadMain.roads.get(0).vehicles.get(1).getXpos());
        roadMain.roads.get(0).getTrafficlight(0).setStatus(true);



        assertEquals(2, roadMain.roads.get(1).vehicles.get(0).getYpos());
        roadMain.moveVehicles();
        roadMain.checkTurns();
        assertEquals(7, roadMain.roads.get(1).vehicles.get(0).getYpos());
        roadMain.moveVehicles();
        assertEquals(17, roadMain.roads.get(0).vehicles.get(0).getXpos());
        roadMain.checkTurns();
        assertEquals(12, roadMain.roads.get(1).vehicles.get(0).getYpos());
        roadMain.moveVehicles();
        roadMain.checkTurns();
        assertEquals(25, roadMain.roads.get(1).vehicles.get(1).getXpos());
        assertEquals(1, roadMain.roads.get(1).vehicles.get(1).getYpos());
        assertEquals(2, roadMain.roads.get(1).getNum_vehicles());
        assertEquals(17, roadMain.roads.get(1).vehicles.get(0).getYpos());
        assertEquals(1, roadMain.roads.get(1).vehicles.get(0).getRoad_ref());
        roadMain.moveVehicles();
        roadMain.checkTurns();
        assertEquals(1, roadMain.roads.get(1).getNum_vehicles());
        roadMain.moveVehicles();
        roadMain.checkTurns();
        assertEquals(25, roadMain.roads.get(2).vehicles.get(0).getYpos());
        assertEquals(23, roadMain.roads.get(2).vehicles.get(0).getXpos());

        roadMain.moveVehicles();
        roadMain.checkTurns();
        assertEquals(18, roadMain.roads.get(2).vehicles.get(0).getXpos());
        roadMain.moveVehicles();
        roadMain.checkTurns();
        roadMain.moveVehicles();
        roadMain.checkTurns();
        roadMain.moveVehicles();
        roadMain.checkTurns();
        roadMain.moveVehicles();
        roadMain.checkTurns();
        //assertEquals(18, roadMain.roads.get(2).vehicles.get(0).getXpos());

    }*//*
*/
/*



    @Test
    void testEmpty(){
        RoadMain roadMain = new RoadMain();
        roadMain.addnewRoad(12, 10, "East", 0, 0);
        roadMain.addConectingRoad(12, "North");
        roadMain.addConectingRoad(12, "West");
        roadMain.addVehicle("Car", 10);
        roadMain.addLight(0, "End");
        roadMain.roads.get(0).getTrafficlight(0).setStatus(false);
        roadMain.moveVehicles();
        roadMain.moveVehicles();
        roadMain.moveVehicles();
        assertEquals(11, roadMain.roads.get(0).vehicles.get(0).getXpos());
        roadMain.addVehicle("Bus", 5);
        roadMain.roads.get(0).vehicles.get(0).setSpeed(1);
        roadMain.moveVehicles();
        assertEquals(8, roadMain.roads.get(0).vehicles.get(1).getXpos());
        assertEquals(11, roadMain.roads.get(0).vehicles.get(0).getXpos());
        roadMain.roads.get(0).getTrafficlight(0).setStatus(true);
        roadMain.moveVehicles();
        roadMain.roads.get(0).vehicles.get(0).setSpeed(5);
        roadMain.moveVehicles();
        assertEquals(4, roadMain.roads.get(1).vehicles.get(0).getYpos());
        assertEquals(false, roadMain.isEmpty(0, 6));

        assertEquals(12, roadMain.roads.get(0).vehicles.get(0).getXpos());
        assertEquals(4, roadMain.roads.get(1).vehicles.get(0).getYpos());
        roadMain.moveVehicles();
        assertEquals(12, roadMain.roads.get(0).vehicles.get(0).getXpos());
        assertEquals(5, roadMain.roads.get(1).vehicles.get(0).getYpos());
        roadMain.moveVehicles();
        assertEquals(12, roadMain.roads.get(0).vehicles.get(0).getXpos());
        assertEquals(6, roadMain.roads.get(1).vehicles.get(0).getYpos());
        roadMain.moveVehicles();
        assertEquals(12, roadMain.roads.get(0).vehicles.get(0).getXpos());
        assertEquals(7, roadMain.roads.get(1).vehicles.get(0).getYpos());
        roadMain.moveVehicles();
        assertEquals(12, roadMain.roads.get(0).vehicles.get(0).getXpos());
        assertEquals(8, roadMain.roads.get(1).vehicles.get(0).getYpos());
        roadMain.moveVehicles();
        assertEquals(12, roadMain.roads.get(0).vehicles.get(0).getXpos());
        assertEquals(9, roadMain.roads.get(1).vehicles.get(0).getYpos());
        roadMain.moveVehicles();
        assertEquals(7, roadMain.roads.get(1).vehicles.get(1).getYpos());
        assertEquals(10, roadMain.roads.get(1).vehicles.get(0).getYpos());
        roadMain.moveVehicles();
        assertEquals(11, roadMain.roads.get(1).vehicles.get(0).getYpos());
        assertEquals(8, roadMain.roads.get(1).vehicles.get(1).getYpos());
        roadMain.moveVehicles();
        assertEquals(12, roadMain.roads.get(1).vehicles.get(0).getYpos());
        assertEquals(11, roadMain.roads.get(1).vehicles.get(1).getYpos());
        roadMain.moveVehicles();


        assertEquals(19, roadMain.roads.get(2).vehicles.get(0).getYpos());
        assertEquals(7, roadMain.roads.get(1).vehicles.get(0).getYpos());


    }
}*/

