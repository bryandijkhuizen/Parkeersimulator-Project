package me.project.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import me.project.logic.CarParkingLogic;

	@SuppressWarnings("serial")
	public class StatisticView extends AbstractView {
		
		private JLabel carCounterLabel;
		private JTextField totalCarCount;
		
		private int totalCars;
		
		public StatisticView(CarParkingLogic model) {
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
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        totalCars = carPark.getTotalCars();
	        totalCarCount.setText("" + totalCars);

	        setVisible(true);
	        super.updateView();
	    }
	}

	
		
		

	