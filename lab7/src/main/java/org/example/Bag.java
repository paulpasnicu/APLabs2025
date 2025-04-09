package org.example;

import java.util.*;

/**
 * Represents a bag containing letter tiles.
 * It supports concurrent extraction of tiles.
 */
public class Bag {
    private final List<Tile> tiles = new LinkedList<>();
    private final Random random = new Random();

    /**
     * Constructs a Bag by adding 10 tiles for each letter (a to z).
     * Each tile is assigned a random point value between 1 and 10.
     */
    public Bag() {
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < 10; i++) {
                tiles.add(new Tile(c, random.nextInt(10) + 1));
            }
        }
        Collections.shuffle(tiles);
    }

    /**
     * Extracts a specified number of tiles from the bag.
     * This method is synchronized to ensure thread-safe extraction.
     *
     * @param howMany the number of tiles to extract
     * @return a list of extracted tiles; may be less than requested if the bag is empty
     */
    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany && !tiles.isEmpty(); i++) {
            extracted.add(tiles.remove(0));
        }
        return extracted;
    }

    /**
     * Checks if the bag is empty.
     *
     * @return true if no tiles remain in the bag, false otherwise
     */
    public synchronized boolean isEmpty() {
        return tiles.isEmpty();
    }
}
