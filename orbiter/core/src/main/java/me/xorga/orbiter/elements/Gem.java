package me.xorga.orbiter.elements;

//Max Britton hi

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;
import playn.core.Image;

import me.xorga.orbiter.core.Background;
import me.xorga.orbiter.core.OrbiterMain;

public class Gem extends Element {

	Image image;
	Image image2;
	Image image3;
	Image image4;
	
	float chose, xSpeed, ySpeed, xMoveLeftOrRight;
	float gemPattern, launchGems, gemSequenceNumber, startPos;

	public Gem(float canvaswidth, float canvasheight, final GroupLayer parentLayer) {
		image = assets().getImageSync("images/gem.png");
		layer = graphics().createImageLayer(image);
		layer.setScale(OrbiterMain.imageSize);
		layer.setDepth(3);
	}

	@Override
	public void update(float delta) {
		y += Background.bgspeed;
//		if (xMoveLeftOrRight < 5) {
//			x -= xSpeed;
//		}else if (xMoveLeftOrRight >= 5) {
//			x += xSpeed;
//		}
//		if (chose < 3) {
//			chose += 1;
//		}
//		if (chose < 2) {
//			ySpeed = (int) (Math.random() * OrbiterMain.imageSize) + 1;
//			xSpeed = (int) (Math.random() * OrbiterMain.imageSize);
//			xMoveLeftOrRight = (int) (Math.random() * 10);
//		}
	}

}
