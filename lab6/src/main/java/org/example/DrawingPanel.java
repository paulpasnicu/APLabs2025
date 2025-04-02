package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * A custom JPanel for drawing the dot game board.
 * It renders dots as small circles on the panel.
 */
public class DrawingPanel extends JPanel {

    private Dot[] dots;

    /**
     * Sets the dots to be drawn on the panel.
     *
     * @param dots an array of Dot objects.
     */
    public void setDots(Dot[] dots) {
        this.dots = dots;
    }

    /**
     * Paints the component by drawing the dots.
     *
     * @param g the Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (dots != null) {
            g.setColor(Color.BLACK);
            for (Dot dot : dots) {
                g.fillOval(dot.getX() - 5, dot.getY() - 5, 10, 10);
            }
        }
    }
}
