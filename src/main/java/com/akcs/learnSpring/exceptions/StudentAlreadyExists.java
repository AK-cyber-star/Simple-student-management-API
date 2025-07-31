package com.akcs.learnSpring.exceptions;

public class StudentAlreadyExists extends RuntimeException {
    public StudentAlreadyExists(String message) {
        super(message);
    }
}
