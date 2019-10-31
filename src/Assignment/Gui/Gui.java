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

        setSize(900, 650);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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


        JButton addToMap = new JButton("Add Road");
        map = new Map(100, 100);


        simulatorMode.addActionListener(this);
        buildMode.addActionListener(this);
        addToMap.addActionListener(this);
        makeDefault.addActionListener(this);



        controlPanel.setLayout(new GridLayout(2, 1));
        modeSelection.setLayout(new GridLayout(3, 1));
        newRoadInputs.setLayout(new GridLayout(5, 1));

        add(controlPanel, BorderLayout.EAST);



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

    }

    void addNewRoad(int ref, int length, String direction) {
        roadMain.addConectingRoad(ref, length, direction);
        addCount();
        map.addRoad(length, 60,
                direction, roadMain.getRoad(roadCount-1).getXwest(), -roadMain.getRoad(roadCount-1).getYnorth() , roadCount);


    }

    public void addCount() {
        existingRoads.addItem(roadCount);
        roadCount++;
    }

    int getRoadCount() {
        return roadCount;
    }




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


                }
            }


        }
    }
}




