package me.project.main;

import java.awt.Color;

import javax.swing.JFrame;

import me.project.abstracts.AbstractController;
import me.project.abstracts.AbstractView;
import me.project.controller.Controller;
import me.project.logic.CarParkingLogic;
import me.project.view.CarParkView;
import me.project.view.currentDayView;
import me.project.view.passHolderQueueView;
import me.project.view.totalCarsView;

/**
 * This class combines all of the functions
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
 *
 */

public class CarSimulator {

	private JFrame frame;
	private AbstractView carParkView;
    private CarParkingLogic carParking;
    private AbstractController controller;
    private totalCarsView totalCars;
    private currentDayView currentDay;
    private passHolderQueueView passHoldersQueueView;
    
    private int tickPause;
    public static boolean run;

	/**
	 * The constructor creates instances of CarParkingLogic, Controller, CarParkView, Screen.
	 */
	
    public CarSimulator() {
		
		/** 
		 * Here the Logic, View and controller are defined 
		 */
		
		carParking = new CarParkingLogic(3, 6, 30);
		controller = new Controller(carParking);
		controller.setBackground(Color.LIGHT_GRAY);
		carParkView = new CarParkView(carParking);
		carParkView.setBackground(Color.GRAY);
		totalCars = new totalCarsView(carParking);
		totalCars.setBackground(Color.LIGHT_GRAY);
		currentDay = new currentDayView(carParking);
		currentDay.setBackground(Color.LIGHT_GRAY);
		passHoldersQueueView = new passHolderQueueView(carParking);
		passHoldersQueueView.setBackground(Color.LIGHT_GRAY);
		
		
		frame=new JFrame("CarParking Simulation");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		frame.setSize(811, 560);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(carParkView);
		frame.getContentPane().add(controller);
		frame.getContentPane().add(totalCars);
		frame.getContentPane().add(currentDay);
		frame.getContentPane().add(passHoldersQueueView);

		carParkView.setBounds(27, 12, 737, 339);
		controller.setBounds(27, 376, 400, 101);
		totalCars.setBounds(437, 421, 275, 29);
		currentDay.setBounds(437, 450, 275, 29);
		passHoldersQueueView.setBounds(437, 470, 275, 29);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		carParkView.updateView();

        run = true;
        tickPause = 500;

        while(true){
            if (run) {
                carParking.tick();
            }
            try{
                Thread.sleep(tickPause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
}