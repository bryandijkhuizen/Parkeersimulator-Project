package me.project.model;

import java.util.Random;

import me.project.view.Colors;

import java.awt.Color;
import java.awt.*;

/**
 * Class for adHocCar when they enter an how long the stay
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
 *
 */

public class AdHocCar extends Car {
	
	private static final Color COLOR=Colors.PASTEL_RED;
	
	/**
	 * creates an instance of AdHocCar
	 */
	
    public AdHocCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }
    
    public Color getColor(){
    	return COLOR;
    }
}
