package me.project.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

import me.project.logic.CarParkingLogic;

public class StatisticView extends AbstractView {
	
	private JLabel carCounterLabel;
	private JTextField totalCarCount;
	
	private int totalCars;
	
	public StatisticView(CarParkingLogic model) {
		super(model);
		this.setLayout(null);

		carCounterLabel = new JLabel("Current Amount of Cars: ");
		
		totalCarCount = new JTextField();    
		
        
        carCounterLabel.setBounds(5, 0, 220, 20);
        totalCarCount.setBounds(180, 0, 100, 20);
        
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
