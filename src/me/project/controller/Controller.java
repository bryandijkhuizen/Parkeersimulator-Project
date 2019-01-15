package me.project.controller;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		private JButton oneStep;
		private ActionEvent event;
		
	/**
	* Controller Constructor
	* Adds the controlls to the GUI
	*/
	
	public Controller(CarParkingLogic carParkingLogic) {
		super(carParkingLogic);
		
		

	}

	public void setActionEvent(ActionEvent e) {
		event = e;
	}
	
	public ActionEvent getActionEvent() {
		return event;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
	
	
	
	
