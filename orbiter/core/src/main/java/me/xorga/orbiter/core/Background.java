package me.xorga.orbiter.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;

//Max Britton hi

public class Background {
	ImageLayer bglayer, bglayer2;
	Image bgimage, bgimage2;

	float bgx, bgy, bgx2, bgy2, moveBackground;
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
		bgy = -(180 * OrbiterMain.imageSize);

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
//		if (OrbiterMain.level == 0) {
//			bglayer.setVisible(false);
//			bglayer2.setVisible(false);
//		}
//		if (moveBackground <= 1) {
//			bgy = -(320 * OrbiterMain.imageSize);
//			moveBackground =+ 1;
//		}
		if (OrbiterMain.level == 0) {
			bgspeed = OrbiterMain.imageSize;
		} else {
			bgspeed = 2 * OrbiterMain.imageSize;
		}
		bglayer.setVisible(true);
		bglayer2.setVisible(true);

		if (OrbiterMain.pause == false) {
			bgy += bgspeed;
			bgy2 += bgspeed;
		}

		bgspeedtimer += 1;
		if (bgspeedtimer > 1000 && bgspeed < 30 && OrbiterMain.level == 1 && OrbiterMain.pause == false) {
			bgspeed += (OrbiterMain.imageSize * 2 / 8);
			bgspeedtimer = 0;
		}
		if (bgy >= 180 * (OrbiterMain.imageSize)) {
			bgy = -180 * (OrbiterMain.imageSize);
		}
		if (bgy2 >= (180 * OrbiterMain.imageSize)) {
			bgy2 = -(180 * OrbiterMain.imageSize);
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
