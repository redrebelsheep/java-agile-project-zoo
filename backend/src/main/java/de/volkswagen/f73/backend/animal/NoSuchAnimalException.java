package de.volkswagen.f73.backend.animal;

public class NoSuchAnimalException extends RuntimeException {
    public NoSuchAnimalException(String message) {
        super(message);
    }

    public NoSuchAnimalException(String message, Throwable cause) {
        super(message, cause);
    }
}
