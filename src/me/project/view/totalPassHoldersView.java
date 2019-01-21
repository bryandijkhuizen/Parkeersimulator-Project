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
	public class totalPassHoldersView extends AbstractView {
		
		private JLabel regularCarsLabel;

		private JTextField regularCars;

		private int totalRegularCars;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public totalPassHoldersView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			regularCarsLabel = new JLabel("Current Member Cars: ");
			
			regularCars = new JTextField();    
	        
			regularCarsLabel.setBounds(5, 0, 200, 20);
			regularCars.setBounds(180, 3, 50, 20);
	       
	        add(regularCarsLabel);
	        add(regularCars);
       
	        regularCars.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        totalRegularCars = carPark.getTotalPassHolders();
	        regularCars.setText("" + totalRegularCars);
	        

	        setVisible(true);
	        super.updateView();
	    }
	}