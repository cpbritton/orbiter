package com.britton2000.orbiter.core;

//Max Britton

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

	public HealthBar() {
		initialWidth = 51 * OrbiterMain.imageSize;
		CanvasImage image = graphics().createImage(51 * OrbiterMain.imageSize, 5 * OrbiterMain.imageSize);
		canvas = image.canvas();
		canvas.setFillColor(0xaaff0000);

		ImageLayer layer = graphics().createImageLayer(image);
		layer.setTranslation(267 * OrbiterMain.imageSize, 2 * OrbiterMain.imageSize);
		graphics().rootLayer().add(layer);
		layer.setDepth(101);
	}

	private void updateHealth() {
		canvas.clear();
		canvas.fillRect(0, 0, healthBarWidth, 5 * OrbiterMain.imageSize);
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