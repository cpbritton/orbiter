package com.britton2000.orbiter.elements;

//Max Britton

import playn.core.GroupLayer;

public class TracerBullet extends Bullet {

	private final String imageName = "images/TracerBullet.png";

	public TracerBullet(GroupLayer parentLayer, Element creator,
			boolean direction) {
		super(parentLayer, creator, direction);
	}

	@Override
	public String getImage() {
		return imageName;
	}

}
