

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


    }
}
