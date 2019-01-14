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
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu1, Thalisa Jagt
 *
 */

@SuppressWarnings("serial")
public class Controller extends AbstractController implements ActionListener {
	private JButton oneStep, start, quit, stop;
	private JButton incrementEnterSpeed, decrementEnterSpeed, incrementWeekDayArrivals, decrementWeekDayArrivals, resetAll;
	private ActionEvent event;
	private JButton getTotalCars, continueSim;
	
/**
* Controller Constructor
* Adds the buttons to the GUI
*/
	
	public Controller(CarParkingLogic carParkingLogic) {
		super(carParkingLogic);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
			
    	oneStep = new JButton("One Step");
    	gbc.gridx = 1;
    	gbc.gridy = 0;
    	oneStep.setBackground(Color.MAGENTA);
    	oneStep.addActionListener((ActionListener) this);
        add(oneStep, gbc);
       
        start= new JButton("Start");
        gbc.gridx = 3;
    	gbc.gridy = 0;
    	start.setBackground(Color.GREEN);
        start.addActionListener((ActionListener) this);
        add(start, gbc);
        
        getTotalCars = new JButton("Total Cars");
        gbc.gridx = 5;
        gbc.gridy = 0;
        getTotalCars.setBackground(Color.YELLOW);
        getTotalCars.addActionListener((ActionListener) this);
        add(getTotalCars, gbc);
        
        stop = new JButton("Stop");
        gbc.gridx = 7;
        gbc.gridy = 0;
        stop.setBackground(Color.CYAN);
        stop.addActionListener((ActionListener) this);
        add(stop, gbc);
        
        continueSim = new JButton("Continue");
        gbc.gridx = 9;
        gbc.gridy = 0;
        continueSim.setBackground(Color.ORANGE);
        continueSim.addActionListener((ActionListener) this);
        add(continueSim, gbc);
        
        quit = new JButton("Quit");
        gbc.gridx = 11;
    	gbc.gridy = 0;
    	quit.setBackground(Color.RED);
        quit.addActionListener((ActionListener) this);
        add(quit, gbc);  
        
        incrementEnterSpeed = new JButton("Increment Enter Speed");
        gbc.gridx = 19;
        gbc.gridy = 0;
        incrementEnterSpeed.addActionListener((ActionListener) this);
        add(incrementEnterSpeed, gbc);
        
        decrementEnterSpeed = new JButton("Decrement Enter Speed");
        gbc.gridx = 19;
        gbc.gridy = 2;
        decrementEnterSpeed.addActionListener((ActionListener) this);
        add(decrementEnterSpeed, gbc);
        
        incrementWeekDayArrivals = new JButton("Increment WeekDayArrivals");
        gbc.gridx = 19;
        gbc.gridy = 4;
        incrementWeekDayArrivals.addActionListener((ActionListener) this);
        add(incrementWeekDayArrivals, gbc);
        
        decrementWeekDayArrivals = new JButton("Decrement WeekDayArrivals");
        gbc.gridx = 19;
        gbc.gridy = 6;
        decrementWeekDayArrivals.addActionListener((ActionListener) this);
        add(decrementWeekDayArrivals, gbc);
        
        resetAll = new JButton("Reset All");
        gbc.gridx = 19;
        gbc.gridy = 8;
        resetAll.addActionListener((ActionListener) this);
        add(resetAll, gbc);
        
        setVisible(true);
	}

	public void setActionEvent(ActionEvent e) {
		event = e;
	}
	
	public ActionEvent getActionEvent() {
		return event;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		setActionEvent(e);
		Thread performerThread = new Thread(){
			
			 public void run (){
				ActionEvent e = getActionEvent();
				String command = e.getActionCommand();
					if (command == "One Step"){
						CarParkingLogic.tick();
					} 

					if (command == "Start"){
						CarParkingLogic.buttonTick(100000);
					}
					
					if(command == "Stop") {
						CarParkingLogic.Stop();
					}
					
					if(command == "Continue") {
						CarParkingLogic.Continue();
					}
					
					if(command == "Quit") {
						System.exit(0);
					}
					
					if(command == "Total Cars") {
						System.out.println("Total AdHoc Cars: " + CarParkingLogic.getTotalAdHocCars());
						System.out.println("Total Pass Cars: " + CarParkingLogic.getTotalPassCars());
						System.out.println("Total Cars: " + CarParkingLogic.getTotalCars());
					}	
					
					if(command == "Increment Enter Speed") {
						CarParkingLogic.incrementEnteringSpeed();
						System.out.println("Current Enter Speed: " + CarParkingLogic.getEnterSpeed());
					}
					
					if(command == "Decrement Enter Speed") {
						CarParkingLogic.decrementEnteringSpeed();
						System.out.println("Current Enter Speed: " + CarParkingLogic.getEnterSpeed());
					}
					
					if(command ==  "Increment WeekDayArrivals") {
						CarParkingLogic.incrementWeekDayArrivals();
						System.out.println("Current WeekDayArrivals: " + CarParkingLogic.getWeekDayArrivals());
					}
					
					if(command ==  "Decrement WeekDayArrivals") {
						CarParkingLogic.decrementWeekDayArrivals();
						System.out.println("Current WeekDayArrivals: " + CarParkingLogic.getWeekDayArrivals());
					}
					
					if(command == "Reset All") {
						CarParkingLogic.resetAll();
						System.out.println("All values have been reset");
					}
			}
		};
			performerThread.start();
	}
}
	
	
	
	
