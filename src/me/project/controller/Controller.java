package me.project.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import me.project.abstracts.AbstractController;
import me.project.logic.CarParkingLogic;
import me.project.main.CarSimulator;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

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
		btnQuit.setForeground(Color.WHITE);
		btnQuit.setFont(new Font("Source Sans Pro SemiBold", Font.BOLD, 15));
		btnQuit.setBackground(SystemColor.textHighlight);
		btnQuit.addActionListener(this);
		add(btnQuit);
		
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
		
		tenSteps = new JButton("tenSteps");
		tenSteps.setForeground(Color.WHITE);
		tenSteps.setFont(new Font("Source Sans Pro SemiBold", Font.BOLD, 15));
		tenSteps.setBackground(SystemColor.textHighlight);
		tenSteps.addActionListener(this);
		add(tenSteps);
		
		oneHundredSteps = new JButton("oneHundredSteps");
		oneHundredSteps.setForeground(Color.WHITE);
		oneHundredSteps.setFont(new Font("Source Sans Pro SemiBold", Font.BOLD, 15));
		oneHundredSteps.setBackground(SystemColor.textHighlight);
		oneHundredSteps.addActionListener(this);
		add(oneHundredSteps);
		
		oneStep = new JButton("oneStep");
		oneStep.setForeground(Color.WHITE);
		oneStep.setFont(new Font("Source Sans Pro SemiBold", Font.BOLD, 15));
		oneStep.setBackground(SystemColor.textHighlight);
		oneStep.addActionListener(this);
		add(oneStep);
		
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