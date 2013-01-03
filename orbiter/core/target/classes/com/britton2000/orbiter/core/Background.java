package com.britton2000.orbiter.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;

//Max Britton

public class Background {
	ImageLayer bglayer, bglayer2;
	Image bgimage, bgimage2;

	float bgx, bgy, bgx2, bgy2;
	public static float bgspeed;
	float bgspeedtimer;

	public static int buttonNum = 2;

	public Background(float canvaswidth, float canvasheight,
			final GroupLayer parentLayer) {
		Image bgimage = assets().getImageSync("images/background.png");
		bglayer2 = graphics().createImageLayer(bgimage);
		graphics().rootLayer().add(bglayer2);
		bglayer2.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		bglayer2.setVisible(false);
		bglayer2.setDepth(1);
		bgx = 0;
		bgy = -OrbiterMain.canvasHeight;

		Image bgimage2 = assets().getImageSync("images/background.png");
		
		bglayer = graphics().createImageLayer(bgimage2);
		graphics().rootLayer().add(bglayer);
		bglayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		bglayer.setVisible(false);
		bglayer.setDepth(1);
		bgx2 = 0;
		bgy2 = 0;
		
		bgspeed = 2 * OrbiterMain.imageSize;
	}

	public void render(float alpha) {
		paint(alpha);
	}

	public void update(float delta) {
		if (OrbiterMain.level == 0) {
			bglayer.setVisible(false);
			bglayer2.setVisible(false);
		}
		if (OrbiterMain.level == 1) {
			bglayer.setVisible(true);
			bglayer2.setVisible(true);
		}

		bgy += bgspeed;
		bgy2 += bgspeed;

		bgspeedtimer += 1;
		if (bgspeedtimer > 1000 && bgspeed < 30 && OrbiterMain.level == 1) {
			bgspeed += (OrbiterMain.imageSize * 2 / 8);
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
	
	public void clear() {
		bglayer.destroy();
		bglayer2.destroy();
	}
}
