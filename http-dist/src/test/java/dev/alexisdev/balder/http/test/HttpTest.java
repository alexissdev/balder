package dev.alexisdev.balder.http.test;

import dev.alexisdev.balder.api.finder.LicenceFinder;
import dev.alexisdev.balder.api.registry.LicenceRegistry;
import dev.alexisdev.balder.api.updater.LicenceUpdater;
import dev.alexisdev.balder.http.HttpLicenceFinder;
import dev.alexisdev.balder.http.HttpLicenceUpdater;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;
import java.util.UUID;

public class HttpTest {

    private static final String URL = "http://localhost:3000";

    @Test
    public void test() {
        LicenceRegistry registry = provideRegistry(
                true
        );

        //TODO: Your licence?
        String licence = UUID.randomUUID().toString();

        try {
            registry.registry(
                    licence
            );
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unknown host", e);
        }
    }

    /**
     * "If custom is true, return a custom licence registry, otherwise return the default licence registry."
     * <p>
     * The custom licence registry is a class that we've written ourselves. It's a subclass of the default licence
     * registry. It's a bit like a child class that inherits from a parent class
     *
     * @param custom If true, the custom registry will be used.
     * @return A LicenceRegistry object.
     */
    private LicenceRegistry provideRegistry(boolean custom) {
        if (custom) {
            return new CustomLicenceRegistry(
                    provideFinder(),
                    provideUpdater()
            );
        }

        return LicenceRegistry.getDefault(
                provideFinder(),
                provideUpdater()
        );

    }

    /**
     * `HttpLicenceFinder.builder().setUrl(URL).build()`
     *
     * @return A LicenceFinder object.
     */
    private LicenceFinder provideFinder() {
        return HttpLicenceFinder.builder()
                .setUrl(URL)
                .build();
    }

    /**
     * It creates a licence updater that will download the licence from the URL provided
     *
     * @return A LicenceUpdater object.
     */
    private LicenceUpdater provideUpdater() {
        return HttpLicenceUpdater.builder()
                .setUrl(URL)
                .build();
    }

}
