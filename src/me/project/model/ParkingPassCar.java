package me.project.model;


import java.util.Random;

import me.project.abstracts.Car;
import me.project.view.otherView.Colors;

import java.awt.*;

	/**
	 * This is class ParkingPassCar
	 * This Class creates the parkingpasses
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 1.0
	 */

public class ParkingPassCar extends Car {
	private static final Color COLOR=Colors.MEMBER_BLUE;
	
	/**
	 * ParkingPassCar Constructor
	 */
	
    public ParkingPassCar() {
    	setIsMember(); 
		  
		Random random = new Random(); 
			
		minutesStay = (int) (random.nextFloat() * 10 * 60);
    }
    
    /**
   	 * gets the car's color
   	 * @return color
   	 */
    
    public Color getColor(){
    	return COLOR;
    }
}