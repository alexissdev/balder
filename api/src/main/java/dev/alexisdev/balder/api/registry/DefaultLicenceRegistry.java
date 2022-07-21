package dev.alexisdev.balder.api.registry;

import dev.alexisdev.balder.api.exceptions.LicenceNotFoundException;
import dev.alexisdev.balder.api.exceptions.LicenceUsedException;
import dev.alexisdev.balder.api.finder.LicenceFinder;
import dev.alexisdev.balder.api.licence.Licence;
import dev.alexisdev.balder.api.updater.LicenceUpdater;
import dev.alexisdev.balder.api.util.Validate;

public class DefaultLicenceRegistry
        implements LicenceRegistry {

    protected final LicenceFinder licenceFinder;
    protected final LicenceUpdater licenceUpdater;

    protected DefaultLicenceRegistry(
            LicenceFinder licenceFinder,
            LicenceUpdater licenceUpdater
    ) {
        this.licenceFinder = Validate.notNull(
                licenceFinder, "licenceFinder"
        );
        this.licenceUpdater = licenceUpdater;
    }

    @Override
    public void registry(
            String licence,
            String address
    ) {
        Licence licenceModel = licenceFinder.find(
                licence
        );

        if (licenceModel == null) {
            throw new LicenceNotFoundException(
                    "The licence of key " + licence + " was not found."
            );
        }

        if (licenceModel.getAddress() != null) {
            licenceUsedAction(
                    licenceModel
            );
            return;
        }

        licenceModel.setAddress(
                address
        );

        licenceUpdater.update(
                licenceModel
        );
    }


    /**
     * This will be the method called in case the license is already used,
     * in case you want a customized action when this event happens,
     * it is recommended to extend the class of {@link DefaultLicenceRegistry}
     * and modify the method of {@link DefaultLicenceRegistry#licenceUsedAction(Licence)}
     */
    protected void licenceUsedAction(
            Licence licence
    ) {
        throw new LicenceUsedException(
                "The licence of key " + licence.getId() + " is already used."
        );
    }
}
