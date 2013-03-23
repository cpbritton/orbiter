 package me.xorga.orbiter.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;

public class ScorePiece {
	int boardx, boardy;
	ImageLayer layer;
	Image [] images = new Image [10];
	int num;
	boolean visible = false;
	public ScorePiece(float canvaswidth, float canvasheight, final GroupLayer parentLayer) {
		// TODO Auto-generated constructor stub
		images [0] = assets().getImageSync("images/numbers/numberZero.png");
		images [1] = assets().getImageSync("images/numbers/numberOne.png");
		images [2] = assets().getImageSync("images/numbers/numberTwo.png");
		images [3] = assets().getImageSync("images/numbers/numberThree.png");
		images [4] = assets().getImageSync("images/numbers/numberFour.png");
		images [5] = assets().getImageSync("images/numbers/numberFive.png");
		images [6] = assets().getImageSync("images/numbers/numberSix.png");
		images [7] = assets().getImageSync("images/numbers/numberSeven.png");
		images [8] = assets().getImageSync("images/numbers/numberEight.png");
		images [9] = assets().getImageSync("images/numbers/numberNine.png");
		layer = graphics().createImageLayer(images[0]);
		parentLayer.add(layer);
		layer.setScale(OrbiterMain.imageSize * 2);
		layer.setVisible(false);
		layer.setDepth(101);
		layer.setTranslation(300 * OrbiterMain.imageSize, 4 * OrbiterMain.imageSize);
	}
	
	public void paint(float alpha) {
		layer.setImage(images[num]);
		layer.setTranslation(boardx, boardy);
	}
	
	public void setNumber(int a){
		num = a;
	}
	
	public void visible(boolean a){
		visible = a;
		if(a){
			layer.setVisible(true);
		}else{
			layer.setVisible(false);
		}
	}
	
	public void setPlace(int x, int y){
		boardx = x;
		boardy = y;
	}
}
