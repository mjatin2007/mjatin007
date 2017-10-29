
package main.se450.exceptions;

public class BadStrategyException
extends Exception {
    private static final long serialVersionUID = 1;
    private String strategy = "Unknown";

    public BadStrategyException() {
    }

    public BadStrategyException(String sStrategy) {
        this.strategy = sStrategy;
    }

    @Override
    public String getMessage() {
        return "Bad Strategy : " + this.strategy;
    }
}

