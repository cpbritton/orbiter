package com.britton2000.orbiter.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.mouse;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;

public class PauseMenu {
	
	ImageLayer pauseBackgroundLayer, backtogameLayer, optionsLayer, quitLayer, quitGameLayer;
	Image pauseBackground;
	Image backtogame, backtogameSelected, options, optionsSelected, quit, quitSelected, quitGame, quitGameSelected;
	GroupLayer pauseMenu;
	
	int pauseMenuToggleRate = 0, pauseMenuButtonSelectionNumber = 0, pauseMenuButtonSelectionHighestNumber = 2, pauseMenuButtonToggleRate;
	private boolean currentVisible = false;

	public PauseMenu(float canvaswidth, float canvasheight, final GroupLayer parentLayer) {
		pauseMenu = graphics().createGroupLayer();
		pauseMenu.setVisible(false);
		
		pauseBackground = assets().getImageSync("images/gui/pauseMenuBackground.png");
		pauseBackgroundLayer = graphics().createImageLayer(pauseBackground);
		pauseBackgroundLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		pauseBackgroundLayer.setTranslation((OrbiterMain.canvasWidth / 2) - (OrbiterMain.imageSize * pauseBackground.width() / 2),
				(OrbiterMain.canvasHeight / 2) - (OrbiterMain.imageSize * pauseBackground.height() / 2));
		pauseBackgroundLayer.setVisible(false);
		pauseBackgroundLayer.setDepth(99);
		
		backtogame = assets().getImageSync("images/buttons/backtogame.png");
		backtogameSelected = assets().getImageSync("images/buttons/backtogameselected.png");
		backtogameLayer = graphics().createImageLayer(backtogame);
		backtogameLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		backtogameLayer.setTranslation((canvaswidth / 2) - ((OrbiterMain.imageSize * pauseBackground.width()) / 2)
				+ 13 * OrbiterMain.imageSize, 
				(canvasheight / 2) - ((OrbiterMain.imageSize * pauseBackground.height()) / 2)
				+ 22 * OrbiterMain.imageSize);
		backtogameLayer.setVisible(false);
		backtogameLayer.setDepth(99);
		backtogameLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		    	   backtogameLayer.setImage(backtogameSelected);
		      }
		      public void onPointerEnd(Pointer.Event event) {
		    	  backtogameLayer.setImage(backtogame);
		    	  OrbiterMain.pause = false;
		      }
		    });
		
		options = assets().getImageSync("images/buttons/options.png");
		optionsSelected = assets().getImageSync("images/buttons/optionsselected.png");
		optionsLayer = graphics().createImageLayer(options);
		optionsLayer.setScale(OrbiterMain.imageSize);
		optionsLayer.setTranslation((canvaswidth / 2) - ((OrbiterMain.imageSize * pauseBackground.width()) / 2)
				+ 13 * OrbiterMain.imageSize, 
				(canvasheight / 2) - ((OrbiterMain.imageSize * pauseBackground.height()) / 2)
				+ 44 * OrbiterMain.imageSize);
		optionsLayer.setVisible(false);
		optionsLayer.setDepth(99);
		optionsLayer.addListener(new Pointer.Adapter() {
			public void onPointerStart(Pointer.Event event) {
				  optionsLayer.setImage(optionsSelected);
			}
			public void onPointerEnd(Pointer.Event event) {
		    	  optionsLayer.setImage(options);
		    	  OptionsMenu.showOptions = true;
			}
		});
		
		quit = assets().getImageSync("images/buttons/quit.png");
		quitSelected = assets().getImageSync("images/buttons/quitselected.png");
		quitLayer = graphics().createImageLayer(quit);
		quitLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		quitLayer.setTranslation((canvaswidth / 2) - ((OrbiterMain.imageSize * pauseBackground.width()) / 2)
				+ 82 * OrbiterMain.imageSize, 
				(canvasheight / 2) - ((OrbiterMain.imageSize * pauseBackground.height()) / 2)
				+ 44 * OrbiterMain.imageSize);
		quitLayer.setVisible(false);
		quitLayer.setDepth(99);
		quitLayer.addListener(new Pointer.Adapter() {
			public void onPointerStart(Pointer.Event event) {
				quitLayer.setImage(quitSelected);
		      }
			public void onPointerEnd(Pointer.Event event) {
				quitLayer.setImage(quit);
		    	OrbiterMain.pause = false;
		    	OrbiterMain.level = 0;
		    	OrbiterMain.reset = true;
		      }
		});
		
		pauseMenu.add(pauseBackgroundLayer);
		pauseMenu.add(backtogameLayer);
		pauseMenu.add(optionsLayer);
		pauseMenu.add(quitLayer);
		graphics().rootLayer().add(pauseMenu);
		pauseMenu.setDepth(100);
	}
	
	private void pauseMenuVisible(boolean visible) {
		if (currentVisible == visible){
			return;
		}
		currentVisible = visible;
		pauseBackgroundLayer.setVisible(visible);
		backtogameLayer.setVisible(visible);
		optionsLayer.setVisible(visible);
		quitLayer.setVisible(visible);
		pauseMenu.setVisible(visible);
	}
	
	public void update(float delta) {
		if (OrbiterMain.pause) {
			pauseMenuVisible(true);
		} else {
			pauseMenuVisible(false);
		}
		if (OrbiterMain.pause == true) {
			if (mouse().hasMouse() == false) {
			if (pauseMenuButtonToggleRate < 6) {
				pauseMenuButtonToggleRate += 1;
			}
			if (OrbiterMain.up) {
				if (pauseMenuButtonToggleRate > 5) {
				pauseMenuButtonSelectionNumber -= 1;
				pauseMenuButtonToggleRate = 0;
				if (pauseMenuButtonSelectionNumber < 0) {
					pauseMenuButtonSelectionNumber = pauseMenuButtonSelectionHighestNumber;
				}
				}
			}
			if (OrbiterMain.down) {
				if (pauseMenuButtonToggleRate > 5) {
					pauseMenuButtonSelectionNumber += 1;
					pauseMenuButtonToggleRate = 0;
				if (pauseMenuButtonSelectionNumber > pauseMenuButtonSelectionHighestNumber) {
					pauseMenuButtonSelectionNumber = 0;
				}
				}
			}

			if (pauseMenuButtonSelectionNumber == 0) {
				backtogameLayer.setImage(backtogameSelected);
				if (OrbiterMain.enter) {
					OrbiterMain.pause = false;
//					OrbiterMain.initiateObjects = false;
//					OrbiterMain.initiateObjectsNumber = 0;
				}
			} else {
				backtogameLayer.setImage(backtogame);
			}
			if (pauseMenuButtonSelectionNumber == 1) {
				optionsLayer.setImage(optionsSelected);
				if (OrbiterMain.enter) {
					OrbiterMain.pause = false;
//					OrbiterMain.initiateObjects = false;
//					OrbiterMain.initiateObjectsNumber = 0;
				}
			} else {
				optionsLayer.setImage(options);
			}
			if (pauseMenuButtonSelectionNumber == 2) {
				quitLayer.setImage(quitSelected);
				if (OrbiterMain.enter) {
					OrbiterMain.pause = false;
			    	OrbiterMain.level = 0;
			    	OrbiterMain.reset = true;
//					OrbiterMain.initiateObjects = false;
//					OrbiterMain.initiateObjectsNumber = 0;
				}
			} else {
				quitLayer.setImage(quit);
			}
			}
		}
	}
}
