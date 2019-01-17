package me.project.model;

import java.awt.*;
import java.util.Random;


/*
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
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
    
    public boolean getHasToPay() {
        return hasToPay;
    }

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
     * Store the location the particular car is parked in.
     * @param location The location the particular car is parked
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return minutesLeft Return how much minutes a particular car will be parked.
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * Keep track of how many minutes a car should be parked.
     * @param minutesLeft The amount of minutes the car should be parked
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    /**
     * @return isPaying	Return whether the particular car is paying
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * Change when a particular car is paying.
     * @param isPaying
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    @Override
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
	 * @return minutesStay The amount of time a particular car is parked
	 */
	public int getStayTime(){
		return minutesStay; 
	}
	   
    /**
     * This method creates a random number, where min and max are the boundries between which the
     * random number should lie.
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