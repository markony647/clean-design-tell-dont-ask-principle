package com.epam.cleandesign.exceptions;

public class WrongDataException extends RuntimeException {
    public WrongDataException() {
        super("Customer not found!");
    }
}
