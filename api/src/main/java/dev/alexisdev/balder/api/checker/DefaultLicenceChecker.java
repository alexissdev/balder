package dev.alexisdev.balder.api.checker;

import dev.alexisdev.balder.api.finder.LicenceFinder;
import dev.alexisdev.balder.api.util.Validate;

public class DefaultLicenceChecker
        implements LicenceChecker {

    protected final LicenceFinder licenceFinder;

    protected DefaultLicenceChecker(
            LicenceFinder licenceFinder
    ) {
        this.licenceFinder = Validate.notNull(
                licenceFinder, "licenceFinder"
        );
    }

    @Override
    public boolean isLicenced(
            String licence
    ) {
        return licenceFinder.find(licence)
                != null;
    }

    public static LicenceChecker create(
            LicenceFinder licenceFinder
    ) {
        return new DefaultLicenceChecker(licenceFinder);
    }
}
