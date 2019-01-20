package me.project.main;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;

import me.project.abstracts.AbstractController;
import me.project.abstracts.AbstractView;
import me.project.controller.Controller;
import me.project.logic.CarParkingLogic;
import me.project.view.CarParkView;
import me.project.view.currentDayView;
import me.project.view.currentTimeView;
import me.project.view.entranceQueueView;
import me.project.view.totalCarPercentageView;
import me.project.view.totalCarsView;
import me.project.view.totalPassHoldersView;
import me.project.view.totalRegularCarsView;
import me.project.view.totalReservationsCarsView;
import me.project.view.totalRevenueView;
import java.awt.Canvas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollBar;

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
    private totalCarsView totalCars;
    private currentDayView currentDay;
    private currentTimeView CurrentTimeView;
    private entranceQueueView EntranceCarQueueView;
    private totalPassHoldersView tphv;
    private totalRegularCarsView rcv;
    private totalCarPercentageView tcpv;
    private totalReservationsCarsView totalRes;
    private totalRevenueView totalRevenue;
    
    private JLabel carLogo;
    
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
		totalCars = new totalCarsView(carParking);
		totalCars.setBorder(new LineBorder(new Color(0, 0, 0)));
		totalCars.setBackground(SystemColor.menu);
		currentDay = new currentDayView(carParking);
		currentDay.setBorder(new LineBorder(new Color(0, 0, 0)));
		currentDay.setBackground(SystemColor.menu);
		CurrentTimeView = new currentTimeView(carParking);
		CurrentTimeView.setBorder(new LineBorder(new Color(0, 0, 0)));
		CurrentTimeView.setBackground(SystemColor.menu);
		EntranceCarQueueView = new entranceQueueView(carParking);
		EntranceCarQueueView.setBorder(new LineBorder(new Color(0, 0, 0)));
		EntranceCarQueueView.setBackground(SystemColor.menu);
		tphv = new totalPassHoldersView(carParking);
		tphv.setBorder(new LineBorder(new Color(0, 0, 0)));
		tphv.setBackground(SystemColor.menu);
		rcv = new totalRegularCarsView(carParking);
		rcv.setBorder(new LineBorder(new Color(0, 0, 0)));
		rcv.setBackground(SystemColor.menu);
		tcpv= new totalCarPercentageView(carParking);
		tcpv.setBorder(new LineBorder(new Color(0, 0, 0)));
		tcpv.setBackground(SystemColor.menu);
		totalRes = new totalReservationsCarsView(carParking);
		totalRes.setBorder(new LineBorder(new Color(0, 0, 0)));
		totalRes.setBackground(SystemColor.menu);
		totalRevenue = new totalRevenueView(carParking);
		totalRevenue.setBorder(new LineBorder(new Color(0, 0, 0)));
		totalRevenue.setBackground(SystemColor.menu);
		Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		
		
		
		frame=new JFrame("CarParking Simulation");
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		
		frame.setSize(1394, 909);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(carParkView);
		frame.getContentPane().add(controller);
		frame.getContentPane().add(totalCars);
		frame.getContentPane().add(currentDay);
		frame.getContentPane().add(CurrentTimeView);
		frame.getContentPane().add(EntranceCarQueueView);
		frame.getContentPane().add(tphv);
		frame.getContentPane().add(rcv);
		frame.getContentPane().add(tcpv);
		frame.getContentPane().add(totalRes);
		frame.getContentPane().add(totalRevenue);
		

		carParkView.setBounds(29, 314, 759, 339);
		controller.setBounds(105, 710, 511, 101);
		totalCars.setBounds(806, 31, 275, 29);
		currentDay.setBounds(65, 259, 275, 29);
		CurrentTimeView.setBounds(482, 259, 275, 29);
		EntranceCarQueueView.setBounds(806, 191, 275, 29);
		tphv.setBounds(806, 111, 275, 29);
		rcv.setBounds(806, 151, 275, 29);
		tcpv.setBounds(1091, 31, 275, 29);
		totalRes.setBounds(806, 71, 275, 29);
		totalRevenue.setBounds(1037, 553, 316, 29);
		
		carLogo = new JLabel("");
		carLogo.setBounds(215, 36, 400, 155);
		frame.getContentPane().add(carLogo);
		carLogo.setIcon(new ImageIcon(img));
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar.setBounds(29, 834, 792, 17);
		scrollBar.setMinimum(0);
		scrollBar.setMaximum(3600);
		
		scrollBar.addAdjustmentListener(new AdjustmentListener() {
			
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				carParking.steps(e.getValue());
				
			}
		});
		frame.getContentPane().add(scrollBar);
		
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