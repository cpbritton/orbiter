package com.britton2000.orbiter.core;

//Max Britton

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.mouse;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;

public class Menu {
	ImageLayer startLayer, helpLayer, nameLayer, logoLayer, menuBackgroundLayer;
	ImageLayer pauseBackgroundLayer, backtogameLayer, optionsLayer, quitLayer;
	Image start, logo, startSelected, help, helpSelected, name, button3, menuBackground, pauseBackground;
	Image backtogame, backtogameSelected, options, optionsSelected, quit, quitSelected;
	GroupLayer menu, pauseMenu;

	public static int buttonNum = 2;
	int toggleRate = 0, buttonSelectionNumber = 0, buttonSelectionHighestNumber = 1, buttonToggleRate;
	private boolean currentVisible = false;

	public Menu(float canvaswidth, float canvasheight, final GroupLayer parentLayer) {

		menu = graphics().createGroupLayer();
		menu.setVisible(false);
		pauseMenu = graphics().createGroupLayer();
		pauseMenu.setVisible(false);

		start = assets().getImage("images/buttons/play.png");
		startSelected = assets().getImage("images/buttons/playSelected.png");
		startLayer = graphics().createImageLayer(start);
		startLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		startLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * startLayer.width()) / 2),
				(OrbiterMain.canvasHeight * 2 / 4) - ((OrbiterMain.imageSize * start.height()) / 2));
		startLayer.setVisible(false);
		startLayer.setDepth(100);

		help = assets().getImage("images/buttons/Help.png");
		helpSelected = assets().getImage("images/buttons/HelpSelected.png");
		helpLayer = graphics().createImageLayer(help);
		helpLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		helpLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * help.width()) / 2),
				(OrbiterMain.canvasHeight * 3 / 4) - ((OrbiterMain.imageSize * help.height()) / 2));
		helpLayer.setVisible(false);
		helpLayer.setDepth(100);

		name = assets().getImage("images/gui/orbiter.png");
		nameLayer = graphics().createImageLayer(name);
		nameLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		nameLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * name.width()) / 2),
				(OrbiterMain.canvasHeight / 4) - ((OrbiterMain.imageSize * name.height()) / 2));
		nameLayer.setVisible(false);
		nameLayer.setDepth(100);
		
		logo = assets().getImage("images/gui/XORlogo.png");
		logoLayer = graphics().createImageLayer(logo);
		logoLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		logoLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * logo.width()) / 2),
				OrbiterMain.canvasHeight - (OrbiterMain.imageSize * logo.height()));
		logoLayer.setVisible(false);
		logoLayer.setDepth(100);
		
		menuBackground = assets().getImage("images/gui/menuBackground.png");
		menuBackgroundLayer = graphics().createImageLayer(menuBackground);
		menuBackgroundLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		menuBackgroundLayer.setTranslation(0, 0);
		menuBackgroundLayer.setVisible(false);
		menuBackgroundLayer.setDepth(99);

		menu.add(helpLayer);
		menu.add(startLayer);
		menu.add(nameLayer);
		menu.add(logoLayer);
		menu.add(menuBackgroundLayer);
		graphics().rootLayer().add(menu);
		menu.setDepth(100);
		
		pauseBackground = assets().getImage("images/gui/pauseMenuBackground.png");
		pauseBackgroundLayer = graphics().createImageLayer(pauseBackground);
		pauseBackgroundLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		pauseBackgroundLayer.setTranslation((canvaswidth / 2) - ((OrbiterMain.imageSize * pauseBackground.width()) / 2), 
				(canvasheight / 2) - ((OrbiterMain.imageSize * pauseBackground.height()) / 2));
		pauseBackgroundLayer.setVisible(false);
		pauseBackgroundLayer.setDepth(99);
		
		backtogame = assets().getImage("images/buttons/backtogame.png");
		backtogameSelected = assets().getImage("images/buttons/backtogameselected.png");
		backtogameLayer = graphics().createImageLayer(backtogame);
		backtogameLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		backtogameLayer.setTranslation((canvaswidth / 2) - ((OrbiterMain.imageSize * pauseBackground.width()) / 2)
				+ 13 * OrbiterMain.imageSize, 
				(canvasheight / 2) - ((OrbiterMain.imageSize * pauseBackground.height()) / 2)
				+ 22 * OrbiterMain.imageSize);
		backtogameLayer.setVisible(false);
		backtogameLayer.setDepth(99);
		
		options = assets().getImage("images/buttons/options.png");
		optionsSelected = assets().getImage("images/buttons/optionsselected.png");
		optionsLayer = graphics().createImageLayer(options);
		optionsLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		optionsLayer.setTranslation((canvaswidth / 2) - ((OrbiterMain.imageSize * pauseBackground.width()) / 2)
				+ 13 * OrbiterMain.imageSize, 
				(canvasheight / 2) - ((OrbiterMain.imageSize * pauseBackground.height()) / 2)
				+ 44 * OrbiterMain.imageSize);
		optionsLayer.setVisible(false);
		optionsLayer.setDepth(99);
		
		quit = assets().getImage("images/buttons/quit.png");
		quitSelected = assets().getImage("images/buttons/quitselected.png");
		quitLayer = graphics().createImageLayer(quit);
		quitLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		quitLayer.setTranslation((canvaswidth / 2) - ((OrbiterMain.imageSize * pauseBackground.width()) / 2)
				+ 82 * OrbiterMain.imageSize, 
				(canvasheight / 2) - ((OrbiterMain.imageSize * pauseBackground.height()) / 2)
				+ 44 * OrbiterMain.imageSize);
		quitLayer.setVisible(false);
		quitLayer.setDepth(99);
		
		pauseMenu.add(pauseBackgroundLayer);
		pauseMenu.add(backtogameLayer);
		pauseMenu.add(optionsLayer);
		pauseMenu.add(quitLayer);
		graphics().rootLayer().add(pauseMenu);
		pauseMenu.setDepth(100);
	}

	public void render(float alpha) {
		paint(alpha);
	}
	
	private void menuVisible(boolean visible){
		
		if (currentVisible == visible){
			return;
		}
		currentVisible = visible;
		menu.setVisible(visible);
		startLayer.setVisible(visible);
		helpLayer.setVisible(visible);
		nameLayer.setVisible(visible);
		logoLayer.setVisible(visible);
		menuBackgroundLayer.setVisible(visible);
		
	}

	public void update(float delta) {

		startLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		        OrbiterMain.level = 1;
		      }
		    });
		
		helpLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		        OrbiterMain.level = 1;
		      }
		    });
		
		backtogameLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		    	   OrbiterMain.pause = false;
		      }
		    });
		quitLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		    	  OrbiterMain.pause = false;
		    	  OrbiterMain.level = 0;
		    	  OrbiterMain.reset = true;
		      }
		    });
		
		if (OrbiterMain.pause) {
			pauseBackgroundLayer.setVisible(true);
			backtogameLayer.setVisible(true);
			optionsLayer.setVisible(true);
			quitLayer.setVisible(true);
			pauseMenu.setVisible(true);
		} else {
			pauseMenu.setVisible(false);
			backtogameLayer.setVisible(false);
			optionsLayer.setVisible(false);
			quitLayer.setVisible(false);
			pauseBackgroundLayer.setVisible(false);
		}
		if (OrbiterMain.level == 1) {
			menuVisible(false);
		}else if (OrbiterMain.level == 0) {
			menuVisible(true);
			
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
				startLayer.setImage(startSelected);
				if (OrbiterMain.enter) {
					OrbiterMain.level = 1;
					OrbiterMain.initiateObjects = false;
					OrbiterMain.initiateObjectsNumber = 0;
				}
			} else {
				startLayer.setImage(start);
			}
			if (buttonSelectionNumber == 1) {
				helpLayer.setImage(helpSelected);
				if (OrbiterMain.enter) {
					OrbiterMain.level = 1;
					OrbiterMain.initiateObjects = false;
					OrbiterMain.initiateObjectsNumber = 0;
				}
			} else {
				helpLayer.setImage(help);
			}
			startLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * startLayer.width()) / 2),
					(OrbiterMain.canvasHeight * 2 / 4) - ((OrbiterMain.imageSize * startLayer.height()) / 2));
			helpLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * helpLayer.width()) / 2),
					(OrbiterMain.canvasHeight * 3 / 4) - ((OrbiterMain.imageSize * helpLayer.height()) / 2));
			}
		}
	}

	public void paint(float alpha) {
	}
}