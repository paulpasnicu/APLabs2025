package org.example;

/**
 * Custom exception class used to indicate errors in repository operations.
 */
public class RepositoryException extends Exception {

    /**
     * Constructs a new RepositoryException with the specified detail message.
     *
     * @param message the detail message
     */
    public RepositoryException(String message) {
        super(message);
    }
}
