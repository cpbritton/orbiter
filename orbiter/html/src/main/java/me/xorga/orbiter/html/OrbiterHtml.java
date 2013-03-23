package me.xorga.orbiter.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import me.xorga.orbiter.core.OrbiterMain;

public class OrbiterHtml extends HtmlGame {

	@Override
	public void start() {
		HtmlPlatform platform = HtmlPlatform.register();
		platform.assets().setPathPrefix("orbiter/");
		PlayN.run(new OrbiterMain());
	}
}
