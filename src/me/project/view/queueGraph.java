package me.project.view;

/**
 * The class that creates the queueGraph
 * @author Bryan Dijkhuizen
 * @version 1.0
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;
import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

@SuppressWarnings("serial")
public class queueGraph extends AbstractView {
	

	private int totalRegularQueue;
	private int totalMemberResQueue;

	

	public queueGraph(CarParkingLogic cpl) {
		super(cpl);
		
		JPanel barChart = new JPanel();
		barChart.setLayout(new FlowLayout());
		
		
	}
	
	public void updateView(){
		CarParkingLogic cpl = (CarParkingLogic) super.model;

		totalRegularQueue = cpl.getCarsInEntranceQueue();
		totalMemberResQueue = cpl.getCarsInSecondEntranceQueue();
		repaint();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Colors.BACKGROUND_BLUE);
		g.fillRect(0, 0, 300, 300);
		g.setColor(Colors.DARK_RED);
		g.fillRect(0, 130, totalRegularQueue * 10, 30);
		g.setColor(Colors.MEMBER_BLUE);
		g.fillRect(0, 160, totalMemberResQueue * 10, 30);



	}	

}
