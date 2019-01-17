package me.project.main;

import javax.swing.JFrame;

import me.project.abstracts.AbstractController;
import me.project.abstracts.AbstractView;
import me.project.controller.Controller;
import me.project.logic.CarParkingLogic;
import me.project.view.CarParkView;
import me.project.view.totalCarsView;
import java.awt.Color;

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
		
		
		frame=new JFrame("CarParking Simulation");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		frame.setSize(811, 560);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(carParkView);
		frame.getContentPane().add(controller);
		frame.getContentPane().add(totalCars);

		carParkView.setBounds(27, 12, 737, 339);
		controller.setBounds(27, 376, 400, 74);
		totalCars.setBounds(437, 421, 275, 29);
		

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