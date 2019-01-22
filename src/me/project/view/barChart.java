package me.project.view;

/**
 * The class that creates the Bar Chart 
 * @author Bryan Dijkhuizen
 * @version 1.0.0 (22-1-2019)
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;
import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

@SuppressWarnings("serial")
public class barChart extends AbstractView {
	

	private int totalRegular;
	private int totalElectrical;
	private int totalParkingpass;
	private int totalReservations;
	

	public barChart(CarParkingLogic cpl) {
		super(cpl);
		
		JPanel barChart = new JPanel();
		barChart.setLayout(new FlowLayout());
		
		
	}
	
	public void updateView(){
		CarParkingLogic cpl = (CarParkingLogic) super.model;


		totalElectrical = cpl.getTotalElectricals();
		totalParkingpass = cpl.getTotalPassHolders();
		totalRegular = cpl.getTotalRegularCars();
		totalReservations = cpl.getTotalReservations();
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Colors.BACKGROUND_BLUE);
		g.fillRect(0, 0, 300, 300);
		g.setColor(Colors.DARK_RED);
		g.fillRect(0, 0, 50, totalRegular);
		g.setColor(Colors.DARK_GREEN);
		g.fillRect(50, 0, 50, totalReservations);
		g.setColor(Colors.MEMBER_BLUE);
		g.fillRect(150, 0, 50, totalParkingpass);
		g.setColor(Colors.DARK_YELLOW);
		g.fillRect(100, 0, 50, totalElectrical);


	}	

}
