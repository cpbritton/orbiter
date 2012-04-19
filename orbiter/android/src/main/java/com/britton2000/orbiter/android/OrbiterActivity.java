package com.britton2000.orbiter.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.britton2000.orbiter.core.OrbiterMain;

public class OrbiterActivity extends GameActivity {

	@Override
	public void main() {
		platform().assets().setPathPrefix("com/britton2000/orbiter/resources");
		PlayN.run(new OrbiterMain());
	}
}
