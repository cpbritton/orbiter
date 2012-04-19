package com.britton2000.orbiter.elements;

import playn.core.GroupLayer;

public class TracerBullet extends Bullet {

	private final String imageName = "images/TracerBullet.png";

	public TracerBullet(GroupLayer parentLayer, boolean direction) {
		super(parentLayer, direction);
	}

	@Override
	public String getImage() {
		return imageName;
	}

}
