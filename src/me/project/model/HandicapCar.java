package me.project.model;

import java.util.Random;

import me.project.abstracts.Car;
import me.project.view.Colors;

import java.awt.Color;

	/**
	 * Class for HandicapCar when they enter an how long the stay
	 * @author Bryan Dijkhuizen, Thalisa Jagt
	 * @version 1.0
	 */

public class HandicapCar extends Car {
	
	private static final Color COLOR=Colors.LIGHT_ORANGE;
	
	/**
	 * creates an instance of HandicapCar
	 * @return 
	 */
	
    public  HandicapCar() {
		
		Random random = new Random(); 
		
		minutesStay = (int) (30 + random.nextFloat() * 10 * 60);
    }
    
    /**
	 * gets the car's color
	 * @return color
	 */
    
    public Color getColor(){
    	return COLOR;
    }
}


