package com.britton2000.orbiter.core;

import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;

public class Shop {

	GroupLayer menu;
	boolean visible = false;

	public Shop() {

		menu = graphics().createGroupLayer();

	}

	public void render(float alpha) {
	}

	public void update(float delta) {

		if (OrbiterMain.level == -1) {
			menu.setVisible(visible);
		}
	}

	public void paint(float alpha) {
	}

}
