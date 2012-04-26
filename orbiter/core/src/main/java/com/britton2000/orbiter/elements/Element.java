package com.britton2000.orbiter.elements;

import playn.core.ImageLayer;
import playn.core.Layer;
import pythagoras.f.Point;

public class Element implements ElementInterface {
	protected int y, x;
	private int y2, x2;
	public int width, height;
	protected ImageLayer layer;
	protected String imageName;
	private boolean collision = false;
	protected boolean _explodes = true;
	protected boolean _collides = true;
	protected ElementInterface creator = null;
	protected ElementInterface collidingElement;

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

	@Override
	public boolean collides() {
		return _collides;
	}

	@Override
	public boolean hasCollision(ElementInterface e) {

		if (e == null || e.getLayer() == null || e == this || collision
				|| !collides() || creatorCheck(e)) {
			return collision;
		}

		if (Layer.Util.hitTest(e.getLayer(), new Point(x, y))) {
			collision = true;
			return collision;
		}

		if (Layer.Util.hitTest(e.getLayer(), new Point(x, getY2()))) {
			collision = true;
			return collision;

		}

		if (Layer.Util.hitTest(e.getLayer(), new Point(getX2(), y))) {
			collision = true;
			return collision;

		}
		if (Layer.Util.hitTest(e.getLayer(), new Point(getX2(), getY2()))) {
			collision = true;
			return collision;

		}

		if (collision) {
			collidingElement = e;
		}
		return collision;
	}

	private boolean creatorCheck(ElementInterface e) {
		if (e != null && e == creator) {
			return true;
		}
		return false;
	}

	public ElementInterface getCreator() {
		return creator;
	}

	public void setCreator(Element creator) {
		this.creator = creator;
	}

	@Override
	public void processCollisions(float delta) {

	}

	@Override
	public ImageLayer getLayer() {
		return layer;
	}

}
