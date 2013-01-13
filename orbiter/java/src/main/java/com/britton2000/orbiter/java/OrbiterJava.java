package com.britton2000.orbiter.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;
import playn.java.JavaPlatform.Config;

import com.britton2000.orbiter.core.OrbiterMain;

public class OrbiterJava {

	public static void main(String[] args) {
		Config config = new Config();
		config.width = 1280;
		config.height = 720;
		JavaPlatform platform = JavaPlatform.register(config);
		platform.assets().setPathPrefix("com/britton2000/orbiter/resources");
		PlayN.run(new OrbiterMain());
	}
}
