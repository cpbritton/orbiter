package me.xorga.orbiter.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import me.xorga.orbiter.core.OrbiterMain;

public class OrbiterActivity extends GameActivity {

	@Override
	public void main() {
		platform().assets().setPathPrefix("me/xorga/orbiter/resources");
		PlayN.run(new OrbiterMain());
	}
}
