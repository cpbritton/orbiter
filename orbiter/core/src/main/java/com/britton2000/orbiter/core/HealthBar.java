package com.britton2000.orbiter.core;

//Max Britton

import static playn.core.PlayN.graphics;
import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.ImageLayer;

public class HealthBar {
	Canvas canvas;
	float health = 50, totalHealth = 100, healthBarWidth = 0, initialWidth,
			healthReGen, healthReGenSpeed = 50;

	public HealthBar() {
		initialWidth = 255;
		CanvasImage image = graphics().createImage(265, 25);
		canvas = image.canvas();
		canvas.setFillColor(0xaaff0000);

		ImageLayer layer = graphics().createImageLayer(image);
		layer.setTranslation(1015, 640);
		graphics().rootLayer().add(layer);

	}

	private void updateHealth() {
		canvas.clear();
		canvas.fillRect(0, 0, healthBarWidth, 25);
	}

	public void update(float delta) {
		healthBarWidth = (health / totalHealth) * initialWidth;
		healthReGen += 1;
		if (health < 100 && healthReGen > healthReGenSpeed) {
			health += 1;
			healthReGen = 1;
		}
	}

	public void paint(float alpha) {
		updateHealth();
	}
}