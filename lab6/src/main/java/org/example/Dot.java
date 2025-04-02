package org.example;

/**
 * Represents a dot with x and y coordinates.
 */
public class Dot {
    private int x;
    private int y;

    /**
     * Constructs a Dot at the specified (x, y) position.
     *
     * @param x the x-coordinate of the dot.
     * @param y the y-coordinate of the dot.
     */
    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the dot.
     *
     * @return the x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the dot.
     *
     * @return the y-coordinate.
     */
    public int getY() {
        return y;
    }
}
