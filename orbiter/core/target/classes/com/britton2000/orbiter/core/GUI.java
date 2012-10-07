package com.britton2000.orbiter.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;

public class GUI {
	ImageLayer guiLayer, nameLayer;
	Image gui, name;
	GroupLayer guiGroup;
	HealthBar hBar;
	GunChargeBar cBar;

	public static int buttonNum = 2;

	public GUI(final GroupLayer parentLayer) {
		if (OrbiterMain.level > 0) {

			guiGroup = graphics().createGroupLayer();

			Image gui = assets().getImage("images/GUI.png");
			guiLayer = graphics().createImageLayer(gui);
			graphics().rootLayer().add(guiLayer);
			guiLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
			guiLayer.setTranslation(0, 0);

			name = assets().getImage("images/name.png");
			nameLayer = graphics().createImageLayer(name);
			graphics().rootLayer().add(nameLayer);
			nameLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
			nameLayer.setTranslation(
					(OrbiterMain.canvasWidth / 2)
							- (name.width() * (OrbiterMain.imageSize)),
					OrbiterMain.canvasHeight
							- (name.height() * (OrbiterMain.imageSize / 2)));

			hBar = new HealthBar();
			cBar = new GunChargeBar();
		}
	}

	public void render(float alpha) {
		if (OrbiterMain.level > 0) {
			paint(alpha);
		}
	}

	public void update(float delta) {

		if (OrbiterMain.level == 1) {
			guiGroup.setVisible(true);
		}
		if (OrbiterMain.level == 0) {
			guiGroup.setVisible(false);
		}
		hBar.update(delta);
		cBar.update(delta);
	}

	public void paint(float alpha) {
		hBar.paint(alpha);
		cBar.paint(alpha);
		guiLayer.setTranslation(0, 0);
		nameLayer.setTranslation(
				(OrbiterMain.canvasWidth / 2)
						- (nameLayer.width() * (OrbiterMain.imageSize / 2)),
				OrbiterMain.canvasHeight
						- (name.height() * (OrbiterMain.imageSize)));
	}
}