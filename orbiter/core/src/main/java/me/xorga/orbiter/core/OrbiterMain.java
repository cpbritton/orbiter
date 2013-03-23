package me.xorga.orbiter.core;

//Max Britton hi

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.keyboard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;
import playn.core.Game;
import playn.core.ImageLayer;
import playn.core.Key;
import playn.core.Keyboard;
import playn.core.PlayN;
import playn.core.gl.GLContext;

import me.xorga.orbiter.elements.Asteroid;
import me.xorga.orbiter.elements.Element;
import me.xorga.orbiter.elements.ElementInterface;
import me.xorga.orbiter.elements.EnemyBeastShip;
import me.xorga.orbiter.elements.EnemyShip;
import me.xorga.orbiter.elements.Gem;
import me.xorga.orbiter.core.ScorePiece;

public class OrbiterMain implements Game, Keyboard.Listener {
	ImageLayer bglayer;
	ImageLayer bglayer2;

	float frameSwitch, tvPositionChange;
	public static boolean right,left,up, down, reset,pause = false, tvMode = false;
	static boolean space,g,  initiateObjects = false, menuPlay = true;
	boolean f, startpos, fireBullet, launchAsteroid, fire, smokeon, b, a;
	public static float imageSize = 6.0f, canvasWidth = 1920f, canvasHeight = 1080f;
	public static Ship ship;
	private Background background;
	private GUI gui;
	private Menu menu;
	private PauseMenu pauseMenu;
	private OptionsMenu optionsMenu;
	private TouchControl touchControl;
	private ArrayList<ElementInterface> elements = new ArrayList<ElementInterface>();
	public static Stack<Element> elementSpawnQueue = new Stack<Element>();
	int asteroidSpawnRate, smokeRate, gemSpawnRate, enemyRate, toggleRate, scoreAdd, pauseToggleRate;
	static int initiateObjectsNumber = 0, score, increaseScore, level = 0;
	ScorePiece board [] = new ScorePiece [6];
	public Instruction instru;

	@Override
	public void init() {

		// figure out screen size
		int deviceWidth = PlayN.graphics().screenWidth();
		int deviceHeight = PlayN.graphics().screenHeight();
		int gWidth = PlayN.graphics().width();
		int gHeight = PlayN.graphics().height();

		PlayN.log().info("Screen sizes, device: "+ deviceWidth + ":" + deviceHeight + " , " + gWidth +":" + gHeight);

		//if (gWidth > 1280){
		imageSize = gWidth /320;
		canvasWidth = gWidth;
		canvasHeight = gHeight;
		//}

		keyboard().setListener(this);
		PlayN.graphics().ctx().setTextureFilter(GLContext.Filter.NEAREST, GLContext.Filter.NEAREST);
		background = new Background(canvasWidth, canvasHeight, graphics().rootLayer());
		gui = new GUI(graphics().rootLayer());
		ship = new Ship(canvasWidth, canvasHeight, graphics().rootLayer());
		menu = new Menu(canvasWidth, canvasHeight, graphics().rootLayer());
		pauseMenu = new PauseMenu(canvasWidth, canvasHeight, graphics().rootLayer());
		optionsMenu = new OptionsMenu(canvasWidth, canvasHeight, graphics().rootLayer());
		touchControl = new TouchControl(canvasWidth, canvasHeight, graphics().rootLayer());
		instru = new Instruction(canvasWidth, canvasHeight, graphics().rootLayer());
		
		if (tvMode) {
			tvPositionChange = 18 * imageSize;
		}else{
			tvPositionChange = 0;
		}

		for(int a = 0; a < 6; a++){
			board[a] = new ScorePiece(canvasWidth, canvasHeight, graphics().rootLayer());
			board[a].setPlace((int) ((310 * OrbiterMain.imageSize) - tvPositionChange
					- ((board[a].layer.scaledWidth() * a)+7 * a)), (int)(4 * OrbiterMain.imageSize) + (int)tvPositionChange);
		}
	}

	@Override
	public void update(float delta) {
		if (reset == true) {
			reset();
		}

		if(level == 0){
			score = 0;
			menuPlay = true;
			for(int a = 0; a < 6; a++) {
				board[a].visible(false);
			}
		}
		if (level == 0 || pause) {
			menu.update(delta);
			gui.update(delta);
			pauseMenu.update(delta);
			optionsMenu.update(delta);
			touchControl.update(delta);
			background.update(delta);
		} else if (level == 1) {
			menuPlay = false;
			processElements(delta);
			ship.update(delta);
			gui.update(delta);
			background.update(delta);
			menu.update(delta);
			pauseMenu.update(delta);
			optionsMenu.update(delta);
			touchControl.update(delta);
			for(int a = 0; a < 6; a++){
				if(a == 0){	
					board[a].setNumber(score%10);
				}else if(a == 1){
					board[a].setNumber((score/10)%10);
				}else if (a == 2){
					board[a].setNumber((score/100)%10);
				}else if (a == 3){
					board[a].setNumber((score/1000)%10);
				}else if (a == 3){
					board[a].setNumber((score/10000)%10);
				}else if (a == 3){
					board[a].setNumber((score/100000)%10);
				}
				board[a].visible(true);
			}
			
			if (!reset){
			
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
			increaseScore++;
			if (increaseScore > 20) {
				score++;
				increaseScore = 0;
			}
			}
		}
	}
	
	private void processElementSpawnQueue(){
		//
		while (!OrbiterMain.elementSpawnQueue.isEmpty()) {
			elements.add(OrbiterMain.elementSpawnQueue.pop());
		}
	}

	public void reset() {
		
		processElementSpawnQueue();
		
		// check elements
		if (elements != null && elements.size() >0){
			//PlayN.log().info("in reset waiting");
			return;
		}
		
		// clear the ship
 		if (ship != null) {
			ship.clear();
		}
		
		// bring up menu
		level=0;

		
		elements = new ArrayList<ElementInterface>();
		
		if (background != null) {
			background.clear();
		}
		background = new Background(canvasWidth, canvasHeight, graphics().rootLayer());
		
		if (gui != null) {
			gui.clear();
		}
		
		gui = new GUI(graphics().rootLayer());

		ship = new Ship(canvasWidth, canvasHeight, graphics().rootLayer());

		if (reset == true) {
			reset = false;
		}
		
	}

	@Override
	public void paint(float alpha) {
		background.render(alpha);
		if (level == 0) {
			menu.render(alpha);
			if(Menu.instruTrue){
				instru.visble(true);
			}else{
				instru.visble(false);
			}
		}
		if (level == 1) {
			paintElements(alpha);
			ship.render(alpha);
			gui.render(alpha);
			menu.render(alpha);
			instru.visble(false);
			for(ScorePiece piece: board){
				piece.paint(alpha);
			}

		}
	}

	private void processElements(float delta) {
			
			processElementSpawnQueue();
		
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
					it.remove();
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
				gameOver();
			}
		
			processElementSpawnQueue();

	}

	void gameOver() {
		if (!reset){
			reset = true;
			reset();						
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
	
	public void onKey(playn.core.Keyboard.Event event) {
		//PlayN.log().info("OnKey: " + event.key().toString());
		
		if (pause){
			this.pauseMenu.onKey( event);
			this.optionsMenu.onKey( event);
		}else if (menuPlay && Menu.instruTrue == false){
			this.menu.onKey( event);
		}
		
		event.key();
		if (OptionsMenu.showOptions == false && PauseMenu.currentVisible == false && level == 1
				&& (event.key() == Key.ESCAPE || event.key() == Key.P )) {
			if (pause == false) {
				pause = true;
			} else if (pause == true) {
				pause = false;
			}
		}
		if ( Menu.instruTrue && (event.key() == Key.ESCAPE || event.key() == Key.P) ){
			Menu.instruTrue = false;
		}
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
		case H:
			int status = 0;
			System.exit(status);
			break;
		}
		
		// process key actions locally
		this.onKey(event);
	
	}

	@Override
	public void onKeyTyped(Keyboard.TypedEvent event) {
		// ...
		
	}

	@SuppressWarnings("incomplete-switch")
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