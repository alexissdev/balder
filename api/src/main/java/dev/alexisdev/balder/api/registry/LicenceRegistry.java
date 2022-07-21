package dev.alexisdev.balder.api.registry;

import dev.alexisdev.balder.api.finder.LicenceFinder;
import dev.alexisdev.balder.api.updater.LicenceUpdater;

import java.net.InetAddress;
import java.net.UnknownHostException;

public interface LicenceRegistry {

    default void registry(String licence) throws UnknownHostException {
        registry(
                licence,
                InetAddress.getLocalHost().getHostAddress()
        );
    }

    /**
     * This function takes a licence and an address.
     *
     * @param licence The licence.
     * @param address The address of the node.
     */
    void registry(
            String licence,
            String address
    );

    /**
     * "This function returns a new instance of the DefaultLicenceRegistry class, which is a subclass of the
     * LicenceRegistry interface."
     * <p>
     * The first line of the function is the function signature. It tells us the name of the function, the type of the
     * return value, and the types of the parameters
     *
     * @param licenceFinder A LicenceFinder object that can be used to find a licence by its ID.
     * @param licenceUpdater This is the object that will be used to update the licence.
     * @return A new instance of the DefaultLicenceRegistry class.
     */
    static LicenceRegistry getDefault(
            LicenceFinder licenceFinder,
            LicenceUpdater licenceUpdater
    ) {
        return new DefaultLicenceRegistry(
                licenceFinder,
                licenceUpdater
        );
    }

}
