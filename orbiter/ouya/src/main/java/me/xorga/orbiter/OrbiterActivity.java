package me.xorga.orbiter;

import me.xorga.orbiter.core.OrbiterMain;
import playn.android.GameActivity;
import playn.core.PlayN;
import tv.ouya.console.api.OuyaController;
import tv.ouya.console.api.OuyaFacade;
import tv.ouya.console.api.OuyaIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class OrbiterActivity extends GameActivity {

	/**
	 * Log onto the developer website (you should have received a URL, a
	 * username and a password in email) and get your developer ID. Plug it in
	 * here. Use your developer ID, not your developer UUID.
	 * <p/>
	 * The current value is just a sample developer account. You should change
	 * it.
	 */
	public static final String DEVELOPER_ID = "5ae6e5d7-b7b2-4cfb-9daf-302ea33d6d29";
	private OuyaFacade ouyaFacade = OuyaFacade.getInstance();

	private BroadcastReceiver receiver;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		OuyaController.init(this);
		ouyaFacade.init(this, DEVELOPER_ID);
		
		OrbiterMain.tvMode=true;

		
	       final IntentFilter theFilter = new IntentFilter();
	        theFilter.addAction(OuyaIntent.ACTION_MENUAPPEARING);

	        this.receiver = new BroadcastReceiver() {
				@Override
				public void onReceive(Context arg0, Intent arg1) {
					OrbiterMain.pause=true;
					
				}
	        };

	        // Registers the receiver so that your service will listen for broadcasts
	        this.registerReceiver(this.receiver, theFilter);
	}
	
	@Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.receiver);
    }
	

	@Override
	public void main() {
		platform().assets().setPathPrefix("me/xorga/orbiter/resources");
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
		// OuyaController.onKeyDown(keyCode, event);
		// System.out.println("Key down: " + keyCode);
		return super.onKeyDown(keyMapper(keyCode), event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// boolean handled = OuyaController.onKeyUp(keyCode, event);
		// System.out.println("Key up: " + keyCode);
		return super.onKeyUp(keyMapper(keyCode), event);
	}

	private int keyMapper(int keyCode) {
		// remap OUYA Buttons and DPAD to enown keytypes
		// W S A D , SPACE , ESC, G ,F

		if (keyCode == OuyaController.BUTTON_L1 || keyCode == OuyaController.BUTTON_R1) {
			keyCode = KeyEvent.KEYCODE_G;
		} 
		//else if (keyCode == OuyaController.BUTTON_R1) {
			//keyCode = KeyEvent.KEYCODE_F;
		//}
		else if (keyCode == OuyaController.BUTTON_L2 || keyCode == OuyaController.BUTTON_R2) {
			keyCode = KeyEvent.KEYCODE_SPACE;
		} else if (keyCode == OuyaController.BUTTON_A) {
			keyCode = KeyEvent.KEYCODE_P;
		} else if (keyCode == OuyaController.BUTTON_O) {
			keyCode = KeyEvent.KEYCODE_ENTER;
		} else if(keyCode == OuyaController.BUTTON_MENU){
			keyCode = KeyEvent.KEYCODE_H;
		}
		// System.out.println("Mapped Key output: " + keyCode);

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

	// public boolean onGenericMotionEvent(MotionEvent event) {
	// int odid = event.getDeviceId();
	// boolean handled = OuyaController.onGenericMotionEvent(event);
	// // System.out.println("Motion Event: " + event.describeContents());
	//
	// OuyaController c =
	// OuyaController.getControllerByDeviceId(event.getDeviceId());
	// if (c != null) {
	// }
	//
	// return handled || super.onGenericMotionEvent(event);
	// }

	@Override
	public boolean onGenericMotionEvent(MotionEvent event) {

		if ((event.getSource() & InputDevice.SOURCE_CLASS_JOYSTICK) == 1) {

			float LS_X = event.getAxisValue(OuyaController.AXIS_LS_X);
			float LS_Y = event.getAxisValue(OuyaController.AXIS_LS_Y);

			// System.out.println("JS output: " + String.format("%.2f", LS_X));

			if (LS_X < -0.1f) {
				OrbiterMain.left = true;
				OrbiterMain.right = false;
				// System.out.println("Detect Left");
			} else if (LS_X > 0.1f) {
				// right
				OrbiterMain.left = false;
				OrbiterMain.right = true;
				// System.out.println("Detect Right");

			} else {
				// centered
				OrbiterMain.left = false;
				OrbiterMain.right = false;
				// System.out.println("Detect X Center");

			}

			if (LS_Y < -0.1f) {
				// up
				OrbiterMain.up = true;
				OrbiterMain.down = false;
				// System.out.println("Detect Up");

			} else if (LS_Y > 0.1f) {
				// down
				OrbiterMain.up = false;
				OrbiterMain.down = true;
				// System.out.println("Detect Down");

			} else {
				// centered
				OrbiterMain.up = false;
				OrbiterMain.down = false;
				// System.out.println("Detect Y Center");

			}
			// System.out.println("Post L" + OrbiterMain.left + " R" +
			// OrbiterMain.right + " U" + OrbiterMain.up + "D" +
			// OrbiterMain.down);
		}

		return super.onGenericMotionEvent(event);

	}
}
