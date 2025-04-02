package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * The main frame for the Dot Game application.
 * It sets up the configuration panel at the top, the drawing panel in the center,
 * and the control panel at the bottom.
 */
public class DotGameGUI extends JFrame {

    private int numDots = 10;
    private Dot[] dots;
    private DrawingPanel drawingPanel;

    /**
     * Constructs the main frame and initializes the GUI components.
     */
    public DotGameGUI() {
        super("Dot Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel configPanel = createConfigPanel();
        add(configPanel, BorderLayout.NORTH);

        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);

        setSize(600, 400);
        setLocationRelativeTo(null); // Center the frame on screen
        setVisible(true);
    }

    /**
     * Creates the configuration panel for setting game parameters.
     *
     * @return a JPanel containing configuration components.
     */
    private JPanel createConfigPanel() {
        JPanel configPanel = new JPanel();
        JLabel label = new JLabel("Number of Dots: ");
        configPanel.add(label);

        JTextField numDotsField = new JTextField("10", 5);
        configPanel.add(numDotsField);

        JButton newGameButton = new JButton("New Game");
        configPanel.add(newGameButton);

        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    numDots = Integer.parseInt(numDotsField.getText());
                    dots = generateDots(numDots, drawingPanel.getWidth(), drawingPanel.getHeight());
                    drawingPanel.setDots(dots);
                    drawingPanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DotGameGUI.this, "Please enter a valid number.");
                }
            }
        });

        return configPanel;
    }

    /**
     * Creates the control panel containing buttons to manage game state.
     *
     * @return a JPanel containing control buttons.
     */
    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        JButton exitButton = new JButton("Exit");

        controlPanel.add(loadButton);
        controlPanel.add(saveButton);
        controlPanel.add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return controlPanel;
    }

    /**
     * Generates an array of dots with random positions within the specified width and height.
     *
     * @param numDots the number of dots to generate.
     * @param width   the width of the drawing area.
     * @param height  the height of the drawing area.
     * @return an array of Dot objects.
     */
    private Dot[] generateDots(int numDots, int width, int height) {
        Random rand = new Random();
        Dot[] dots = new Dot[numDots];
        for (int i = 0; i < numDots; i++) {
            int x = rand.nextInt(Math.max(width, 1));
            int y = rand.nextInt(Math.max(height, 1));
            dots[i] = new Dot(x, y);
        }
        return dots;
    }

    /**
     * The application's entry point.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DotGameGUI());
    }
}
