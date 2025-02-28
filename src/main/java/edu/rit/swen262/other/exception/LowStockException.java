package edu.rit.swen262.other.exception;

public class LowStockException extends Exception {
    public LowStockException(String messageString) {
        super(messageString);
    }
}