package Assignment.Gui;

import java.awt.*;
import java.util.ArrayList;

public class RoadShape {

    private String direction;
    private int xinit;
    private int yinit;
    private int length;
    private int width;
    private int roadNum;



    public RoadShape(int length, int width, String direction, int xinit, int yinit, int roadnum) {
        this.direction = direction;
        this.length = length;
        this.width = width;
        this.xinit = xinit;
        this.yinit = yinit;
        this.roadNum = roadnum;
    }

    int getXinit(){
        return xinit;
    }

    int getYinit(){
        return yinit;
    }


    String getDirection(){
        return direction;
    }
    int getLength(){
        return length;
    }

    //@Override

   int getXStart(){
        int x = 0;
        switch (direction){
            case "North":
          x = xinit+30;
                break;
            case "South":
                x = xinit+30;
                break;
            case "East":
                x = xinit;
                break;
            case "West":
                x = xinit+length;
                break;
        }
        return x;
   }

    int getYStart(){
        int y = 0;
        switch (direction){
            case "North":
                y = yinit+length;
                break;
            case "South":
                y = yinit;
                break;
            case "East":
                y = yinit+30;
                break;
            case "West":
                y = yinit+30;
                break;
        }
        return y;
    }


    public void draw(Graphics g) {
      //  g.fillRect(xinit, yinit, length, width);
        //super.draw(g);
        switch (direction) {
            case "North":
                g.setColor(Color.WHITE);
                g.fillRect(xinit, yinit, width, length);
                g.setColor(Color.BLACK);
                g.fillRect(xinit+5, yinit, width-10, length);
                g.fillRect(xinit, yinit - width, width, width);
                g.setColor(Color.BLUE);
                g.drawString("Road: "+this.roadNum, xinit, yinit+40);

                break;
            case "South":
                g.setColor(Color.WHITE);
                g.fillRect(xinit, yinit, width, length);
                g.setColor(Color.BLACK);
                g.fillRect(xinit+5, yinit, width-10, length);
                g.fillRect(xinit, yinit + length, width, width);
                g.setColor(Color.BLUE);
                g.drawString("Road: "+this.roadNum, xinit, yinit+40);

                break;
            case "East":
                g.setColor(Color.WHITE);
                g.fillRect(xinit, yinit, length, width);
                g.setColor(Color.BLACK);
                g.fillRect(xinit, yinit+5, length, width-10);
                g.fillRect(xinit+length, yinit , width, width);
                g.setColor(Color.BLUE);
                g.drawString("Road: "+this.roadNum, xinit, yinit+40);

                break;
            case "West":
                g.setColor(Color.WHITE);
                g.fillRect(xinit, yinit, length, width);
                g.setColor(Color.BLACK);
                g.fillRect(xinit, yinit+5, length, width-10);
                g.fillRect(xinit-width, yinit , width, width);
                g.setColor(Color.BLUE);
                g.drawString("Road: "+this.roadNum, xinit, yinit+40);

                break;
        }
    }
}



