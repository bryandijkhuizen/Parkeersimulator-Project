package me.project.abstracts;

import javax.swing.JPanel;

import javafx.stage.Stage;

	/**
	 * All views should extend AbstractView.
	 *
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 * @version 14.01.2019
	 */

public abstract class AbstractView extends JPanel {
	private static final long serialVersionUID = 6437976554496769048L;
	protected AbstractModel model;
	private Stage stage;

    /**
     * The constructor initializes the instance variable model with a model that applies to this view.
	 * 
     * @param model The model that applies to this view
     */
	
    public AbstractView(AbstractModel model) {
        this.model = model;
        model.addView(this);
    }
	
    /**
     * @return model Returns the model that belongs to this view
     */
    
	public AbstractModel getModel() {
		return model;
	}
	
	/**
	 * Makes sure that when the view is updated, it will not be painted over the last painted view
	 * but the View will first be cleared and then repainted with the updates.
	 */
	
	public void updateView() {
		repaint();
	}

	public void start(Stage stage) throws Exception {
		
		this.stage = stage;
	}
	
	public Stage getStage() {
		return stage;
	}
}