package dev.alexisdev.balder.http;

import com.google.gson.Gson;
import dev.alexisdev.balder.api.finder.LicenceFinder;
import dev.alexisdev.balder.api.licence.Licence;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpLicenceFinder
        implements LicenceFinder {

    protected static final CloseableHttpClient HTTP_CLIENT
            = HttpClients.createDefault();
    protected static final Gson GSON =
            new Gson();
    protected final String requestWebsite;

    protected HttpLicenceFinder(
            String requestWebsite
    ) {
        this.requestWebsite = requestWebsite;
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
            HttpResponse httpResponse = HTTP_CLIENT.execute(get);

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
     * If the response has an entity, return the entity as a string, otherwise return null.
     *
     * @return A lambda expression that takes a response and returns a string.
     */
    private ResponseHandler<String> handleResponse() {
        return response -> {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        };
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
        if (rawWebsite.endsWith("/")) {
            return rawWebsite + key;
        }

        return rawWebsite + "/" + key;
    }
}