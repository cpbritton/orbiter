package com.britton2000.orbiter.core;

//Max Britton hi

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.keyboard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

import playn.core.Game;
import playn.core.ImageLayer;
import playn.core.Keyboard;
import playn.core.PlayN;
import playn.core.gl.GLContext;

import com.britton2000.orbiter.elements.Asteroid;
import com.britton2000.orbiter.elements.Element;
import com.britton2000.orbiter.elements.ElementInterface;
import com.britton2000.orbiter.elements.EnemyBeastShip;
import com.britton2000.orbiter.elements.EnemyShip;
import com.britton2000.orbiter.elements.Gem;

public class OrbiterMain implements Game, Keyboard.Listener {
	ImageLayer bglayer;
	ImageLayer bglayer2;

	float frameSwitch;
	static boolean right, left, up, down, space, g, enter, escape, pause = false, initiateObjects = false;
	boolean f, startpos, fireBullet, launchAsteroid, fire, smokeon, b, a;
	static boolean reset;
	// public static float screenWidth, screenHeight;
	public static float imageSize = 4.0f, canvasWidth = 1280f, canvasHeight = 720f;
	public static Ship ship;
	// private ScoreText scoreText;
	private Background background;
	private GUI gui;
	private Menu menu;
	private PauseMenu pauseMenu;
	private OptionsMenu optionsMenu;
	private ArrayList<ElementInterface> elements = new ArrayList<ElementInterface>();
	public static Stack<Element> elementSpawnQueue = new Stack<Element>();
	int asteroidSpawnRate, smokeRate, gemSpawnRate, enemyRate, toggleRate, scoreAdd, pauseToggleRate;
	static int initiateObjectsNumber = 0, score, level = 0;

	@Override
	public void init() {
		imageSize = 4;
//		if (graphics().height() != canvasHeight && graphics().height() > 0) {
//			canvasHeight = graphics().height();
//		}
//		if (graphics().width() != canvasWidth && graphics().width() > 0) {
//			canvasWidth = graphics().width();
//		}
//		imageSize = canvasWidth / 320;
//		if ((canvasWidth / 320) > (canvasHeight / 180)) {
//			imageSize = canvasHeight / 180;
//			canvasWidth = imageSize * 320;
//			canvasHeight = imageSize * 180;
//		}
//		if ((canvasWidth / 320) < (canvasHeight / 180)) {
//			imageSize = canvasWidth / 320;
//			canvasWidth = imageSize * 320;
//			canvasHeight = imageSize * 180;
//		}
		keyboard().setListener(this);
		PlayN.graphics().ctx().setTextureFilter(GLContext.Filter.NEAREST, GLContext.Filter.NEAREST);
		background = new Background(canvasWidth, canvasHeight, graphics().rootLayer());
		gui = new GUI(graphics().rootLayer());
		ship = new Ship(canvasWidth, canvasHeight, graphics().rootLayer());
		menu = new Menu(canvasWidth, canvasHeight, graphics().rootLayer());
		pauseMenu = new PauseMenu(canvasWidth, canvasHeight, graphics().rootLayer());
		optionsMenu = new OptionsMenu(canvasWidth, canvasHeight, graphics().rootLayer());
	}

	@Override
	public void update(float delta) {
		if (reset == true) {
			reset();
		}
		if (pauseToggleRate < 6) {
			pauseToggleRate += 1;
		}
		if (escape && pauseToggleRate > 5 && level == 1) {
			pauseToggleRate = 0;
			if (pause == false) {
				pause = true;
			} else if (pause == true) {
				pause = false;
			}
		}
		if (level == 0 || pause) {
			menu.update(delta);
			gui.update(delta);
			pauseMenu.update(delta);
			optionsMenu.update(delta);
		} else if (level == 1) {
			processElements(delta);
			ship.update(delta);
			gui.update(delta);
			background.update(delta);
			menu.update(delta);
			pauseMenu.update(delta);
			optionsMenu.update(delta);
			if (space) {
				fireBullet(true);
			} else {
				// recharge gun
			}
			// spawn asteroid
			asteroidSpawnRate += 1;
			if (asteroidSpawnRate > 75) {
				launchAsteroid(true);
				asteroidSpawnRate = 0;
			}
			gemSpawnRate += 0;
			if (gemSpawnRate > 250) {
				launchGem(true);
				gemSpawnRate = 0;
			}
			smokeRate = 0;
			if (smokeon == true) {
				if (smokeRate == 0) {
					smoke(true);
					smokeRate = 0;
				}
			}
			enemyRate += 1;
			if (enemyRate > 1000) {
				launchEnemy(true);
				enemyRate = 0;
			}
			if (toggleRate < 6) {
				toggleRate += 1;
			}
			if (f && toggleRate > 5) {
				toggleRate = 0;
				if (smokeon == false) {
					smokeon = true;
				} else if (smokeon == true) {
					smokeon = false;
				}
			}
			if (g && toggleRate > 5) {
				toggleRate = 0;
				ship.gunType += 1;
				if (ship.gunType > 5) {
					ship.gunType = 1;
				}
			}
		}
	}

	public void reset() {
		if (reset == true) {
			reset = false;
		}
		if (ship != null) {
			ship.clear();
		}
		ship = new Ship(canvasWidth, canvasHeight, graphics().rootLayer());
		if (elements != null) {
			for (Iterator<ElementInterface> it = elements.iterator(); it.hasNext();) {
				ElementInterface e = it.next();
				e.clear();
				it.remove();
			}
		}
		elements = new ArrayList<ElementInterface>();
		if (background != null) {
			background.clear();
		}
		background = new Background(canvasWidth, canvasHeight, graphics().rootLayer());
		if (gui != null) {
			gui.clear();
		}
		gui = new GUI(graphics().rootLayer());
	}

	@Override
	public void paint(float alpha) {
		background.render(alpha);
		if (level == 0) {
			menu.render(alpha);
		}
		if (level == 1) {
			paintElements(alpha);
			ship.render(alpha);
			gui.render(alpha);
			menu.render(alpha);
		}
	}

	private void processElements(float delta) {
		if (elements != null) {

			for (Iterator<ElementInterface> it = elements.iterator(); it.hasNext();) {
				ElementInterface e = it.next();
				e.update(delta);
				if (checkCollisions(e)) {
					e.processCollisions(delta);
				}
				// check ship collision
				if (ship.hasCollision(e)) {
					ship.processCollisions(delta);
				}
			}

			// now process collisions
			for (Iterator<ElementInterface> it = elements.iterator(); it.hasNext();) {
				ElementInterface e = it.next();
				if (e.getLayer().destroyed()) {
					// no op when already destroyed
				} else if (e.getY2() < 0 || e.getY() > canvasHeight) {
					e.clear();
					it.remove();
				} else if (e.toRemove()) {
					e.clear();
					it.remove();
				}
			}
			if (ship.toRemove()) {
				// game over!!!
				level = 0;
				reset();
			}
		}
		while (!OrbiterMain.elementSpawnQueue.isEmpty()) {
			elements.add(OrbiterMain.elementSpawnQueue.pop());
		}
	}

	private boolean checkCollisions(ElementInterface element) {
		boolean collide = false;
		if (elements != null) {
			for (ElementInterface e : elements) {
				collide = element.hasCollision(e);
			}
		}
		if (ship != null) {
			// ship collisions
			collide = element.hasCollision(ship);
		}
		return collide;
	}

	@Override
	public int updateRate() {
		return 25;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void onKeyDown(Keyboard.Event event) {

		switch (event.key()) {

		case A:
		case LEFT:
		case DPAD_LEFT:
			left = true;
			break;
		case D:
		case RIGHT:
		case DPAD_RIGHT:
			right = true;
			break;
		case W:
		case UP:
		case DPAD_UP:
			up = true;
			break;
		case S:
		case DOWN:
		case DPAD_DOWN:
			down = true;
			break;
		case SPACE:
			space = true;
			break;
		case F:
			f = true;
			break;
		case G:
			g = true;
			break;
		case ENTER:
			enter = true;
			break;
		case ESCAPE:
			escape = true;
			break;
		}
	}

	@Override
	public void onKeyTyped(Keyboard.TypedEvent event) {
		// ...
	}

	@Override
	public void onKeyUp(Keyboard.Event event) {

		switch (event.key()) {
		case A:
		case LEFT:
		case DPAD_LEFT:
			left = false;
			break;
		case D:
		case RIGHT:
		case DPAD_RIGHT:
			right = false;
			break;
		case W:
		case UP:
		case DPAD_UP:
			up = false;
			break;
		case S:
		case DOWN:
		case DPAD_DOWN:
			down = false;
		case SPACE:
			space = false;
			break;
		case F:
			f = false;
			break;
		case G:
			g = false;
			break;
		case ENTER:
			enter = false;
			break;
		case ESCAPE:
			escape = false;
			break;
		}
	}

	private void fireBullet(boolean r) {
		addElement(ship.fireGun());
	}

	public void addElement(Element e) {
		if (e != null) {
			elements.add(e);
		}
	}

	private void paintElements(float alpha) {
		if (elements != null) {
			for (ElementInterface a : elements) {
				a.paint(alpha);
			}
		}
	}

	private void launchAsteroid(boolean r) {
		Asteroid a = new Asteroid(canvasWidth, canvasHeight, graphics().rootLayer());
		a.setPosition((int) Math.round((Math.random() * canvasWidth)), (int) (0 - a.getLayer().scaledHeight()));
		elements.add(a);
	}

	private void launchGem(boolean r) {
		Gem g = new Gem(canvasWidth, canvasHeight, graphics().rootLayer());
		g.setPosition((int) Math.round((Math.random() * canvasWidth)), (int) (0 - g.getLayer().scaledHeight()));
		elements.add(g);
	}

	private void smoke(boolean r) {
		ShipSmoke s = new ShipSmoke(ship, graphics().rootLayer());
		elements.add(s);
	}

	private void launchEnemy(boolean r) {
		EnemyShip e = null;
		Random aar = new Random();
		if (aar.nextBoolean()) {
			e = new EnemyBeastShip(graphics().rootLayer());
		} else {
			e = new EnemyShip(graphics().rootLayer());
		}
		e.setPosition((int) Math.round((Math.random() * OrbiterMain.canvasWidth)), (int) (0 - e.getLayer().scaledHeight()));
		elements.add(e);
	}
}