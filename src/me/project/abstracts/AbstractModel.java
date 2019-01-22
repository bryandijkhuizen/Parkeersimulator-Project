package me.project.abstracts;

import java.util.*;

	/**
	 * Abstract Class for the model subclasses
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 1.0.0
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