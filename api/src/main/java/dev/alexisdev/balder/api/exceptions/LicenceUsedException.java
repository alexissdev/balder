package dev.alexisdev.balder.api.exceptions;

public class LicenceUsedException
        extends RuntimeException {

    public LicenceUsedException() {
        super();
    }

    public LicenceUsedException(
            String message
    ) {
        super(message);
    }

    public LicenceUsedException(
            String message,
            Throwable throwable
    ) {
        super(message, throwable);
    }

    public LicenceUsedException(
            Throwable throwable
    ) {
        super(throwable);
    }
}
