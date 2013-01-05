package com.britton2000.orbiter.elements;

//Max Britton hi

import static playn.core.PlayN.graphics;

import com.britton2000.orbiter.core.OrbiterMain;

import playn.core.ImageLayer;
import playn.core.Layer;
import pythagoras.f.Point;

public class Element implements ElementInterface {
	protected int y, x;
	private int y2, x2;
	protected int width, height;
	int damage;
	protected ImageLayer layer;
	protected String imageName;
	protected boolean collision = false;
	protected boolean _explodes = true;
	protected boolean _collides = true;
	protected boolean _remove = false;
	protected ElementInterface creator = null;
	protected ElementInterface collidingElement;

	/**
	 * 
	 */
	protected Element() {
		super();
	}

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
	public void setPosition(int bx, int by) {

		this.x = bx;
		this.y = by;
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
	public boolean toRemove() {
		return _remove;
	}

	@Override
	public boolean hasCollision(ElementInterface e) {
		
		if (e == null || e.getLayer() == null || e.getLayer().image() == null || e.getLayer().destroyed() || e == this || collision
				|| !collides() || creatorCheck(e)) {
			return collision;
		}

		if (Layer.Util.hitTest(e.getLayer(), new Point(x, y))) {
			collision = true;
		}

		if (Layer.Util.hitTest(e.getLayer(), new Point(x, getY2()))) {
			collision = true;
		}

		if (Layer.Util.hitTest(e.getLayer(), new Point(getX2(), y))) {
			collision = true;
		}
		if (Layer.Util.hitTest(e.getLayer(), new Point(getX2(), getY2()))) {
			collision = true;
		}

		if (collision && !(e instanceof Bullet) && !(e instanceof Explosion)) {
			collidingElement = e;
			e.setCollision(true);
			e.setCollidingElement(this);
			
			//create boom
			Explosion boom = new Explosion(OrbiterMain.canvasWidth, OrbiterMain.canvasHeight, graphics().rootLayer());
			OrbiterMain.elementSpawnQueue.push(boom);
			boom.setPosition(e.getX(), e.getY());
		}
		return collision;
	}

	public void resetCollision() {
		collision = false;
		collidingElement = null;
	}

	@Override
	public boolean creatorCheck(ElementInterface e) {
		if (e != null && (e == creator || this == e.getCreator())) {
			return true;

		}

		return false;
	}

	@Override
	public ElementInterface getCreator() {
		return creator;
	}

	public void setCreator(Element creator) {
		this.creator = creator;
	}

	@Override
	public void processCollisions(float delta) {
		if (!collision)
			return;
	}
	
	

	@Override
	public ImageLayer getLayer() {
		return layer;
	}

	@Override
	public void setCollision(boolean collides) {
		collision = collides;
	}

	@Override
	public void setCollidingElement(ElementInterface element) {
		collidingElement = element;		
	}

}
