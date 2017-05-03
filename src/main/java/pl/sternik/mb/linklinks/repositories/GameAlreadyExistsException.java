package pl.sternik.mb.linklinks.repositories;

public class GameAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public GameAlreadyExistsException() {     
    }

    public GameAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public GameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameAlreadyExistsException(String message) {
        super(message);
    }

    public GameAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    
}
