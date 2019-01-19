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
	public class totalRevenueView extends AbstractView {
		
		private JLabel revenueLabel;

		private JTextField totalRevenueView;

		private int totalRevenue;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public totalRevenueView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			revenueLabel = new JLabel("Total Revenue: ");
			
			totalRevenueView = new JTextField();    
	        
			revenueLabel.setBounds(5, 0, 200, 20);
			totalRevenueView.setBounds(180, 3, 50, 20);
	       
	        add(revenueLabel);
	        add(totalRevenueView);
       
	        totalRevenueView.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        totalRevenue = carPark.getTotalRevenue();
	        totalRevenueView.setText("€ " + totalRevenue);
	        

	        setVisible(true);
	        super.updateView();
	    }
	}