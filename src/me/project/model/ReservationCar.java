package me.project.model;

import java.util.Random;

import me.project.abstracts.Car;
import me.project.view.Colors;

import java.awt.Color;

	/**
	 * Class for adHocCar when they enter an how long the stay
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 *
	 */

public class ReservationCar extends Car {
	
	private static final Color COLOR=Colors.PASTEL_GREEN;
	
	/**
	 * creates an instance of AdHocCar
	 */
	
    public ReservationCar() {
		
		Random random = new Random(); 
		
		minutesStay = (int) (15 + (random.nextFloat() * 10 * 60) + 30);
    }
    
    /**
	 * gets the car's color
	 * @return color
	 */
    
    public Color getColor(){
    	return COLOR;
    }
}