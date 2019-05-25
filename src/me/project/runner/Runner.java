package me.project.runner;

import java.applet.Applet;

import javax.swing.JFrame;

import me.project.main.CarSimulator;

/**
	 * Runner Class to run the simulator.
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 14.01.2019
	 */

@SuppressWarnings("serial")
public class Runner extends Applet {
	
	/**
	 * Main Method; starts the program.
	 * @throws Exception 
	 */

	public static void main(String[] args) throws Exception {
		CarSimulator cp = new CarSimulator();
		
	}

}
