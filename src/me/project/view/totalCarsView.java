package me.project.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import me.project.logic.CarParkingLogic;

	/**
	 * This class contains the totalCarsCounter
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 *
	 */

	@SuppressWarnings("serial")
	public class totalCarsView extends AbstractView {
		
		private JLabel carCounterLabel;

		private JTextField totalCarCount;

		private int totalCars;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public totalCarsView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			carCounterLabel = new JLabel("Current Amount of Cars: ");
			
			totalCarCount = new JTextField();    
	        
	        carCounterLabel.setBounds(5, 0, 200, 20);
	        totalCarCount.setBounds(180, 3, 50, 20);
	       
	        add(carCounterLabel);
	        add(totalCarCount);
       
	        totalCarCount.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        totalCars = carPark.getTotalCars();
	        totalCarCount.setText("" + totalCars);
	        

	        setVisible(true);
	        super.updateView();
	    }
	}
