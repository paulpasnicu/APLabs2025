package org.example;

import java.util.*;

/**
 * The main class representing the word game.
 * It sets up the bag, board, dictionary, and player threads.
 */
public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final MockDictionary dictionary = new MockDictionary();
    private final List<Player> players = new ArrayList<>();

    /**
     * Gets the bag of tiles used in the game.
     *
     * @return the bag
     */
    public Bag getBag() {
        return bag;
    }

    /**
     * Gets the game board.
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the dictionary used for word validation.
     *
     * @return the dictionary
     */
    public MockDictionary getDictionary() {
        return dictionary;
    }

    /**
     * Adds a player to the game and assigns this game to the player.
     *
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    /**
     * Starts the game by launching a new thread for each player.
     */
    public void play() {
        for (Player player : players) {
            new Thread(player).start();
        }
    }

    /**
     * The main method to run the game.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer(new Player("Alice"));
        game.addPlayer(new Player("Bob"));
        game.addPlayer(new Player("Charlie"));
        game.play();
    }
}

