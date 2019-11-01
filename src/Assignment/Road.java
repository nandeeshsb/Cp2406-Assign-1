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
    private int trafficLightNorth;
    private int trafficLightWest;
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
                trafficLightNorth = ynorth;
                trafficLightWest = xwest - 30;
                break;
            case "South":
                ysouth = yinit - length;
                ynorth = yinit;
                xeast = xinit + width;
                xwest = xinit;
                trafficLightNorth = ysouth+60;
                trafficLightWest = xeast;
                break;
            case "East":
                xeast = xinit + length;
                xwest = xinit;
                ynorth = yinit + width;
                ysouth = yinit;
                trafficLightNorth = ynorth + 60;
                trafficLightWest = xeast-30;
                break;
            case "West":
                xwest = xinit - length;
                xeast = xinit;
                ynorth = yinit + width;
                ysouth = yinit;
                trafficLightNorth = ysouth;
                trafficLightWest = xwest;
                break;
        }
    }

    void setConnectedRoads(int connectedRoads) {
        /*Adds reference number to keep track of what roads are connected to the end
         * of current road */
        this.connectedRoads.add(connectedRoads);
        this.index++;
    }
    public int getTrafficLightNorth(){
        return trafficLightNorth;
    }
    public int getTrafficLightWest(){
        return trafficLightWest;
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

        vehiclesOnRoad++;
    }

    void addBus(int speed) {

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

        if (trafficLightend) {
            endLight.setStatus(status);
        }
    }

    public void setStartLight(boolean status) {

        if (trafficLightStart) {
            startLight.setStatus(status);
        }
    }

    void moveVehicles() {


        for (int i = 0; i < vehicles.size(); i++) {
            if (checkCollision(i)) {
                System.out.println("\n\n\n\n...........................................................................Collision with " + vehicles.get(i).getType() + "  " + vehicles.get(i-1).getType());

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


    private boolean checkCollision(int vRef) {

        boolean status = false;
        if (vRef != 0){

            if (direction == "North") {
                if (vehicles.get(vRef).getYpos() + vehicles.get(vRef).getSpeed() >=
                        vehicles.get(vRef - 1).getYrear()- 20) {
                    vehicles.get(vRef).setSpeed(vehicles.get(vRef - 1).getSpeed());
                    vehicles.get(vRef).setPos(vehicles.get(vRef - 1).getYrear() - 21);
                    status = true;
                }

            } else if (direction == "East") {
                if (vehicles.get(vRef).getXpos() + vehicles.get(vRef).getSpeed() >=
                        vehicles.get(vRef - 1).getXrear()-20) {
                    vehicles.get(vRef).setSpeed(vehicles.get(vRef - 1).getSpeed());
                    vehicles.get(vRef).setPos(vehicles.get(vRef - 1).getXrear() - 21);
                    status = true;
                }

            } else if (direction == "West") {
                if (vehicles.get(vRef).getXpos() - vehicles.get(vRef).getSpeed() <=
                        vehicles.get(vRef - 1).getXrear()+ 20) {
                    vehicles.get(vRef).setSpeed(vehicles.get(vRef - 1).getSpeed());
                    vehicles.get(vRef).setPos(vehicles.get(vRef - 1).getXrear() + 21);
                    status = true;
                }

            } else if (direction == "South") {
                if (vehicles.get(vRef).getYpos() - vehicles.get(vRef).getSpeed() <=
                        vehicles.get(vRef - 1).getYrear()+ 20) {
                    vehicles.get(vRef).setSpeed(vehicles.get(vRef - 1).getSpeed());
                    vehicles.get(vRef).setPos(vehicles.get(vRef - 1).getYrear() + 21);
                    status = true;
                }

            }

        }
        return status;
    }



    boolean lightAtStart() {
        return trafficLightStart;
    }

    public boolean lightAtEnd() {
        return trafficLightend;
    }

    public TrafficLight getEndLight() {
        return endLight;
    }

    TrafficLight getStartLight() {
        return startLight;
    }

    private boolean checkMovement(int i) {



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

        return vehicles.get(ref_num);

    }


    private void isOffRoad(int i) {

        vehiclesOffRoad.add(vehicles.get(i));
    }



    public ArrayList<Vehicle> getVehicles_off_road() {

        return vehiclesOffRoad;
    }


    void reset_offRoad() {

        vehiclesOffRoad.clear();
    }


    public int getLength() {

        return length;
    }

    public String getDirection() {

        return direction;
    }

    int getNum_vehicles() {

        return vehiclesOnRoad;
    }


    public int getXeast() {

        return xeast;
    }

    public int getXwest() {

        return xwest;
    }

    int getWidth() {
        return width;
    }

    public int getYnorth() {

        return ynorth;
    }

    public int getYsouth() {

        return ysouth;
    }

    public ArrayList<Vehicle> getVehicles(){
        return vehicles;
    }

}
