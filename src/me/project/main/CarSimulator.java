package me.project.main;

import java.awt.Color;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

import me.project.abstracts.AbstractController;
import me.project.abstracts.AbstractView;
import me.project.controller.Controller;
import me.project.logic.CarParkingLogic;
import me.project.view.CarParkView;
import me.project.view.barChart;
import me.project.view.currentDayView;
import me.project.view.currentTimeView;
import me.project.view.pieGraph;
import me.project.view.queueGraph;
import me.project.view.secondQueueView;
import me.project.view.slideControl;
import me.project.view.totalElectricalsCarView;
import me.project.view.totalPassHoldersView;
import me.project.view.totalRegularCarsView;
import me.project.view.totalReservationsCarsView;
import me.project.view.entranceQueueView;

	/**
	 * This class combines all of the functions
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 *
	 */

public class CarSimulator {

	private JFrame frame;
	private AbstractView carParkView;
    private CarParkingLogic carParking;
    private AbstractController controller;
    private currentDayView currentDay;
    private currentTimeView CurrentTimeView;
    private totalPassHoldersView tphv;
    private totalRegularCarsView rcv;
    private totalReservationsCarsView totalRes;
    private totalElectricalsCarView TotalElectricalsCarView;
    private JLabel carLogo;
    private pieGraph PieGraph;
    private barChart BarGraph;
    private slideControl slideController;
    private queueGraph QueueGraph;
    
    private entranceQueueView eq;
    private secondQueueView sq;
    
    private int tickPause;
    public static boolean run;


	/**
	 * The constructor creates instances of CarParkingLogic, Controller, CarParkView, Screen.
	 */
	
    public CarSimulator() {
		
		/** 
		 * Here the Logic, View and controller are defined 
		 */
		
		carParking = new CarParkingLogic(3, 6, 30);
		
		controller = new Controller(carParking);
		controller.setBackground(Color.LIGHT_GRAY);
		
		carParkView = new CarParkView(carParking);
		carParkView.setBorder(new LineBorder(new Color(0, 0, 0)));
		carParkView.setBackground(Color.GRAY);
		
		currentDay = new currentDayView(carParking);
		currentDay.setBorder(new LineBorder(new Color(0, 0, 0)));
		currentDay.setBackground(SystemColor.menu);
		
		CurrentTimeView = new currentTimeView(carParking);
		CurrentTimeView.setBorder(new LineBorder(new Color(0, 0, 0)));
		CurrentTimeView.setBackground(SystemColor.menu);
		
		tphv = new totalPassHoldersView(carParking);
		tphv.setBorder(new LineBorder(new Color(0, 0, 0)));
		tphv.setBackground(SystemColor.menu);
		
		rcv = new totalRegularCarsView(carParking);
		rcv.setBorder(new LineBorder(new Color(0, 0, 0)));
		rcv.setBackground(SystemColor.menu);
		
		totalRes = new totalReservationsCarsView(carParking);
		totalRes.setBorder(new LineBorder(new Color(0, 0, 0)));
		totalRes.setBackground(SystemColor.menu);
		
		Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		
		TotalElectricalsCarView = new totalElectricalsCarView(carParking);
		TotalElectricalsCarView.setBorder(new LineBorder(new Color(0, 0, 0)));
		TotalElectricalsCarView.setBackground(SystemColor.menu);
		
		carLogo = new JLabel("");
		carLogo.setIcon(new ImageIcon(img));
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar.setMinimum(0);
		scrollBar.setMaximum(3600);
		
		Image legendImg = new ImageIcon(this.getClass().getResource("/legenda.png")).getImage();
		
		BarGraph = new barChart(carParking);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		slideController = new slideControl(carParking);
		
		QueueGraph = new queueGraph(carParking);
		
		eq = new entranceQueueView(carParking);
		eq.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		sq = new secondQueueView(carParking);
		sq.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		frame=new JFrame("CarParking Simulation");
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		
		frame.setSize(1394, 909);
		frame.getContentPane().setLayout(null);
		
		frame.getContentPane().add(carParkView);
		frame.getContentPane().add(controller);
		frame.getContentPane().add(currentDay);
		frame.getContentPane().add(CurrentTimeView);
		frame.getContentPane().add(tphv);
		frame.getContentPane().add(rcv);
		frame.getContentPane().add(totalRes);
		frame.getContentPane().add(TotalElectricalsCarView);
		frame.getContentPane().add(carLogo);
		frame.getContentPane().add(scrollBar);
		frame.getContentPane().add(tabbedPane);
		frame.getContentPane().add(eq);
		frame.getContentPane().add(sq);
		
		carParkView.setBounds(29, 314, 759, 339);
		controller.setBounds(105, 710, 511, 101);
		currentDay.setBounds(65, 259, 275, 29);
		CurrentTimeView.setBounds(482, 259, 275, 29);
		tphv.setBounds(970, 559, 143, 29);
		rcv.setBounds(970, 441, 143, 29);
		totalRes.setBounds(970, 482, 143, 29);
		TotalElectricalsCarView.setBounds(970, 522, 143, 29);
		carLogo.setBounds(238, 31, 400, 155);
		scrollBar.setBounds(30, 826, 792, 17);
		tabbedPane.setBounds(970, 80, 343, 300);
		eq.setBounds(1123, 441, 185, 29);
		sq.setBounds(1123, 482, 185, 29);
		
		PieGraph = new pieGraph(carParking);
		tabbedPane.addTab("Pie Chart", PieGraph);
		tabbedPane.addTab("Bar Chart", BarGraph);
		tabbedPane.addTab("Value Slider", slideController);
		tabbedPane.addTab("Queue Graph", QueueGraph);
		
		scrollBar.addAdjustmentListener(new AdjustmentListener() {
			
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				carParking.steps(e.getValue());
				
			}
		});
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		carParkView.updateView();

        run = true;
        tickPause = 500;

        while(true){
            if (run) {
                carParking.tick();
            }
            try{
                Thread.sleep(tickPause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
}