package Assignment.Gui;

import java.awt.*;

public class VehicleImage {


    String type;
    int xinit;
    int yinit;
    int length;
    int width;


    public VehicleImage(int xinit, int yinit, int length, int width, String type) {
        this.type = type;
        this.xinit = xinit;
        this.yinit = yinit;
        this.length = length;
        this.width = width;
    }

   /* public void update(int xinit, int yinit, int distanceTraveled){
        this.xinit = xinit;
        this.yinit = yinit;
        this.distanceTraveled = distanceTraveled*10;
    }*/


    public void drawTraffic(Graphics g) {
        switch (type) {
            case "Car":
                g.setColor(Color.RED);
                g.fillRect(xinit, yinit, length, width);
                break;

            case "Bus":
                g.setColor(Color.YELLOW);
                g.fillRect(xinit, yinit, length, width);
                break;
            case "Motorbike":
                g.setColor(Color.BLUE);
                g.fillRect(xinit, yinit, length, width);
                break;
        }
    }
}








