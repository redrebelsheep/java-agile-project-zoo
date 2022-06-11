package de.volkswagen.f73.backend.animal;

public class NoSuchAnimalException extends RuntimeException {
    public NoSuchAnimalException(String message) {
        super(message);
    }
}
