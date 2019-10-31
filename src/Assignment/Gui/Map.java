package Assignment.Gui;

import Assignment.Road;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.Color;


public class Map extends JPanel {
    public static final Color DARK_YELLO = new Color(251, 245, 227);
    ArrayList<RoadShape> roads = new ArrayList<>();
    ArrayList<VehicleImage> vehicleImages = new ArrayList<>();
    ArrayList<TrafficLightImage> trafficLightImages = new ArrayList<>();

    public Map(int length, int width) {

        setBackground(DARK_YELLO);

    }



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




    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (RoadShape road : roads) {

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



