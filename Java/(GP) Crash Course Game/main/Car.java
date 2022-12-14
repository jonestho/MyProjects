package main;
import java.awt.*;
import java.util.ArrayList;

/**
 * Car class - Manages and draws cars of Crash Course Game.
 * @author - Mariah Bray, Dylan Howard, Thomas Jones
 * @version - 2/21/22
 */

public class Car{

    /** Represents the color of the car. */
    private Color carColor;
    /** Represents the X position of the obstacle. */
    private final int positionX;
    /** Represents the Y position of the obstacle. */
    private int positionY;
    /** Represents the location and crash area of the car. */
    private ArrayList<Point> points = new ArrayList<Point>();

    /**
     * A constructor to create a car.
     * @param x An integer representing the X position of the car.
     * @param y An integer representing the Y position of the car.
     */
    Car(int x, int y){
        positionX = x;
        positionY = y;
    }

    /**
     * A method to set the color of the car.
     * @param color A color object representing the desired color.
     */
    public void setColor(Color color){
        this.carColor = color;
    }

    /**
     * A method to get the color of the car.
     * @return A color object representing the desired color.
     */
    public Color getColor(){
        return carColor;
    }

    /**
     * A method to set the Y position of the car.
     * @param y An integer representing the desired Y position of the car.
     */
    public void setY(int y){
        if (y > 450 && y < 750){
            positionY = y;
        }
    }

    /**
     * A method to obtain the X position of the car.
     * @return An integer representing the X position of the car.
     */
    public int getX(){
        return positionX;
    }

    /**
     * A method to obtain the Y position of the car.
     * @return An integer representing the Y position of the car.
     */
    public int getY(){
        return positionY;
    }
    
    /**
     * A method to obtain the Array List of points that represent a car.
     * @return An Array List of points representing the car.
     */
    public ArrayList<Point> getPoints(){
        return points;
    }
    
    /**
     * A method to create a Volkswagen Beetle.
     * @param view A Graphics type object, this must be the same as the Graphics object used in all classes.
     */
    public void drawCar(Graphics view) {
        if(carColor == null){
            carColor = Color.pink;
        }
        points.clear();

        // create car body
        view.setColor(carColor);
        view.fillRect(positionX, positionY, 200, 50);
        
        for(int i=positionX + 100; i <= positionX + 200; ++i) {
        	for(int j=positionY; j <= positionY + 50; ++j) {
        		points.add(new Point(i,j));
        	}
        }
        
        view.setColor(carColor);
        view.fillOval(positionX + 30, positionY - 50, 135, 100);
         
         // create windows
        view.setColor(Color.CYAN);
        view.fillRect(positionX + 55, positionY - 35, 40, 30);
         
        view.setColor(Color.CYAN);
        view.fillRect(positionX + 105, positionY - 35, 40, 30);
         
         // create wheels
        view.setColor(Color.BLACK);
        view.fillOval(positionX + 20, positionY + 10, 50, 50);
         
        view.setColor(Color.BLACK);
        view.fillOval(positionX + 135, positionY + 10, 50, 50);
         
         // create headlights
        view.setColor(Color.WHITE);
        view.fillOval(positionX + 185, positionY, 20, 20);
        
    }
    
    /**
     * A method to create a Truck.
     * @param view A Graphics type object, this must be the same as the Graphics object used in all classes.
     */
    public void drawTruck(Graphics view) {
    	points.clear();
    	
        if(carColor == null){
            carColor = Color.pink;
        }
        
        //TRUCK
        view.setColor(carColor);
        view.fillRect(positionX, positionY - 10, 200, 50);
        
        //Create point array to represent truck
        for(int i=positionX + 100; i <= positionX + 200; ++i) {
        	for(int j=positionY - 10; j <= positionY + 40; ++j) {
        		points.add(new Point(i,j));
        	}
        }
 
        view.setColor(carColor);
        view.fillRect(positionX + 100, positionY - 50, 60, 75);
         
         // create windows
        view.setColor(Color.DARK_GRAY);
        view.fillRect(positionX + 110, positionY - 45, 40, 30);
         
         // create wheels
        view.setColor(Color.BLACK);
        view.fillOval(positionX + 20, positionY + 10, 50, 50);
         
        view.setColor(Color.BLACK);
        view.fillOval(positionX + 135, positionY + 10, 50, 50);
         
         // create headlights
        view.setColor(Color.WHITE);
        view.fillRect(positionX + 185, positionY, 15, 15);
        
    }

}
