package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * A custom JPanel for drawing the game board.
 * It uses retained mode by storing dots and lines in the game state.
 */
public class DrawingPanel extends JPanel {

    private GameState gameState;
    // Used for mouse interaction: storing the first dot selected (or drag start).
    private Dot selectedDot = null;

    public DrawingPanel() {
        // Set a default preferred size.
        setPreferredSize(new Dimension(600, 400));
        // Enable mouse events for click & drag.
        MyMouseListener listener = new MyMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    /**
     * Sets a new array of dots and creates a new game state.
     * Clears any existing game state.
     * @param dots the dots to be drawn.
     */
    public void setDots(Dot[] dots) {
        this.gameState = new GameState(dots);
    }

    /**
     * Restores the game state (used when loading a game).
     * @param state the game state to restore.
     */
    public void setGameState(GameState state) {
        this.gameState = state;
        repaint();
    }

    /**
     * Exports the current drawing as a PNG image.
     * @param fileName the file name for the PNG image.
     */
    public void exportToPNG(String fileName) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        // Paint the panel onto the image.
        printAll(g2);
        g2.dispose();
        try {
            javax.imageio.ImageIO.write(image, "png", new java.io.File(fileName));
            JOptionPane.showMessageDialog(this, "Image exported to " + fileName);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error exporting image: " + ex.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameState == null) {
            return;
        }
        // Draw lines first.
        for (Line line : gameState.lines) {
            g.setColor(line.getColor());
            Dot start = line.getStart();
            Dot end = line.getEnd();
            g.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
        }
        // Draw dots.
        if (gameState.dots != null) {
            g.setColor(Color.BLACK);
            for (Dot dot : gameState.dots) {
                // Center the dot (draw a filled circle).
                g.fillOval(dot.getX() - 5, dot.getY() - 5, 10, 10);
            }
        }
    }

    /**
     * Private inner class to handle mouse events (click and drag).
     */
    private class MyMouseListener extends MouseAdapter {
        // For dragging, we show a temporary line.
        private Point dragEnd = null;

        @Override
        public void mousePressed(MouseEvent e) {
            Dot dot = getDotAtPosition(e.getX(), e.getY());
            if (dot != null) {
                selectedDot = dot;
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            dragEnd = e.getPoint();
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Dot dot = getDotAtPosition(e.getX(), e.getY());
            if (selectedDot != null && dot != null && selectedDot != dot) {
                // Create a new line between selectedDot and this dot.
                Color playerColor = (gameState.blueTurn ? Color.BLUE : Color.RED);
                Line newLine = new Line(selectedDot, dot, playerColor);
                gameState.addLine(newLine);
            }
            selectedDot = null;
            dragEnd = null;
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Alternatively, allow click-to-select: if no drag, use two clicks.
            if (e.getClickCount() == 1) {
                Dot dot = getDotAtPosition(e.getX(), e.getY());
                if (dot != null) {
                    if (selectedDot == null) {
                        selectedDot = dot;
                    } else if (selectedDot != dot) {
                        Color playerColor = (gameState.blueTurn ? Color.BLUE : Color.RED);
                        Line newLine = new Line(selectedDot, dot, playerColor);
                        gameState.addLine(newLine);
                        selectedDot = null;
                        repaint();
                    }
                }
            }
        }

        /**
         * Returns the dot which is near the specified (x,y) coordinate.
         * @param x the x-coordinate.
         * @param y the y-coordinate.
         * @return the dot if found within a threshold distance; otherwise null.
         */
        private Dot getDotAtPosition(int x, int y) {
            if (gameState == null || gameState.dots == null) {
                return null;
            }
            for (Dot dot : gameState.dots) {
                // Compute distance from click to dot center.
                double dist = Math.hypot(dot.getX() - x, dot.getY() - y);
                if (dist <= 8) {  // threshold radius for selection
                    return dot;
                }
            }
            return null;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Optionally, if dragging, draw the temporary line
        if (selectedDot != null) {
            Point mouse = getMousePosition();
            if (mouse != null) {
                g.setColor(Color.GRAY);
                g.drawLine(selectedDot.getX(), selectedDot.getY(), mouse.x, mouse.y);
            }
        }
    }
}
