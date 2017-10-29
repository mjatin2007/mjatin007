
package main.se450.exceptions;

public class BadShapeException
extends Exception {
    private static final long serialVersionUID = 1;
    private String type = "Unknown";

    public BadShapeException() {
    }

    public BadShapeException(String sType) {
        this.type = sType;
    }

    @Override
    public String getMessage() {
        return "Bad Shape : " + this.type;
    }
}

