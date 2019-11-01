package Assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Assignment.*;


public class Gui extends  JFrame   implements ActionListener {

    private JComboBox<String> direction;
    private JTextField newRoadLength;
    private JComboBox<Integer> existingRoads;
    private int roadCount = 0;
    private JComboBox<String> addTrafficLight;
    Assignment2.Map map;
    RoadMain roadMain = new RoadMain();
    //int[] roadList = new int[roadCount];

    public static void main(String[] args) {
        //new Gui().setVisible(true);


    }

    public Gui() {
        super("Traffic Simulator");
        setSize(1400, 1000);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLayout(new BorderLayout());


        ////////Create gui layout here////////


        ///Create Elements///
        JPanel controlPanel = new JPanel();
        JPanel newRoadInputs = new JPanel();
        JPanel modeSelection = new JPanel();
        JButton simulatorMode = new JButton("Simulator Mode");
        JButton buildMode = new JButton("Build Mode");
        direction = new JComboBox<>(new String[]{"North", "South", "East", "West"});
        newRoadLength = new JTextField("Road Length");
        existingRoads = new JComboBox<>(new Integer[roadCount]);
        addTrafficLight = new JComboBox<>(new String[]{"Add Traffic Light?", "Yes", "No"});
        //map = new JPanel();
        //map.setSize(1000, 1000);

        JButton addToMap = new JButton("Add Road");
        map = new Map(100, 100);
        ///Give actions///

        simulatorMode.addActionListener(this);
        buildMode.addActionListener(this);
        addToMap.addActionListener(this);


        ///Layout///
        controlPanel.setLayout(new GridLayout(2, 1));
        modeSelection.setLayout(new GridLayout(2, 1));
        newRoadInputs.setLayout(new GridLayout(5, 1));

        add(controlPanel, BorderLayout.EAST);
        //add(map);
        //add(a);


        add(map, BorderLayout.CENTER);
        modeSelection.add(simulatorMode);
        modeSelection.add(buildMode);

        newRoadInputs.add(direction);
        newRoadInputs.add(addTrafficLight);
        newRoadInputs.add(newRoadLength);
        newRoadInputs.add(existingRoads);
        newRoadInputs.add(addToMap);

        controlPanel.add(modeSelection);
        controlPanel.add(newRoadInputs);
    }

    String getDirection() {
        return direction.getItemAt(direction.getSelectedIndex());
    }

    String getTrafficLight() {
        return addTrafficLight.getItemAt(addTrafficLight.getSelectedIndex());
    }

    int getNewRoadLength() {
        int length = Integer.parseInt(newRoadLength.getText());
        return length;
    }

    boolean isLengthInt() {
        String input = newRoadLength.getText();
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }


    private int getConnectedRoad() {
        int roadNum;
        if (roadCount == 0) {
            roadNum = 0;
        } else {
            roadNum = existingRoads.getItemAt(existingRoads.getSelectedIndex());
        }
        return roadNum;
    }

    /*void map.addRoad(int length, int width, String direction, int xinit, int yinit) {
        //PaintRoad a = new PaintRoad(length, width, direction, xinit, yinit);
        map.addRoad(length, width, direction, xinit, yinit);
        map.paintRoads();
    }*/

    void addNewRoad(int ref, int length, String direction) {

        if (roadCount == 0) {
            roadMain.addnewRoad(length, 8, direction, 10, -10);
            length *=10;
            switch (direction) {
                case "North":
                    map.addRoad(length, 80,
                            direction, 10, -length, roadCount);
                   // map.paintRoads();
                    addCount();
                    break;
                case "South":
                    map.addRoad(length, 80,
                            direction, 10, 10, roadCount);
                   // map.paintRoads();
                    addCount();
                    break;
                case "East":
                    map.addRoad(length, 80,
                            direction, 10, 10, roadCount);
                   // map.paintRoads();
                    addCount();
                    break;
                case "West":

                    map.addRoad(length, 80,
                            direction, -length, 10, roadCount);
                  //  map.paintRoads();
                    addCount();
                    break;

            }


        } else {

            roadMain.addConectingRoad(ref, length, direction);
            int yNorth = map.roads.get(ref).getYinit();
            int xWest = map.roads.get(ref).getXinit();
            String dir = roadMain.getRoad(ref).getDirection();
            int conectedLength = map.roads.get(ref).getLength();
            length*=10;

            if (dir.equals("North")) {
                if (!direction.equals("South")) {
                    switch (direction) {
                        case "East":
                            map.addRoad(length, 80,
                                    direction, xWest+80, yNorth - 80, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "West":
                            map.addRoad(length, 80,
                                    direction, xWest - length, yNorth - 80, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "North":
                            map.addRoad(length, 80,
                                    direction, xWest, yNorth-length-80, roadCount);
                            map.paintRoads();

                            addCount();
                            break;
                    }

                }
            }
            if (dir.equals("South")) {
                if (!direction.equals("North")) {

                    switch (direction) {
                        case "East":
                            map.addRoad(length, 80,
                                    direction, xWest+80, yNorth+conectedLength, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "West":
                            map.addRoad(length, 80,
                                    direction, xWest-length, yNorth+conectedLength, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "South":
                            map.addRoad(length, 80,
                                    direction, xWest, yNorth+ conectedLength + 80, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                    }

                }
            }
            if (dir.equals("East")) {
                if (!direction.equals("West")) {

                    switch (direction) {
                        case "North":
                            map.addRoad(length, 80,
                                    direction, xWest+conectedLength, yNorth - length, roadCount);
                            map.paintRoads();
                            addCount();

                            break;
                        case "South":
                            map.addRoad(length, 80,
                                    direction, xWest+conectedLength, yNorth+80, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "East":
                            map.addRoad(length, 80,
                                    direction, xWest+conectedLength + 80, yNorth, roadCount);
                            map.paintRoads();
                            addCount();

                            break;
                    }

                }
            }
            if (dir.equals("West")) {
                if (!direction.equals("East")) {

                    switch (direction) {
                        case "North":
                            map.addRoad(length, 80,
                                    direction, xWest-80, yNorth - length, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "South":
                            map.addRoad(length, 80,
                                    direction, xWest - 80, yNorth+80, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "West":
                            map.addRoad(length, 80,
                                    direction, xWest - length-80, yNorth, roadCount);
                            map.paintRoads();
                            addCount();

                            break;
                    }

                }
            }
        }

    }


/*
    int getXinit(int roadRef){
        String dir = roads.get(roadRef).getDirection();
        int x;




        if (roadMain.getRoad(roadRef).getDirection().equals("East")){
            x = roadMain.getRoad(roadRef).getXeast();

        }else {
            x = roadMain.getRoad(roadRef).getXwest();
        }
        return x*10;
    }

    int getYinit(int roadRef){
        int y;
        if (roadMain.getRoad(roadRef).getDirection().equals("South")){
            y = roadMain.getRoad(roadRef).getYsouth();

        }else {
            y = roadMain.getRoad(roadRef).getYnorth();
        }
        return y*10;
    }*/

    public void addCount() {
        existingRoads.addItem(roadCount);
        roadCount++;
    }

    int getRoadCount(){
        return roadCount;
    }

    public void addVehicle(int roadRef, String type, int distanceTraveled){

    }

    public void updateVehicles(){
        for(Road road: roadMain.getRoads()){

            road.getVehicles();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String name = actionEvent.getActionCommand();

        if (name.equals("Simulator Mode")) {
            System.out.println("Simulator Mode pressed");
                roadMain.runSim();
        } else if (name.equals("Build Mode")) {
            roadMain = new RoadMain();
            System.out.println("Build Mode pressed");


        } else if (name.equals("Add Road")) {
            if (!isLengthInt()) {
                System.out.println("Enter a valid integer length");

            } else {

                if (getTrafficLight().equals("Add Traffic Light?")) {
                    System.out.println("Please select whether or not you want a traffic light...");


                } else if (getTrafficLight().equals("Yes")) {
                    addNewRoad(getConnectedRoad(), getNewRoadLength(), getDirection());
                    roadMain.addTrafficLight(roadCount, "End");
                    System.out.println("New road added! Direction: " + getDirection() + "  Length: " + getNewRoadLength() + " On the end of road: " + getConnectedRoad() + "  With a traffic light at its end");


                } else {
                    addNewRoad(getConnectedRoad(), getNewRoadLength(), getDirection());
                    System.out.println("New road added! Direction: " + getDirection() + "  Length: " + getNewRoadLength() + " On the end of road: " + getConnectedRoad());
                    //addRoadImage(getNewRoadLength()*10, 80, getDirection(), getXinit(getConnectedRoad()), getYinit(getConnectedRoad()));

                }
            }


        }
    }
}





/*int north_end = roadMain.getRoad(ref).getYnorth()*10;
            int south_end = roadMain.getRoad(ref).getYsouth()*10;
            int east_end = roadMain.getRoad(ref).getXeast()*10;
            int west_end = roadMain.getRoad(ref).getXwest()*10;*/
            //length*=10;

            /*if(getConnectedRoad() == 0){
                if (dir.equals("South")){
                    east_end-=90;
                    west_end-=90;
                    south_end+= 90;
                }else if (dir.equals("East")){
                    north_end+=10;
                    east_end-=90;
                    south_end+=10;

                }
            }*/
     /*               if (dir.equals("North")) {
                    if (!direction.equals("South")) {
                    switch (direction) {
                    case "East":
                    map.addRoad(length, 80,
                    direction, east_end, -north_end - 80, roadCount);
                    map.paintRoads();
                    addCount();
                    break;
                    case "West":
                    map.addRoad(length, 80,
                    direction, west_end - length, -north_end - 80, roadCount);
                    map.paintRoads();
                    addCount();
                    break;
                    case "North":
                    map.addRoad(length, 80,
                    direction, west_end, -north_end - 80, roadCount);
                    map.paintRoads();

                    addCount();
                    break;
                    }

                    }
                    }
                    if (dir.equals("South")) {
                    if (!direction.equals("North")) {

                    switch (direction) {
                    case "East":
                    map.addRoad(length, 80,
                    direction, east_end, -south_end, roadCount);
                    map.paintRoads();
                    addCount();
                    break;
                    case "West":
                    map.addRoad(length, 80,
                    direction, west_end - length, -south_end, roadCount);
                    map.paintRoads();
                    addCount();
                    break;
                    case "South":
                    map.addRoad(length, 80,
                    direction, west_end, -south_end + 80, roadCount);
                    map.paintRoads();
                    addCount();
                    break;
                    }

                    }
                    }
                    if (dir.equals("East")) {
                    if (!direction.equals("West")) {

                    switch (direction) {
                    case "North":
                    map.addRoad(length, 80,
                    direction, east_end, -north_end - length, roadCount);
                    map.paintRoads();
                    addCount();

                    break;
                    case "South":
                    map.addRoad(length, 80,
                    direction, east_end, -south_end, roadCount);
                    map.paintRoads();
                    addCount();
                    break;
                    case "East":
                    map.addRoad(length, 80,
                    direction, east_end + 80, -north_end, roadCount);
                    map.paintRoads();
                    addCount();

                    break;
                    }

                    }
                    }
                    if (dir.equals("West")) {
                    if (!direction.equals("East")) {

                    switch (direction) {
                    case "North":
                    map.addRoad(length, 80,
                    direction, west_end, -north_end - length, roadCount);
                    map.paintRoads();
                    addCount();
                    break;
                    case "South":
                    map.addRoad(length, 80,
                    direction, west_end - 80, -south_end, roadCount);
                    map.paintRoads();
                    addCount();
                    break;
                    case "West":
                    map.addRoad(length, 80,
                    direction, west_end - length, -north_end, roadCount);
                    map.paintRoads();
                    addCount();

                    break;
                    }

                    }
                    }
                    }

                    }
*/