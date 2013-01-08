package com.britton2000.orbiter.core;

//Max Britton hi

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Sound;

import com.britton2000.orbiter.elements.Bullet;
import com.britton2000.orbiter.elements.Element;
import com.britton2000.orbiter.elements.TracerBullet;

public class Gun {

	private final Sound pew;

	private int bulletCount = 0;
	int fireRate;
	final Element parent;

	/**
	 * @param element
	 * 
	 */
	public Gun(Element element) {
		super();
		pew = assets().getSound("sounds/pew");
		parent = element;
	}

	public Bullet fireBullet(float bx, float by, boolean direction, int gunType) {

		fireRate += 1;
		if (gunType == 1) {
			if (fireRate > 10) {
				bulletCount += 2;
				fireRate = 0;
				Bullet b = null;
				b = new Bullet(graphics().rootLayer(), parent, direction);
				if (OptionsMenu.soundOptionOn == true) {
					pew.play();
				}
				if (direction) {
					GunChargeBar.charge -= 1;
				}
				b.setPosition(Math.round(bx), Math.round(by));
				return b;
			}
		}
		if (gunType == 2) {
			if (fireRate > 10) {
				bulletCount += 2;
				fireRate = 0;
				Bullet b = null;
				b = new TracerBullet(graphics().rootLayer(), parent, direction);
				if (OptionsMenu.soundOptionOn == true) {
					pew.play();
				}
				if (direction) {
					GunChargeBar.charge -= 1;
				}
				b.setPosition(Math.round(bx), Math.round(by));
				return b;
			}
		}
		if (gunType == 3) {
			if (fireRate > 5) {
				fireRate = 0;
				Bullet b = null;
				b = new Bullet(graphics().rootLayer(), parent, direction);
				if (OptionsMenu.soundOptionOn == true) {
					pew.play();
				}
				if (direction) {
					GunChargeBar.charge -= 1;
				}
				b.setPosition(Math.round(bx), Math.round(by));
				return b;
			}
		}
		if (gunType == 4) {
			if (fireRate > 5) {
				fireRate = 0;
				Bullet b = null;
				b = new TracerBullet(graphics().rootLayer(), parent, direction);
				if (OptionsMenu.soundOptionOn == true) {
					pew.play();
				}
				b.setPosition(Math.round(bx), Math.round(by));
				if (direction) {
					GunChargeBar.charge -= 1;
				}
				return b;
			}
		}
		if (gunType == 5) {
			if (fireRate > 5) {
				bulletCount += 1;
				fireRate = 0;
				Bullet b = null;
				if (bulletCount % 2 == 0) {
					b = new TracerBullet(graphics().rootLayer(), parent,
							direction);
				} else {
					b = new Bullet(graphics().rootLayer(), parent, direction);
				}
				if (OptionsMenu.soundOptionOn == true) {
					pew.play();
				}
				if (direction) {
					GunChargeBar.charge -= 1;
				}
				b.setPosition(Math.round(bx), Math.round(by));
				return b;
			}
		}
		return null;
	}

	public int getBulletCount() {
		return bulletCount;
	}

	public void increseBulletCount(int bulletCount) {
		this.bulletCount += bulletCount;
	}
	
	

}

// Deadmau5:It's actually pinned down.
// Girl:Hmm
// Deadmau5:With christmas lights﻿ around it.
// Girl: :|
// Deadmau5: :D
