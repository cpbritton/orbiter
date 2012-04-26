package com.britton2000.orbiter.core;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.keyboard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

import playn.core.Game;
import playn.core.ImageLayer;
import playn.core.Keyboard;

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
	static boolean right, left, up, down, g;
	boolean space, f, startpos, fireBullet, launchAsteroid, fire, smokeon;
	public final static int canvasWidth = 1280, canvasHeight = 720;
	private Ship ship;
	private Background background;
	private GUI gui;
	private Menu buttons;
	private final ArrayList<ElementInterface> elements = new ArrayList<ElementInterface>();
	public static Stack<Element> elementSpawnQueue = new Stack<Element>();
	boolean b, a;
	int launchRate, smokeRate, gemRate, enemyRate, toggleRate;
	public static int imageSize = 5;
	static int level = 1;

	@Override
	public void init() {
		keyboard().setListener(this);
		graphics().setSize(canvasWidth, canvasHeight);
		background = new Background(canvasWidth, canvasHeight, graphics()
				.rootLayer());
		if (level == 1) {
			ship = new Ship(canvasWidth, canvasHeight, graphics().rootLayer());

		}
		if (level == 0) {
			buttons = new Menu(canvasWidth, canvasHeight, graphics()
					.rootLayer());
		}

		gui = new GUI(graphics().rootLayer());
	}

	@Override
	public void update(float delta) {
		if (level == 1) {
			ship.update(delta);
			if (space) {
				fireBullet(true);
			}
			launchRate += 1;
			if (launchRate > 150) {
				launchAsteroid(true);
				launchRate = 0;
			}
			gemRate += 1;
			if (gemRate > 250) {
				launchGem(true);
				gemRate = 0;
			}
			smokeRate = 0;
			if (smokeon == true) {
				if (smokeRate == 0) {
					smoke(true);
					smokeRate = 0;
				}
			}

			enemyRate += 1;
			if (enemyRate > 500) {
				launchEnemy(true);
				enemyRate = 0;
			}
			toggleRate += 1;
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
			processElements(delta);
			background.update(delta);
		}
	}

	@Override
	public void paint(float alpha) {
		if (1 == level) {

			ship.render(alpha);
			paintElements(alpha);
		}
		background.render(alpha);
		gui.render(alpha);
	}

	private void processElements(float delta) {
		if (elements != null) {
			for (Iterator<ElementInterface> it = elements.iterator(); it
					.hasNext();) {
				ElementInterface e = it.next();
				e.update(delta);
				if (e.getY2() < 0 || e.getY() > canvasHeight) {
					e.clear();
					it.remove();
				} else {
					if (checkCollisions(e)) {
						e.processCollisions(delta);
					}
				}

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
			// collide = element.hasCollision(ship);
		}

		return collide;
	}

	@Override
	public int updateRate() {
		return 25;
	}

	@Override
	public void onKeyDown(Keyboard.Event event) {

		switch (event.key()) {
		case A:
			left = true;
			break;
		case D:
			right = true;
			break;
		case W:
			up = true;
			break;
		case S:
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
			left = false;
			break;
		case D:
			right = false;
			break;
		case W:
			up = false;
			break;
		case S:
			down = false;
			break;
		case SPACE:
			space = false;
			break;
		case F:
			f = false;
			break;
		case G:
			g = false;
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
		Asteroid a = new Asteroid(canvasWidth, canvasHeight, graphics()
				.rootLayer());

		a.setPosition((int) Math.round((Math.random() * canvasWidth)),
				(int) (0 - a.getLayer().scaledHeight()));
		elements.add(a);
	}

	private void launchGem(boolean r) {
		Gem g = new Gem(canvasWidth, canvasHeight, graphics().rootLayer());

		g.setPosition((int) Math.round((Math.random() * canvasWidth)),
				(int) (0 - g.getLayer().scaledHeight()));
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

		e.setPosition(
				(int) Math.round((Math.random() * OrbiterMain.canvasWidth)),
				(int) (0 - e.getLayer().scaledHeight()));
		elements.add(e);
	}
}