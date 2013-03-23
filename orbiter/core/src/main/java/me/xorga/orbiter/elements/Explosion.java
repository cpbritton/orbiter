package me.xorga.orbiter.elements;

//Max Britton hi

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ResourceCallback;

import me.xorga.orbiter.core.Background;
import me.xorga.orbiter.core.OrbiterMain;

public class Explosion extends Element {
	
	Image boom1;
	Image boom2;
	Image boom3;
	Image boom4;
	Image boom5;
	Image boom6;
	Image boom7;
	Image boom8;
	Image boom9;
	Image boom10;
	Image boom11;
	
	int imageNumber, centerX, centerY;

	public Explosion( ) {
		_collides=false;
		boom1 = assets().getImageSync("images/boom/boom1.png");
		boom2 = assets().getImageSync("images/boom/boom2.png");
		boom3 = assets().getImageSync("images/boom/boom3.png");
		boom4 = assets().getImageSync("images/boom/boom4.png");
		boom5 = assets().getImageSync("images/boom/boom5.png");
		boom6 = assets().getImageSync("images/boom/boom6.png");
		boom7 = assets().getImageSync("images/boom/boom7.png");
		boom8 = assets().getImageSync("images/boom/boom8.png");
		boom9 = assets().getImageSync("images/boom/boom9.png");
		boom10= assets().getImageSync("images/boom/boom10.png");
		boom11= assets().getImageSync("images/boom/boom11.png");
		layer = graphics().createImageLayer(boom1);
		layer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		layer.setDepth(6);
		centerX = (int) (layer.scaledWidth() / 2);
		centerY = (int) (layer.scaledHeight() / 2);
		graphics().rootLayer().add(layer);
		
	}
	
	@Override
	public void update(float delta) {
		if (layer == null || layer.destroyed()){
			return;
		}
		
		y += Background.bgspeed;
		if (imageNumber < 24) {
			imageNumber += 1;
		}
		if (imageNumber == 0) {
			layer.setImage(boom1);
		} else if (imageNumber == 2) {
			layer.setImage(boom2);
		} else if (imageNumber == 4) {
			layer.setImage(boom3);
		} else if (imageNumber == 6) {
			layer.setImage(boom4);
		} else if (imageNumber == 8) {
			layer.setImage(boom5);
		} else if (imageNumber == 10) {
			layer.setImage(boom6);
		} else if (imageNumber == 12) {
			layer.setImage(boom7);
		} else if (imageNumber == 14) {
			layer.setImage(boom8);
		} else if (imageNumber == 16) {
			layer.setImage(boom9);
		} else if (imageNumber == 18) {
			layer.setImage(boom10);
		} else if (imageNumber == 20) {
			layer.setImage(boom11);
		} else if (imageNumber == 22) {
			layer.destroy();
			_remove = true;
		}
	}

	@Override
	public void processCollisions(float delta) {
		
		resetCollision();
	}
}
