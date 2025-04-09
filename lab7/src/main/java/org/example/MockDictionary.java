package org.example;

/**
 * A mock implementation of a dictionary.
 * It provides a simple check to determine if a string is an acceptable word.
 */
public class MockDictionary {

    /**
     * Checks if the given string is a word.
     *
     * @param str the string to check
     * @return true if the string qualifies as a word (for example, if its length is more than 2), false otherwise
     */
    public boolean isWord(String str) {
        return str.length() > 2;
    }
}
