package main;
import java.awt.*;
import java.util.ArrayList;

/**
 * Obstacle class - Manages and draws obstacles of Crash Course Game.
 * @author - Mariah Bray, Dylan Howard, Thomas Jones
 * @version - 2/21/22
 */
public class PowerUp{

    /** Represents the X position of the PowerUp. */
    private int positionX;
    /** Represents the Y position of the PowerUp. */
    private int positionY;
    /** Represents the location and crash area of the PowerUp. */
    private ArrayList<Point> points = new ArrayList<Point>();

    /**
     * A constructor to create an PowerUp.
     * @param x An integer representing the X position of the PowerUp.
     * @param y An integer representing the Y position of the PowerUp.
     */
    PowerUp(int x, int y){
        positionX = x;
        positionY = y;
    }

    /**
     * A method to set the X position of the PowerUp.
     * @param x An integer representing the desired X position of the PowerUp.
     */
    public void setX(int x){
        if (x > -10 && x<=1600){
            positionX = x;
        }
    }

    /**
     * A method to set the Y position of the PowerUp.
     * @param y An integer representing the desired Y position of the PowerUp.
     */
    public void setY(int y){
        if (y > 450 && y<750){
            positionY = y;
        }
    }

    /**
     * A method to obtain the X position of the PowerUp.
     * @return An integer representing the X position of the PowerUp.
     */
    public int getX(){
        return positionX;
    }

    /**
     * A method to obtain the Y position of the PowerUp.
     * @return An integer representing the Y position of the PowerUp.
     */
    public int getY(){
        return positionY;
    }
    
    /**
     * A method to move the PowerUp out of the player's range after a crash.
     */
    public void move() {
    	positionX = looperX(positionX, 220);
    	points.clear();
    }
    
    /**
     * A method to obtain the Array List of points that represent an PowerUp.
     * @return An Array List of points representing the PowerUp.
     */
    public ArrayList<Point> getPoints(){
        return points;
    }

    /**
     * A method to create a flower and control the movement for this specific PowerUp.
     * @param view A Graphics type object, this must be the same as the Graphics object used in all classes.
     */
    public void drawFlower(Graphics view){
    	points.clear();
        positionX = looperX(positionX,4);
        
        view.setColor(Color.pink);
        view.fillOval(positionX + 20, positionY + 7, 25, 25);
        view.fillOval(positionX-20, positionY+7, 25, 25);
        view.fillOval(positionX+12, positionY-15, 25, 25);
        view.fillOval(positionX-12, positionY-15, 25, 25);
        view.fillOval(positionX, positionY+20, 25, 25);
        view.setColor(Color.yellow);
        view.fillOval(positionX, positionY, 25, 25);
        
        //Create point array to represent log
        for(int i=positionX -40; i <= positionX + 40; ++i) {
        	for(int j=positionY - 30; j <= positionY + 40; ++j) {
        		points.add(new Point(i,j));
        	}
        }
    }

    /**
     * A method to allow PowerUps to loop back around once they have gone off-screen
     * @param l An integer representing the PowerUp's X position on screen.
     * @param p An integer representing how many pixels the PowerUp is to move each loop.
     * @return An integer representing the new X position of the PowerUp.
     */
    public int looperX(int l, int p){
        if(l < -10){
            //positionY = positionY - 11;
            l = 3000;
        }
        else{
            l = l-p;
        }
        return l;
    }
}
