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
	
	int chose, xSpeed, ySpeed, xMoveLeftOrRight;

	public Gem(float canvaswidth, float canvasheight, final GroupLayer parentLayer) {
		image = assets().getImage("images/gem.png");
		layer = graphics().createImageLayer(image);
		layer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		layer.setDepth(3);
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
		y += ySpeed;
		if (xMoveLeftOrRight < 5) {
			x -= xSpeed;
		}else if (xMoveLeftOrRight >= 5) {
			x += xSpeed;
		}
		if (chose < 3) {
			chose += 1;
		}
		if (chose < 2) {
			ySpeed = (int) (Math.random() * OrbiterMain.imageSize) + 1;
			xSpeed = (int) (Math.random() * OrbiterMain.imageSize);
			xMoveLeftOrRight = (int) (Math.random() * 10);
		}
	}

}
