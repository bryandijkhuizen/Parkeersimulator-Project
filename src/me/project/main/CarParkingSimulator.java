package me.project.main;

import me.project.controller.Controller;
import me.project.logic.CarParkingLogic;


/**
 * Main class to run the project
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
 * 
 */

public class CarParkingSimulator {
	
	@SuppressWarnings("unused")
	public CarParkingSimulator() {
			CarParkingLogic carParkingLogic = new CarParkingLogic();
			Controller controller = new Controller(carParkingLogic);
	}
}
