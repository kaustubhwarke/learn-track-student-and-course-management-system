package com.airtribe.learntrack.util;

public class IdGenerator {
    private static int idCounter = 1; // Centralized counter

    public static Integer generateId() {
        return idCounter++; // Increment and return the counter
    }
}