package Assignment.Gui;

import java.awt.*;

public class TrafficLightImage {

    int xinit;
    int yinit;
    boolean status;


    public TrafficLightImage(int xinit, int yinit, boolean status) {
        this.status = status;
        this.xinit = xinit;
        this.yinit = -yinit;

    }

    public void drawLights(Graphics g) {
        if (status){
         g.setColor(Color.GREEN);
         g.fillOval(xinit,yinit,30,30);
         g.setColor(Color.GRAY);
         g.fillOval(xinit,yinit+30,30,30);
     }else{
            g.setColor(Color.GRAY);
            g.fillOval(xinit, yinit, 30, 30);
            g.setColor(Color.RED);
            g.fillOval(xinit, yinit + 30, 30, 30);
        }

    }
}
