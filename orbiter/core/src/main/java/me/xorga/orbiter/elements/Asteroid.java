package me.xorga.orbiter.elements;

//Max Britton hi

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ResourceCallback;

import me.xorga.orbiter.core.Background;
import me.xorga.orbiter.core.OrbiterMain;
import me.xorga.orbiter.core.Ship;

public class Asteroid extends Element {

	Image image;
	Image image1;
	Image image2;
	Image image3;

	int health = 20, speed, speedtimer, chose, imageNumber, xSpeed, ySpeed, xMoveLeftOrRight, size;

	public Asteroid(float canvaswidth, float canvasheight,
			final GroupLayer parentLayer) {
		super();
		image = assets().getImageSync("images/asteroids/asteroid.png");
		image1 = assets().getImageSync("images/asteroids/asteroid2.png");
		image2 = assets().getImageSync("images/asteroids/asteroid3.png");
		image3 = assets().getImageSync("images/asteroids/asteroid4.png");
		layer = graphics().createImageLayer(image);
		layer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		layer.setDepth(3);
		parentLayer.add(layer);
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
			layer.setImage(image3);
			health = 10;
			size = 1;
		} else if (imageNumber == 1) {
			layer.setImage(image2);
			health = 20;
			size = 2;
		} else if (imageNumber == 2) {
			layer.setImage(image1);
			health = 30;
			size = 3;
		}else if (imageNumber == 3) {
			layer.setImage(image);
			health = 40;
			size = 4;
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
