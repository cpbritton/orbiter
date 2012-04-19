package com.britton2000.orbiter.elements;

import playn.core.ImageLayer;

public class Element implements ElementInterface {
	protected int y, x, y2, x2;
	public static int width, height;
	public ImageLayer layer;
	protected String imageName;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#render(float)
	 */
	@Override
	public void render(float alpha) {
		layer.setTranslation(x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#paint(float)
	 */
	@Override
	public void paint(float alpha) {
		render(alpha);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#clear()
	 */
	@Override
	public void clear() {
		layer.destroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#update(float)
	 */
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#getY()
	 */
	@Override
	public int getY() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#setY(int)
	 */
	@Override
	public void setY(int y) {
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#getX()
	 */
	@Override
	public int getX() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#setX(int)
	 */
	@Override
	public void setX(int x) {
		this.x = x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Orbiter.elements.ElementInterface#setPosition(int, int)
	 */
	@Override
	public void setPosition(int x, int y) {

		this.x = x;
		this.y = y;
	}

	@Override
	public int getY2() {
		// TODO Auto-generated method stub
		y2 = (int) (layer.scaledHeight() + y);
		return y2;
	}

	@Override
	public int getX2() {
		// TODO Auto-generated method stub
		x2 = (int) (layer.scaledWidth() + x);
		return x2;
	}

	@Override
	public String getImage() {
		return this.imageName;
	}

}
