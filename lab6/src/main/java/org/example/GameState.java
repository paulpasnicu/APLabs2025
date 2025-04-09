package org.example;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the current game state, including dots, lines and scores.
 */
public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    public Dot[] dots;
    public List<Line> lines;
    // Current turn: true for Blue's turn, false for Red's turn.
    public boolean blueTurn;

    // Player scores: sum of line lengths for each.
    public double blueScore;
    public double redScore;

    public GameState(Dot[] dots) {
        this.dots = dots;
        this.lines = new ArrayList<>();
        this.blueTurn = true;
        this.blueScore = 0;
        this.redScore = 0;
    }

    /**
     * Adds a new line to the game state. Updates the appropriate player's score.
     * @param line the new line to add.
     */
    public void addLine(Line line) {
        lines.add(line);
        double len = Math.hypot(
                line.getStart().getX() - line.getEnd().getX(),
                line.getStart().getY() - line.getEnd().getY()
        );
        if (blueTurn) {
            blueScore += len;
        } else {
            redScore += len;
        }
        // Toggle turn
        blueTurn = !blueTurn;
    }

    /**
     * Computes the optimal score based on the MST (minimum spanning tree) of all dots.
     * Uses a simple Prim's algorithm.
     * @return the MST total length.
     */
    public double computeBestScore() {
        if (dots == null || dots.length == 0) {
            return 0;
        }
        int n = dots.length;
        boolean[] inTree = new boolean[n];
        double[] minDist = new double[n];
        for (int i = 0; i < n; i++) {
            minDist[i] = Double.MAX_VALUE;
        }
        minDist[0] = 0;
        double mstCost = 0;

        for (int i = 0; i < n; i++) {
            // Find the dot with the smallest distance not in MST yet.
            int u = -1;
            for (int j = 0; j < n; j++) {
                if (!inTree[j] && (u == -1 || minDist[j] < minDist[u])) {
                    u = j;
                }
            }
            inTree[u] = true;
            mstCost += minDist[u];
            // update distances for remaining dots
            for (int v = 0; v < n; v++) {
                if (!inTree[v]) {
                    double dist = Math.hypot(
                            dots[u].getX() - dots[v].getX(),
                            dots[u].getY() - dots[v].getY());
                    if (dist < minDist[v]) {
                        minDist[v] = dist;
                    }
                }
            }
        }
        return mstCost;
    }
}
