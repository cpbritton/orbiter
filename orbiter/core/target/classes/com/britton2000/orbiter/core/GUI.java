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

	public static int buttonNum = 2;

	public GUI(final GroupLayer parentLayer) {

		guiGroup = graphics().createGroupLayer();

		Image gui = assets().getImage("images/GUI.png");
		guiLayer = graphics().createImageLayer(gui);
		graphics().rootLayer().add(guiLayer);
		guiLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		guiLayer.setTranslation(0, 0);

		Image name = assets().getImage("images/name.png");
		nameLayer = graphics().createImageLayer(name);
		graphics().rootLayer().add(nameLayer);
		nameLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		nameLayer.setTranslation(0, 0);
	}

	public void render(float alpha) {
		paint(alpha);
	}

	public void update(float delta) {

		// if (OrbiterMain.level == 1) {
		// guiGroup.setVisible(visible);
		// }
	}

	public void paint(float alpha) {
		guiLayer.setTranslation(0, 0);
		nameLayer.setTranslation(0, 0);
	}
}