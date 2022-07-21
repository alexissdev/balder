package dev.alexisdev.balder.api.buildable;

public interface Buildable<T> {

    /**
     * Builds an instance of T.
     *
     * @return A new instance of the {@link T}.
     */

    T build();
}
