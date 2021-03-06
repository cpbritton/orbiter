package me.xorga.orbiter.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.Touch;
import playn.core.Touch.Event;

//Max Britton hi

public class GUI {
	ImageLayer scoreLayer;
	ImageLayer statsLayer;
	ImageLayer pauseLayer;
	ImageLayer nameLayer;
	Image score, scoreTv, stats, statsTv, pause, name;
	HealthBar hBar;
	GunChargeBar cBar;

	public static int buttonNum = 2;
	private boolean currentVisible = false;
	double alpha = 0.6;
	float tvPositionChange;

	public GUI(final GroupLayer parentLayer) {
			if(OrbiterMain.tvMode) {
				tvPositionChange = 18 * OrbiterMain.imageSize;
				score = assets().getImageSync("images/gui/guiRightTv.png");
				stats = assets().getImageSync("images/gui/guiLeftTv.png");
			} else {
				tvPositionChange = 0;
				score = assets().getImageSync("images/gui/guiRight.png");
				stats = assets().getImageSync("images/gui/guiLeft.png");
			}
			scoreLayer = graphics().createImageLayer(score);
			graphics().rootLayer().add(scoreLayer);
			scoreLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
			scoreLayer.setTranslation(OrbiterMain.canvasWidth - (score.width() * OrbiterMain.imageSize), 0);
			scoreLayer.setDepth(100);
			scoreLayer.setVisible(false);
			scoreLayer.setAlpha((float) alpha);
			
			statsLayer = graphics().createImageLayer(stats);
			graphics().rootLayer().add(statsLayer);
			statsLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
			statsLayer.setTranslation(0, 0);
			statsLayer.setDepth(100);
			statsLayer.setVisible(false);
			statsLayer.setAlpha((float) alpha);
			
			pause = assets().getImageSync("images/buttons/pauseButton.png");
			pauseLayer = graphics().createImageLayer(pause);
			graphics().rootLayer().add(pauseLayer);
			pauseLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
			pauseLayer.setTranslation(304 * OrbiterMain.imageSize, 20 * OrbiterMain.imageSize);
			pauseLayer.setDepth(100);
			pauseLayer.setVisible(false);
			pauseLayer.addListener(new Pointer.Adapter() {
			      public void onPointerStart(Pointer.Event event) {
			    	  if (OrbiterMain.pause == false) { 
			    		  OrbiterMain.pause = true;
			    	  } else if (OrbiterMain.pause == true) {
			    		  OrbiterMain.pause = false;
			    	  }
			      }
			    });
			pauseLayer.addListener(new Touch.LayerListener() {
				@Override
				public void onTouchStart(Event touch) {
					if (OrbiterMain.pause == false) { 
			    		  OrbiterMain.pause = true;
			    	  } else if (OrbiterMain.pause == true) {
			    		  OrbiterMain.pause = false;
			    	  }
				}
				@Override
				public void onTouchMove(Event touch) {
					//nothing	
				}
				@Override
				public void onTouchEnd(Event touch) {
					//nothing
				}	
				@Override
				public void onTouchCancel(Event touch) {
					//nothing
				}
			});
			
//			name = assets().getImageSync("images/gui/name.png");
//			nameLayer = graphics().createImageLayer(name);
//			graphics().rootLayer().add(nameLayer);
//			nameLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
//			nameLayer.setTranslation(
//					(OrbiterMain.canvasWidth / 2) - ((name.width() * OrbiterMain.imageSize) / 2), 0);
//			nameLayer.setDepth(100);
//			nameLayer.setVisible(false);

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
		if (TouchControl.showButtons == false) {
			pauseLayer.setVisible(false);
		} else {
			pauseLayer.setVisible(visible);
		}
//		nameLayer.setVisible(visible); 
	}
	
	public void update(float delta) {
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
//		nameLayer.destroy();
		hBar.clear();
		cBar.clear();
	}
}