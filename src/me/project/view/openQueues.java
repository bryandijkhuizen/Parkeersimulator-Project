package me.project.view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

	/**
	 * This class contains the slideControl constructor and updateView method
	 * @author Bryan Dijkhuizen
	 * @version 1.0
	 */

	@SuppressWarnings("serial")
	public class openQueues extends AbstractView {
		
		private JCheckBox queueBox;
		private JCheckBox secondQueueBox;
		
		
		
		/**
		 * This is the slideControl Constructor
		 */
		
		public openQueues(CarParkingLogic model) {
			super(model);
			this.setLayout(null);

			queueBox = new JCheckBox("Entrance Queue");
			queueBox.setSelected(true);
			
			secondQueueBox = new JCheckBox("Second Entrance Queue");
			secondQueueBox.setSelected(true);

			HandlerClass handler = new HandlerClass(model);
			queueBox.addItemListener(handler);
			secondQueueBox.addItemListener(handler);

			
			secondQueueBox.setBounds(150, 0, 200, 20);
			queueBox.setBounds(5, 0, 200, 20);

			
			add(secondQueueBox);
			add(queueBox);
			
			
			secondQueueBox.setVisible(true);
			queueBox.setVisible(true);
			
		}
		
		private class HandlerClass extends AbstractView implements ItemListener {

			public HandlerClass(CarParkingLogic model) {
				super(model);
			
				
			}

			@Override
			public void itemStateChanged(ItemEvent event) {
				CarParkingLogic cpl = (CarParkingLogic) super.model;
				if(queueBox.isSelected()) {
					cpl.setIsOpen(cpl.getEntranceCarQueue(), true);
				} else if (!queueBox.isSelected()) {
					cpl.setIsOpen(cpl.getEntranceCarQueue(), false);
				}
				
				if(secondQueueBox.isSelected()) {
					cpl.setIsOpen(cpl.getSecondEntranceCarQueue(), true);
				} else if (!secondQueueBox.isSelected()) {
					cpl.setIsOpen(cpl.getSecondEntranceCarQueue(), false);
				}
				
				
				
				
			}
			
		}
		
		/**
		 * This method updates the counter every time a tick has been done
		 */
		
		public void updateView(){

	        CarParkingLogic carPark = (CarParkingLogic) super.model;
	        
	        super.updateView();
	        
	    }
	}