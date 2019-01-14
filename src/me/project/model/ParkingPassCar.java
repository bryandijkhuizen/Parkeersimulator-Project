package me.project.model;


import java.util.Random;

import me.project.view.Colors;

import java.awt.*;

/*
 * This is class ParkingPassCar
 * This Class creates the parkingpasses
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
 */

public class ParkingPassCar extends Car {
	private static final Color COLOR=Colors.PASTEL_BLUE;
	
	/**
	 * ParkingPassCar Constructor
	 */
	
    public ParkingPassCar() {
    	Random random = new Random();
    	
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
    	
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    public Color getColor(){
    	return COLOR;
    }
}
