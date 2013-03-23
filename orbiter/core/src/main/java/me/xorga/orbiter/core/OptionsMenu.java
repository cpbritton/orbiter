package me.xorga.orbiter.core;

//Max Britton hi

//import static playn.core.PlayN.assets;
//import static playn.core.PlayN.graphics;
//import static playn.core.PlayN.log;
//import static playn.core.PlayN.mouse;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.mouse;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.Key;
import playn.core.Pointer;
import playn.core.Sound;
import playn.core.Touch;
//import playn.core.Image;
//import playn.core.ImageLayer;
//import playn.core.Pointer;
import playn.core.ImageLayer;
import playn.core.Touch.Event;

public class OptionsMenu {
	
	ImageLayer optionsTitleLayer, backLayer, optionsBackgroundLayer, soundLayer;
	Image optionsTitle, back, backSelected, optionsBackground, soundOn, soundOnSelected, soundOff, soundOffSelected;
	GroupLayer optionsMenu;
	
	int buttonToggleRate, buttonSelectionNumber, buttonSelectionHighestNumber;
	private boolean currentVisible = false;
	static boolean showOptions;
	static boolean soundOptionOn = true;
	private final Sound enterSelect;
	private final Sound select;

	public OptionsMenu(float canvaswidth, float canvasheight, final GroupLayer parentLayer) {
		optionsMenu = graphics().createGroupLayer();
		optionsMenu.setVisible(false);
		
		optionsBackground = assets().getImageSync("images/smoke.png");
		optionsBackgroundLayer = graphics().createImageLayer(optionsBackground);
		optionsBackgroundLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		optionsBackgroundLayer.setTranslation(0, 0);
		optionsBackgroundLayer.setVisible(false);
		optionsBackgroundLayer.setDepth(101);
		
		optionsTitle = assets().getImageSync("images/buttons/option.png");
		optionsTitleLayer = graphics().createImageLayer(optionsTitle);
		optionsTitleLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		optionsTitleLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * optionsTitle.width()) / 2),
				(OrbiterMain.canvasHeight / 4) - ((OrbiterMain.imageSize * optionsTitle.height()) / 2));
		optionsTitleLayer.setVisible(false);
		optionsTitleLayer.setDepth(102);
		
		soundOn = assets().getImageSync("images/buttons/soundOn.png");
		soundOnSelected = assets().getImageSync("images/buttons/soundOnSelected.png");
		soundOff = assets().getImageSync("images/buttons/soundOff.png");
		soundOffSelected = assets().getImage("images/buttons/soundOffSelected.png");
		soundLayer = graphics().createImageLayer(soundOn);
		soundLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		soundLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * soundOn.width()) / 2),
				(OrbiterMain.canvasHeight * 2 / 4) - ((OrbiterMain.imageSize * soundOn.height()) / 2));
		soundLayer.setVisible(false);
		soundLayer.setDepth(102);
		
		back = assets().getImageSync("images/buttons/back.png");
		backSelected = assets().getImageSync("images/buttons/backSelected.png");
		backLayer = graphics().createImageLayer(back);
		backLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		backLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * back.width()) / 2),
				(OrbiterMain.canvasHeight * 3 / 4) - ((OrbiterMain.imageSize * back.height()) / 2));
		backLayer.setVisible(false);
		backLayer.setDepth(102);
		
		optionsMenu.add(optionsBackgroundLayer);
		optionsMenu.add(optionsTitleLayer);
		optionsMenu.add(soundLayer);
		optionsMenu.add(backLayer);
		graphics().rootLayer().add(optionsMenu);
		optionsMenu.setDepth(102);
		
		backLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		    	   backLayer.setImage(backSelected);
		      }
		      public void onPointerEnd(Pointer.Event event) {
		    	  backLayer.setImage(back);
		    	  showOptions = false;
		      }
		    });
		soundLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		    	  if (soundOptionOn == true) {
		    		  soundLayer.setImage(soundOnSelected);
		    	  } else {
		    		  soundLayer.setImage(soundOffSelected);
		    	  }
		      }
		      public void onPointerEnd(Pointer.Event event) {
		    	  if (soundOptionOn == true) {
		    		  soundLayer.setImage(soundOff);
		    		  soundOptionOn = false;
		    	  } else {
		    		  soundLayer.setImage(soundOn);
		    		  soundOptionOn = true;
		    	  }
		      }
		    });
		backLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				backLayer.setImage(backSelected);
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
				backLayer.setImage(back);
		    	showOptions = false;
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});
		soundLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				if (soundOptionOn == true) {
					soundLayer.setImage(soundOnSelected);
		    	} else {
		    		soundLayer.setImage(soundOffSelected);
		    	}
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
				if (soundOptionOn == true) {
					soundLayer.setImage(soundOff);
					soundOptionOn = false;
				} else {
					soundLayer.setImage(soundOn);
					soundOptionOn = true;
				}
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});
		
		enterSelect = assets().getSound("sounds/EnterSelect");
		select = assets().getSound("sounds/Select");
	}
	
	private void optionsMenuVisible(boolean visible) {
		if (currentVisible == visible){
			return;
		}
		currentVisible = visible;
		optionsBackgroundLayer.setVisible(visible);
		backLayer.setVisible(false);
		soundLayer.setVisible(visible);
		optionsTitleLayer.setVisible(visible);
		optionsMenu.setVisible(visible);
	}
	
	public void update(float delta) {
		if (showOptions == true) {
			optionsMenuVisible(true);
		} else {
			optionsMenuVisible(false);
		}
		if (showOptions == true) {
			//if (mouse().hasMouse() == false && mouse().hasMouse() == true) {
			if (buttonToggleRate < 6) {
				buttonToggleRate += 1;
			}
			if (OrbiterMain.up) {
				if (buttonToggleRate > 5) {
				buttonSelectionNumber -= 1;
				buttonToggleRate = 0;
				if (OptionsMenu.soundOptionOn == true) {
					select.play();
				}
				if (buttonSelectionNumber < 0) {
					buttonSelectionNumber = buttonSelectionHighestNumber;
				}
				}
			}
			if (OrbiterMain.down) {
				if (buttonToggleRate > 5) {
					buttonSelectionNumber += 1;
					buttonToggleRate = 0;
					if (OptionsMenu.soundOptionOn == true) {
						select.play();
					}
				if (buttonSelectionNumber > buttonSelectionHighestNumber) {
					buttonSelectionNumber = 0;
				}
				}
			}

			if (buttonSelectionNumber == 0) {
				if (soundOptionOn == true) {
		    		  soundLayer.setImage(soundOnSelected);
		    	  } else {
		    		  soundLayer.setImage(soundOffSelected);
		    	  }
//				if (OrbiterMain.enter) {
//					if (soundOptionOn == true) {
//			    		  soundLayer.setImage(soundOff);
//			    		  soundOptionOn = false;
//			    	  } else {
//			    		  soundLayer.setImage(soundOn);
//			    		  soundOptionOn = true;
//			    	  }
//					if (OptionsMenu.soundOptionOn == true) {
//						enterSelect.play();
//					}
////					OrbiterMain.initiateObjects = false;
////					OrbiterMain.initiateObjectsNumber = 0;
//				}
			} else {
				soundLayer.setImage(soundOn);
			}
			if (buttonSelectionNumber == 1) {
				backLayer.setImage(backSelected);
			} else {
				backLayer.setImage(back);
			}
			}
		//}
	}

	public void onKey(playn.core.Keyboard.Event event) {
		// TODO Auto-generated method stub
		
		if (this.showOptions && buttonSelectionNumber == 1 && event.key() == event.key().ENTER) {
		    	showOptions = false;
				backLayer.setImage(back);

				if (OptionsMenu.soundOptionOn == true) {
					enterSelect.play();
				}
			
		}else if(this.showOptions && buttonSelectionNumber == 0 && event.key() == event.key().ENTER){
				if (soundOptionOn == true) {
					soundLayer.setImage(soundOff);
					soundOptionOn = false;
				} else {
					soundLayer.setImage(soundOn);
					soundOptionOn = true;
				}
				if (OptionsMenu.soundOptionOn == true) {
					enterSelect.play();
				}
		}
		if(this.showOptions && (event.key() == Key.ESCAPE || event.key() == Key.P )){
			showOptions = false;
			OrbiterMain.pause = true;
			backLayer.setImage(back);

			if (OptionsMenu.soundOptionOn == true) {
				enterSelect.play();
			}
		}
		
	}
	
	
}
