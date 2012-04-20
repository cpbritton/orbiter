package com.britton2000.orbiter.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

import com.britton2000.orbiter.elements.Bullet;
import com.britton2000.orbiter.elements.Element;

public class Ship extends Element {

	ImageLayer layer;
	Image image;
	Image image2;

	float frameSwitch;
	public static int y;
	public static int x;
	public int y2;
	public int x2;
	public int width, height, gunType = 1;
	private final Gun mainGun = new Gun();
	private final Gun sideGunLeft = new Gun();
	private final Gun sideGunRight = new Gun();
	private boolean fireLeft = false;

	public Ship(int canvasWidth, int canvasHeight, final GroupLayer parentLayer) {
		image = assets().getImage("images/Ship1.png");
		image2 = assets().getImage("images/Ship2.png");
		layer = graphics().createImageLayer(image);

		width = image.width();
		height = image.height();
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

		layer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		x = (canvasWidth / 2);
		y = (canvasHeight / 2);
	}

	@Override
	public void render(float alpha) {
		if (frameSwitch > 10f) {
			if (layer.image() == image) {
				layer.setImage(image2);
			} else {
				layer.setImage(image);
			}
			frameSwitch = 0;

			layer.setTranslation(x, y);
		}
	}

	@Override
	public void update(float delta) {
		x2 = x + (width * OrbiterMain.imageSize);
		y2 = y + (height * OrbiterMain.imageSize);
		frameSwitch += delta;
		if (OrbiterMain.right) {
			if (x2 < OrbiterMain.canvasWidth) {
				x += 10;
			}
		} else if (OrbiterMain.left) {
			if (x > 0) {
				x -= 10;
			}
		}
		if (OrbiterMain.up) {
			if (y > 0) {
				y -= 10;
			}
		} else if (OrbiterMain.down) {
			if (y2 < OrbiterMain.canvasHeight) {
				y += 10;
			}
		}
	}

	public Bullet fireGun() {
		Bullet b = null;
		if (gunType == 1 || gunType == 2) {
			b = fireMainGun();
		}
		if (gunType == 3 || gunType == 4 || gunType == 5) {
			b = fireSideGuns();
		}

		return b;

	}

	public Bullet fireMainGun() {
		int by = y;

		// @todo fix bullet width
		int bx = x + width * OrbiterMain.imageSize / 2 - (2 * OrbiterMain.imageSize / 2);
		return mainGun.fireBullet(bx, by, true, gunType);
	}

	public Bullet fireSideGuns() {
		int by = y + (height * 2);
		int bx = x;

		Bullet b = null;
		if (!fireLeft) {
			b = sideGunLeft.fireBullet(bx + (width * OrbiterMain.imageSize * 2 / 7), by, true, gunType);
		} else {
			b = sideGunRight.fireBullet(bx + (width * OrbiterMain.imageSize * 4 / 7), by, true, gunType);
		}
		if (b != null) {
			fireLeft = !fireLeft;
		}

		return b;
	}
}
