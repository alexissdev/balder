package dev.alexisdev.balder.http;

import dev.alexisdev.balder.api.finder.LicenceFinder;
import dev.alexisdev.balder.api.licence.Licence;
import dev.alexisdev.balder.http.provider.GsonProvider;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static dev.alexisdev.balder.http.provider.HttpConnectionProvider.*;
import static dev.alexisdev.balder.http.util.HttpUtil.LICENCE_FORMAT;
import static dev.alexisdev.balder.http.util.HttpUtil.handleResponse;

public class HttpLicenceFinder
        implements LicenceFinder {
    protected final String requestWebsite;

    protected HttpLicenceFinder(
            String requestWebsite
    ) {
        this.requestWebsite = requestWebsite;
    }

    /**
     * > The `builder()` function returns a new instance of the `HttpLicenceFinderBuilder` class
     *
     * @return A new instance of the HttpLicenceFinderBuilder class.
     */
    public static HttpLicenceFinderBuilder builder() {
        return new HttpLicenceFinderBuilder();
    }

    @Override
    public @Nullable Licence find(
            String licence
    ) {
        try {
            URI url = new URL(String.format(
                    LICENCE_FORMAT,
                    requestWebsite,
                    licence
            )).toURI();
            ResponseHandler<String> responseHandler = handleResponse();
            HttpGet get = new HttpGet(url);
            HttpResponse httpResponse = getHttpClient().execute(get);

            String response = responseHandler.handleResponse(httpResponse);

            return GsonProvider.getGson().fromJson(
                    response,
                    Licence.class
            );
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
