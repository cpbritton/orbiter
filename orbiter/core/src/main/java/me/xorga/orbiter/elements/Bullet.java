package me.xorga.orbiter.elements;

//Max Britton hi

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ResourceCallback;

import me.xorga.orbiter.core.OrbiterMain;

public class Bullet extends Element {

	Image image;

	boolean bulletDirection;

	private final String imageName = "images/Bullet.png";

	public Bullet(final GroupLayer parentLayer, Element creator,
			boolean direction) {
		bulletDirection = direction;
		image = assets().getImageSync(this.getImage());
		layer = graphics().createImageLayer(image);
		layer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		layer.setDepth(2);
		height = (int) image.height();
		width = (int) image.width();
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
			y -= 6 * OrbiterMain.imageSize;
		} else {
			y += 6 * OrbiterMain.imageSize;
		}
	}

	@Override
	public String getImage() {
		return imageName;
	}

	@Override
	public void processCollisions(float delta) {
		
		if (!(collidingElement instanceof Bullet) || !(collidingElement instanceof Explosion)) {
			_remove = true;
		}
		resetCollision();
	}

}
