
package me.xorga.orbiter.core;

//Max Britton hi

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Key;
import playn.core.Pointer;
import playn.core.Sound;
import playn.core.Touch;
import playn.core.Touch.Event;

public class Menu {
	ImageLayer startLayer, helpLayer, nameLayer, logoLayer, menuBackgroundLayer;
	ImageLayer pauseBackgroundLayer, backtogameLayer, optionsLayer, quitLayer, quitGameLayer;
	Image start, logo, startSelected, help, helpSelected, name, button3, menuBackground, pauseBackground;
	Image backtogame, backtogameSelected, options, optionsSelected, quit, quitSelected, quitGame, quitGameSelected;
	GroupLayer menu, pauseMenu;

	public static int buttonNum = 2;
	int toggleRate = 0, buttonSelectionNumber = 0, buttonSelectionHighestNumber = 2, buttonToggleRate;
	int pauseMenuToggleRate = 0, pauseMenuButtonSelectionNumber = 0, pauseMenuButtonSelectionHighestNumber = 2, pauseMenuButtonToggleRate;
	int status;
	public static boolean instruTrue = false;
	private boolean currentVisible = false;
	private final Sound enterSelect;
	private final Sound select;

	public Menu(float canvaswidth, float canvasheight, final GroupLayer parentLayer) {

		menu = graphics().createGroupLayer();
		menu.setVisible(false);

		start = assets().getImageSync("images/buttons/play.png");
		startSelected = assets().getImageSync("images/buttons/playSelected.png");
		startLayer = graphics().createImageLayer(start);
		startLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		startLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * startLayer.width()) / 2),
				(OrbiterMain.canvasHeight * 2 / 4) - ((OrbiterMain.imageSize * start.height()) / 2));
		startLayer.setVisible(false);
		startLayer.setDepth(100);

		help = assets().getImageSync("images/buttons/Help.png");
		helpSelected = assets().getImageSync("images/buttons/HelpSelected.png");
		helpLayer = graphics().createImageLayer(help);
		helpLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		helpLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * help.width()) / 2),
				(OrbiterMain.canvasHeight * 5 / 8) - ((OrbiterMain.imageSize * help.height()) / 2));
		helpLayer.setVisible(true);
		helpLayer.setDepth(101);

		name = assets().getImageSync("images/gui/OrbiterLogo.png");
		nameLayer = graphics().createImageLayer(name);
		nameLayer.setScale(OrbiterMain.imageSize);
		nameLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * name.width()) / 2),
				(OrbiterMain.canvasHeight / 4) - ((OrbiterMain.imageSize * name.height()) / 6));
		nameLayer.setVisible(false);
		nameLayer.setDepth(99);

		logo = assets().getImageSync("images/CopyrightStuff.png");
		logoLayer = graphics().createImageLayer(logo);
		logoLayer.setScale(OrbiterMain.imageSize / 2);
		logoLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * logo.width()) / 4),
				OrbiterMain.canvasHeight - (OrbiterMain.imageSize * logo.height()) - (18 * OrbiterMain.imageSize));
		logoLayer.setVisible(false);
		logoLayer.setDepth(100);

		quitGame = assets().getImageSync("images/buttons/quit.png");
		quitGameSelected = assets().getImageSync("images/buttons/quitselected.png");
		quitGameLayer = graphics().createImageLayer(quitGame);
		quitGameLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		quitGameLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * quitGame.width()) / 2),
				(OrbiterMain.canvasHeight * 25 / 32) - (OrbiterMain.imageSize * quitGame.height()));
		quitGameLayer.setVisible(false);
		quitGameLayer.setDepth(102);

		menu.add(helpLayer);
		menu.add(startLayer);
		menu.add(nameLayer);
		menu.add(logoLayer);
		menu.add(quitGameLayer);
		graphics().rootLayer().add(menu);
		menu.setDepth(100);

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
				quit();
			}
		});
		startLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				startLayer.setImage(startSelected);
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
				startLayer.setImage(start);
				OrbiterMain.level = 1;
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});
		helpLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				helpLayer.setImage(helpSelected);
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
				instruTrue = true;
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});
		quitGameLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				quitGameLayer.setImage(quitGameSelected);
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
				quit();
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});

		enterSelect = assets().getSound("sounds/EnterSelect");
		select = assets().getSound("sounds/Select");
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
	}

	public void update(float delta) {
		
		if (OrbiterMain.level == 1) {
			menuVisible(false);
		} else if (OrbiterMain.level == 0) {
			menuVisible(true);
			
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
					startLayer.setImage(start);
//					if (OrbiterMain.enter) {
//						OrbiterMain.level = 1;
//						OrbiterMain.initiateObjects = false;
//						OrbiterMain.initiateObjectsNumber = 0;
//						if (OptionsMenu.soundOptionOn == true) {
//							enterSelect.play();
//						}
//					}
				} else {
					startLayer.setImage(startSelected);
				}
				if (buttonSelectionNumber == 1) {
					helpLayer.setImage(help);
////					if (OrbiterMain.enter) {
////						OrbiterMain.level = 1;
////						OrbiterMain.initiateObjects = false;
////						OrbiterMain.initiateObjectsNumber = 0;
////						if (OptionsMenu.soundOptionOn == true) {
////							enterSelect.play();
////						}
//					}
				} else {
					helpLayer.setImage(helpSelected);
				}
				if (buttonSelectionNumber == 2) {
					quitGameLayer.setImage(quitGame);
//					if (OrbiterMain.enter) {
//						System.exit(status);
//					}
				} else {
					quitGameLayer.setImage(quitGameSelected);
				}
				startLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * startLayer.width()) / 2),
						(OrbiterMain.canvasHeight * 2 / 4) - ((OrbiterMain.imageSize * startLayer.height()) / 2));
//				helpLayer.setTranslation((OrbiterMain.canvasWidth / 2) - ((OrbiterMain.imageSize * helpLayer.width()) / 2),
//						(OrbiterMain.canvasHeight * 3 / 4) - ((OrbiterMain.imageSize * helpLayer.height()) / 2));
			//}

		}
	}

	public void paint(float alpha) {
	}

	public void onKey(playn.core.Keyboard.Event event) {
		event.key();
		if(this.currentVisible && buttonSelectionNumber == 0 && event.key() == Key.ENTER){
			OrbiterMain.level = 1;
			OrbiterMain.initiateObjects = false;
			OrbiterMain.initiateObjectsNumber = 0;
			if (OptionsMenu.soundOptionOn == true) {
				enterSelect.play();
			}
		}else if (this.currentVisible && buttonSelectionNumber == 1 && event.key() == Key.ENTER){
			instruTrue = true;
		}else if (this.currentVisible && buttonSelectionNumber == 2 && event.key() == Key.ENTER){
			quit();
		}
		
		
	}
	
	
	private void quit(){
		quitGameLayer.setImage(quitGame);
		System.exit(status);
	}
}