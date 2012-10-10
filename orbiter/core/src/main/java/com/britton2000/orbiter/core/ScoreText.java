//package com.britton2000.orbiter.core;
//
//Max Britton
//
//import static playn.core.PlayN.graphics;
//import playn.core.CanvasLayer;
//import playn.core.Font;
//import playn.core.GroupLayer;
//import playn.core.Layer;
//import playn.core.TextFormat;
//import playn.core.TextLayout;
//
//public class ScoreText {
//	private final GroupLayer base;
//
//	public String name() {
//		return "Text";
//	}
//
//	public ScoreText() {
//
//		base = graphics().createGroupLayer();
//		graphics().rootLayer().add(base);
//		//
//		// for (String name : new String[] { "Helvetica", "Museo" }) {
//		// for (Font.Style style : Font.Style.values()) {
//		// for (float size : new float[] { 12f, 24f, 32f }) {
//		// Font font = graphics().createFont(name, style, size);
//		// TextFormat format = new TextFormat().withFont(font);
//		// TextLayout layout = graphics().layoutText(
//		// "Hello PlayN World", format);
//		// Layer layer = createTextLayer(layout);
//		// layer.setTranslation(0, 0);
//		// base.add(layer);
//		// }
//		// }
//		// }
//
//		Font font = graphics().createFont("Courier", Font.Style.PLAIN, 32);
//		String text = String.valueOf(OrbiterMain.score);
//		TextLayout layout = graphics().layoutText(
//				text,
//				new TextFormat().withFont(font)
//						.withWrapping(200, TextFormat.Alignment.RIGHT)
//						.withEffect(TextFormat.Effect.outline(0x44444444))
//						.withTextColor(0xFF00FFFF));
//		Layer layer = createTextLayer(layout);
//		layer.setTranslation(25, 650);
//		base.add(layer);
//	}
//
//	protected Layer createTextLayer(TextLayout layout) {
//		CanvasLayer layer = graphics().createCanvasLayer(
//				(int) Math.ceil(layout.width()),
//				(int) Math.ceil(layout.height()));
//		layer.canvas().drawText(layout, 0, 0);
//		return layer;
//	}
//
//	public void update(float delta) {
//
//	}
//}
//
// Courier, Helvetica, Museo

