package com.britton2000.orbiter.elements;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ResourceCallback;

import com.britton2000.orbiter.core.OrbiterMain;

public class Asteroid extends Element {

	Image image;
	Image image2;
	Image image3;
	Image image4;

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
		y += 15;
	}

	@Override
	public void processCollisions(float delta) {

		if (collidingElement instanceof Bullet) {

			System.out.print("ooo:");
		}

	}
}
