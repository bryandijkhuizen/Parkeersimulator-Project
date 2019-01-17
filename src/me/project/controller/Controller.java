package me.project.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import me.project.abstracts.AbstractController;
import me.project.logic.CarParkingLogic;
import me.project.main.CarSimulator;

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
	private JButton startSimulator;
	private JButton pauseSimulator;
	private JButton tenSteps;
	private JButton oneHundredSteps;
		
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
		
		startSimulator = new JButton("Start Simulator");
		startSimulator.addActionListener(this);
		add(startSimulator);
		
		pauseSimulator = new JButton("Pause Simulator");
		pauseSimulator.addActionListener(this);
		add(pauseSimulator);
		
		tenSteps = new JButton("tenSteps");
		tenSteps.addActionListener(this);
		add(tenSteps);
		
		oneHundredSteps = new JButton("oneHundredSteps");
		oneHundredSteps.addActionListener(this);
		add(oneHundredSteps);
		
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
	
	if (e.getSource() == startSimulator) {
		try {
			CarSimulator.run = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return;
	}
	
	if (e.getSource() == pauseSimulator) {
		try {
			CarSimulator.run = false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return;
	}
	
	if (e.getSource() == tenSteps) {
		try {
			cpl.steps(10);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return;
	}
	
	if (e.getSource() == oneHundredSteps) {
		try {
			cpl.steps(100);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return;
	}
}
	
	
}