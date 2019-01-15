package me.project.main;

import javax.swing.JFrame;

import me.project.controller.AbstractController;
import me.project.controller.Controller;
import me.project.logic.CarParkingLogic;
import me.project.view.AbstractView;
import me.project.view.CarParkView;
import me.project.view.StatisticView;

/**
 * Main ParkingSimulator class
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
 *
 */

public class CarParkingSimulator {
	
	private JFrame screen;
	private AbstractView carParkView;
    private CarParkingLogic carParking;
    private AbstractController controller;
    private AbstractView statView;
    
    private int tickPause;
    public static boolean run;
	
	/**
	 * The constructor creates instances of CarParkingLogic, Controller, CarParkView, Screen.
	 */
    
	public CarParkingSimulator() {
		carParking = new CarParkingLogic(3, 6, 30);
		controller = new Controller(carParking);
		carParkView = new CarParkView(carParking);
		statView = new StatisticView(carParking);
		
		
		screen=new JFrame("CarParking Simulation");
		screen.setSize(1200, 500);
		screen.setLayout(null);
		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(controller);
		screen.getContentPane().add(statView);

		carParkView.setBounds(50, 10, 700, 330);
		controller.setBounds(0, 360, 750, 80);
		statView.setBounds(800, 10, 400, 20);

		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
		
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