package com.britton2000.orbiter.core;

import static playn.core.PlayN.graphics;

import com.britton2000.orbiter.elements.Bullet;
import com.britton2000.orbiter.elements.Element;
import com.britton2000.orbiter.elements.TracerBullet;

public class Gun {

	private int bulletCount = 0;
	int fireRate;
	private Element parent;

	/**
	 * @param element
	 * 
	 */
	public Gun(Element element) {
		super();
		parent = element;
	}

	public Bullet fireBullet(int x, int y, boolean direction, int gunType) {

		fireRate += 1;
		if (gunType == 1) {
			if (fireRate > 10) {
				bulletCount += 2;
				fireRate = 0;
				Bullet b = null;
				b = new Bullet(graphics().rootLayer(), parent, direction);
				b.setPosition(x, y);
				return b;
			}
		}
		if (gunType == 2) {
			if (fireRate > 10) {
				bulletCount += 2;
				fireRate = 0;
				Bullet b = null;
				b = new TracerBullet(graphics().rootLayer(), parent, direction);
				b.setPosition(x, y);
				return b;
			}
		}
		if (gunType == 3) {
			if (fireRate > 5) {
				fireRate = 0;
				Bullet b = null;
				b = new Bullet(graphics().rootLayer(), parent, direction);
				b.setPosition(x, y);
				return b;
			}
		}
		if (gunType == 4) {
			if (fireRate > 5) {
				fireRate = 0;
				Bullet b = null;
				b = new TracerBullet(graphics().rootLayer(), parent, direction);
				b.setPosition(x, y);
				return b;
			}
		}
		// not working!!!
		if (gunType == 5) {
			if (fireRate > 5) {
				bulletCount += 1;
				fireRate = 0;
				Bullet b = null;
				if (bulletCount % 2 == 0) {
					b = new TracerBullet(graphics().rootLayer(), parent, direction);
				} else {
					b = new Bullet(graphics().rootLayer(), parent, direction);
				}
				b.setPosition(x, y);
				return b;
			}
		}
		return null;
	}

}
