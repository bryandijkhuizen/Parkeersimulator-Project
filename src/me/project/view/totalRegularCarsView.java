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
	public class totalRegularCarsView extends AbstractView {
		
		private JLabel passHoldersLabel;

		private JTextField passHolders;

		private int totalPassHolders;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public totalRegularCarsView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			passHoldersLabel = new JLabel("Current Regular Cars: ");
			
			passHolders = new JTextField();    
	        
			passHoldersLabel.setBounds(5, 0, 200, 20);
			passHolders.setBounds(180, 3, 50, 20);
	       
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