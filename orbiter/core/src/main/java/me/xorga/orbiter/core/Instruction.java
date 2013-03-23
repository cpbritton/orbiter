package me.xorga.orbiter.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;

public class Instruction {
	Image instruction;
	ImageLayer ilayer;

	public Instruction(float canvaswidth, float canvasheight, final GroupLayer parentLayer){
		
		instruction = assets().getImageSync("images/Ouyacontrols.png");
		
		ilayer = graphics().createImageLayer(instruction);
		System.out.println(ilayer.height());
		System.out.println(ilayer.width());
		ilayer.setVisible(true);
		parentLayer.add(ilayer);
		ilayer.setDepth(102);
		ilayer.setTranslation((OrbiterMain.canvasWidth / 2) - (ilayer.scaledWidth()/2),
				(OrbiterMain.canvasHeight / 2)-(ilayer.scaledHeight()/2));
	}
	
	public void visble(boolean a){
		if(a){
			ilayer.setVisible(true);
		}else{
			ilayer.setVisible(false);
		}
	}
	

}
