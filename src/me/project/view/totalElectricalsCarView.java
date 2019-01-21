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
	public class totalElectricalsCarView extends AbstractView {
		
		private JLabel electricalsLabel;

		private JTextField electricals;

		private int totalElectricals;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public totalElectricalsCarView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			electricalsLabel = new JLabel("Current Electrical Cars: ");
			
			electricals = new JTextField();    
	        
			electricalsLabel.setBounds(5, 0, 200, 20);
			electricals.setBounds(180, 3, 50, 20);
	       
	        add(electricalsLabel);
	        add(electricals);
       
	        electricals.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        totalElectricals = carPark.getTotalElectricals();
	        electricals.setText("" + totalElectricals);
	        

	        setVisible(true);
	        super.updateView();
	    }
	}