package me.project.model;


import java.util.Random;

import me.project.abstracts.Car;
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
    	setIsMember(); 
		  
		Random random = new Random(); 
			
		minutesStay = (int) (15 + random.nextFloat() * 10 * 60);
    }
    
    /**
   	 * gets the car's color
   	 * @return color
   	 */
    
    public Color getColor(){
    	return COLOR;
    }
}