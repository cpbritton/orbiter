package com.britton2000.orbiter.ouya;

import playn.android.GameActivity;
import playn.core.PlayN;
import tv.ouya.console.api.OuyaController;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.britton2000.orbiter.core.OrbiterMain;

public class OrbiterActivity extends GameActivity {

	/**
	 * Log onto the developer website (you should have received a URL, a
	 * username and a password in email) and get your developer ID. Plug it in
	 * here. Use your developer ID, not your developer UUID.
	 * <p/>
	 * The current value is just a sample developer account. You should change
	 * it.
	 */
	public static final String DEVELOPER_ID = "310a8f51-4d6e-4ae5-bda0-b93878e5f5d0";

	@Override
	public void main() {

		platform().assets().setPathPrefix("com/britton2000/orbiter/resources");
		PlayN.run(new OrbiterMain());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see playn.android.GameActivity#onKeyDown(int, android.view.KeyEvent) //
	 * // Field descriptor #59 I public static final int BUTTON_O = 96; // Field
	 * descriptor #59 I public static final int BUTTON_U = 99; // Field
	 * descriptor #59 I public static final int BUTTON_Y = 100; // Field
	 * descriptor #59 I public static final int BUTTON_A = 97; // Field
	 * descriptor #59 I public static final int BUTTON_L1 = 102; // Field
	 * descriptor #59 I public static final int BUTTON_L2 = 104; // Field
	 * descriptor #59 I public static final int BUTTON_R1 = 103; // Field
	 * descriptor #59 I public static final int BUTTON_R2 = 105; // Field
	 * descriptor #59 I public static final int BUTTON_SYSTEM = 3; // Field
	 * descriptor #59 I public static final int AXIS_LS_X = 0; // Field
	 * descriptor #59 I public static final int AXIS_LS_Y = 1; // Field
	 * descriptor #59 I public static final int AXIS_RS_X = 11; // Field
	 * descriptor #59 I public static final int AXIS_RS_Y = 14; // Field
	 * descriptor #59 I public static final int AXIS_L2 = 17; // Field
	 * descriptor #59 I public static final int AXIS_R2 = 18; // Field
	 * descriptor #59 I public static final int BUTTON_DPAD_UP = 19; // Field
	 * descriptor #59 I public static final int BUTTON_DPAD_RIGHT = 22; // Field
	 * descriptor #59 I public static final int BUTTON_DPAD_DOWN = 20; // Field
	 * descriptor #59 I public static final int BUTTON_DPAD_LEFT = 21; // Field
	 * descriptor #59 I public static final int BUTTON_R3 = 107; // Field
	 * descriptor #59 I public static final int BUTTON_L3 = 106;
	 * 
	 * OUYA Color Default Function O green select U blue options (Android's menu
	 * function) Y yellow - A red back/cancel Please use the naming conventions
	 * below when referring to the controller buttons in help screens for your
	 * game:
	 * 
	 * O, U, Y, A System (the home button) D-Pad LS (the left joystick movement)
	 * L1 (the left bumper) L2 (the left trigger) L3 (the button function of
	 * pressing the left joystick straight down) RS R1 R2 R3 Touchpad (not
	 * "Trackpad")
	 */

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		System.out.println("Key down: " + keyCode);
		return super.onKeyDown(keyMapper(keyCode), event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return super.onKeyUp(keyMapper(keyCode), event);
	}

	private int keyMapper(int keyCode) {
		// remap OUYA Buttons and DPAD to enown keytypes
		// W S A D , SPACE , ESC, G ,F

		if (keyCode == OuyaController.BUTTON_L1) {
			keyCode = KeyEvent.KEYCODE_G;
		} else if (keyCode == OuyaController.BUTTON_R1) {
			keyCode = KeyEvent.KEYCODE_F;
		} else if (keyCode == OuyaController.BUTTON_L2 || keyCode == OuyaController.BUTTON_R2) {
			keyCode = KeyEvent.KEYCODE_SPACE;
		} else if (keyCode == OuyaController.BUTTON_U) {
			keyCode = KeyEvent.KEYCODE_ESCAPE;
		} else if (keyCode == OuyaController.BUTTON_O) {
			keyCode = KeyEvent.KEYCODE_ENTER;
		}

		return keyCode;
	}

	// @Override
	// public boolean onGenericMotionEvent(MotionEvent event) {
	// if ((event.getSource() & InputDevice.SOURCE_CLASS_JOYSTICK) == 0) {
	// // Not a joystick movement, so ignore it.
	// return false;
	// }
	// return getControllerView(event).onGenericMotionEvent(event);
	// }

	public boolean onGenericMotionEvent(MotionEvent event) {
		int odid = event.getDeviceId();
		boolean handled = OuyaController.onGenericMotionEvent(event);
		System.out.println("Motion Event: " + event.describeContents());

		OuyaController c = OuyaController.getControllerByDeviceId(event.getDeviceId());
		if (c != null) {
		}

		return handled || super.onGenericMotionEvent(event);
	}
}
