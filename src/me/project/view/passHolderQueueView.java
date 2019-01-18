package me.project.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

	/**
	 * This class contains the totalCarsCounter
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 *
	 */

	@SuppressWarnings("serial")
	public class passHolderQueueView extends AbstractView {
		
		private JLabel currentCarsInQueueLabel;

		private JTextField currentCarsInQueue;

		private int currentCarsInQueueNumber;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public passHolderQueueView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			currentCarsInQueueLabel = new JLabel("Current PassHolder Queue: ");
			
			currentCarsInQueue = new JTextField();    
	        
			currentCarsInQueueLabel.setBounds(5, 0, 200, 20);
			currentCarsInQueue.setBounds(180, 3, 75, 20);
	       
	        add(currentCarsInQueueLabel);
	        add(currentCarsInQueue);
       
	        currentCarsInQueue.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        currentCarsInQueueNumber = carPark.getPassHoldersQueue().carsInQueue();
	        currentCarsInQueue.setText("" + currentCarsInQueueNumber);
	        

	        setVisible(true);
	        super.updateView();
	    }
	}