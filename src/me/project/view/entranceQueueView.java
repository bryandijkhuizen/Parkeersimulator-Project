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
	public class entranceQueueView extends AbstractView {
		
		private JLabel currentAmountOfWaitingPassHoldersLabel;

		private JTextField currentAmountOfWaitingPassHolders;

		private int currentAmountOfWaitingPassHoldersNumber;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public entranceQueueView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			currentAmountOfWaitingPassHoldersLabel = new JLabel("Current Entrance Queue: ");
			
			currentAmountOfWaitingPassHolders = new JTextField();    
	        
			currentAmountOfWaitingPassHoldersLabel.setBounds(5, 0, 200, 20);
			currentAmountOfWaitingPassHolders.setBounds(180, 3, 75, 20);
	       
	        add(currentAmountOfWaitingPassHoldersLabel);
	        add(currentAmountOfWaitingPassHolders);
       
	        currentAmountOfWaitingPassHolders.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        currentAmountOfWaitingPassHoldersNumber = carPark.getEntranceCarQueue().carsInQueue();
	        currentAmountOfWaitingPassHolders.setText("" + currentAmountOfWaitingPassHoldersNumber);
	        

	        setVisible(true);
	        super.updateView();
	    }
	}