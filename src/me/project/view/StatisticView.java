package me.project.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

import me.project.logic.CarParkingLogic;

public class StatisticView extends AbstractView {
	
	private JLabel title;
	private JTextField carCount;
	private int totalCars;
	
	public StatisticView(CarParkingLogic model) {
		super(model);

		this.setLayout(null);

		
		title = new JLabel("Live car park information:");
        
        carCount = new JTextField();
        carCount.setText(model.getTotalCars() + "");       
        
        
        title.setBounds(5, 0, 220, 20);
        carCount.setBounds(180, 0, 100, 20);
        
        add(title);
        add(carCount);
        
        
			
	}
	
	public void updateView(){

        CarParkingLogic carPark = (CarParkingLogic) super.model;

        
        
        totalCars = carPark.getTotalCars();
        carCount.setText("" + totalCars);


        setVisible(true);
        super.updateView();
    }
	
	

}
