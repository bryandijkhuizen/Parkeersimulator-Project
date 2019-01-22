package me.project.view.queueView;

import javax.swing.JLabel;
import javax.swing.JTextField;

import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

	/**
	 * This class contains the secondQueueView constructor
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 1.0
	 */

	@SuppressWarnings("serial")
	public class secondQueueView extends AbstractView {
		
		private JLabel currentAmountOfWaitingPassHoldersLabel;

		private JTextField currentAmountOfWaitingPassHolders;

		private int currentAmountOfWaitingPassHoldersNumber;
		
		/**
		 * This is the secondQueueView Constructor
		 */
		
		public secondQueueView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			currentAmountOfWaitingPassHoldersLabel = new JLabel("Member Queue: ");
			
			currentAmountOfWaitingPassHolders = new JTextField();    
	        
			currentAmountOfWaitingPassHoldersLabel.setBounds(5, 0, 200, 20);
			currentAmountOfWaitingPassHolders.setBounds(100, 3, 75, 20);
	       
	        add(currentAmountOfWaitingPassHoldersLabel);
	        add(currentAmountOfWaitingPassHolders);
       
	        currentAmountOfWaitingPassHolders.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        currentAmountOfWaitingPassHoldersNumber = carPark.getCarsInSecondEntranceQueue();
	        currentAmountOfWaitingPassHolders.setText("" + currentAmountOfWaitingPassHoldersNumber);
	        

	        setVisible(true);
	        super.updateView();
	    }
	}