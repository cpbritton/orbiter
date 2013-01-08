package com.britton2000.orbiter.core;

//Max Britton hi

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.mouse;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;

public class Menu {
	ImageLayer startLayer, helpLayer, nameLayer, logoLayer, menuBackgroundLayer;
	ImageLayer pauseBackgroundLayer, backtogameLayer, optionsLayer, quitLayer, quitGameLayer;
	Image start, logo, startSelected, help, helpSelected, name, button3, menuBackground, pauseBackground;
	Image backtogame, backtogameSelected, options, optionsSelected, quit, quitSelected, quitGame, quitGameSelected;
	GroupLayer menu, pauseMenu;

	public static int buttonNum = 2;
	int toggleRate = 0, buttonSelectionNumber = 0, buttonSelectionHighestNumber = 1, buttonToggleRate;
	int pauseMenuToggleRate = 0, pauseMenuButtonSelectionNumber = 0, pauseMenuButtonSelectionHighestNumber = 2, pauseMenuButtonToggleRate;
	int status;
	private boolean currentVisible = false;

	public Menu(float canvaswidth, float canvasheight, final GroupLayer parentLayer) {

		menu = graphics().createGroupLayer();
		menu.setVisible(false);

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
		
		quitGame = assets().getImage("images/buttons/quit.png");
		quitGameSelected = assets().getImage("images/buttons/quitselected.png");
		quitGameLayer = graphics().createImageLayer(quitGame);
		quitGameLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		quitGameLayer.setTranslation(0, 0);
		quitGameLayer.setVisible(false);
		quitGameLayer.setDepth(100);
		
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
		menu.add(quitGameLayer);
		menu.add(menuBackgroundLayer);
		graphics().rootLayer().add(menu);
		menu.setDepth(100);
	}

	public void render(float alpha) {
		paint(alpha);
	}
	
	private void menuVisible(boolean visible) {
		if (currentVisible == visible){
			return;
		}
		currentVisible = visible;
		menu.setVisible(visible);
		startLayer.setVisible(visible);
		helpLayer.setVisible(visible);
		nameLayer.setVisible(visible);
		logoLayer.setVisible(visible);
		quitGameLayer.setVisible(visible);
		menuBackgroundLayer.setVisible(visible);
	}

	public void update(float delta) {

		startLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		    	  startLayer.setImage(startSelected);
		      }
		      public void onPointerEnd(Pointer.Event event) {
		    	  startLayer.setImage(start);
		    	  OrbiterMain.level = 1;
		      }
		});
		helpLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		    	  helpLayer.setImage(helpSelected);
		      }
		      public void onPointerEnd(Pointer.Event event) {
		    	  helpLayer.setImage(help);
		    	  OrbiterMain.level = 1;
		      }
		});
		quitGameLayer.addListener(new Pointer.Adapter() {
		      public void onPointerStart(Pointer.Event event) {
		    	  quitGameLayer.setImage(quitGameSelected);
		      }
		      public void onPointerEnd(Pointer.Event event) {
		    	  quitGameLayer.setImage(quitGame);
		    	  System.exit(status);
		      }
		});
		
		if (OrbiterMain.level == 1) {
			menuVisible(false);
		}else if (OrbiterMain.level == 0) {
			menuVisible(true);
			quitGameLayer.setTranslation(0, 0);
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