package me.project.abstracts;

import javax.swing.JPanel;

	/**
	 * Abstract Class for the controller
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 *
	 */

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	protected AbstractModel model;
	
	/**
	 * The constructor initializes the instance variable model with a model that applies to this controller.
	 * @param model Model life is ascribed to the instance variable life
	 */
	public AbstractController(AbstractModel model) {
		this.model = model;
	}
}