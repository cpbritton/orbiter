package com.britton2000.orbiter.elements;

//Max Britton

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ResourceCallback;

import com.britton2000.orbiter.core.Background;
import com.britton2000.orbiter.core.OrbiterMain;

public class Gem extends Element {

	Image image;
	Image image2;
	Image image3;
	Image image4;

	public Gem(int canvaswidth, int canvasheight, final GroupLayer parentLayer) {
		image = assets().getImage("images/gem.png");
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
	}

}
