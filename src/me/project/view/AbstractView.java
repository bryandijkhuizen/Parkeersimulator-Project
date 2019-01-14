package me.project.view;

import javax.swing.*;

import me.project.logic.CarParkingLogic;

/**
 * Abstract class for the View subclasses
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
 *
 */

@SuppressWarnings("serial")
public abstract class AbstractView extends JFrame {
	protected static CarParkingLogic carParkingLogic;

	@SuppressWarnings("static-access")
	public AbstractView(CarParkingLogic carParkingLogic) {
		this.carParkingLogic=carParkingLogic;
		carParkingLogic.addView(this);
		
	}
	
	public CarParkingLogic getModel() {
		return carParkingLogic;
	}
	
	public void updateView() {
		repaint();
	}
	
}