package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

/**
 * The main frame for the Dot Game application.
 * It sets up the configuration panel (top), the drawing panel (center),
 * and the bottom panel which includes a control panel with buttons labeled
 * "Save", "Load", "Export PNG", and "Exit", along with a status label.
 */
public class DotGameGUI extends JFrame {

    private int numDots = 10;
    private Dot[] dots;
    private DrawingPanel drawingPanel;
    private JLabel statusLabel;

    public DotGameGUI() {
        super("Dot Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top configuration panel (for new game settings).
        JPanel configPanel = createConfigPanel();
        add(configPanel, BorderLayout.NORTH);

        // Center drawing panel (for rendering dots and lines).
        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        // Create bottom panel that will hold both the control panel and status label.
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        // Control panel (with the Save, Load, Export PNG, and Exit buttons) goes in the top part of bottomPanel.
        JPanel controlPanel = createControlPanel();
        bottomPanel.add(controlPanel, BorderLayout.NORTH);
        // Status label goes in the bottom part of bottomPanel.
        statusLabel = new JLabel("Game not started");
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates the configuration panel at the top.
     * This panel contains a text field to specify the number of dots and a "New Game" button.
     */
    private JPanel createConfigPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Number of Dots: ");
        panel.add(label);

        JTextField numDotsField = new JTextField("10", 5);
        panel.add(numDotsField);

        JButton newGameButton = new JButton("New Game");
        panel.add(newGameButton);

        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    numDots = Integer.parseInt(numDotsField.getText());
                    // Generate dots in the available drawing area.
                    dots = generateDots(numDots, drawingPanel.getWidth(), drawingPanel.getHeight());
                    drawingPanel.setDots(dots);
                    updateStatus();
                    drawingPanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DotGameGUI.this, "Please enter a valid number.");
                }
            }
        });
        return panel;
    }

    /**
     * Creates the control panel at the top of the bottom panel.
     * This panel has buttons labeled "Save", "Load", "Export PNG", and "Exit".
     */
    private JPanel createControlPanel() {
        JPanel panel = new JPanel();

        JButton saveGameButton = new JButton("Save");
        JButton loadGameButton = new JButton("Load");
        JButton exportPNGButton = new JButton("Export PNG");
        JButton exitButton = new JButton("Exit");

        // Save game state using object serialization.
        saveGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveGameState();
            }
        });

        // Load game state from file.
        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadGameState();
            }
        });

        // Export the current drawing panel to a PNG file.
        exportPNGButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawingPanel.exportToPNG("dotGameBoard.png");
            }
        });

        // Exit the application.
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(saveGameButton);
        panel.add(loadGameButton);
        panel.add(exportPNGButton);
        panel.add(exitButton);
        return panel;
    }

    /**
     * Updates the status label to show the current scores and the best (MST) score.
     */
    private void updateStatus() {
        GameState state = getCurrentGameState();
        if (state == null) return;
        double bestScore = state.computeBestScore();
        String status = String.format("Blue: %.2f, Red: %.2f, Best possible (MST): %.2f",
                state.blueScore, state.redScore, bestScore);
        statusLabel.setText(status);
    }

    /**
     * Returns the current game state by accessing the drawing panel's game state.
     */
    private GameState getCurrentGameState() {
        try {
            java.lang.reflect.Field field = DrawingPanel.class.getDeclaredField("gameState");
            field.setAccessible(true);
            return (GameState) field.get(drawingPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Generates an array of random dots positioned within the specified width and height.
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
     * Saves the current game state to a file using object serialization.
     */
    private void saveGameState() {
        GameState state = getCurrentGameState();
        if (state == null) {
            JOptionPane.showMessageDialog(this, "No game in progress to save.");
            return;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game_state.ser"))) {
            oos.writeObject(state);
            JOptionPane.showMessageDialog(this, "Game saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving game: " + e.getMessage());
        }
    }

    /**
     * Loads the game state from a file and updates the drawing panel.
     */
    private void loadGameState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game_state.ser"))) {
            GameState state = (GameState) ois.readObject();
            drawingPanel.setGameState(state);
            updateStatus();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading game: " + e.getMessage());
        }
    }

    /**
     * The application's main entry point.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DotGameGUI());
    }
}
