package dev.alexisdev.balder.api.checker;

public interface LicenceChecker {

    /**
     * This function returns true if the licence is valid, false otherwise.
     *
     * @param licence The licence key to check.
     * @return A boolean value.
     */
    boolean isLicenced(
            String licence
    );
}
