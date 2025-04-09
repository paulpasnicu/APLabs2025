package org.example;

import java.util.*;

/**
 * Represents the game board where players submit their words.
 * It maintains a list of words that have been added.
 */
public class Board {
    private final List<String> words = new ArrayList<>();

    /**
     * Adds a word submitted by a player to the board.
     * This method is synchronized to ensure safe access when multiple threads add words concurrently.
     *
     * @param player the player who submitted the word
     * @param word   the word to add to the board
     */
    public synchronized void addWord(Player player, String word) {
        words.add(word);
        System.out.println(player.getName() + " submitted: " + word);
    }

    /**
     * Returns a string representation of all words on the board.
     *
     * @return a string representing the list of words
     */
    @Override
    public String toString() {
        return words.toString();
    }
}
