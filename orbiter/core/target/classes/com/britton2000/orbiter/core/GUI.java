package com.britton2000.orbiter.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;

//Max Britton

public class GUI {
	ImageLayer scoreLayer, statsLayer, pauseLayer, nameLayer;
	Image score, stats, pause, name;
	HealthBar hBar;
	GunChargeBar cBar;

	public static int buttonNum = 2;
	private boolean currentVisible = false;

	public GUI(final GroupLayer parentLayer) {

			score = assets().getImage("images/gui/guiLeft.png");
			scoreLayer = graphics().createImageLayer(score);
			graphics().rootLayer().add(scoreLayer);
			scoreLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
			scoreLayer.setTranslation(0, 0);
			scoreLayer.setDepth(100);
			scoreLayer.setVisible(false);
			
			stats = assets().getImage("images/gui/guiRight.png");
			statsLayer = graphics().createImageLayer(stats);
			graphics().rootLayer().add(statsLayer);
			statsLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
			statsLayer.setTranslation(260 * OrbiterMain.imageSize, 0);
			statsLayer.setDepth(100);
			statsLayer.setVisible(false);
			
			pause = assets().getImage("images/buttons/pauseButton.png");
			pauseLayer = graphics().createImageLayer(pause);
			graphics().rootLayer().add(pauseLayer);
			pauseLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
			pauseLayer.setTranslation(304 * OrbiterMain.imageSize, 20 * OrbiterMain.imageSize);
			pauseLayer.setDepth(100);
			pauseLayer.setVisible(false);
			
			name = assets().getImage("images/gui/name.png");
			nameLayer = graphics().createImageLayer(name);
			graphics().rootLayer().add(nameLayer);
			nameLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
			nameLayer.setTranslation(
					(OrbiterMain.canvasWidth / 2) - ((name.width() * OrbiterMain.imageSize) / 2), 0);
			nameLayer.setDepth(100);
			nameLayer.setVisible(false);

			hBar = new HealthBar();
			cBar = new GunChargeBar();
	}

	public void render(float alpha) {
		if (OrbiterMain.level == 1) {
			paint(alpha);
		}
	}

	private void guiVisible(boolean visible) {
		if (currentVisible == visible){
			return;
		}
		currentVisible = true;
		scoreLayer.setVisible(visible);
		statsLayer.setVisible(visible);
		pauseLayer.setVisible(visible);
		nameLayer.setVisible(visible); 
	}
	
	public void update(float delta) {
		
		pauseLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		    	  if (OrbiterMain.pause == false) { 
		    		  OrbiterMain.pause = true;
		    	  } else if (OrbiterMain.pause == true) {
		    		  OrbiterMain.pause = false;
		    	  }
		      }
		    });
		
		if (OrbiterMain.level == 1) {
			guiVisible(true);
		}
		if (OrbiterMain.level == 0) {
			guiVisible(false);
		}
		hBar.update(delta);
		cBar.update(delta);
	}

	public void paint(float alpha) {
		hBar.paint(alpha);
		cBar.paint(alpha);	
	}
	
	public void clear() {
		scoreLayer.destroy();
		statsLayer.destroy();
		pauseLayer.destroy();
		nameLayer.destroy();
		hBar.clear();
		cBar.clear();
	}
}