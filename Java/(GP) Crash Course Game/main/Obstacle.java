package main;
import java.awt.*;
import java.util.ArrayList;

/**
 * Obstacle class - Manages and draws obstacles of Crash Course Game.
 * @author - Mariah Bray, Dylan Howard, Thomas Jones
 * @version - 2/21/22
 */
public class Obstacle{

    /** Represents the X position of the obstacle. */
    private int positionX;
    /** Represents the Y position of the obstacle. */
    private int positionY;
    /**  Represents the number of times the obstacle has been passed successfully. */
    private int obstacleCounter = 0;

    /** The color used to create bark in the log obstacle. */
    private Color woodColor = new Color(65,35,0);
    /** The color used to create wood in the log obstacle. */
    private Color innerColor = new Color(222,188,153);
    /** The color used to create the cone in the traffic cone obstacle. */
    private Color trafficColor = new Color(255, 100, 0);
    /** Represents the location and crash area of the obstacle. */
    private ArrayList<Point> points = new ArrayList<Point>();

    /**
     * A constructor to create an obstacle.
     * @param x An integer representing the X position of the obstacle.
     * @param y An integer representing the Y position of the obstacle.
     */
    Obstacle(int x, int y){
        positionX = x;
        positionY = y;
    }

    /**
     * A method to set the X position of the obstacle.
     * @param x An integer representing the desired X position of the obstacle.
     */
    public void setX(int x){
        if (x > -10 && x<=1600){
            positionX = x;
        }
    }

    /**
     * A method to set the Y position of the obstacle.
     * @param y An integer representing the desired Y position of the obstacle.
     */
    public void setY(int y){
        if (y > 450 && y<750){
            positionY = y;
        }
    }

    /**
     * A method to obtain the X position of the obstacle.
     * @return An integer representing the X position of the obstacle.
     */
    public int getX(){
        return positionX;
    }

    /**
     * A method to obtain the Y position of the obstacle.
     * @return An integer representing the Y position of the obstacle.
     */
    public int getY(){
        return positionY;
    }

    /**
     * A method to obtain the internal count of obstacles the user has passed successfully.
     * @return An integer representing the obstacles passed count.
     */
    public int getCounter(){
        return obstacleCounter;
    }
    
    /**
     * A method to move the obstacle out of the player's range after a crash.
     */
    public void move() {
    	positionX = looperX(positionX, 220);
    	points.clear();
    }
    
    /**
     * A method to obtain the Array List of points that represent an obstacle.
     * @return An Array List of points representing the obstacle
     */
    public ArrayList<Point> getPoints(){
        return points;
    }
    
    /**
     * A method to create a traffic cone and control the movement for this specific obstacle.
     * @param view A Graphics type object, this must be the same as the Graphics object used in all classes.
     */
    public void drawTrafficCone(Graphics view){
    	
    	points.clear();
    	
        positionX = looperX(positionX,4);
        view.setColor(trafficColor);
        view.fillPolygon(new int[] {positionX, positionX+25, positionX+50}, new int[] {positionY, positionY-50, positionY}, 3);
        view.fillRect(positionX - 5, positionY, 60, 10);
        view.setColor(Color.WHITE);
        view.fillPolygon(new int[] {positionX+15, positionX+25, positionX+35}, new int[] {positionY-30, positionY-50, positionY-30}, 3);
        
        //Create point array to represent triangle
        for(int i=positionX - 5; i <= positionX + 55; ++i) {
        	for(int j=positionY; j <= positionY + 10; ++j) {
        		points.add(new Point(i,j));
        	}
        }
        
        for(int i=positionX + 15; i <= positionX + 35; ++i) {
        	for(int j= positionY - 30; j <= positionY; ++j) {
        		points.add(new Point(i,j));
        	}
        }

    }

    /**
     * A method to create a log and control the movement for this specific obstacle.
     * @param view A Graphics type object, this must be the same as the Graphics object used in all classes.
     */
    public void drawLog(Graphics view){
    	points.clear();
        positionX = looperX(positionX,6);
        view.setColor(woodColor);
        view.fillOval(positionX, positionY, 60, 60);
        view.setColor(innerColor);
        view.fillOval(positionX + 5, positionY + 5, 50, 50);
        view.setColor(woodColor);
        view.drawOval(positionX+10, positionY+10, 40, 40);
        view.drawOval(positionX+15, positionY+15, 30, 30);
        view.drawOval(positionX+20, positionY+20, 20, 20);
        view.drawOval(positionX+25, positionY+25, 10, 10);
        
        //Create point array to represent log
        for(int i=positionX + 5; i <= positionX + 55; ++i) {
        	for(int j=positionY + 5; j <= positionY + 55; ++j) {
        		points.add(new Point(i,j));
        	}
        }
    }

    /**
     * A method to allow obstacle to loop back around once they have gone off-screen
     * @param l An integer representing the obstacles X position on screen.
     * @param p An integer representing how many pixels the obstacle is to move each loop.
     * @return An integer representing the new X position of the obstacle.
     */
    public int looperX(int l, int p){
        if(l < -10){
            //positionY = positionY - 11;
            l = 1500;
            obstacleCounter++;
        }
        else{
            l = l-p;
        }
        return l;
    }
}
