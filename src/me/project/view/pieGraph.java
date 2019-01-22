package me.project.view;

/**
 * The class that creates the PieView
 * @author Bryan Dijkhuizen
 * @version 3.0 (22.1.2019)
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

@SuppressWarnings("serial")
public class pieGraph extends AbstractView {
	

	private int totalRegular;
	private int totalElectrical;
	private int totalParkingpass;
	private int totalReservations;
	@SuppressWarnings("unused")
	private int totalCars;

	public pieGraph(CarParkingLogic cpl) {
		super(cpl);
		
		JPanel pieView = new JPanel();
		pieView.setLayout(new FlowLayout());
		pieView.setBackground(Color.WHITE);
		JLabel lblPieView = new JLabel("Cars");
		pieView.add(lblPieView);
		this.add(pieView);
		
	}
	
	public void updateView(){
		CarParkingLogic cpl = (CarParkingLogic) super.model;

		totalElectrical = cpl.getTotalElectricals()-1;
		totalParkingpass = cpl.getTotalPassHolders()-1;
		totalRegular = cpl.getTotalRegularCars()-1;
		totalReservations = cpl.getTotalReservations()-1;
		totalCars = cpl.getTotalCars();
		repaint();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Colors.BACKGROUND_BLUE);
		g.fillRect(0, 0, 300, 300);
		g.setColor(Colors.DARK_RED);
		g.fillArc(50, 50, 180, 180, 0, totalRegular );
		g.setColor(Colors.DARK_GREEN);
		g.fillArc(50, 50, 180, 180, totalRegular, totalReservations);
		g.setColor(Colors.MEMBER_BLUE);
		g.fillArc(50, 50, 180, 180, totalRegular + totalReservations, totalParkingpass);
		g.setColor(Colors.DARK_YELLOW);
		g.fillArc(50, 50, 180, 180, totalRegular + totalReservations + totalParkingpass, totalElectrical);

	}	

}
