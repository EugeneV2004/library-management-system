package by.eugene.library.exceptions;

public class BookOwnerNotFoundException extends RuntimeException {
    public BookOwnerNotFoundException(String message) {
        super(message);
    }
}
