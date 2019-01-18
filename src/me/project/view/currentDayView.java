package me.project.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

	/**
	 * This class contains the totalCarsCounter
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 *
	 */

	@SuppressWarnings("serial")
	public class currentDayView extends AbstractView {
		
		private JLabel currentDayLabel;

		private JTextField currenDayView;

		private String currentDay;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public currentDayView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			currentDayLabel = new JLabel("Current Day: ");
			
			currenDayView = new JTextField();    
	        
			currentDayLabel.setBounds(5, 0, 200, 20);
			currenDayView.setBounds(180, 3, 75, 20);
	       
	        add(currentDayLabel);
	        add(currenDayView);
       
	        currenDayView.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        currentDay = carPark.getCurrentDay();
	        currenDayView.setText("" + currentDay);
	        

	        setVisible(true);
	        super.updateView();
	    }
	}