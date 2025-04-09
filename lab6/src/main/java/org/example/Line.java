package org.example;

import java.awt.Color;
import java.io.Serializable;

/**
 * Represents a line connecting two dots along with its color.
 */
public class Line implements Serializable {
    private static final long serialVersionUID = 1L;
    private Dot start;
    private Dot end;
    private Color color;

    /**
     * Constructs a Line with the specified endpoints and color.
     *
     * @param start the starting dot.
     * @param end   the ending dot.
     * @param color the color of the line.
     */
    public Line(Dot start, Dot end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public Dot getStart() {
        return start;
    }

    public Dot getEnd() {
        return end;
    }

    public Color getColor() {
        return color;
    }
}
