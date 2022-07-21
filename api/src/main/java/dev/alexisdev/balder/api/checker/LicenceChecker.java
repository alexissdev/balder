package dev.alexisdev.balder.api.checker;

import dev.alexisdev.balder.api.finder.LicenceFinder;

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

    /**
     * Return the default checker of licence's
     *
     * @return A new instance of the DefaultLicenceChecker class.
     */
    static LicenceChecker getDefault(
            LicenceFinder licenceFinder
    ) {
        return DefaultLicenceChecker.create(
                licenceFinder
        );
    }
}
