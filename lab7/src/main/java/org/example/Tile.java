package org.example;

/**
 * Represents a letter tile with an associated point value.
 */
public class Tile {
    private final char letter;
    private final int points;

    /**
     * Constructs a Tile with the specified letter and point value.
     *
     * @param letter the character on the tile
     * @param points the point value of the tile (typically between 1 and 10)
     */
    public Tile(char letter, int points) {
        this.letter = letter;
        this.points = points;
    }

    /**
     * Gets the letter on the tile.
     *
     * @return the letter
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Gets the point value of the tile.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns a string representation of the Tile.
     *
     * @return a string in the format "letter(points)"
     */
    @Override
    public String toString() {
        return letter + "(" + points + ")";
    }
}

