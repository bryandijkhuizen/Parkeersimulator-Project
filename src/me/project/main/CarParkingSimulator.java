package me.project.main;

import javax.swing.JFrame;

import me.project.controller.AbstractController;
import me.project.controller.Controller;
import me.project.logic.CarParkingLogic;
import me.project.view.AbstractView;
import me.project.view.CarParkView;

public class CarParkingSimulator {
	
	private JFrame screen;
	private AbstractView carParkView;
    private CarParkingLogic carParking;
    private AbstractController controller;
    
    private int tickPause;
    public static boolean run;
	
	/**
	 * The Constructor creates new instances of LifeLogic, InitController, RunController, FieldView, StatView and screen.
	 * It sets the values (title, layout, size) for this screen, fills it and then makes it visible. 
	 */
	public CarParkingSimulator() {
		carParking = new CarParkingLogic(3, 6, 30);
		controller = new Controller(carParking);
		carParkView = new CarParkView(carParking);
		
		screen=new JFrame("Car park simulation");
		screen.setSize(1000, 820);
		screen.setLayout(null);
		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(controller);

		carParkView.setBounds(50, 10, 700, 330);
		controller.setBounds(0, 360, 750, 80);

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