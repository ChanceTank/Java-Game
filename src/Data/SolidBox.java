package Data;

public class SolidBox {
	private BoundingBox stopBox; //make a stop box that halts movement
	private BoundingBox hands;

	public SolidBox(int minX, int minY, int width, int height) {
		stopBox = new BoundingBox(minX, minY, width, height);
		hands = new BoundingBox(minX, minY, width, height);
	}

	public static Boolean stoppable(SolidBox box1, SolidBox box2) {
		return box1.stopBox.intersects(box2.stopBox);
	}

	public static Boolean touchable(SolidBox box1, SolidBox box2) {
		return box1.hands.intersects(box2.stopBox);
	}

	public void moveBox(SolidBox box1) {
		stopBox = box1.stopBox;
		hands = box1.hands;
	}

	public void moveBox(int minX, int minY, int width, int height) {
		stopBox = new BoundingBox(minX, minY, width, height);
		hands = new BoundingBox(minX, minY, width, height);
	}

	public void moveHands(int x, int y) {
		hands.moveCoords(x, y);
	}

	public void shiftHands(int x, int y) {
		hands.shiftCoords(x, y);
	}
}