package me.project.main;

import java.awt.Color;

import javax.swing.JFrame;

import me.project.abstracts.AbstractController;
import me.project.abstracts.AbstractView;
import me.project.controller.Controller;
import me.project.logic.CarParkingLogic;
import me.project.view.CarParkView;
import me.project.view.currentDayView;
import me.project.view.currentTimeView;
import me.project.view.entranceQueueView;
import me.project.view.totalCarPercentageView;
import me.project.view.totalCarsView;
import me.project.view.totalPassHoldersView;
import me.project.view.totalRegularCarsView;
import me.project.view.totalReservationsCarsView;

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
    private currentTimeView CurrentTimeView;
    private entranceQueueView EntranceCarQueueView;
    private totalPassHoldersView tphv;
    private totalRegularCarsView rcv;
    private totalCarPercentageView tcpv;
    private totalReservationsCarsView totalRes;
    
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
		CurrentTimeView = new currentTimeView(carParking);
		CurrentTimeView.setBackground(Color.LIGHT_GRAY);
		EntranceCarQueueView = new entranceQueueView(carParking);
		EntranceCarQueueView.setBackground(Color.LIGHT_GRAY);
		tphv = new totalPassHoldersView(carParking);
		tphv.setBackground(Color.LIGHT_GRAY);
		rcv = new totalRegularCarsView(carParking);
		rcv.setBackground(Color.LIGHT_GRAY);
		tcpv= new totalCarPercentageView(carParking);
		tcpv.setBackground(Color.LIGHT_GRAY);
		totalRes = new totalReservationsCarsView(carParking);
		totalRes.setBackground(Color.LIGHT_GRAY);
		
		frame=new JFrame("CarParking Simulation");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		frame.setSize(1142, 587);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(carParkView);
		frame.getContentPane().add(controller);
		frame.getContentPane().add(totalCars);
		frame.getContentPane().add(currentDay);
		frame.getContentPane().add(CurrentTimeView);
		frame.getContentPane().add(EntranceCarQueueView);
		frame.getContentPane().add(tphv);
		frame.getContentPane().add(rcv);
		frame.getContentPane().add(tcpv);
		frame.getContentPane().add(totalRes);

		carParkView.setBounds(27, 12, 737, 339);
		controller.setBounds(125, 379, 511, 101);
		totalCars.setBounds(806, 31, 275, 29);
		currentDay.setBounds(806, 393, 275, 29);
		CurrentTimeView.setBounds(806, 435, 275, 29);
		EntranceCarQueueView.setBounds(806, 255, 275, 29);
		tphv.setBounds(806, 111, 275, 29);
		rcv.setBounds(806, 151, 275, 29);
		tcpv.setBounds(806, 188, 275, 29);
		totalRes.setBounds(806, 71, 275, 29);
		
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