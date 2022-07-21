package dev.alexisdev.balder.api.util;

public class Validate {

    public Validate() {
        throw new UnsupportedOperationException();
    }

    /**
     * If the object is null, throw a NullPointerException with the given message.
     *
     * @param object The object to check.
     * @param message The message to be displayed if the object is null.
     * @return The object that was passed in.
     */
    public static <T> T notNull(
            T object,
            String message
    ) {
        if (object == null) {
            throw new NullPointerException(message);
        }

        return object;
    }
}
