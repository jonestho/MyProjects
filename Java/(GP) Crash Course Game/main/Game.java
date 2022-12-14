package main;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Game class -draws all components of Crash Course Game.
 * @author - Mariah Bray, Dylan Howard, Thomas Jones
 * @version - 2/21/22
 */
public class Game extends JPanel implements MouseMotionListener, MouseListener {
	/** Boolean isCar to determine if player will use a car or truck. */
    private boolean isCar = false;
    /** Boolean to determine if game is running. */
    private static boolean isRunning = true;
    /** Boolean to determine if game is in day mode. */
    private boolean isDay = true;
    /** Integer to determine current screen of game. */
    private int screen = 1;
    /** Integer to determine current lives left in game. */
    private int lives = 3;
    /** Integer to determine current score of game. */
    private int passed = 0;
    /** A Car object for the player to control */
    private Car car = new Car(100,475);
    /** A Car object to choose car model on vehicle choice screen. */
    private Car choice1 = new Car(250,400);
    /** A Car object to choose truck model on vehicle choice screen. */
    private Car choice2 = new Car(850,400);
    /** A Background object to control the game's background. */
    private Background b1 = new Background(1400);
    /** Collection of all active Obstacles in game. */
    private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    /** Collection of all active PowerUps in game. */
    private ArrayList<PowerUp> powers = new ArrayList<PowerUp>();

    /**
     * A method to paint the Game depending on screen value.
     * @param g A Graphics type object, this must be the same as the Graphics object used in all classes.
     */
    public void paintComponent(Graphics g) {

        // this statement required
        super.paintComponent(g);

        if(screen == 2 && lives <= 0){
            b1.drawBackgroundCrash(g);
            b1.drawTextFinalCrash(g, obstaclesPassed());
        }
        else{

            if(screen == 3){
            	if(isDay) {
            		setBackground(Color.CYAN);
            	}
            	else {
            		setBackground(Color.darkGray);
            	}
            	
                b1.drawBackgroundGame(g, isDay);
                
                for (int i = 0; i < obstacles.size(); ++i) {
                	if (i%2 == 0) {
                		obstacles.get(i).drawTrafficCone(g);
                	}
                	else {
                		obstacles.get(i).drawLog(g);
                	}
                }
                for(int i = 0; i< obstacles.size(); ++i) {
                	if(i%2 == 0) {
                		powers.get(i).drawFlower(g);
                	}
                	else {
                		//obstacles.get(i).drawLog(g);
                	}
                }
    
                // draw car
                if(isCar) {
                	car.drawCar(g);
                }
                else {
                	car.drawTruck(g);
                }
    
                g.setColor(Color.BLACK);
                g.setFont(new Font("Comic Sans", Font.PLAIN, 20)); 
                g.drawString("Obstacles Passed: " + obstaclesPassed() + " Lives: " + lives, 1100, 50);
                
            }
            else if(screen == 2){
                b1.drawBackgroundCrash(g);
                b1.drawTextCrash(g, lives);
            }
            else if(screen == 1){
                b1.drawStartScreen(g);
            }
            else if(screen == 4){
                b1.drawCarScreen(g);
                choice1.drawCar(g);
                choice2.drawTruck(g);
            }
            else{
                System.out.println("Error Screen");
            }
            // drawing must be written in code where pieces on the top layer are written last
        }
    }
    
    /**
     * A method to control the game loop.
     */
	public void run() {
		// Event Handling
		for (Obstacle o : obstacles) {
			if (crash(car.getPoints(), o.getPoints())) {
				o.move();
				lives = lives - 1;
				screen = 2;
			}
		}
		
		for (PowerUp p : powers) {
			if (crash(car.getPoints(), p.getPoints())) {
				p.move();
				lives = lives + 1;
				
			}
		}
		
		// Screen Update
		repaint();
	}
	
	/**
     * A method to add Obstacles to the game.
     * @param o An Obstacle to be added to the game.
     */
	public void addObstacle(Obstacle o) {
		obstacles.add(o);
	}
	
	/**
     * A method to add PowerUps to the game.
     * @param p An PowerUps to be added to the game.
     */
	public void addPowerUp(PowerUp p) {
		powers.add(p);
	}
	
	/**
     * A method to add up the score of the player.
     * @return An integer representing the player's score.
     */
	public int obstaclesPassed() {
		passed = 0;
		for(Obstacle o : obstacles) {
			passed += o.getCounter();
		}
		return passed;
	}
	
	/**
     * A method to check for crashes between game objects.
     * @param o1 An ArrayList of Points that represent the objects location.
     * @param o2 An ArrayList of Points that represent the other objects location.
     * @return An boolean representing if a crash occurred.
     */
	public boolean crash(ArrayList<Point> o1, ArrayList<Point> o2) {
		
		// make shallow copy of array to avoid concurrent modification
		o1 = (ArrayList<Point>)o1.clone();
		o2 = (ArrayList<Point>)o2.clone();
		
		for(Point p1 : o1){			
			for (Point p2 : o2) {
				
				if (p1.equals(p2)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
     * A method to control the player's movement.
     * @param e A MouseEvent representing the player's movement.
     */
    public void mouseDragged(MouseEvent e) {

        if(e.getY() < 425){
            car.setY(425);
        }
        else if(e.getY() > 700){
            car.setY(700);
        }
        else{
            car.setY(e.getY());
        }
    }
    
    /**
     * A method to control the player's game selections.
     * @param e A MouseEvent representing the player's selections.
     */
	public void mouseClicked(MouseEvent e) {
		if(screen == 1){
            if(e.getY() < 375 && e.getX() < 725){
                car.setColor(Color.blue);
                choice1.setColor(Color.blue);
                choice2.setColor(Color.blue);
            }
            else if(e.getY() >= 375 && e.getX() >= 725){
                car.setColor(Color.green);
                choice1.setColor(Color.green);
                choice2.setColor(Color.green);
            }
            else if(e.getY() < 375 && e.getX() >= 725){
                car.setColor(Color.magenta);
                choice1.setColor(Color.magenta);
                choice2.setColor(Color.magenta);
            }
            else if(e.getY() >= 375 && e.getX() < 725){
                car.setColor(Color.YELLOW);
                choice1.setColor(Color.YELLOW);
                choice2.setColor(Color.YELLOW);
            }
            else{
                car.setColor(Color.PINK);
                choice1.setColor(Color.PINK);
                choice2.setColor(Color.PINK);
            }
            screen = 4;
        }
		else if(screen == 3){
            if(e.getY() < 30 && e.getX() < 85){
                isDay = !isDay;
            }
		}
		
		// Allow player to restart game after crash.
		else if (screen == 2) {
			if(lives == 0) {
            	isRunning = false;
            }
			else {
				screen = 3;
			}
			
		}
		else if(screen == 4){
            if(e.getX() < 725){
                isCar = true;
            }
            else if(e.getX() >= 725){
            	isCar = false;
            }
            screen = 3;
        }
		
	}

	/**
     * Main method to start and run game.
     * @param a A String array.
     */
    public static void main(String[] a) {
        JFrame f = new JFrame();
        Game g = new Game();
        f.setContentPane(g);
        f.setSize(1450, 750);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
        Obstacle o1 = new Obstacle(1400, 500);
        Obstacle o2 = new Obstacle(1600 + 300, 650);
        g.addObstacle(o1);
        g.addObstacle(o2);
        
        PowerUp p1 = new PowerUp(2000, 550);
        g.addPowerUp(p1);

        Sound music = new Sound();
        music.playMusic();
        g.addMouseMotionListener(g);
        g.addMouseListener(g);
        while(isRunning) {
        	g.run();
        }
    }
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
    public void mouseMoved(MouseEvent e) {}
}