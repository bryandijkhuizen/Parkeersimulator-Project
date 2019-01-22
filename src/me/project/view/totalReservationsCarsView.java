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
	public class totalReservationsCarsView extends AbstractView {
		
		private JLabel reservationsLabel;

		private JTextField reservations;

		private int totalReservations;
		
		/**
		 * This is the totalCarsView Constructor
		 */
		
		public totalReservationsCarsView(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			reservationsLabel = new JLabel("Reservations: ");
			
			reservations = new JTextField();    
	        
			reservationsLabel.setBounds(5, 0, 200, 20);
			reservations.setBounds(85, 3, 50, 20);
	       
	        add(reservationsLabel);
	        add(reservations);
       
	        reservations.setEditable(false);
		
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;

	        totalReservations = carPark.getTotalReservations();
	        reservations.setText("" + totalReservations);
	        

	        setVisible(true);
	        super.updateView();
	    }
	}