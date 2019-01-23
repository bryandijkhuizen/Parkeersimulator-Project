package me.project.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

	/**
	 * This class contains the totalRevenue
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 1.0
	 */

	@SuppressWarnings("serial")
	public class missedProfitView extends AbstractView {
		
		private JLabel revenueLabel;

		private JTextField totalRevenueView;

		private int totalRevenue;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public missedProfitView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			revenueLabel = new JLabel("Profit / Week: ");
			
			totalRevenueView = new JTextField();    
	        
			revenueLabel.setBounds(5, 0, 200, 20);
			totalRevenueView.setBounds(130, 3, 120, 20);
	       
	        add(revenueLabel);
	        add(totalRevenueView);
       
	        totalRevenueView.setEditable(false);
	        setVisible(true);
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        totalRevenue = carPark.getTotalRevenue();
	        totalRevenueView.setText("€ " + totalRevenue);
	        

	        
	        super.updateView();
	    }
	}