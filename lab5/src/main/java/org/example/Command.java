package org.example;

/**
 * Defines a command interface for executing operations.
 */
public interface Command {
    /**
     * Executes the command with the provided arguments.
     *
     * @param args the arguments for the command
     */
    void execute(String[] args);
}
