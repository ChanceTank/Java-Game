package Data;

public class BoxSprite {
	spriteInfo Cl, Op;
	Boolean isOpen = false;
	Boolean hasKey = false;

	//constructor
	public BoxSprite(Vector2D v2d, String ClTag, String OpTag) {
		Cl = new spriteInfo(v2d, ClTag);
		Op = new spriteInfo(v2d, OpTag);
	}

	//methods
	public void setCoords(Vector2D newV2D) {
		Cl.setCoords(newV2D);
		Op.setCoords(newV2D);
	}

	public void setCoords(int x, int y) {
		Cl.setCoords(x, y);
		Op.setCoords(x, y);
	}

	public Vector2D getCoords() {
		return Cl.getCoords();
	}

	public String getTag() {
		if (isOpen) {
			return Op.getTag();
		}
		return Cl.getTag();
	}

	public void OpenBox() {
		isOpen = true;
	}

	public void CloseBox() {
		isOpen = false;
	}

	public Boolean isOpen() {
		return isOpen;
	}

	public void setKey(Boolean value) {
		hasKey = value;
	}

	public Boolean getKey() {
		return hasKey;
	}

}
