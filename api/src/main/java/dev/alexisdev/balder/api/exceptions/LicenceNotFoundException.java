package dev.alexisdev.balder.api.exceptions;

public class LicenceNotFoundException
        extends RuntimeException {

    public LicenceNotFoundException() {
    }

    public LicenceNotFoundException(
            String message
    ) {
        super(message);
    }

    public LicenceNotFoundException(
            String message,
            Throwable throwable
    ) {
        super(message, throwable);
    }

    public LicenceNotFoundException(
            Throwable throwable
    ) {
        super(throwable);
    }
}
