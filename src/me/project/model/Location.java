package me.project.model;

	/*
	 * This is the location class
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 * 
	 */

public class Location {

    private int floor;
    private int row;
    private int place;

    /**
     * Location Constructor
     */
    
    public Location(int floor, int row, int place) {
        this.floor = floor;
        this.row = row;
        this.place = place;
    }

    /**
     * @return The floor.
     */
    
    public int getFloor() {
        return floor;
    }

    /**
     * @return The row.
     */
    
    public int getRow() {
        return row;
    }

    /**
     * @return The place.
     */
    
    public int getPlace() {
        return place;
    }
    
    public void setRow(int row) {
    	this.row = row;
    }

}