package me.xorga.orbiter.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.mouse;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Key;
import playn.core.Pointer;
import playn.core.Sound;
import playn.core.Touch;
import playn.core.Touch.Event;

public class PauseMenu {
	
	ImageLayer pauseBackgroundLayer, backtogameLayer, optionsLayer, quitLayer, quitGameLayer;
	Image pauseBackground;
	Image backtogame, backtogameSelected, options, optionsSelected, quit, quitSelected, quitGame, quitGameSelected;
	GroupLayer pauseMenu;
	
	int pauseMenuToggleRate = 0, pauseMenuButtonSelectionNumber = 0, pauseMenuButtonSelectionHighestNumber = 2, pauseMenuButtonToggleRate;
	public static boolean currentVisible = false;
	private final Sound enterSelect;
	private final Sound select;
	double alpha = 0.6;

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
		pauseBackgroundLayer.setAlpha((float) alpha);
		
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
		    	  if (OptionsMenu.soundOptionOn == true) {
			    		enterSelect.play();
			    	}
		    	  backtogameLayer.setImage(backtogame);
		    	  OrbiterMain.pause = false;
		      }
		    });
		
		options = assets().getImageSync("images/buttons/option.png");
		optionsSelected = assets().getImageSync("images/buttons/optionselected.png");
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
				if (OptionsMenu.soundOptionOn == true) {
		    		enterSelect.play();
		    	}
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
		    	if (OptionsMenu.soundOptionOn == true) {
		    		enterSelect.play();
		    	}
				quitLayer.setImage(quit);
		    	OrbiterMain.pause = false;
		    	
		    	OrbiterMain.reset=true;
		      }
		});
		
		pauseMenu.add(pauseBackgroundLayer);
		pauseMenu.add(backtogameLayer);
		pauseMenu.add(optionsLayer);
		pauseMenu.add(quitLayer);
		graphics().rootLayer().add(pauseMenu);
		pauseMenu.setDepth(100);
	
		backtogameLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				backtogameLayer.setImage(backtogameSelected);
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
				if (OptionsMenu.soundOptionOn == true) {
		    		enterSelect.play();
		    	}
				backtogameLayer.setImage(backtogame);
		    	  OrbiterMain.pause = false;
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});
		optionsLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				optionsLayer.setImage(optionsSelected);
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
				if (OptionsMenu.soundOptionOn == true) {
		    		enterSelect.play();
		    	}
				optionsLayer.setImage(options);
		    	OptionsMenu.showOptions = true;
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});
		quitLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				quitLayer.setImage(quitSelected);
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
		    	if (OptionsMenu.soundOptionOn == true) {
		    		enterSelect.play();
		    	}
				quitLayer.setImage(quit);
		    	OrbiterMain.pause = false;
		    	OrbiterMain.reset = true;
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});
		enterSelect = assets().getSound("sounds/EnterSelect");
		select = assets().getSound("sounds/Select");
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
	
	public void onKey(playn.core.Keyboard.Event event){
		// process keystroke
		if (this.currentVisible && pauseMenuButtonSelectionNumber == 1 && event.key() == event.key().ENTER) {
//			optionsLayer.setImage(options);
	    	OptionsMenu.showOptions = true;
//			OrbiterMain.initiateObjects = false;
//			OrbiterMain.initiateObjectsNumber = 0;
			if (OptionsMenu.soundOptionOn == true) {
				enterSelect.play();
			}
		}else if (this.currentVisible && pauseMenuButtonSelectionNumber == 0 && event.key() == event.key().ENTER){
			OrbiterMain.pause = false;
//			OrbiterMain.initiateObjects = false;
//			OrbiterMain.initiateObjectsNumber = 0;
			if (OptionsMenu.soundOptionOn == true) {
				enterSelect.play();
			}
		}else if (this.currentVisible && pauseMenuButtonSelectionNumber == 2 && event.key() == event.key().ENTER){
			if (OptionsMenu.soundOptionOn == true) {
	    		enterSelect.play();
	    	}
//			quitLayer.setImage(quit);
	    	OrbiterMain.pause = false;
	    	OrbiterMain.reset = true;
	    	
		} else if(this.currentVisible && (event.key() == Key.ESCAPE || event.key() == Key.P )){
			OrbiterMain.pause = false;
//			OrbiterMain.initiateObjects = false;
//			OrbiterMain.initiateObjectsNumber = 0;
			if (OptionsMenu.soundOptionOn == true) {
				enterSelect.play();
			}
		}
			
	}
	
	public void update(float delta) {
		if (OrbiterMain.pause && OptionsMenu.showOptions == false) {
			pauseMenuVisible(true);
		} else {
			pauseMenuVisible(false);
		}
		if (OrbiterMain.pause == true && OptionsMenu.showOptions == false) {
			//if (mouse().hasMouse() == false && mouse().hasMouse() == true) {
			if (pauseMenuButtonToggleRate < 6) {
				pauseMenuButtonToggleRate += 1;
			}
			if (OrbiterMain.up) {
				if (pauseMenuButtonToggleRate > 5) {
				pauseMenuButtonSelectionNumber -= 1;
				pauseMenuButtonToggleRate = 0;
				if (OptionsMenu.soundOptionOn == true) {
					select.play();
				}
				if (pauseMenuButtonSelectionNumber < 0) {
					pauseMenuButtonSelectionNumber = pauseMenuButtonSelectionHighestNumber;
				}
				}
			}
			if (OrbiterMain.down) {
				if (pauseMenuButtonToggleRate > 5) {
					pauseMenuButtonSelectionNumber += 1;
					pauseMenuButtonToggleRate = 0;
					if (OptionsMenu.soundOptionOn == true) {
						select.play();
					}
					if (pauseMenuButtonSelectionNumber > pauseMenuButtonSelectionHighestNumber) {
						pauseMenuButtonSelectionNumber = 0;
					}
				}
			}

			if (pauseMenuButtonSelectionNumber == 0) {
				backtogameLayer.setImage(backtogame);
//				if (OrbiterMain.enter) {
//					OrbiterMain.pause = false;
////					OrbiterMain.initiateObjects = false;
////					OrbiterMain.initiateObjectsNumber = 0;
//					if (OptionsMenu.soundOptionOn == true) {
//						enterSelect.play();
//					}
//				}
			} else {
				backtogameLayer.setImage(backtogameSelected);
			}
			if (pauseMenuButtonSelectionNumber == 1) {
				optionsLayer.setImage(options);
//				if (OrbiterMain.enter) {
//					optionsLayer.setImage(options);
//			    	OptionsMenu.showOptions = true;
////					OrbiterMain.initiateObjects = false;
////					OrbiterMain.initiateObjectsNumber = 0;
//					if (OptionsMenu.soundOptionOn == true) {
//						enterSelect.play();
//					}
//				}
			} else {
				optionsLayer.setImage(optionsSelected);
			}
			if (pauseMenuButtonSelectionNumber == 2) {
				quitLayer.setImage(quit);
//				if (OrbiterMain.enter) {
//					if (OptionsMenu.soundOptionOn == true) {
//			    		enterSelect.play();
//			    	}
//					quitLayer.setImage(quit);
//			    	OrbiterMain.pause = false;
//			    	OrbiterMain.level = 0;
//			    	OrbiterMain.reset = true;
//			    	
//				}
			} else {
				quitLayer.setImage(quitSelected);
			}
			}
		//}
	}
}
