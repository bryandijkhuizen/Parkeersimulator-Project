package me.project.controller;

import javax.swing.JPanel;

import me.project.logic.CarParkingLogic;

/**
 * Abstract Class for the controller
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
 *
 */

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	protected CarParkingLogic carParkingLogic;
	
	
	/**
	 * AbstractController Constructor
	 */
	
	public AbstractController(CarParkingLogic carParkingLogic) {
		this.carParkingLogic=carParkingLogic;
	}
}
