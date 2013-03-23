package me.xorga.orbiter.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Touch;
import playn.core.Touch.Event;

public class TouchControl {
	Image arrowUp, arrowDown, arrowLeft, arrowRight, shootButton;
	ImageLayer arrowUpLayer, arrowDownLayer, arrowLeftLayer, arrowRightLayer, shootButtonLayer;
	
	private boolean currentVisible = false;
	static boolean showButtons = false;

	public TouchControl(float canvaswidth, float canvasheight, final GroupLayer parentLayer) {
		arrowUp = assets().getImageSync("images/touchControls/arrowUp.png");
		arrowUpLayer = graphics().createImageLayer(arrowUp);
		parentLayer.add(arrowUpLayer);
		arrowUpLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		arrowUpLayer.setTranslation(35 * OrbiterMain.imageSize, 100 * OrbiterMain.imageSize);
		arrowUpLayer.setDepth(100);
		arrowUpLayer.setVisible(false);
		arrowUpLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				OrbiterMain.up = true;
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
				OrbiterMain.up = false;
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});
		arrowDown = assets().getImageSync("images/touchControls/arrowDown.png");
		arrowDownLayer = graphics().createImageLayer(arrowDown);
		parentLayer.add(arrowDownLayer);
		arrowDownLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		arrowDownLayer.setTranslation(35 * OrbiterMain.imageSize, 150 * OrbiterMain.imageSize);
		arrowDownLayer.setDepth(100);
		arrowDownLayer.setVisible(false);
		arrowDownLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				OrbiterMain.down = true;
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
				OrbiterMain.down = false;
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});
		arrowLeft = assets().getImageSync("images/touchControls/arrowLeft.png");
		arrowLeftLayer = graphics().createImageLayer(arrowLeft);
		parentLayer.add(arrowLeftLayer);
		arrowLeftLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		arrowLeftLayer.setTranslation(10 * OrbiterMain.imageSize, 125 * OrbiterMain.imageSize);
		arrowLeftLayer.setDepth(100);
		arrowLeftLayer.setVisible(false);
		arrowLeftLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				OrbiterMain.left = true;
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
				OrbiterMain.left = false;
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});
		arrowRight = assets().getImageSync("images/touchControls/arrowRight.png");
		arrowRightLayer = graphics().createImageLayer(arrowRight);
		parentLayer.add(arrowRightLayer);
		arrowRightLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		arrowRightLayer.setTranslation(60 * OrbiterMain.imageSize, 125 * OrbiterMain.imageSize);
		arrowRightLayer.setDepth(100);
		arrowRightLayer.setVisible(false);
		arrowRightLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				OrbiterMain.right = true;
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
				OrbiterMain.right = false;
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});
		shootButton = assets().getImageSync("images/touchControls/shootButton.png");
		shootButtonLayer = graphics().createImageLayer(shootButton);
		parentLayer.add(shootButtonLayer);
		shootButtonLayer.setScale(OrbiterMain.imageSize, OrbiterMain.imageSize);
		shootButtonLayer.setTranslation(280 * OrbiterMain.imageSize, 125 * OrbiterMain.imageSize);
		shootButtonLayer.setDepth(100);
		shootButtonLayer.setVisible(false);
		shootButtonLayer.addListener(new Touch.LayerListener() {
			@Override
			public void onTouchStart(Event touch) {
				OrbiterMain.space = true;
			}
			@Override
			public void onTouchMove(Event touch) {
				//nothing	
			}
			@Override
			public void onTouchEnd(Event touch) {
				OrbiterMain.space = false;
			}	
			@Override
			public void onTouchCancel(Event touch) {
				//nothing
			}
		});
	}
	
	public void update(float delta) {
		
		if (OrbiterMain.level == 0) {
			controlsVisible(false);
		}
		if (OrbiterMain.level == 1) {
			if(showButtons == true) {
				controlsVisible(true);
			}
		}
	}
	
	private void controlsVisible(boolean visible) {
		if (currentVisible == visible){
			return;
		}
		currentVisible = visible;
		arrowUpLayer.setVisible(visible);
		arrowDownLayer.setVisible(visible);
		arrowLeftLayer.setVisible(visible);
		arrowRightLayer.setVisible(visible);
		shootButtonLayer.setVisible(visible);
	}

}
