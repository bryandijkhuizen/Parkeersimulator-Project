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
	private JButton btnQuit;
	private JButton startSimulator;
	private JButton pauseSimulator;
	private JButton oneStep;
	private JButton tenSteps;
	private JButton oneHundredSteps;
	private JButton oneHour;
	private JButton oneDay;
		
	/**
	* Controller Constructor
	* Adds the controls to the GUI
	*/
	
	public Controller(CarParkingLogic carParkingLogic) {
		super(carParkingLogic);
		
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
		
		oneStep = new JButton("oneStep");
		oneStep.addActionListener(this);
		add(oneStep);
		
		oneHour = new JButton("oneHour");
		oneHour.addActionListener(this);
		add(oneHour);
		
		oneDay = new JButton("oneDay");
		oneDay.addActionListener(this);
		add(oneDay);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		CarParkingLogic cpl = (CarParkingLogic) super.model;
	
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
	
	if (e.getSource() == oneStep) {
		try {
			cpl.steps(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return;
	}
	
	if (e.getSource() == oneHour) {
		try {
			cpl.steps(60);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return;
	}
	
	if (e.getSource() == oneDay) {
		try {
			cpl.steps(1440);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return;
	}
}
	
	
}