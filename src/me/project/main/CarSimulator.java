package me.project.main;

import java.awt.Color;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
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
import me.project.view.entranceQueueView;
import me.project.view.missedProfitView;
import me.project.view.pieGraph;
import me.project.view.queueGraph;
import me.project.view.secondQueueView;
import me.project.view.slideControl;
import me.project.view.totalElectricalsCarView;
import me.project.view.totalPassHoldersView;
import me.project.view.totalRegularCarsView;
import me.project.view.totalReservationsCarsView;
import me.project.view.totalRevenueView;
import javax.swing.SwingConstants;
import java.awt.Font;


	/**
	 * This class combines all of the functions
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 2.2.0 (22-1-2019)
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
    public JSlider simSpeed;
    private totalRevenueView totalRevenue;
    private missedProfitView missedRevenue; 
    
    private int tickPause;
    public static boolean run;


	/**
	 * The constructor creates instances of CarParkingLogic, Controller, CarParkView, Screen.
	 */
	
    public CarSimulator() throws Exception {
		
		/** 
		 * Here the Logic, View and controller are defined 
		 */
		
		carParking = new CarParkingLogic(3, 6, 30);
		
		controller = new Controller(carParking);
		controller.setBackground(SystemColor.inactiveCaption);
		
		carParkView = new CarParkView(carParking);
		carParkView.setBorder(new LineBorder(new Color(0, 0, 0)));
		carParkView.setBackground(SystemColor.inactiveCaption);
		
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
		
		BarGraph = new barChart(carParking);
		BarGraph.setBackground(SystemColor.inactiveCaption);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tabbedPane.setBackground(SystemColor.text);
		
		slideController = new slideControl(carParking);
		slideController.setBackground(SystemColor.inactiveCaption);
		
		QueueGraph = new queueGraph(carParking);
		QueueGraph.setBackground(SystemColor.inactiveCaption);
		
		eq = new entranceQueueView(carParking);
		eq.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		sq = new secondQueueView(carParking);
		sq.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		simSpeed = new JSlider(0, 500, 500);
		simSpeed.setMinorTickSpacing(100);
		simSpeed.setBackground(SystemColor.inactiveCaption);
		simSpeed.setForeground(SystemColor.activeCaptionText);
		simSpeed.setToolTipText("Tick Pause\r\n");
		simSpeed.setPaintTicks(true);
		simSpeed.setMajorTickSpacing(50);
		
		frame=new JFrame("CarParking Simulation");
		frame.getContentPane().setBackground(new Color(191, 205, 219));
		
		frame.setSize(1302, 711);
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
		frame.getContentPane().add(tabbedPane);
		frame.getContentPane().add(eq);
		frame.getContentPane().add(sq);
		frame.getContentPane().add(simSpeed);
		
		carParkView.setBounds(10, 177, 813, 339);
		controller.setBounds(85, 582, 586, 44);
		currentDay.setBounds(480, 50, 275, 29);
		CurrentTimeView.setBounds(480, 90, 275, 29);
		tphv.setBounds(1030, 90, 143, 29);
		rcv.setBounds(877, 50, 143, 29);
		totalRes.setBounds(1030, 50, 143, 29);
		TotalElectricalsCarView.setBounds(877, 90, 143, 29);
		carLogo.setBounds(10, 11, 400, 155);
		tabbedPane.setBounds(877, 177, 360, 300);
		eq.setBounds(877, 487, 185, 29);
		sq.setBounds(877, 527, 185, 29);
		simSpeed.setBounds(10, 527, 813, 44);
		
		PieGraph = new pieGraph(carParking);
		PieGraph.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Pie Chart", PieGraph);
		tabbedPane.setForegroundAt(0, SystemColor.text);
		tabbedPane.setEnabledAt(0, true);
		tabbedPane.setBackgroundAt(0, SystemColor.textHighlight);
		tabbedPane.addTab("Bar Chart", BarGraph);
		tabbedPane.setEnabledAt(1, true);
		tabbedPane.setForegroundAt(1, SystemColor.text);
		tabbedPane.setBackgroundAt(1, SystemColor.textHighlight);
		tabbedPane.addTab("Value Slider", slideController);
		tabbedPane.setForegroundAt(2, SystemColor.text);
		tabbedPane.setEnabledAt(2, true);
		tabbedPane.setBackgroundAt(2, SystemColor.textHighlight);
		tabbedPane.addTab("Queue Graph", QueueGraph);
		tabbedPane.setEnabledAt(3, true);
		tabbedPane.setForegroundAt(3, SystemColor.text);
		tabbedPane.setBackgroundAt(3, SystemColor.textHighlight);
		
		totalRevenue = new totalRevenueView(carParking);
		totalRevenue.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Profits", null, totalRevenue, null);
		tabbedPane.setForegroundAt(4, SystemColor.window);
		tabbedPane.setEnabledAt(4, true);
		tabbedPane.setBackgroundAt(4, SystemColor.textHighlight);
		totalRevenue.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		missedRevenue = new missedProfitView(carParking);
		missedRevenue.setBackground(SystemColor.inactiveCaption);
		missedRevenue.setBounds(0, 31, 252, 29);
		totalRevenue.add(missedRevenue);
		
		JLabel lblNewLabel = new JLabel("Fast");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(SystemColor.textHighlight);
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBounds(10, 582, 65, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("Slow");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		label.setBackground(SystemColor.textHighlight);
		label.setBounds(758, 582, 65, 37);
		frame.getContentPane().add(label);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		run = true;
		
		
		
		carParkView.updateView();

        

        while(true){
            if (run) {
                carParking.tick();
            }
            try{
                Thread.sleep(simSpeed.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
    
    public JSlider getJSlider() {
    	return simSpeed;
    }
}