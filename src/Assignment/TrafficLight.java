package Assignment;

import java.util.Random;

public class TrafficLight {

    private int xpos;
    private int ypos;
    private boolean status = true;
    String whichEnd;


    public TrafficLight(String where) {
        this.whichEnd = where;
    }

    boolean isGreen() {

        return status;

    }

    void setStatus(boolean status) {

        this.status = status;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }
}

