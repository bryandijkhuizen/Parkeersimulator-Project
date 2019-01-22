package me.project.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

	/**
	 * This class contains the totalCarsCounter
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 1.0
	 */

	@SuppressWarnings("serial")
	public class currentTimeView extends AbstractView {
		
		private JLabel currentTimeLabel;

		private JTextField currentTimeView;

		private String currentTime;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public currentTimeView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			currentTimeLabel = new JLabel("Current Time: ");
			
			currentTimeView = new JTextField();    
	        
			currentTimeLabel.setBounds(5, 0, 200, 20);
			currentTimeView.setBounds(180, 3, 75, 20);
	       
	        add(currentTimeLabel);
	        add(currentTimeView);
       
	        currentTimeView.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        currentTime = carPark.getCurrentTime();
	        currentTimeView.setText("" + currentTime);
	        
	        setVisible(true);
	        super.updateView();
	    }
	}