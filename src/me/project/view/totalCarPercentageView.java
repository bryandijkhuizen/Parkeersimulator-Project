package me.project.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

	/**
	 * This class contains the totalCarsPercentage
	 * @author Bryan Dijkhuizen, Daphne Gritter Thalisa Jagt
	 * @version 1.0
	 */

	@SuppressWarnings("serial")
	public class totalCarPercentageView extends AbstractView {
		
		private JLabel totalCarPercentageLabel;

		private JTextField percentage;

		private double totalPercentage;
		
		/**
		 * This is the totalCarsPercentage Constructor
		 */
		
		public totalCarPercentageView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			totalCarPercentageLabel = new JLabel("Current Car %: ");
			
			percentage = new JTextField();    
	        
			totalCarPercentageLabel.setBounds(5, 0, 200, 20);
			percentage.setBounds(180, 3, 50, 20);
	       
	        add(totalCarPercentageLabel);
	        add(percentage);
       
	        percentage.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;
	        
	        double pass = carPark.getTotalPassHolders() + 0.0001;
	        double reg = carPark.getTotalRegularCars() + 0.0001 ;
	        double total = pass + reg;
	        double per = Math.round(pass / total * 100);
	        

	        totalPercentage = per;
	        percentage.setText("" + totalPercentage + " %");
	        
	        setVisible(true);
	        super.updateView();
	    }
	}