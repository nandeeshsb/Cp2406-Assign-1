package Assignment;

import java.util.ArrayList;
import java.util.*;

public class Road {
    /* The road class is used to create the map
      and gives vehicles a direction and end points where they can turn/leave the map   */

    private int length;
    private int width;
    private int xinit;
    private int yinit;
    private int xeast;
    private int xwest;
    private int ynorth;
    private int ysouth;
    private String direction;
    private int vehicleRef = 0;
    private int vehiclesOnRoad;
    private int totTrafficLights = 0;
    private boolean trafficLightStart = false;
    private boolean trafficLightend = false;
    private TrafficLight startLight;
    private TrafficLight endLight;
    private int index = 0;

    ArrayList<Integer> connectedRoads = new ArrayList<Integer>();
    ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    ArrayList<Vehicle> vehiclesOffRoad = new ArrayList<>();


    Road(int length, int width, String direction, int xinit, int yinit) {
        this.direction = direction;
        this.length = length;
        this.width = width;
        this.xinit = xinit;
        this.yinit = yinit;
    }

    void makeRoad() {
        /*Builds the road based on initial positions and direction of road */
        switch (this.getDirection()) {
            case "North":
                ynorth = yinit + length;
                ysouth = yinit;
                xeast = xinit + width;
                xwest = xinit;
                break;
            case "South":
                ysouth = yinit - length;
                ynorth = yinit;
                xeast = xinit + width;
                xwest = xinit;
                break;
            case "East":
                xeast = xinit + length;
                xwest = xinit;
                ynorth = yinit + width;
                ysouth = yinit;
                break;
            case "West":
                xwest = xinit - length;
                xeast = xinit;
                ynorth = yinit + width;
                ysouth = yinit;
                break;
        }
    }

    void setConnectedRoads(int connectedRoads) {
        /*Adds reference number to keep track of what roads are connected to the end
         * of current road */
        this.connectedRoads.add(connectedRoads);
        this.index++;
    }

    void addCar(int speed) {
        /*Adds a car to the beginning of the road in the direction of the road at a specified speed*/
        switch (direction) {
            case "North":
                vehicles.add(new Car("North", speed, xwest + width / 2, ysouth));
                break;
            case "South":
                vehicles.add(new Car("South", speed, xwest + width / 2, ynorth));
                break;
            case "East":
                vehicles.add(new Car("East", speed, xwest, ysouth + width / 2));
                break;
            case "West":
                vehicles.add(new Car("West", speed, xeast, ysouth + width / 2));
                break;
        }
        vehicles.get(vehicles.size() - 1).intiDir();
        //vehicleRef = vehicles.size();
        vehiclesOnRoad++;
    }

    void addMotorbike(int speed) {
        /*Adds a motorbike to the beginning of the road in the direction of the road at a specified speed*/
        switch (direction) {
            case "North":
                vehicles.add(new Motorbike("North", speed, xwest + width / 2, ysouth));
                break;
            case "South":
                vehicles.add(new Motorbike("South", speed, xwest + width / 2, ynorth));
                break;
            case "East":
                vehicles.add(new Motorbike("East", speed, xwest, ysouth + width / 2));
                break;
            case "West":
                vehicles.add(new Motorbike("West", speed, xeast, ysouth + width / 2));
                break;
        }
        vehicles.get(vehicles.size() - 1).intiDir();
        // vehicles.get(vehicleRef).intiDir();
        // vehicleRef = vehicles.size();
        vehiclesOnRoad++;
    }

    void addBus(int speed) {
        /*Adds a bus to the beginning of the road in the direction of the road at a specified speed*/
        switch (direction) {
            case "North":
                vehicles.add(new Bus("North", speed, xwest + width / 2, ysouth));
                break;
            case "South":
                vehicles.add(new Bus("South", speed, xwest + width / 2, ynorth));
                break;
            case "East":
                vehicles.add(new Bus("East", speed, xwest, ysouth + width / 2));
                break;
            case "West":
                vehicles.add(new Bus("West", speed, xeast, ysouth + width / 2));
                break;
        }
        vehicles.get(vehicles.size() - 1).intiDir();
        vehiclesOnRoad++;
    }

    void addTrafficLight(String which_end) {
        /*Adds a traffic light to the end or beginning of the road */
        if (which_end.equals("Start")) {
            this.trafficLightStart = true;
            this.startLight = new TrafficLight("Start");
            totTrafficLights++;
        } else if (which_end.equals("End")) {
            this.endLight = new TrafficLight("End");
            this.trafficLightend = true;
            totTrafficLights++;
        }
    }

    void setEndLight(boolean status) {
        /*Change the status of the traffic light at the end of the road*/
        if (trafficLightend) {
            endLight.setStatus(status);
        }
    }

    public void setStartLight(boolean status) {
        /*Change the status of the traffic light at the beginning of the road*/
        if (trafficLightStart) {
            startLight.setStatus(status);
        }
    }

    void moveVehicles() {
        /*Moves all the vehicles currently on the road. Ensures that there are no collisions
         * and that the vehicles are not moving through red lights*/

        for (int i = 0; i < vehicles.size(); i++) {
            if (checkCollision(i)) {
                vehicles.get(i).setSpeed(vehicles.get(i - 1).getSpeed());
                vehicles.get(i).setPos(vehicles.get(i - 1).getRear());
            } else if (checkMovement(i)) {
                if (trafficLightend) {
                    if (endLight.isGreen()) {
                        isOffRoad(i);
                    }
                    vehicles.get(i).setPos(getRoadEnd());
                } else isOffRoad(i);
            } else vehicles.get(i).moveVehicle();

        }
    }

    boolean lightAtStart() {
        return trafficLightStart;
    }

    boolean lightAtEnd() {
        return trafficLightend;
    }

    TrafficLight getEndLight() {
        return endLight;
    }

    TrafficLight getStartLight() {
        return startLight;
    }

    private boolean checkMovement(int i) {

        /* Returns true if the vehicle will be moving off the current road*/

        boolean status = false;

        if (vehicles.get(i).getDirection().equals("North")) {
            if (vehicles.get(i).getYpos() + vehicles.get(i).getSpeed() > ynorth)
                status = true;
        } else if (vehicles.get(i).getDirection().equals("South")) {
            if (vehicles.get(i).getYpos() - vehicles.get(i).getSpeed() < ysouth) {
                status = true;
            }

        } else if (vehicles.get(i).getDirection().equals("West")) {
            if (vehicles.get(i).getXpos() - vehicles.get(i).getSpeed() < xwest) {
                status = true;
            }

        } else if (vehicles.get(i).getDirection().equals("East")) {
            if (vehicles.get(i).getXpos() + vehicles.get(i).getSpeed() > xeast) {
                status = true;
            }

        }
        return status;
    }


    int getRoadEnd() {
        /*Returns the position of the end of the road based on its direction*/
        int max = 0;
        if (direction.equals("North")) {
            max = ynorth;
        }
        if (direction.equals("South")) {
            max = ysouth;
        }
        if (direction.equals("East")) {
            max = xeast;
        }
        if (direction.equals("West")) {
            max = xwest;
        }
        return max;


    }

    Vehicle getVehicleOnRoad(int ref_num) {
        /*Returns a specified vehicle currently on the road*/
        return vehicles.get(ref_num);

    }


    private void isOffRoad(int i) {
        /*Adds a vehicle to the liost of vehicles that would be moving off the road*/
        vehiclesOffRoad.add(vehicles.get(i));
    }


    private boolean checkCollision(int vRef) {
        /*Returns true if a vehicle will move past the vehicle
         * in front of it. If it does, position is set to the end of front vehicle
         * and speed is set to the first vehicles speed. Used in move vehicles function */
        boolean status = false;
        if (vehicles.size() == 1) {
            status = false;
        } else if (vRef == 0) {
            status = false;
        } else {
            if (direction == "North") {
                if (vehicles.get(vRef).getYpos() + vehicles.get(vRef).getSpeed() >=
                        vehicles.get(vRef - 1).getYrear()) {
                    status = true;
                } else status = false;

            } else if (direction == "East") {
                if (vehicles.get(vRef).getXpos() + vehicles.get(vRef).getSpeed() >=
                        vehicles.get(vRef - 1).getXrear()) {
                    status = true;
                } else status = false;

            } else if (direction == "West") {
                if (vehicles.get(vRef).getXpos() - vehicles.get(vRef).getSpeed() <=
                        vehicles.get(vRef - 1).getXrear()) {
                    status = true;
                } else status = false;

            } else if (direction == "South") {
                if (vehicles.get(vRef).getYpos() - vehicles.get(vRef).getSpeed() <=
                        vehicles.get(vRef - 1).getYrear()) {
                    status = true;
                } else status = false;

            }

        }
        return status;
    }

    public ArrayList<Vehicle> getVehicles_off_road() {
        /*Returns list of vehicles moving off the current road. Used to determine if vehicles will be turning etc.*/
        return vehiclesOffRoad;
    }


    void reset_offRoad() {
        /*Resets the vehicles off road list*/
        vehiclesOffRoad.clear();
    }


    public int getLength() {
        /*Returns length of the road*/
        return length;
    }

    String getDirection() {
        /*Returns the direction of the road*/
        return direction;
    }

    int getNum_vehicles() {
        /*Returns the number of vehicles currently on the road*/
        return vehiclesOnRoad;
    }


    int getXeast() {
        /*Returns the coordinate of east edge of road*/
        return xeast;
    }

    int getXwest() {
        /*Returns the coordinate of west edge of road*/
        return xwest;
    }

    int getWidth() {
        return width;
    }

    int getYnorth() {
        /*Returns the coordinate of north edge of road*/
        return ynorth;
    }

    int getYsouth() {
        /*Returns the coordinate of south edge of road*/
        return ysouth;
    }

}


/*Old code i wasnt ready to delete   */
/*
    public void addTrafficLight(String which_end) {
        if (which_end == "Start") {
            if (direction == "North") {
                trafficLights.add(new TrafficLight(xwest, ysouth, "NS", "Start"));
            } else if (direction == "South") {
                trafficLights.add(new TrafficLight(xeast, ynorth, "NS", "Start"));
            } else if (direction == "East") {
                trafficLights.add(new TrafficLight(xwest, ynorth, "EW", "Start"));
            } else if (direction == "West") {
                trafficLights.add(new TrafficLight(xeast, ysouth, "EW", "Start"));
            }
        } else if (which_end == "End") {
            if (direction == "North") {
                trafficLights.add(new TrafficLight(xwest, ynorth, "NS", "End"));

            } else if (direction == "South") {
                trafficLights.add(new TrafficLight(xeast, ysouth, "NS", "End"));

            } else if (direction == "East") {
                trafficLights.add(new TrafficLight(xeast, ynorth, "EW", "End"));

            } else if (direction == "West") {
                trafficLights.add(new TrafficLight(xwest, ysouth, "EW", "End"));
            }
        }
        tot_TrafficLights++;

    }*/




 /*   public void moveVehicles() {
        for (int i = 0; i < vehicles.size(); i++) {

            if (vehicles.get(i).getDirection() == "North") {
                if (checkCollision(i)) {
                    vehicles.get(i).setSpeed(vehicles.get(i - 1).getSpeed());
                    vehicles.get(i).setPos(vehicles.get(i - 1).getYrear() - 1);
                } else if (checkTrafficLights(i)) {
                    if (trafficLights.get(light_ref).isGreen()) {
                        if (trafficLights.get(light_ref).isEnd()) {
                            isOffRoad(i, vehicles.get(i).getRoad_ref() + 1);
                        }
                    } else vehicles.get(i).setPos(trafficLights.get(light_ref).getYpos() - 1);

                } else if (vehicles.get(i).getYpos() + vehicles.get(i).getSpeed() > ynorth) {
                    isOffRoad(i, vehicles.get(i).getRoad_ref() + 1);

                } else vehicles.get(i).moveVehicle();

            } else if (vehicles.get(i).getDirection() == "South") {
                if (checkCollision(i)) {
                    vehicles.get(i).setSpeed(vehicles.get(i - 1).getSpeed());
                    vehicles.get(i).setPos(vehicles.get(i - 1).getYrear() + 1);
                } else if (checkTrafficLights(i)) {
                    if (trafficLights.get(light_ref).isGreen()) {
                        if (trafficLights.get(light_ref).isEnd()) {
                            isOffRoad(i, vehicles.get(i).getRoad_ref() + 1);
                        }
                    } else vehicles.get(i).setPos(trafficLights.get(light_ref).getYpos() - 1);

                } else if (vehicles.get(i).getYpos() - vehicles.get(i).getSpeed() < ysouth) {
                    isOffRoad(i, vehicles.get(i).getRoad_ref() + 1);

                } else vehicles.get(i).moveVehicle();

            } else if (vehicles.get(i).getDirection() == "East") {
                if (checkCollision(i)) {
                    vehicles.get(i).setSpeed(vehicles.get(i - 1).getSpeed());
                    vehicles.get(i).setPos(vehicles.get(i - 1).getXrear() - 1);
                } else if (checkTrafficLights(i)) {
                    if (trafficLights.get(light_ref).isGreen()) {
                        if (trafficLights.get(light_ref).isEnd()) {
                            isOffRoad(i, vehicles.get(i).getRoad_ref() + 1);
                        }
                    } else vehicles.get(i).setPos(trafficLights.get(light_ref).getXpos() - 1);

                } else if (vehicles.get(i).getXpos() + vehicles.get(i).getSpeed() > xeast) {
                    isOffRoad(i, vehicles.get(i).getRoad_ref() + 1);
                } else vehicles.get(i).moveVehicle();

            } else if (vehicles.get(i).getDirection() == "West") {
                if (checkCollision(i)) {
                    vehicles.get(i).setSpeed(vehicles.get(i - 1).getSpeed());
                    vehicles.get(i).setPos(vehicles.get(i - 1).getXrear() + 1);
                } else if (checkTrafficLights(i)) {
                    if (trafficLights.get(light_ref).isGreen()) {
                        if (trafficLights.get(light_ref).isEnd()) {
                            isOffRoad(i, vehicles.get(i).getRoad_ref() + 1);
                        } else vehicles.get(i).moveVehicle();
                    } else vehicles.get(i).setPos(trafficLights.get(light_ref).getXpos() - 1);

                } else if (vehicles.get(i).getXpos() - vehicles.get(i).getSpeed() < xwest) {
                    isOffRoad(i, vehicles.get(i).getRoad_ref() + 1);
                } else vehicles.get(i).moveVehicle();

            }

        }
    }*/

 /*
    public void addVehicle(String type, int speed){
        if (type == "Car"){
            addCar(speed);
        }else if (type == "Bus"){
            addBus(speed);
        }else if (type =="Motorbike"){
            addMotorbike(speed);
        }
    }*/