package me.xorga.orbiter.core;

//Max Britton hi

import static playn.core.PlayN.graphics;
import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.ImageLayer;

public class HealthBar {
	Canvas canvas;
	float health;
	float totalHealth;
	float healthBarWidth;
	float initialWidth;
	float tvPositionChange;

	public HealthBar() {
		if (OrbiterMain.tvMode) {
			tvPositionChange = 18 * OrbiterMain.imageSize;
		} else {
			tvPositionChange = 0;
		}
		initialWidth = 50 * OrbiterMain.imageSize;
		CanvasImage image = graphics().createImage(50 * OrbiterMain.imageSize, 4 * OrbiterMain.imageSize);
		canvas = image.canvas();
		canvas.setFillColor(0xaaff0000);

		ImageLayer layer = graphics().createImageLayer(image);
		layer.setTranslation(2 * OrbiterMain.imageSize + tvPositionChange, 4 * OrbiterMain.imageSize + tvPositionChange);
		graphics().rootLayer().add(layer);
		layer.setDepth(101);
		//267
	}

	private void updateHealth() {
		canvas.clear();
		canvas.fillRect(0, 0, healthBarWidth, 4 * OrbiterMain.imageSize);
	}

	public void update(float delta) {
		health = Ship.health;
		totalHealth = Ship.totalHealth;
  		healthBarWidth = (health / totalHealth) * initialWidth;
		if (OrbiterMain.level == 0) {
			canvas.clear();
		}
	}

	public void paint(float alpha) {
		updateHealth();
	}
	
	public void clear() {
		canvas.clear();
	}
}