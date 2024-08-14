
/* This class is used to give you a handy way to pass a pair of 2D coordinates as a
single object. The behavior (methods) of the class should allow for robust options 
in the future. */
package Data;

public class Vector2D {
    // Fields
    // TODO: Add private class fields to store x and y values given in
    private int xCoord, yCoord;

    // Constructor
    public Vector2D(int x, int y) {
        // TODO: Save the constructor parameters into class fields
        xCoord = x;
        yCoord = y;
    }

    // Methods
    /**
     * Returns X coordinate of a 2D vector.
     * @param   none
     * @return  int x,   a coordinate on the x-axis.
     */
    public int getX() {
        // TODO: Remove my placeholder code below (which is there to prevent an error) and replace it with returning the value of your private field x
        return xCoord;
    }

    /**
     * Returns Y coordinate of a 2D vector.
     * @param   none
     * @return  int y,   a coordinate on the y-axis.
     */
    public int getY() {
        // TODO: Remove my placeholder code below (which is there to prevent an error) and replace it with returning the value of your private field y
        return yCoord;
    }

    /**
     * Sets the X coordinate of a 2D vector.
     * @param   int x, a coordinate on the x-axis.
     */
    public void setX(int newX) {
        // TODO: Update the value of x to be the value in newX (Absolute assignment)
        xCoord = newX;
    }

    /**
     * Sets the Y coordinate of a 2D vector.
     * @param   int y, a coordinate on the y-axis.
     */
    public void setY(int newY) {
        // TODO: Update the value of y to be the value in newY (Absolute assignment)
        yCoord = newY;
    }

    /**
     * Moves the X coordinate of a 2D vector to a new location. 
     Positive values move right. Negative values move left.
     * @param   int adjustment
     */
    public void adjustX(int adjustment) {
        // TODO: Change the previous value of x by adding the adjustment to the previous value (Relative assignment) Backward adjustments can be made by passing a negative number as an adjustment
        xCoord = xCoord + adjustment;
    }

    /**
     * Moves the Y coordinate of a 2D vector to a new location. 
     Positive values move right. Negative values move left.
     * @param   int adjustment
     */
    public void adjustY(int adjustment) {
        // TODO: Change the previous value of y by adding the adjustment to the previous value (Relative assignment) Backward adjustments can be made by passing a negative number as an adjustment
        yCoord = yCoord + adjustment;
    }
}
