package com.britton2000.orbiter.elements;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ResourceCallback;

import com.britton2000.orbiter.core.Gun;
import com.britton2000.orbiter.core.OrbiterMain;
import com.britton2000.orbiter.core.Ship;

public class EnemyShip extends Element implements ElementInterface {
	Image image;

	private final String imageName = "images/EnemyShip.png";
	private final Gun mainGun, sideGunLeft, sideGunRight;
	private boolean fireLeft = false;
	private final int gunType = 3;

	int damage = 30, attack, attackNumber, enemyType, fireRate, dodge,
			dodgeTime, followAttack;

	public EnemyShip(final GroupLayer parentLayer) {
		image = assets().getImage(this.getImage());
		layer = graphics().createImageLayer(image);
		layer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				parentLayer.add(layer);
			}

			@Override
			public void error(Throwable err) {
				log().error("Error loading image!", err);
			}
		});

		mainGun = new Gun(this);
		sideGunLeft = new Gun(this);
		sideGunRight = new Gun(this);

	}

	@Override
	public void update(float delta) {
		Bullet b = null;

		attack += 1;
		followAttack += 1;
		if (y < 20) {
			y += 20;
		}
		if (attack > 500) {
			y += 20;
			b = fireSideGuns();
			if (Ship.x > x) {
				x += 10;
			}
			if (Ship.x < x) {
				x -= 10;
			}
		}
		if (y > OrbiterMain.canvasHeight) {
			attack = 0;
		}
		if (followAttack > 50) {
			b = fireSideGuns();
			if (Ship.x > x) {
				x += 10;
			}
			if (Ship.x < x) {
				x -= 10;
			}
			if (followAttack >= 100) {
				followAttack = 0;
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

	public Bullet fireMainGun() {
		int by = y;
		int bx = x + width * (OrbiterMain.imageSize / 2)
				- (2 * OrbiterMain.imageSize / 2);
		return mainGun.fireBullet(bx, by, false, 1);
	}

	public Bullet fireSideGuns() {
		int by = y + (height * 2);
		int bx = x;
		Bullet b = null;
		if (!fireLeft) {
			b = sideGunLeft.fireBullet(bx
					+ (width * OrbiterMain.imageSize * 2 / 7), by, false,
					gunType);
		} else {
			b = sideGunRight.fireBullet(bx
					+ (width * OrbiterMain.imageSize * 4 / 7), by, false,
					gunType);
		}
		if (b != null) {
			fireLeft = !fireLeft;
		}
		return b;
	}

	@Override
	public void processCollisions(float delta) {
		if (!collision)
			return;

		if (collidingElement instanceof Bullet) {
			damage -= 10;
		}

		if (collidingElement instanceof Asteroid) {
			damage -= 100;
		}

		if (damage < 0) {
			_remove = true;
		}

		resetCollision();

	}
}
