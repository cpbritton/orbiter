package me.xorga.orbiter.elements;

//Max Britton hi

import playn.core.GroupLayer;

import me.xorga.orbiter.core.OrbiterMain;
import me.xorga.orbiter.core.Ship;

public class EnemyBeastShip extends EnemyShip {

	private final String imageName = "images/EnemyBeastShip.png";
	int damage = 150;

	public EnemyBeastShip(GroupLayer parentLayer) {
		super(parentLayer);
		layer.setDepth(3);
	}

	@Override
	public void update(float delta) {
		Bullet b = null;
		attack += 1;
		if (y < 20) {
			y += 2 * OrbiterMain.imageSize;
		}
		if (attack > 100 && attackNumber < 3) {
			y += 2 * OrbiterMain.imageSize;
			b = fireMainGun();
			if (y > OrbiterMain.canvasHeight / 2) {
				attack = 0;
				attackNumber += 1;
			}
		}
		if (attack > 100 && attackNumber >= 3) {
			y += 15;
			b = fireMainGun();
			if (OrbiterMain.ship.x > x) {
				x += 2 * OrbiterMain.imageSize;
			}
			if (OrbiterMain.ship.x < x) {
				x -= 2 * OrbiterMain.imageSize;
			}
		}
		if (attack < 100 && y >= 20) {
			y -= 2 * OrbiterMain.imageSize;
		}
		if (attack < 50 && y < 20) {
			b = fireMainGun();
		}
		if (y >= OrbiterMain.canvasHeight) {
			attack = 0;
		}
		if (OrbiterMain.ship.x == x) {
			dodge += 1;
			if (dodge < 25) {
				x += 2 * OrbiterMain.imageSize;
			}
			if (dodge >= 25) {
				x -= 2 * OrbiterMain.imageSize;
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
	
	@Override
	public void processCollisions(float delta) {
		if (!collision)
			return;

		if (collidingElement instanceof Bullet) {
			damage -= 10;
		}
		
		if (collidingElement instanceof TracerBullet) {
			damage -= 20;
		}

		if (collidingElement instanceof Asteroid) {
			damage -= 100;
		}
			
		if (collidingElement instanceof Ship) {
				damage -= 200;
		}
		
		if (damage < 0) {
			_remove = true;
		}

		

		resetCollision();

	}
}
