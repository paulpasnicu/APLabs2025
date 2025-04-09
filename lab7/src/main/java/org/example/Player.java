package org.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a player in the word game.
 * Each player runs as a separate thread and tries to extract tiles, form words, and submit them.
 */
public class Player implements Runnable {
    private String name;
    private Game game;

    /**
     * Constructs a Player with the specified name.
     *
     * @param name the player's name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Associates a game with this player.
     *
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Attempts to extract tiles, form a word, and submit it to the board.
     * If the extracted tiles form a valid word (per the dictionary), the word is added to the board.
     * Afterwards, new tiles are extracted based on the length of the submitted word.
     * If no valid word is formed, the player discards the tiles.
     *
     * @return false if no tiles were extracted (i.e., the bag is empty); true otherwise.
     */
    private boolean submitWord() {
        List<Tile> hand = game.getBag().extractTiles(7);
        if (hand.isEmpty()) {
            return false;
        }

        // Create a word from the tiles (for simplicity, just concatenate the letters)
        String word = hand.stream()
                .map(tile -> String.valueOf(tile.getLetter()))
                .collect(Collectors.joining());

        // Check if the word is acceptable according to the dictionary
        if (game.getDictionary().isWord(word)) {
            game.getBoard().addWord(this, word);
            // Try to extract new tiles equal to the word length after successful submission
            List<Tile> newTiles = game.getBag().extractTiles(word.length());
        } else {
            System.out.println(name + " couldn't form a valid word and discarded tiles.");
        }

        // Pause for a short period to simulate processing time
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return true;
    }

    /**
     * The main execution method for the player thread.
     * The player continues to submit words until the bag runs out of tiles.
     */
    @Override
    public void run() {
        while (!game.getBag().isEmpty()) {
            if (!submitWord()) {
                break;
            }
        }
    }
}
