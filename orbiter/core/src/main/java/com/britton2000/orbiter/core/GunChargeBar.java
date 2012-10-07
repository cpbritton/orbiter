package com.britton2000.orbiter.core;

import static playn.core.PlayN.graphics;
import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.ImageLayer;

public class GunChargeBar {

	Canvas canvas;
	static float charge = 100;
	float totalCharge = 100;
	float chargeBarWidth;
	float initialWidth;
	float chargeReGen;
	float chargeReGenSpeed = 10;

	public GunChargeBar() {
		initialWidth = 255;
		CanvasImage image = graphics().createImage(260, 25);
		canvas = image.canvas();
		canvas.setFillColor(0xaa00ff00);

		ImageLayer layer = graphics().createImageLayer(image);
		layer.setTranslation(1015, 685);
		graphics().rootLayer().add(layer);

	}

	private void updateHealth() {
		canvas.clear();
		canvas.fillRect(0, 0, chargeBarWidth, 25);
	}

	public void update(float delta) {
		chargeBarWidth = (charge / totalCharge) * initialWidth;
		chargeReGen += 1;
		if (charge < 100 && chargeReGen > chargeReGenSpeed
				&& OrbiterMain.space == false) {
			charge += 1;
			chargeReGen = 1;
		}
		// if (OrbiterMain.space == true) {
		// charge -= 10;
		// }
	}

	public void paint(float alpha) {
		updateHealth();
	}

}
