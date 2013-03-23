package me.xorga.orbiter.elements;

//Max Britton hi

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ResourceCallback;

import me.xorga.orbiter.core.Gun;
import me.xorga.orbiter.core.OrbiterMain;
import me.xorga.orbiter.core.Ship;

public class EnemyShip extends Element implements ElementInterface {
	Image image;

	private final String imageName = "images/EnemyShip.png";
	private final Gun mainGun, sideGunLeft, sideGunRight;
	private boolean fireLeft = false;
	private final int gunType = 3;

	int damage = 100, attack, attackNumber, enemyType, fireRate, dodge,
			dodgeTime, followAttack;

	public EnemyShip(final GroupLayer parentLayer) {
		image = assets().getImageSync(this.getImage());
		layer = graphics().createImageLayer(image);
		layer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		layer.setDepth(3);
		height = (int) layer.height();
		width = (int) layer.width();
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
		
		if (OrbiterMain.reset){
			y += 10 * OrbiterMain.imageSize;
			return;
		}

		attack += 1;
		followAttack += 1;
		if (y < 20) {
			y += 5 * OrbiterMain.imageSize;
		}
		if (attack > 500) {
			y += 5 * OrbiterMain.imageSize;
			b = fireSideGuns();
			if (OrbiterMain.ship.x > x) {
				x += 2 * OrbiterMain.imageSize;
			}
			if (OrbiterMain.ship.x < x) {
				x -= 2 * OrbiterMain.imageSize;
			}
		}
		if (y > OrbiterMain.canvasHeight) {
			attack = 0;
		}
		if (followAttack > 50) {
			b = fireSideGuns();
			if (OrbiterMain.ship.x > x) {
				x += 2 * OrbiterMain.imageSize;
			}
			if (OrbiterMain.ship.x < x) {
				x -= 2 * OrbiterMain.imageSize;
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
		float by = y + (height * OrbiterMain.imageSize / 2);
		float bx = x + (width * OrbiterMain.imageSize / 2) - (2 * OrbiterMain.imageSize / 2);
		return mainGun.fireBullet(bx, by, false, 2);
	}

	public Bullet fireSideGuns() {
		float by = y + (height * OrbiterMain.imageSize * 12 / 14);
		float bx = x;
		Bullet b = null;
		if (!fireLeft) {
			b = sideGunLeft.fireBullet(bx + (width * OrbiterMain.imageSize * 1 / 14), by, false, gunType);
		} else {
			b = sideGunRight.fireBullet(bx + (width * OrbiterMain.imageSize * 11 / 14), by, false, gunType);
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
		
		if (collidingElement instanceof Bullet) {
			damage -= 20;
		}

		if (collidingElement instanceof Asteroid) {
			damage -= 80;
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
