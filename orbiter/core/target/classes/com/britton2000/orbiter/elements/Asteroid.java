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
import com.britton2000.orbiter.core.Ship;

public class Asteroid extends Element {

	Image image;
	Image image1;
	Image image2;
	Image image3;

	int health = 20, speed, speedtimer, chose, imageNumber, xSpeed, ySpeed, xMoveLeftOrRight;

	public Asteroid(float canvaswidth, float canvasheight,
			final GroupLayer parentLayer) {

		image = assets().getImage("images/asteroids/asteroid.png");
		image1 = assets().getImage("images/asteroids/asteroid2.png");
		image2 = assets().getImage("images/asteroids/asteroid3.png");
		image3 = assets().getImage("images/asteroids/asteroid4.png");
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
			imageNumber = (int) (Math.random() * 3);
			ySpeed = (int) (Math.random() * OrbiterMain.imageSize) + 1;
			xSpeed = (int) (Math.random() * OrbiterMain.imageSize);
			xMoveLeftOrRight = (int) (Math.random() * 10);
		}
		if (imageNumber == 0) {
			layer.setImage(image);
		} else if (imageNumber == 1) {
			layer.setImage(image1);
		} else if (imageNumber == 2) {
			layer.setImage(image2);
		}else if (imageNumber == 3) {
			layer.setImage(image3);
		}
		// speedtimer += 1;
		//
		// if (speedtimer > 100) {
		// speed += 1;
		// speedtimer = 0;
		// }}
	}

	@Override
	public void processCollisions(float delta) {
		if (!collision)
			return;

		if (collidingElement instanceof Bullet) {
			health -= 10;
		}
		
		if (collidingElement instanceof TracerBullet) {
			health -= 20;
		}

		if (collidingElement instanceof EnemyShip
				|| collidingElement instanceof EnemyBeastShip) {
			health -= 100;
		}
		
		if (collidingElement instanceof Ship) {
			health -= 100;
		}

		if (health <= 0) {
			_remove = true;
		}

		resetCollision();

	}
	
	
}
