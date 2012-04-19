package com.britton2000.orbiter.elements;

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

	public abstract int getY();

	public abstract int getY2();

	public abstract int getX2();

	public abstract void setY(int y);

	public abstract int getX();

	public abstract String getImage();

	public abstract void setX(int x);

	public abstract void setPosition(int x, int y);

}