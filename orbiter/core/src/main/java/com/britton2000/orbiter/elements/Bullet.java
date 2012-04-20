package com.britton2000.orbiter.elements;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ResourceCallback;

import com.britton2000.orbiter.core.OrbiterMain;

public class Bullet extends Element {

	Image image;

	boolean bulletDirection;

	private final String imageName = "images/Bullet.png";

	public Bullet(final GroupLayer parentLayer, Element creator, boolean direction) {
		bulletDirection = direction;
		image = assets().getImage(this.getImage());
		layer = graphics().createImageLayer(image);
		layer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		height = image.height();
		width = image.width();
		this.creator = creator;
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
		if (bulletDirection) {
			y -= 15;
		} else {
			y += 15;
		}
	}

	@Override
	public String getImage() {
		return imageName;
	}

}
