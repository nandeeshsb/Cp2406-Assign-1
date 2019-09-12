package Assignment;

public class Vehicle {
    private int carlength = 2;
    private int length;
    private int breadth;
    private String direction;
    private String type;
    private int xpos;
    private int ypos;
    private int xprev;
    private int yprev;
    private int xrear;
    private int yrear;
    private int speed;
    private int xinit;
    private int yinit;
    private int roadRef = 0;


    public Vehicle(String direction, int speed, int x, int y) {
        this.xinit = x;
        this.yinit = y;
        this.direction = direction;
        this.speed = speed;
    }

    void setLength(int length) {
        /*Set the length of a vehicle */
        this.length = length;
    }

    int getCarlength() {
        /*Returns the length of a car */
        return carlength;
    }

    int getLength() {
        /*Returns the length of vehicle */
        return length;
    }


    void intiDir() {
        /*Initiates the front and rear positions of vehicle based on its length and direction */
        if (getDirection().equals("North")) {
            ypos = yinit + this.getLength();
            yrear = yinit;
            xpos = xinit;
        } else if (getDirection().equals("South")) {
            ypos = yinit - this.getLength();
            yrear = yinit;
            xpos = xinit;
        } else if (getDirection().equals("East")) {
            xpos = xinit + this.getLength();
            xrear = xinit;
            ypos = yinit;
        } else if (getDirection().equals("West")) {
            xpos = xinit - this.getLength();
            xrear = xinit;
            ypos = yinit;
        }
    }

    void moveVehicle() {
        /*Moves the vehicle based on its direction and speed */
        if (direction.equals("North")) {
            yprev = ypos;
            ypos = ypos + speed;
            yrear = yrear + speed;
        } else if (this.direction.equals("South")) {
            yprev = ypos;
            ypos = ypos - speed;
            yrear = yrear - speed;
        } else if (this.direction.equals("East")) {
            xprev = xpos;
            xpos = xpos + speed;
            xrear = xrear + speed;
        } else if (this.direction.equals("West")) {
            xprev = xpos;
            xpos = xpos - speed;
            xrear = xrear - speed;
        }

    }

    void setType(String type) {
        /*Sets the type of a vehicle */
        this.type = type;
    }

    public String getType() {
        /*Returns the type of a vehicle */
        return type;
    }

    String getDirection() {
        /*Returns the direction of a vehicle */
        return direction;
    }

    int getXpos() {
        /*Returns front x coordinate of a vehicle */
        return xpos;
    }

    int getYpos() {
        /*Returns front y coordinate of a vehicle */
        return ypos;
    }

    int getXrear() {
        /*Returns rear x coordinate of a vehicle */
        return xrear;
    }

    int getYrear() {
        /*Returns rear y coordinate of a vehicle */
        return yrear;
    }

    void setSpeed(int newspeed) {
        /*Sets the speed of a vehicle */
        this.speed = newspeed;
    }

    void setPos(int newposition) {
        /*Sets the front and rear positions of a vehicle based on its direction and length */
        if (getDirection().equals("North")) {
            ypos = newposition;
            yrear = newposition - this.getLength();
        } else if (getDirection().equals("South")) {
            ypos = newposition;
            yrear = newposition + this.getLength();
        } else if (getDirection().equals("East")) {
            xpos = newposition;
            xrear = newposition - this.getLength();
        } else if (getDirection().equals("West")) {
            xpos = newposition;
            xrear = newposition + this.getLength();
        }

    }

    int getPos() {
        /*Returns front coordinate of a vehicle based on its direction */
        int position = 0;
        if (direction.equals("North") || direction.equals("South")) {
            position = ypos;
        } else {
            position = xpos;
        }
        return position;
    }

    int getRear() {
        /*Returns rear coordinate of a vehicle based on its direction */
        int position = 0;
        if (direction.equals("North") || direction.equals("South")) {
            position = yrear;
        } else {
            position = xrear;
        }
        return position;

    }


    int getSpeed() {
        /*Returns the speed of a vehicle*/
        return speed;
    }

    public int getRoadRef() {
        /*Returns the reference road the vehicle is currently on*/
        return roadRef;

    }

    void setRoadRef(int num) {
        /*Sets the reference for road that a vehicle is currently on*/
        this.roadRef = num;

    }


}








