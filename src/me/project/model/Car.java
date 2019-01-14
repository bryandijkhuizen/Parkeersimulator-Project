package me.project.model;

import java.awt.*;

/*
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
 */

public abstract class Car extends AbstractModel {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;

    /**
     * Car constructor
     */
    public Car() {

    }
    
    /**
     * returns the location of the car
     * @return location
     */
    
    public Location getLocation() {
        return location;
    }

    /**
     * sets the location of the car to the specified location
     * @param location
     */
    
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns the amount of minutes the car has left to stay
     * @return minutesLeft
     */
    
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * Sets the amount of minutes which te car wil stay
     * @param minutesLeft
     */
    
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    /**
     * Returns if a car is paying
     * @return isPaying
     */
    
    public boolean getIsPaying() {
        return isPaying;
    }
    
    /**
     * Sets if the car is paying
     * @param isPaying
     */
    
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    /**
     * subtracts one minute from the time the car is staying
     */
    
    public void tick() {
        minutesLeft--;
        removeArrivalTime();
    }
    
    /**
     * does nothing, ReservationCar implements this method.
     */
    
    public void removeArrivalTime(){
    	
    }
    
    
    public abstract Color getColor();
}