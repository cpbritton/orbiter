package com.britton2000.orbiter.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.britton2000.orbiter.core.OrbiterMain;

public class OrbiterJava {

	public static void main(String[] args) {
		JavaPlatform platform = JavaPlatform.register();
		platform.assets().setPathPrefix("com/britton2000/orbiter/resources");
		PlayN.run(new OrbiterMain());
	}
}
