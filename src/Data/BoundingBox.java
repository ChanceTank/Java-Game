package Data;

public class BoundingBox {
	private int _minX, _minY, _width, _height;

	//constructor
	public BoundingBox(int minX, int minY, int width, int height) {
		_minX = minX;
		_minY = minY;
		_width = width;
		_height = height;
	}

	//methods	

	/**
	*	Method for detecting if 2 BoundingBoxes overlap.
	*	@param b the second BoundingBox
	*	@return boolean whether they intersect or not.
	*/
	public boolean intersects(BoundingBox b) {
		if (_minX > b.getX2())
			return false;
		if (_minX + _width < b.getX1())
			return false;
		if (_minY > b.getY2())
			return false;
		if (_minY + _height < b.getY1())
			return false;

		return true;
	}

	/**
	*	Method for returning the left x-coord
	*/
	public int getX1() {
		return _minX;
	}

	/**
	*	Method for returning the right x-coord
	*/
	public int getX2() {
		return _minX + _width;
	}

	/**
	*	Method for returning the upper y-coord
	*/
	public int getY1() {
		return _minY;
	}

	/**
	*	Method for returning the lower y-coord
	*/
	public int getY2() {
		return _minY + _height;
	}
	
	public void moveCoords(int x, int y){
		_minX = x;
		_minY = y;
	}
	
	public void shiftCoords(int x, int y){
		_minX += x;
		_minY += y;
	}

}