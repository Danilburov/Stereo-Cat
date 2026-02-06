package Exceptions;

public class TokenDoesNotExistException extends IllegalArgumentException {

    private String message;

    public TokenDoesNotExistException(String text) {
        this.message = text;
    }
}
