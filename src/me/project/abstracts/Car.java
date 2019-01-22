package me.project.abstracts;

import java.awt.*;
import java.util.Random;

import me.project.model.Location;


	/**
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 1.0.0
	 */


public abstract class Car extends AbstractModel {
	private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean isMember;
    protected int minutesStay; 
    private boolean hasToPay;
    
    /**
     * Constructor for objects of class Car
     */
    
    public Car() {
    	isMember = false; 
    }
    
    /**
     * Check if a car has to pay
     * @return boolean(hasToPay)
     */
    
    public boolean getHasToPay() {
        return hasToPay;
    }
    
    /**
     * sets hasToPay to true or false
     * @param hasToPay
     */

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    /**
     * @return location Return the location where the particular car is parked
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location 
     */
    
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return minutesLeft 
     */
    
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * @param minutesLeft 
     */
    
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    /**
     * @return isPaying	
     */
    
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * @param isPaying
     */
    
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * Whenever a minute (tick) passes, the car will stay one minute less before leaving.
     */
    
    public void tick() {
        minutesLeft--;
    }
    
    /**
     * Make a car a member.
     */
    
    public void setIsMember(){
    	isMember = true; 
    }
    
    /**
    * Check if isMember is true in the simulator class, then let it skip payment if true. 
    */
    
	public boolean getIsMember(){
		return isMember; 
	}

	/**
	 * @return minutesStay 
	 */
	
	public int getStayTime(){
		return minutesStay; 
	}
	   
    /**
     * This method creates a random number.
     * @param max
     * @param min
     * @return returns a random number between the max and min value 
     */
	
	public int randInt(int max, int min){
	    	Random rand;
	    	rand = new Random();
	    	
	    	int randNum = rand.nextInt(max - min) + min; 
	    	
	    	return randNum;
	    }
	
	 /**
	  * gets the car's color
	  * @return color
	  */

	public abstract Color getColor();

}