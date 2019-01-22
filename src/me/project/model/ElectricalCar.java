package me.project.model;

import java.util.Random;

import me.project.abstracts.Car;
import me.project.view.otherView.Colors;

import java.awt.Color;

	/**
	 * Class for ElectricalCar when they enter an how long the stay
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 1.0
	 */

public class ElectricalCar extends Car {
	
	private static final Color COLOR=Colors.DARK_YELLOW;
	
	/**
	 * creates an instance of AdHocCar
	 */
	
    public ElectricalCar() {
		
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