package Exceptions;

public class DuplicateItemException extends Exception {
    public DuplicateItemException() {
        super("Item duplicado...");
    }
}
