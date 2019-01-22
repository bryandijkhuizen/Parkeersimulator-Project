package me.project.view.queueView;

import javax.swing.JLabel;
import javax.swing.JTextField;

import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

	/**
	 * This class contains the totalCarsCounter
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 1.0
	 */

	@SuppressWarnings("serial")
	public class entranceQueueView extends AbstractView {
		
		private JLabel currentLabel;

		private JTextField currentText;

		private int currentNumber;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public entranceQueueView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			currentLabel = new JLabel("Entrance Queue: ");
			
			currentText = new JTextField();    
	        
			currentLabel.setBounds(5, 0, 200, 20);
			currentText.setBounds(100, 3, 75, 20);
	       
	        add(currentLabel);
	        add(currentText);
       
	        currentText.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        currentNumber = carPark.getCarsInEntranceQueue();
	        currentText.setText("" + currentNumber);
	        

	        setVisible(true);
	        super.updateView();
	    }
	}