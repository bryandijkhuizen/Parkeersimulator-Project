package me.project.view;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

	/**
	 * This class contains the totalCarsCounter
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 *
	 */

	@SuppressWarnings("serial")
	public class slideControl extends AbstractView {
		
		private JLabel enterSpeedLabel;
		private JLabel exitSpeedLabel;

		private JTextField enterSpeed;
		private JTextField exitSpeed;
		

		private int speed;
		private int exit;
		
		private JSlider enterSpeedSlider;
		private JSlider exitSpeedSlider;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public slideControl(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			enterSpeedLabel = new JLabel("Enter Speed: ");
			exitSpeedLabel = new JLabel("Exit Speed: ");
			
			enterSpeed = new JTextField();    
			exitSpeed = new JTextField();
			
			
			enterSpeedSlider = new JSlider(0, 50, model.getEnterSpeed());
			exitSpeedSlider = new JSlider(0, 50, model.getExitSpeed());
	        
			enterSpeedLabel.setBounds(5, 0, 200, 20);
			enterSpeed.setBounds(180, 3, 50, 20);
			enterSpeedSlider.setBounds(0, 30 , 200, 10);
			
			exitSpeedLabel.setBounds(5, 45, 200, 20);
			exitSpeed.setBounds(180, 45, 50, 20);
			exitSpeedSlider.setBounds(0, 80, 200, 10);
			
	        add(enterSpeedLabel);
	        add(enterSpeed);
	        add(enterSpeedSlider);
	        
	        add(exitSpeedLabel);
	        add(exitSpeed);
	        add(exitSpeedSlider);

	        
	        enterSpeed.setEditable(false);
	        exitSpeed.setEditable(false);
	        
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;
	        
	        carPark.setEnterSpeed(enterSpeedSlider.getValue());
	        carPark.setExitSpeed(exitSpeedSlider.getValue());

	        speed = carPark.getEnterSpeed();
	        enterSpeed.setText("" + speed);
	        
	        exit = carPark.getExitSpeed();
	        exitSpeed.setText("" + exit);
	        
	        super.updateView();
	    }

		
	}