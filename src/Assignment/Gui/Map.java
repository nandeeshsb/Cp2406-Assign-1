package Assignment.Gui;

import Assignment.Road;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.Color;


public class Map extends JPanel {
    public static final Color DARK_GREEN = new Color(0, 51, 0);
    ArrayList<RoadShape> roads = new ArrayList<>();
    ArrayList<VehicleImage> vehicleImages = new ArrayList<>();
    ArrayList<TrafficLightImage> trafficLightImages = new ArrayList<>();

    public Map(int length, int width) {

        setBackground(DARK_GREEN);
       // setPreferredSize(new Dimension(length, width));
    }

    /*public void paintRoads() {
       // repaint();
        System.out.println("LALALALALA");
    }*/

    public void addRoad(int length, int width, String direction, int xinit, int yinit, int roadnum) {
        this.roads.add(new RoadShape(length, width, direction, xinit, yinit, roadnum));
        repaint();
    }

    void addVehicle(String type, int yNorth, int xWest, int length, String direction) {
        int width = 10;
        switch (direction){
            case "North":
            case "South":
                vehicleImages.add(new VehicleImage(xWest, yNorth, width, length, type));
                break;
            case "East":
            case "West":
                vehicleImages.add(new VehicleImage(xWest, yNorth, length, width, type));
                break;
        }




        vehicleImages.add(new VehicleImage(xWest, -yNorth, length, 10, type));
    }

    void addTrafficLight(int xWest, int yNorth, boolean status) {
        trafficLightImages.add(new TrafficLightImage(xWest, yNorth, status));
        }




   /* void addVehicle(int roadRef, String type, int front, int rear){
        int xRoad = roads.get(roadRef).getXStart();
        int yRoad = roads.get(roadRef).getYStart();
        int yVehicle;
        int xVehicle;
        int length = 0;
        int width = 10;
        String direction = roads.get(roadRef).getDirection();
        switch (type){
            case "Car":
                length = 20;
                break;
            case "Bus":
                length = 60;
                break;
            case "Motorbike":
                length = 10;
                break;

        }

        switch (direction){
            case "North":
                xVehicle = xRoad - 5;
                yVehicle = -front;
                vehicleImages.add(new VehicleImage(xVehicle, yVehicle, width, length, type));
                break;
            case "South":
                xVehicle = xRoad - 5;
                yVehicle = -rear;
                vehicleImages.add(new VehicleImage(xVehicle, yVehicle, width, length, type));
                break;
            case "East":
                xVehicle = rear;
                yVehicle = yRoad - 5;
                vehicleImages.add(new VehicleImage(xVehicle, yVehicle, length, width, type));
                break;
            case "West":
                xVehicle = front;
                yVehicle = yRoad - 5;
                vehicleImages.add(new VehicleImage(xVehicle, yVehicle, length, width, type));
                break;
        }
    }*/



    /*public void setRoads(ArrayList<Road> roads) {
        this.mainRoads = roads;
        mainRoads.clear();


        for (Road road : mainRoads) {
            for (Vehicle vehicle : road.getVehicles()) {

                vehicleImages.add(vehicle.getDistanceTraveled(), vehicle.getType(), this.roads.get(vehicle.getRoadRef()).getXinit(),
                        this.roads.get(vehicle.getRoadRef()).getYinit(), road.getDirection());

            }

        }
    }*/



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (RoadShape road : roads) {
            //g.setColor(Color.WHITE);
            road.draw(g);
        }
        for (VehicleImage vehicle : vehicleImages){
            vehicle.drawTraffic(g);
        }
        for (TrafficLightImage trafficLightImage : trafficLightImages){
            trafficLightImage.drawLights(g);
        }
    }
}



    /*void addVehicle(int roadRef, String type, int distanceTraveled){
        int xRoad = roads.get(roadRef).getXStart();
        int yRoad = roads.get(roadRef).getYStart();
      //  distanceTraveled*=10;
        int yVehicle;
        int xVehicle;
        int length = 0;
        int width = 10;
        String direction = roads.get(roadRef).getDirection();
        switch (type){
            case "Car":
                length = 20;
                break;
            case "Bus":
                length = 60;
                break;
            case "Motorbike":
                length = 10;
                break;

        }

        switch (direction){
            case "North":
                xVehicle = xRoad - 5;
                yVehicle = yRoad - distanceTraveled - length;
                vehicleImages.add(new VehicleImage(xVehicle, yVehicle, width, length, type));
                break;
            case "South":
                xVehicle = xRoad - 5;
                yVehicle = yRoad + distanceTraveled;
                vehicleImages.add(new VehicleImage(xVehicle, yVehicle, width, length, type));
                break;
            case "East":
                xVehicle = xRoad + distanceTraveled;
                yVehicle = yRoad - 5;
                vehicleImages.add(new VehicleImage(xVehicle, yVehicle, length, width, type));
                break;
            case "West":
                xVehicle = xRoad - distanceTraveled - length;
                yVehicle = yRoad - 5;
                vehicleImages.add(new VehicleImage(xVehicle, yVehicle, length, width, type));
                break;
        }
    }*/

/*
package Assignment2;

        import javax.swing.*;
        import java.awt.*;
        import java.util.ArrayList;

public class PaintRoad extends JPanel {
    String direction;
    int xinit;
    int yinit;
    int length;
    int width;
    private ArrayList<Rectangle> rectangleList = new ArrayList<Rectangle>();


    public PaintRoad(int length, int width, String direction, int xinit, int yinit){
        this.direction = direction;
        this.length = length;
        this.width = width;
        this.xinit = xinit;
        this.yinit = yinit;

        setupListeners();





        //repaint();
    }
    void setupListeners(){
        switch (direction) {
            case "North":
                rectangleList.add(new Rectangle(xinit, yinit + length, width, length ));
                break;
            case "South":
                rectangleList.add(new Rectangle(xinit, yinit, width, length ));
                break;
            case "East":
                rectangleList.add(new Rectangle(xinit, yinit, length, width ));
                break;
            case "West":
                rectangleList.add(new Rectangle(xinit - length, yinit, length, width ));
                break;
        }

        public void paint(Graphics g) {
            super.paint(g);
            for (Rectangle rect : rectangleList){
                rectangleList.add(g.f)
                Graphics2D g = (Graphics2D) g;
            }
        }
    }
}

*/
