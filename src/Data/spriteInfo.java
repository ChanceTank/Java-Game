/* This is a way to pass a sprite's key information in one entity. (x, y, tag) */

package Data;

public class spriteInfo {
	// Fields
		// TODO: Add private class fields to store x, y (use Vector2D for this) and tag (String) values given in class constructor
	private Vector2D _vec = null;
	private String _tag = null;
	
	// Constructor
	
	public spriteInfo(Vector2D v2d, String tag){
		// TODO: Save the constructor parameters into class fields
		_vec = new Vector2D(v2d.getX(), v2d.getY());
		_tag = new String(tag);
	}
	
	// Methods
	/**
     * Returns the sprite's tag.
     * @param   none
     * @return  String tag,   a string to load sprites from Art.txt.
     */
	public String getTag(){
		// TODO: Remove my placeholder code below (which is there to prevent an error) and replace it with returning the value of your private field tag
		return new String(_tag);
	}
	/**
     * Returns the sprite's coords as a 2D vector.
     * @param   none
     * @return  Vector2D vec,   a 2D vector obj.
     */
	public Vector2D getCoords(){
		// TODO: Remove my placeholder code below (which is there to prevent an error) and replace it with returning the value of your private field v2d
		return new Vector2D(_vec.getX(), _vec.getY()); 
	}
	/**
     * Sets the sprite's Tag.
     * @param
     * @return void 
     */
	public void setTag(String newTag){
		// TODO: Update the value of tag to be the value in newTag (Absolute assignment)
		_tag = new String(newTag);
	}
	/**
     * Sets the sprite's 2D coords.
     * @param
     * @return void 
     */
	public void setCoords(Vector2D newV2D){
		// TODO: Update the value of v2d to be the value in newV2D (Absolute assignment)
		_vec = new Vector2D(newV2D.getX(), newV2D.getY());
	}
	/**
     * Sets the sprite's 2D coords.
     * @param
     * @return void 
     */
	public void setCoords(int x, int y){
		// TODO: Overload the setCoords method to allow another way to set the coordinates. Place the x, y integers into v2d by changing the values of v2d to hold x and y (Absolute assignment)
		_vec = new Vector2D(x, y);
	}
	
	public String toString(){
		// TODO: Create a "toString" method to test. Assume an x, y of 100, 50 and a tag of "star", this should return: [100, 50, star]
		return "["+ 
				Integer.toString(_vec.getX()) +
				", " + 
				Integer.toString(_vec.getY()) +
				", " + 
				_tag + 
				"]";
	}
}