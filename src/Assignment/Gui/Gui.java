package Assignment.Gui;

import Assignment.Road;
import Assignment.RoadMain;
import Assignment.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui extends JFrame implements ActionListener {

    private JComboBox<String> direction;
    private JTextField newRoadLength;
    private JComboBox<Integer> existingRoads;
    private int roadCount = 0;
    private JComboBox<String> addTrafficLight;
    Map map;
    RoadMain roadMain = new RoadMain();
    Timer timer;

    public static void main(String[] args) {
        new Gui().setVisible(true);



    }

    public Gui() {
        super("Traffic Simulator");
        //setVisible(true);
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
        JButton makeDefault = new JButton("Make Default");
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
        makeDefault.addActionListener(this);


        ///Layout///
        controlPanel.setLayout(new GridLayout(2, 1));
        modeSelection.setLayout(new GridLayout(3, 1));
        newRoadInputs.setLayout(new GridLayout(5, 1));

        add(controlPanel, BorderLayout.EAST);
        //add(map);
        //add(a);


        add(map, BorderLayout.CENTER);
        modeSelection.add(simulatorMode);
        modeSelection.add(buildMode);
        modeSelection.add(makeDefault);

        newRoadInputs.add(direction);
        newRoadInputs.add(addTrafficLight);
        newRoadInputs.add(newRoadLength);
        newRoadInputs.add(existingRoads);
        newRoadInputs.add(addToMap);

        controlPanel.add(modeSelection);
        controlPanel.add(newRoadInputs);
        addInitialRoad();
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



    void confirmReset() {
        int input = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to reset the map?", "Select an Option...", JOptionPane.YES_NO_OPTION);
    System.out.println(input);
        if (input == 0) {
            this.dispose();
            new Gui().setVisible(true);


        }


    }

    void addInitialRoad(){
        roadMain.addnewRoad(200, 60, "South", 500, 0);
        addCount();
        map.addRoad(roadMain.getRoad(0).getLength(), 60,
                "South", roadMain.getRoad(0).getXwest(), -roadMain.getRoad(0).getYnorth() , 0);
       // map.paintRoads();
        //addCount();
    }

    void addNewRoad(int ref, int length, String direction) {
        roadMain.addConectingRoad(ref, length, direction);
        addCount();
        map.addRoad(length, 60,
                direction, roadMain.getRoad(roadCount-1).getXwest(), -roadMain.getRoad(roadCount-1).getYnorth() , roadCount);
        //map.paintRoads();

    }

    public void addCount() {
        existingRoads.addItem(roadCount);
        roadCount++;
    }

    int getRoadCount() {
        return roadCount;
    }


    /*public void redrawVehicles() {
        map.moveVehicles();
    }*/

    public void updateImages() {
        map.vehicleImages.clear();
        map.trafficLightImages.clear();
        //redrawVehicles();
        for (Road road : roadMain.getRoads()) {
            if (road.lightAtEnd()){
                boolean status = road.getEndLight().isGreen();
                    map.addTrafficLight(road.getTrafficLightWest(), road.getTrafficLightNorth(), status);
            }
            for (Vehicle vehicle : road.getVehicles()) {
               // map.addVehicle(vehicle.getRoadRef(), vehicle.getType(), vehicle.getDistanceTraveled());
                //String type, int yNorth, int xWest, int length
                map.addVehicle(vehicle.getType(), -vehicle.getYnorth(), vehicle.getXwest(), vehicle.getLength(), road.getDirection() );

            }

        }
        map.repaint();
    }


    void animate() {

        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(1000/60, e -> {

            roadMain.runSim();
            updateImages();

        });
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String name = actionEvent.getActionCommand();

        if (name.equals("Simulator Mode")) {
            System.out.println("Simulator Mode pressed");
              animate();
            //roadMain.runSim();
            //updateImages();

        } else if (name.equals("Build Mode")) {
            confirmReset();

        } else if (name.equals("Make Default")) {

            addNewRoad(0, 250, "East"); //1
            addNewRoad(0, 250, "West");//2
            addNewRoad(0, 250, "South");//3
            addNewRoad(1, 250, "East");//4
            addNewRoad(2, 250, "South");//5
            addNewRoad(5, 250, "East");//6
            addNewRoad(5, 250, "West");//7
            roadMain.addTrafficLight(0, "End");
            roadMain.addTrafficLight(5, "End");

            System.out.println("Make Default");

        } else if (name.equals("Add Road")) {
            if (!isLengthInt()) {
                System.out.println("Enter a valid integer length");

            } else {

                if (getTrafficLight().equals("Add Traffic Light?")) {
                    System.out.println("Please select whether or not you want a traffic light...");


                } else if (getTrafficLight().equals("Yes")) {
                    addNewRoad(getConnectedRoad(), getNewRoadLength(), getDirection());
                    roadMain.addTrafficLight(roadCount-1, "End");
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




/*
    void addNewRoad(int ref, int length, String direction) {

        if (roadCount == 0) {
            roadMain.addnewRoad(length, 60, direction, 500, -10);
            switch (direction) {
                case "North":
                    map.addRoad(length, 60,
                            direction, 10, -length, roadCount);
                    map.paintRoads();
                    addCount();
                    break;
                case "South":
                    map.addRoad(length, 60,
                            direction, 500, 10, roadCount);
                    map.paintRoads();
                    addCount();
                    break;
                case "East":
                    map.addRoad(length, 60,
                            direction, 10, 10, roadCount);
                    map.paintRoads();
                    addCount();
                    break;
                case "West":

                    map.addRoad(length, 60,
                            direction, -length, 10, roadCount);
                    map.paintRoads();
                    addCount();
                    break;

            }


        } else {

            roadMain.addConectingRoad(ref, length, direction);
            int yNorth = map.roads.get(ref).getYinit();
            int xWest = map.roads.get(ref).getXinit();
            String dir = roadMain.getRoad(ref).getDirection();
            int conectedLength = map.roads.get(ref).getLength();

            if (dir.equals("North")) {
                if (!direction.equals("South")) {
                    switch (direction) {
                        case "East":
                            map.addRoad(length, 60,
                                    direction, xWest + 60, yNorth - 60, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "West":
                            map.addRoad(length, 60,
                                    direction, xWest - length, yNorth - 60, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "North":
                            map.addRoad(length, 60,
                                    direction, xWest, yNorth - length - 60, roadCount);
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
                            map.addRoad(length, 60,
                                    direction, xWest + 60, yNorth + conectedLength, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "West":
                            map.addRoad(length, 60,
                                    direction, xWest - length, yNorth + conectedLength, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "South":
                            map.addRoad(length, 60,
                                    direction, xWest, yNorth + conectedLength + 60, roadCount);
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
                            map.addRoad(length, 60,
                                    direction, xWest + conectedLength, yNorth - length, roadCount);
                            map.paintRoads();
                            addCount();

                            break;
                        case "South":
                            map.addRoad(length, 60,
                                    direction, xWest + conectedLength, yNorth + 60, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "East":
                            map.addRoad(length, 60,
                                    direction, xWest + conectedLength + 60, yNorth, roadCount);
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
                            map.addRoad(length, 60,
                                    direction, xWest - 60, yNorth - length, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "South":
                            map.addRoad(length, 60,
                                    direction, xWest - 60, yNorth + 60, roadCount);
                            map.paintRoads();
                            addCount();
                            break;
                        case "West":
                            map.addRoad(length, 60,
                                    direction, xWest - length - 60, yNorth, roadCount);
                            map.paintRoads();
                            addCount();

                            break;
                    }

                }
            }
        }

    }*/






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