package com.britton2000.orbiter.core;

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

	public static int width, height;
	public int smokecolour = 2;

	public ShipSmoke(int canvaswidth, int canvasheight, final GroupLayer parentLayer) {
		image = assets().getImage("images/smokecolour.png");
		image2 = assets().getImage("images/smokecolour.png");
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
		if (smokecolour == 1) {
			layer.setImage(image2);
		} else if (smokecolour == 2) {
			layer.setImage(image);
		}
	}

}
