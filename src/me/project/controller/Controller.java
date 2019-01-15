package me.project.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import me.project.logic.CarParkingLogic;

	/**
	 * This is class Controller for Simulator 
	 * This Class contains all the additional functions in the GUI of the simulator
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 *
	 */
	
	@SuppressWarnings("serial")
	public class Controller extends AbstractController implements ActionListener {
		private JButton printCars;
		private JButton btnQuit;
		
	/**
	* Controller Constructor
	* Adds the controls to the GUI
	*/
	
	public Controller(CarParkingLogic carParkingLogic) {
		super(carParkingLogic);
		
		printCars = new JButton("Print CarParking information");
		printCars.addActionListener(this);
		add(printCars);
		
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(this);
		add(btnQuit);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		CarParkingLogic cpl = (CarParkingLogic) super.model;
	
	if (e.getSource() == printCars) {
		try {
			cpl.printCarParkingDetails();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return;
	}
	
	if (e.getSource() == btnQuit) {
		try {
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return;
	}
}
	
	
}