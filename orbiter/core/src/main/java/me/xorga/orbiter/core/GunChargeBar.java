package me.xorga.orbiter.core;

//Max Britton hi

import static playn.core.PlayN.graphics;
import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.ImageLayer;

public class GunChargeBar {

	Canvas canvas;
	static float charge;
	float totalCharge = 20;
	float chargeBarWidth;
	float initialWidth;
	float chargeReGen;
	float chargeReGenSpeed = 10;
	float tvPositionChange;

	public GunChargeBar() {
		if (OrbiterMain.tvMode) {
			tvPositionChange = 18 * OrbiterMain.imageSize;
		} else {
			tvPositionChange = 0;
		}
		charge = totalCharge;
		initialWidth = 50 * OrbiterMain.imageSize;
		CanvasImage image = graphics().createImage(50 * OrbiterMain.imageSize, 4 * OrbiterMain.imageSize);
		canvas = image.canvas();
		canvas.setFillColor(0xaa00ff00);

		ImageLayer layer = graphics().createImageLayer(image);
		layer.setTranslation(2 * OrbiterMain.imageSize + tvPositionChange, 12 * OrbiterMain.imageSize + tvPositionChange);
		graphics().rootLayer().add(layer);

		layer.setDepth(101);
	}

	private void updateHealth() {
		canvas.clear();
		canvas.fillRect(0, 0, chargeBarWidth, 5 * OrbiterMain.imageSize);
	}

	public void update(float delta) {
		chargeBarWidth = (charge / totalCharge) * initialWidth;
		chargeReGen += 1;
			if (charge < totalCharge && chargeReGen > chargeReGenSpeed && OrbiterMain.space == false && OrbiterMain.pause == false) {
				charge += 1;
				chargeReGen = 1;
		}
		if (OrbiterMain.level == 0) {
			canvas.clear();
		}
		// if (OrbiterMain.space == true) {
		// charge -= 10;
		// }
	}

	public void paint(float alpha) {
		updateHealth();
	}

	public void clear() {
		canvas.clear();
	}

}
