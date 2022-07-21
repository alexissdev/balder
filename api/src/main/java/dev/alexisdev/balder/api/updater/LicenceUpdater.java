package dev.alexisdev.balder.api.updater;

import dev.alexisdev.balder.api.licence.Licence;

public interface LicenceUpdater {

    /**
     * It updates the licence.
     *
     * @param licence The licence object to be updated.
     */

    void update(Licence licence);
}
