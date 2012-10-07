package com.britton2000.orbiter.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;

public class Background {
	ImageLayer bglayer, bglayer2;
	Image bgimage, bgimage2;

	float bgx, bgy, bgx2, bgy2;
	public static int bgspeed = 8;
	float bgspeedtimer;

	public static int buttonNum = 2;

	public Background(int canvaswidth, int canvasheight,
			final GroupLayer parentLayer) {
		Image bgimage = assets().getImage("images/Stars.png");
		bglayer2 = graphics().createImageLayer(bgimage);
		graphics().rootLayer().add(bglayer2);
		bglayer2.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		bgx = 0;
		bgy = -OrbiterMain.canvasHeight;

		Image bgimage2 = assets().getImage("images/Stars.png");
		bglayer = graphics().createImageLayer(bgimage2);
		graphics().rootLayer().add(bglayer);
		bglayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		bgx2 = 0;
		bgy2 = 0;
	}

	public void render(float alpha) {
		paint(alpha);
	}

	public void update(float delta) {

		bgy += bgspeed;
		bgy2 += bgspeed;

		bgspeedtimer += 1;

		if (bgspeedtimer > 1000 && bgspeed < 50 && OrbiterMain.level > 0) {
			bgspeed += (1 / 4);
			bgspeedtimer = 0;
		}

		if (bgy >= OrbiterMain.canvasHeight) {
			bgy = -OrbiterMain.canvasHeight;
		}
		if (bgy2 >= OrbiterMain.canvasHeight) {
			bgy2 = -OrbiterMain.canvasHeight;
		}
	}

	public void paint(float alpha) {
		bglayer.setTranslation(bgx, bgy);
		bglayer2.setTranslation(bgx2, bgy2);
	}
}
