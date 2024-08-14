/* This will handle the "Hot Key" system. */

package Main;

import logic.Control;
import timer.stopWatchX;

public class KeyProcessor {
	// Static Fields
	private static char last = ' '; // For debouncing purposes
	private static stopWatchX sw = new stopWatchX(250);

	// Static Method(s)
	public static void processKey(char key) {
		if (key == ' ') {
			if (Main.dir != 0)
				Main.standStill();
			return;
		}
		// Debounce routine below...
		if (key == last)
			if (sw.isTimeUp() == false)
				return;
		last = key;
		sw.resetWatch();

		/* TODO: You can modify values below here! */
		switch (key) {
			case '%': // ESC key
				System.exit(0);
				break;
			case 'm':
				// For mouse coordinates
				Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
				break;
			case 'w':
				//Main.trigger = "W-key has been triggered";
				if (Main.dir != 1)
					Main.moveBack();
				break;
			case 'a':
				//Main.trigger = "A-key has been triggered";
				if (Main.dir != 3)
					Main.moveLeft();
				break;
			case 's':
				//Main.trigger = "S-key has been triggered";
				if (Main.dir != 2)
					Main.moveForward();
				break;
			case 'd':
				//Main.trigger = "D-key has been triggered";
				if (Main.dir != 4)
					Main.moveRight();
				break;
			case '$':
				//Main.trigger = "Space Bar has been triggered";
				Main.touchable();
				break;
		}
	}
}