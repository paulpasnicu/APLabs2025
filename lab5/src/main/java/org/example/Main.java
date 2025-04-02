package org.example;

/**
 * Main application entry point for the Image Manager.
 */
public class Main {
    /**
     * Starts the image management shell.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ImageRepository repository = new ImageRepository();
        Shell shell = new Shell(repository);
        shell.run();
    }
}
