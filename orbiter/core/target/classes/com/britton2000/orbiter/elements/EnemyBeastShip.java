package com.britton2000.orbiter.elements;

//Max Britton

import playn.core.GroupLayer;

import com.britton2000.orbiter.core.OrbiterMain;
import com.britton2000.orbiter.core.Ship;

public class EnemyBeastShip extends EnemyShip {

	private final String imageName = "images/EnemyBeastShip.png";

	public EnemyBeastShip(GroupLayer parentLayer) {
		super(parentLayer);
	}

	@Override
	public void update(float delta) {
		Bullet b = null;
		attack += 1;
		if (y < 20) {
			y += 10;
		}
		if (attack > 100 && attackNumber < 3) {
			y += 10;
			b = fireMainGun();
			if (y > OrbiterMain.canvasHeight / 2) {
				attack = 0;
				attackNumber += 1;
			}
		}
		if (attack > 100 && attackNumber >= 3) {
			y += 15;
			b = fireMainGun();
			if (Ship.x > x) {
				x += 10;
			}
			if (Ship.x < x) {
				x -= 10;
			}
		}
		if (attack < 100 && y >= 20) {
			y -= 10;
		}
		if (attack < 50 && y < 20) {
			b = fireMainGun();
		}
		if (y >= OrbiterMain.canvasHeight) {
			attack = 0;
		}
		if (Ship.x == x) {
			dodge += 1;
			if (dodge < 25) {
				x += 10;
			}
			if (dodge >= 25) {
				x -= 10;
			}
			if (dodge >= 50) {
				dodge = 0;
			}
		}

		if (b != null) {
			OrbiterMain.elementSpawnQueue.push(b);
		}
	}

	@Override
	public String getImage() {
		return imageName;
	}
}
