package me.project.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import me.project.abstracts.AbstractController;
import me.project.logic.CarParkingLogic;
import me.project.main.CarSimulator;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

	/**
	 * This is class Controller for Simulator 
	 * This Class contains all the additional functions in the GUI of the simulator
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 3.0.0 (22-01-2019)
	 */
	
@SuppressWarnings("serial")
public class Controller extends AbstractController implements ActionListener {
	private JButton startSimulator;
	private JButton pauseSimulator;
	private JButton oneHour;
	private JButton oneDay;
		
	/**
	* Controller Constructor
	* Adds the controls to the GUI
	*/
	
	public Controller(CarParkingLogic carParkingLogic) {
		super(carParkingLogic);
		
		startSimulator = new JButton("Start Simulator");
		startSimulator.setForeground(Color.WHITE);
		startSimulator.setFont(new Font("Source Sans Pro SemiBold", Font.BOLD, 15));
		startSimulator.setBackground(SystemColor.textHighlight);
		startSimulator.addActionListener(this);
		add(startSimulator);
		
		pauseSimulator = new JButton("Pause Simulator");
		pauseSimulator.setForeground(Color.WHITE);
		pauseSimulator.setFont(new Font("Source Sans Pro SemiBold", Font.BOLD, 15));
		pauseSimulator.setBackground(SystemColor.textHighlight);
		pauseSimulator.addActionListener(this);
		add(pauseSimulator);
		
		oneHour = new JButton("oneHour");
		oneHour.setForeground(Color.WHITE);
		oneHour.setFont(new Font("Source Sans Pro SemiBold", Font.BOLD, 15));
		oneHour.setBackground(SystemColor.textHighlight);
		oneHour.addActionListener(this);
		add(oneHour);
		
		oneDay = new JButton("oneDay");
		oneDay.setForeground(Color.WHITE);
		oneDay.setFont(new Font("Source Sans Pro SemiBold", Font.BOLD, 15));
		oneDay.setBackground(SystemColor.textHighlight);
		oneDay.addActionListener(this);
		add(oneDay);	
	}
	
	/**
	 * registers the actions when clicked on the button
	 */
	
	public void actionPerformed(ActionEvent e) {
		CarParkingLogic cpl = (CarParkingLogic) super.model;
	
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