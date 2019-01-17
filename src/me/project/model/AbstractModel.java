package me.project.model;

import java.util.*;

import me.project.view.AbstractView;

	/**
	 * Abstract Class for the model subclasses
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 *
	 */

public abstract class AbstractModel {
	private List<AbstractView> AbstractViews;
	
	/**
	 * AbstractModel Constructor
	 */
	
	public AbstractModel() {
		AbstractViews=new ArrayList<AbstractView>();
	}
	
	/**
	 * Adds view to views list.
	 */
	
	public void addView(AbstractView AbstractView) {
		AbstractViews.add(AbstractView);
	}
	
	/**
	 * updates the views
	 */

	public void notifyViews() {
		for(AbstractView v: AbstractViews) v.updateView();
	}
	
	/**
	 * Tick()
	 */
	
	public void tick() {
	}
}