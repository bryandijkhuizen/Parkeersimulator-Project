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
	public class tickSpeedSlider extends AbstractView {
		
		private JLabel enterSpeedLabel;

		private JTextField enterSpeed;
		

		private int speed;
		
		private JSlider enterSpeedSlider;
		
		/**
		 * This is the slideControl Constructor
		 */
		
		public tickSpeedSlider(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			enterSpeedLabel = new JLabel("Speed: ");
			
			enterSpeed = new JTextField();    
				
			enterSpeedSlider = new JSlider(0, 50, model.getEnterSpeed());
	        
			enterSpeedLabel.setBounds(5, 0, 200, 20);
			enterSpeed.setBounds(180, 3, 50, 20);
			enterSpeedSlider.setBounds(0, 30 , 200, 10);
			
	        add(enterSpeedLabel);
	        add(enterSpeed);
	        add(enterSpeedSlider);
	        
	        
	        enterSpeed.setEditable(false);
	        
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;
	        

	        speed = carPark.getEnterSpeed();
	        enterSpeed.setText("" + speed);
	        
	        
	        super.updateView();
	    }

		
	}