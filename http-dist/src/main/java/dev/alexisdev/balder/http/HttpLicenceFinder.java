package dev.alexisdev.balder.http;

import com.google.gson.Gson;
import dev.alexisdev.balder.api.finder.LicenceFinder;
import dev.alexisdev.balder.api.licence.Licence;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
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

    protected static final Gson GSON =
            new Gson();
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
            URI url = new URL(getRequestWebsite(
                    requestWebsite,
                    licence
            )).toURI();
            ResponseHandler<String> responseHandler = handleResponse();
            HttpGet get = new HttpGet(url);
            HttpResponse httpResponse = getHttpClient().execute(get);

            String response = responseHandler.handleResponse(httpResponse);

            return GSON.fromJson(
                    response,
                    Licence.class
            );
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * If the website ends with a slash, append the key to the website. Otherwise, append a slash and the key to the
     * website
     *
     * @param rawWebsite The website you want to send the request to.
     * @param key The key to be used to generate the request.
     * @return The website with the key appended to the end.
     */
    private String getRequestWebsite(
            String rawWebsite,
            String key
    ) {
        return String.format(
                LICENCE_FORMAT,
                rawWebsite,
                key
        );
    }
}
