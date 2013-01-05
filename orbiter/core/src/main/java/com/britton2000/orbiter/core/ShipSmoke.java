package com.britton2000.orbiter.core;

//Max Britton hi

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ResourceCallback;

import com.britton2000.orbiter.elements.Element;

public class ShipSmoke extends Element {

	Image image;
	Image image2;

	public int smokecolour = 1;
	protected boolean _explodes = false;
	protected boolean _collides = false;

	public ShipSmoke(Ship ship, final GroupLayer parentLayer) {
		image = assets().getImage("images/smokecolour.png");
		image2 = assets().getImage("images/smokecolour.png");
		layer = graphics().createImageLayer(image);
		layer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		layer.setDepth(2);
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

		float x = OrbiterMain.ship.getX() + ship.width * OrbiterMain.imageSize * 5 / 14;
		float y = OrbiterMain.ship.getY() + (ship.height * OrbiterMain.imageSize * 11 / 14);
		setPosition(Math.round(x), Math.round(y));
		setCreator(ship);
	}

	@Override
	public void update(float delta) {
		y += 15;
		if (smokecolour == 1) {
			layer.setImage(image2);
		} else if (smokecolour == 2) {
			layer.setImage(image);
		}
	}

}
