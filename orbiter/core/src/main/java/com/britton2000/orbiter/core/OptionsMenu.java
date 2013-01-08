package com.britton2000.orbiter.core;

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
import playn.core.Pointer;
//import playn.core.Image;
//import playn.core.ImageLayer;
//import playn.core.Pointer;
import playn.core.ImageLayer;

public class OptionsMenu {
	
	ImageLayer optionsTitleLayer, backLayer, optionsBackgroundLayer, soundLayer;
	Image optionsTitle, back, backSelected, optionsBackground, soundOn, soundOnSelected, soundOff, soundOffSelected;
	
	int buttonToggleRate, buttonSelectionNumber, buttonSelectionHighestNumber;
	private boolean currentVisible = false;
	static boolean showOptions;
	static boolean soundOptionOn;

	public OptionsMenu(float canvaswidth, float canvasheight, final GroupLayer parentLayer) {
		optionsBackground = assets().getImageSync("images/gui/pauseMenuBackground.png");
		optionsBackgroundLayer = graphics().createImageLayer(optionsBackground);
		optionsBackgroundLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		optionsBackgroundLayer.setTranslation((canvaswidth / 2) - ((OrbiterMain.imageSize * optionsBackground.width()) / 2), 
				(canvasheight / 2) - ((OrbiterMain.imageSize * optionsBackground.height()) / 2));
		optionsBackgroundLayer.setVisible(false);
		optionsBackgroundLayer.setDepth(101);
		
		optionsTitle = assets().getImageSync("images/buttons/options.png");
		optionsTitleLayer = graphics().createImageLayer(optionsTitle);
		optionsTitleLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		optionsTitleLayer.setTranslation((canvaswidth / 2) - ((OrbiterMain.imageSize * optionsTitle.width()) / 2), 
				(canvasheight / 2) - ((OrbiterMain.imageSize * optionsTitle.height()) / 2));
		optionsTitleLayer.setVisible(false);
		optionsTitleLayer.setDepth(102);
		
		soundOn = assets().getImageSync("images/buttons/soundOn.png");
		soundOnSelected = assets().getImage("images/buttons/soundOnSelected.png");
		soundOff = assets().getImageSync("images/buttons/soundOff.png");
		soundOffSelected = assets().getImage("images/buttons/soundOffSelected.png");
		soundLayer = graphics().createImageLayer(soundOn);
		soundLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		soundLayer.setTranslation((canvaswidth / 2) - ((OrbiterMain.imageSize * soundOn.width()) / 2), 
				(canvasheight / 2) - ((OrbiterMain.imageSize * soundOn.height()) / 2));
		soundLayer.setVisible(false);
		soundLayer.setDepth(102);
		
		back = assets().getImageSync("images/buttons/back.png");
		backSelected = assets().getImageSync("images/buttons/backSelected.png");
		backLayer = graphics().createImageLayer(back);
		backLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		backLayer.setTranslation((canvaswidth / 2) - ((OrbiterMain.imageSize * back.width()) / 2), 
				(canvasheight / 2) - ((OrbiterMain.imageSize * back.height()) / 2));
		backLayer.setVisible(false);
		backLayer.setDepth(102);
		
		backLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		    	   OrbiterMain.pause = false;
		      }
		    });
		soundLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		    	  OrbiterMain.pause = false;
		    	  OrbiterMain.level = 0;
		    	  OrbiterMain.reset = true;
		      }
		    });
		
	}
	
	private void optionsMenuVisible(boolean visible) {
		if (currentVisible == visible){
			return;
		}
		optionsBackgroundLayer.setVisible(visible);
		backLayer.setVisible(visible);
		soundLayer.setVisible(visible);
		optionsTitleLayer.setVisible(visible);
	}
	
	public void update(float delta) {

		if (showOptions == true) {
			optionsMenuVisible(true);
		} else {
			optionsMenuVisible(false);
		}
		if (showOptions == true) {
			if (mouse().hasMouse() == false) {
			if (buttonToggleRate < 6) {
				buttonToggleRate += 1;
			}
			if (OrbiterMain.up) {
				if (buttonToggleRate > 5) {
				buttonSelectionNumber -= 1;
				buttonToggleRate = 0;
				if (buttonSelectionNumber < 0) {
					buttonSelectionNumber = buttonSelectionHighestNumber;
				}
				}
			}
			if (OrbiterMain.down) {
				if (buttonToggleRate > 5) {
					buttonSelectionNumber += 1;
					buttonToggleRate = 0;
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
				if (OrbiterMain.enter) {
					if (soundOptionOn == true) {
						soundOptionOn = false;
					} else {
						soundOptionOn = true;
					}
//					OrbiterMain.initiateObjects = false;
//					OrbiterMain.initiateObjectsNumber = 0;
				}
			} else {
				soundLayer.setImage(soundOn);
			}
			if (buttonSelectionNumber == 1) {
				backLayer.setImage(backSelected);
				if (OrbiterMain.enter) {
					showOptions = false;
//					OrbiterMain.initiateObjects = false;
//					OrbiterMain.initiateObjectsNumber = 0;
				}
			} else {
				backLayer.setImage(back);
			}
			}
		}
	}
}
