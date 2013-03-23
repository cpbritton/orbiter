package me.xorga.orbiter.core;

//Max Britton hi

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ResourceCallback;
import playn.core.Sound;

import me.xorga.orbiter.elements.Asteroid;
import me.xorga.orbiter.elements.Bullet;
import me.xorga.orbiter.elements.Element;
import me.xorga.orbiter.elements.EnemyShip;
import me.xorga.orbiter.elements.TracerBullet;

public class Ship extends Element {

	//ImageLayer layer;
	Image image;
	Image image2;

	float frameSwitch;
	public static int xVelocity = 0, yVelocity = 0;
	public int width, height, gunType = 1;
	public static int health, totalHealth = 100;
	private final Gun mainGun, sideGunLeft, sideGunRight;
	private boolean fireLeft = false;
	float healthReGen, healthReGenSpeed = 50, playAlertSound;
	private final Sound explosion;
	private final Sound hit;
	private final Sound alert;

	public Ship(float canvasWidth, float canvasHeight, final GroupLayer parentLayer) {
		super();
		image = assets().getImageSync("images/Ship1.png");
		image2 = assets().getImageSync("images/Ship2.png");
		layer = graphics().createImageLayer(image);
		layer.setDepth(4);
		width = (int) image.width();
		height = (int) image.height();
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
		layer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		x = (int) ((canvasWidth / 2) - ((OrbiterMain.imageSize * layer.width()) / 2));
		y = (int) ((canvasHeight / 2) - ((OrbiterMain.imageSize * layer.height()) / 2));
		mainGun = new Gun(this);
		sideGunLeft = new Gun(this);
		sideGunRight = new Gun(this);
		layer.setVisible(false);
		health = (int) totalHealth;
		explosion = assets().getSound("sounds/Explosion3");
		hit = assets().getSound("sounds/Hit_Hurt7");
		alert = assets().getSound("sounds/AlertGood");
	}

	@Override
	public void render(float alpha) {
		if (frameSwitch > 20f) {
			if (layer.image() == image) {
				layer.setImage(image2);
			} else {
				layer.setImage(image);
			}
			frameSwitch = 0;
			layer.setTranslation(x, y);
		}
	}

	@Override
	public void update(float delta) {
		
		if (layer == null || layer.destroyed() || _remove){
			return;
		}
		if (OrbiterMain.level == 0) {
			layer.setVisible(false);
		}
		if (OrbiterMain.level == 1) {
			layer.setVisible(true);
		}
		
		
		frameSwitch += delta;
		if (OrbiterMain.right) {
			if (this.getX2() < OrbiterMain.canvasWidth) {			
				x += 2 * OrbiterMain.imageSize;
			}	
		} else if (OrbiterMain.left) {
			if (x > 0) {
				x -= 2 * OrbiterMain.imageSize;
			}
		}
		if (OrbiterMain.up) {
			if (y > 0) {
				y -= 2 * OrbiterMain.imageSize;
			}
		} else if (OrbiterMain.down) {
			if (this.getY2() < OrbiterMain.canvasHeight) {
				y += 2 * OrbiterMain.imageSize;
			}
		}
		healthReGen += 1;
		if (health < totalHealth && healthReGen > healthReGenSpeed) {
			health += 1;
			healthReGen = 1;
		}
		if (health <= 20) {
			playAlertSound =+ 1;
			if (playAlertSound > 20) {
				alert.play();
				playAlertSound = 0;
			}
		}
		if (health > 20) {
			playAlertSound = 0;
		}
	}

	public Bullet fireGun() {
		Bullet b = null;
		if (GunChargeBar.charge > 0) {
			if (gunType == 1 || gunType == 2) {
				b = fireMainGun();
			}
			if (gunType == 3 || gunType == 4 || gunType == 5) {
				b = fireSideGuns();
			}
		}
		return b;
	}

	public Bullet fireMainGun() {
		float by = y + ((OrbiterMain.imageSize * height) / 3);
		float bx = x + (width * OrbiterMain.imageSize / 2)
				- (2 * OrbiterMain.imageSize / 2);
		return mainGun.fireBullet(bx, by, true, gunType);
	}

	public Bullet fireSideGuns() {
		float by = y + ((OrbiterMain.imageSize * height) / 3);
		float bx = x;
		Bullet b = null;
		if (!fireLeft) {
			b = sideGunLeft.fireBullet(bx + (width * OrbiterMain.imageSize * 4 / 14), by, true, gunType);
		} else {
			b = sideGunRight.fireBullet(bx + (width * OrbiterMain.imageSize * 8 / 14), by, true, gunType);
		}
		if (b != null) {
			fireLeft = !fireLeft;
		}
		return b;
	}
	
	public int getBulletCount(){
		return mainGun.getBulletCount() + sideGunRight.getBulletCount() + sideGunLeft.getBulletCount();
	}
	
	@Override
	public void processCollisions(float delta) {
		if (!collision)
			return;
		if (collidingElement instanceof Bullet) {
			health -= 5;
			hit.play();
		}
		if(collidingElement instanceof EnemyShip){
			health -= 80;
			hit.play();
		}
		if (collidingElement instanceof TracerBullet) {
			health -= 10;
			hit.play();
		}
		if (collidingElement instanceof Asteroid) {
			health -= 60;
			if (OptionsMenu.soundOptionOn == true) {
				hit.play();
			}
		}
		if (health < 0) {
			if (OptionsMenu.soundOptionOn == true) {
				explosion.play();
			}
			layer.setVisible(false);
			_remove = true;
		}
		resetCollision();
	}
}
