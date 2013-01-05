package com.britton2000.orbiter.elements;

//Max Britton hi

import playn.core.ImageLayer;

public interface ElementInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#render(float)
	 */
	public abstract void render(float alpha);

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#paint(float)
	 */
	public abstract void paint(float alpha);

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#clear()
	 */
	public abstract void clear();

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#update(float)
	 */
	public abstract void update(float delta);

	public abstract ImageLayer getLayer();

	public abstract void processCollisions(float delta);

	public abstract int getY();

	public abstract int getY2();

	public abstract int getX2();

	public abstract void setY(int y);

	public abstract int getX();

	public abstract String getImage();

	public abstract void setX(int x);

	public abstract void setPosition(int x, int y);
	
	public abstract void setCollision(boolean collides);
	
	public abstract void setCollidingElement(ElementInterface element);

	public abstract boolean hasCollision(ElementInterface element);

	boolean collides();

	public abstract boolean toRemove();

	abstract boolean creatorCheck(ElementInterface e);

	public abstract ElementInterface getCreator();

}