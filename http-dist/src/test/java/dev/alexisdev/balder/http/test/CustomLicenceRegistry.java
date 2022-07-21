package dev.alexisdev.balder.http.test;

import dev.alexisdev.balder.api.finder.LicenceFinder;
import dev.alexisdev.balder.api.licence.Licence;
import dev.alexisdev.balder.api.registry.DefaultLicenceRegistry;
import dev.alexisdev.balder.api.updater.LicenceUpdater;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class CustomLicenceRegistry
        extends DefaultLicenceRegistry {

    public CustomLicenceRegistry(
            LicenceFinder licenceFinder,
            LicenceUpdater licenceUpdater
    ) {
        super(licenceFinder, licenceUpdater);
    }

    @Override
    protected void licenceUsedAction(Licence licence) {
        try {
            String serverIp = InetAddress.getLocalHost().getHostAddress();
            String licenceAddress = licence.getAddress();

            if (licenceAddress != null && licenceAddress.equals(serverIp)) {
                return;
            }

            throw new IllegalArgumentException("Licence is used");
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unknown host", e);
        }
    }
}
