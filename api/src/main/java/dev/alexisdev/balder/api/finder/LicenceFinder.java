package dev.alexisdev.balder.api.finder;

import dev.alexisdev.balder.api.licence.Licence;

public interface LicenceFinder {

    /**
     * Find a licence by its key.
     *
     * @param licence The licence key to find.
     * @return A licence model.
     */
    Licence find(
            String licence
    );
}
