package Exceptions;

public class TokenDoesNotExistException extends IllegalArgumentException {

    public TokenDoesNotExistException() {
        super("No Discord token avialble.");
    }
}
