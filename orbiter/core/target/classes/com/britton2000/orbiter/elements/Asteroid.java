package com.britton2000.orbiter.elements;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ResourceCallback;

import com.britton2000.orbiter.core.Background;
import com.britton2000.orbiter.core.OrbiterMain;

public class Asteroid extends Element {

	Image image;
	Image image2;
	Image image3;
	Image image4;

	int damage = 20, speed = 8, speedtimer;

	public Asteroid(int canvaswidth, int canvasheight,
			final GroupLayer parentLayer) {

		image = assets().getImage("images/asteroid.png");
		image2 = assets().getImage("images/asteroid2.png");
		image3 = assets().getImage("images/asteroid3.png");
		image4 = assets().getImage("images/asteroid4.png");
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
	}

	@Override
	public void update(float delta) {
		y += Background.bgspeed;
		y += 1;

		// speedtimer += 1;
		//
		// if (speedtimer > 100) {
		// speed += 1;
		// speedtimer = 0;
		// }
	}

	@Override
	public void processCollisions(float delta) {
		if (!collision)
			return;

		if (collidingElement instanceof Bullet) {
			damage -= 10;
		}

		if (collidingElement instanceof EnemyShip
				|| collidingElement instanceof EnemyBeastShip) {
			damage -= 100;
		}

		if (damage < 0) {
			_remove = true;
		}

		resetCollision();

	}
}
