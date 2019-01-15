package me.project.view;

import java.util.List;

import javax.swing.*;

import me.project.model.AbstractModel;

/**
 * All views should extend AbstractView.
 *
 * @author Femke Hoornveld
 * @version 2.0 (11-04-2016)
 */
public abstract class AbstractView extends JPanel {
	
	private static final long serialVersionUID = 6437976554496769048L;
	private static List<AbstractView> views;
	protected AbstractModel model;

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
	
	public static void notifyViews() {
        for(AbstractView v: views) v.updateView();
    }
}
