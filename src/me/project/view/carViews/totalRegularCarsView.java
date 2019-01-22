package me.project.view.carViews;

import javax.swing.JLabel;
import javax.swing.JTextField;

import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

	/**
	 * This class contains the totalRegularCarsCounter
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 1.0
	 */

	@SuppressWarnings("serial")
	public class totalRegularCarsView extends AbstractView {
		
		private JLabel passHoldersLabel;

		private JTextField passHolders;

		private int totalPassHolders;
		
		/**
		 * This is the totalRegularCarsCounter Constructor
		 */
		
		public totalRegularCarsView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			passHoldersLabel = new JLabel("Regular: ");
			
			passHolders = new JTextField();    
	        
			passHoldersLabel.setBounds(5, 0, 200, 20);
			passHolders.setBounds(85, 3, 50, 20);
	       
	        add(passHoldersLabel);
	        add(passHolders);
       
	        passHolders.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        totalPassHolders = carPark.getTotalRegularCars();
	        passHolders.setText("" + totalPassHolders);
	        

	        setVisible(true);
	        super.updateView();
	    }
	}