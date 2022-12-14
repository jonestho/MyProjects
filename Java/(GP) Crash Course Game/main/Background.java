package main;

import java.awt.*;

/**
 * Background class -Draws backgrounds of Crash Course Game.
 * @author - Mariah Bray, Dylan Howard, Thomas Jones
 * @version - 2/21/22
 */
public class Background {

    /** Represents the X position of the first cloud in the background. */
    private int firstCloud = 0;
    /** Represents the X position of the second cloud in the background. */
    private int secondCloud = firstCloud + 400;
    /** Represents the X position of the third cloud in the background. */
    private int thirdCloud = firstCloud + 700;

    /**
     * A constructor to create the games background.
     * @param firstCloud An integer representing the X position 
     * of the first cloud in the background (Recommended : 0).
     */
    Background(int firstCloud) {
        this.firstCloud = firstCloud;
    }
    
    /**
     * A method to draw the games background when it is being played.
     * @param view A Graphics type object, this must be the same as 
     * the Graphics object used in all classes. 
     */
    public void drawBackgroundGame(Graphics view, boolean day) {
    	
    	// Create Day/Night Mode button
    	view.setColor(Color.blue);
        view.fillRect(0, 0, 85, 30);
       
        // create road
        view.setColor(Color.GRAY);
        view.fillRect(0, 450, 1450, 600);
        view.setColor(Color.YELLOW);
        view.fillRect(0, 600, 1500, 10);

        if (day) {
        	// draw clouds
            drawCloud(firstCloud, 100, view);
            drawCloud(secondCloud, 30, view);
            drawCloud(thirdCloud, 230, view);
            
            view.setColor(Color.white);
            view.setFont(new Font("Comic Sans", Font.PLAIN, 15)); 
            view.drawString("Night Mode", 6, 20);
        }
        else {
        	// draw stars
            drawStar(firstCloud,100, view);
            drawStar(secondCloud,30, view);
            drawStar(thirdCloud, 230, view);
            
            view.setColor(Color.white);
            view.setFont(new Font("Comic Sans", Font.PLAIN, 15)); 
            view.drawString("Day Mode", 8, 20);
        }
        
        firstCloud = looperCloud(firstCloud,1);
        secondCloud = looperCloud(secondCloud,1);
        thirdCloud = looperCloud(thirdCloud,1);

    }

    /**
     * A method to draw the games background when it has yet to be started.
     * @param view A Graphics type object, this must be the same as 
     * the Graphics object used in all classes. 
     */
    public void drawStartScreen(Graphics view) {
        view.setColor(Color.blue);
        view.fillRect(0, 0, 725, 375);

        view.setColor(Color.green);
        view.fillRect(725, 375,1450 , 750);

        view.setColor(Color.magenta);
        view.fillRect(725, 0,1450, 375);

        view.setColor(Color.yellow);
        view.fillRect(0, 375,725 , 750);

        // display words
        view.setColor(Color.WHITE);
        view.setFont(new Font("Comic Sans", Font.PLAIN, 60));
        view.drawString("Crash Course!", 553, 340);
        view.setColor(Color.BLACK);
        view.setFont(new Font("Comic Sans", Font.PLAIN, 30)); 
        view.drawString("Click a Color to Start", 540, 435);
    }

    /**
     * A method to draw clouds for the games background.
     * @param x An integer representing the cloud's X position on screen.
     * @param y An integer representing the cloud's Y position on screen.
     */
    public void drawCloud(int x, int y, Graphics view) {

        view.setColor(Color.WHITE);
        view.fillOval( x, y, 50, 50);
        
        view.setColor(Color.WHITE);
        view.fillOval( x - 20, y + 15, 50, 50);
        
        view.setColor(Color.WHITE);
        view.fillOval( x + 20, y + 10, 50, 50);
        
    }
    
    /**
     * A method to draw stars for the games background.
     * @param x An integer representing the star's X position on screen.
     * @param y An integer representing the star's Y position on screen.
     */
    public void drawStar(int x, int y, Graphics view) {

    	// Stars
    	int cordX[] = {x, x-25, x+15, x+40, x+40, x+80, x+40, x+40, x+15, x-25};
  	  	int cordY[] = {y, y-35, y-20, y-50, y-12, y, y+12, y+50, y+20, y+35};
  	  	view.setColor(Color.YELLOW);
  	  	view.fillPolygon( cordX, cordY, 10);
        
    }
    
    /**
     * A method to draw clouds for the games background.
     * @param x An integer representing the cloud's X position on screen.
     * @param y An integer representing the cloud's Y position on screen.
     */
    public void drawStars(int x, int y, Graphics view) {

        int cordX[] = {x, x-25, x+15, x+40, x+40, x+80, x+40, x+40, x+15, x-25};
        int cordY[] = {y, y-35, y-20, y-50, y-12, y, y+12, y+50, y+20, y+35};
        view.setColor(Color.YELLOW);
        view.fillPolygon( cordX, cordY, 10);
    }

    /**
     * A method to allow clouds to loop back around once they have gone off-screen
     * @param l An integer representing the cloud's X position on screen.
     * @param p An integer representing how many pixels the cloud is to move each loop.
     * @return An integer representing the new X position of the cloud.
     */
    public int looperCloud(int l, int p) {
        if(l < -10){
            l = 1500;
        }
        else{
            l = l-p;
        }
        return l;
    }

    /**
     * A method to draw the games background when a crash has occurred.
     * @param view A Graphics type object, this must be the same as 
     * the Graphics object used in all classes. 
     */
    public void drawBackgroundCrash(Graphics view) {
        view.setColor(Color.RED);
        view.fillRect(0, 0, 1500, 750);
        view.setColor(Color.WHITE);
        view.setFont(new Font("Comic Sans", Font.PLAIN, 50));
        view.drawString("CRASH!", 600, 300);
    }
    
    /**
     * A method to draw the games text when the final crash has occurred.
     * @param view A Graphics type object, this must be the same as 
     * the Graphics object used in all classes. 
     * @param passed An integer representing how many objects the player has passed.
     */
    public void drawTextFinalCrash(Graphics view, int passed) {
    	view.setColor(Color.WHITE);
        view.setFont(new Font("Comic Sans", Font.PLAIN, 30)); 
        view.drawString("Game Over", 600, 500);
        view.drawString("Obstacles Passed: " + passed, 550 , 450);
    }
    
    /**
     * A method to draw the games text when a crash has occurred.
     * @param view A Graphics type object, this must be the same as 
     * the Graphics object used in all classes. 
     * @param lives An integer representing how many lives the player has left.
     */
    public void drawTextCrash(Graphics view, int lives) {
    	view.setColor(Color.WHITE);
        view.setFont(new Font("Comic Sans", Font.PLAIN, 30)); 
        view.drawString("Lives Left: " + lives, 605, 450);
        view.drawString("Click to Continue", 575, 500);
    }
    
    /**
     * A method to draw the vehicle choice screen background.
     * @param view A Graphics type object, this must be the same as 
     * the Graphics object used in all classes. 
     * @param lives An integer representing how many lives the player has left.
     */
    public void drawCarScreen(Graphics view) {
        view.setColor(Color.BLACK);
        view.setFont(new Font("Comic Sans", Font.PLAIN, 30)); 
        view.drawString("Choose a vehicle!", 530, 235);   
    }
    
}
