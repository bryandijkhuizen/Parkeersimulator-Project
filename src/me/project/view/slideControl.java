package me.project.view;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

	/**
	 * This class contains the slideControl contstructor and updateView method
	 * @author Bryan Dijkhuizen
	 * @version 1.0
	 */

	@SuppressWarnings("serial")
	public class slideControl extends AbstractView {
		
		private JLabel enterSpeedLabel;
		private JLabel exitSpeedLabel;
		private JLabel maxCarsInQueueLabel;
		private JLabel ticketPriceLabel;

		private JTextField enterSpeed;
		private JTextField exitSpeed;
		private JTextField maxCarsInQueue;
		private JTextField ticketPrice;
		

		private int speed;
		private int exit;
		private int maxCars;
		private double price;
		
		private JSlider enterSpeedSlider;
		private JSlider exitSpeedSlider;
		private JSlider maxCarsInQueueSlider;
		private JSlider ticketPriceSlider;
		
		/**
		 * This is the slideControl Constructor
		 */
		
		public slideControl(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			enterSpeedLabel = new JLabel("Enter Speed: ");
			exitSpeedLabel = new JLabel("Exit Speed: ");
			maxCarsInQueueLabel = new JLabel("Max in Queue");
			ticketPriceLabel = new JLabel("Price per Hour");
			
			enterSpeed = new JTextField();    
			exitSpeed = new JTextField();
			maxCarsInQueue = new JTextField();
			ticketPrice = new JTextField();
			
			enterSpeedSlider = new JSlider(0, 50, model.getEnterSpeed());
			exitSpeedSlider = new JSlider(0, 50, model.getExitSpeed());
	        maxCarsInQueueSlider = new JSlider(0, 50, model.getMaxCarsInQueue());
			ticketPriceSlider = new JSlider(0, 30, (int)Math.round(model.getTicketPrice()));
			
			enterSpeedLabel.setBounds(5, 0, 200, 20);
			enterSpeed.setBounds(220, 25, 50, 20);
			enterSpeedSlider.setBounds(0, 30 , 200, 10);
			
			exitSpeedLabel.setBounds(5, 45, 200, 20);
			exitSpeed.setBounds(220, 70, 50, 20);
			exitSpeedSlider.setBounds(0, 80, 200, 10);
			
			maxCarsInQueueLabel.setBounds(5, 95, 200, 20);
			maxCarsInQueue.setBounds(220, 130, 50, 20);
			maxCarsInQueueSlider.setBounds(0, 130, 200, 10);
			
			ticketPriceLabel.setBounds(5, 150, 200, 20);
			ticketPrice.setBounds(220, 180, 50, 20);
			ticketPriceSlider.setBounds(0, 180, 200, 20);
			
	        add(enterSpeedLabel);
	        add(enterSpeed);
	        add(enterSpeedSlider);
	        
	        add(exitSpeedLabel);
	        add(exitSpeed);
	        add(exitSpeedSlider);
	        
	        add(maxCarsInQueueLabel);
	        add(maxCarsInQueue);
	        add(maxCarsInQueueSlider);

	        add(ticketPriceLabel);
	        add(ticketPrice);
	        add(ticketPriceSlider);
	        
	        enterSpeed.setEditable(false);
	        exitSpeed.setEditable(false);
	        maxCarsInQueue.setEditable(false);
	        ticketPrice.setEditable(false);
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;
	        
	        carPark.setEnterSpeed(enterSpeedSlider.getValue());
	        carPark.setExitSpeed(exitSpeedSlider.getValue());
	        carPark.setMaxCarsInQueue(maxCarsInQueueSlider.getValue());
	        carPark.setTicketPrice(ticketPriceSlider.getValue());

	        speed = carPark.getEnterSpeed();
	        enterSpeed.setText("" + speed);
	        
	        exit = carPark.getExitSpeed();
	        exitSpeed.setText("" + exit);
	        
	        maxCars = carPark.getMaxCarsInQueue();
	        maxCarsInQueue.setText("" + maxCars);
	        
	        price = carPark.getTicketPrice();
	        ticketPrice.setText("" + price);
	        
	        super.updateView();
	        
	    }

		
	}