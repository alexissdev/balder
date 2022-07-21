package dev.alexisdev.balder.http;

import dev.alexisdev.balder.api.buildable.Buildable;
import dev.alexisdev.balder.api.util.Validate;

public class HttpLicenceFinderBuilder implements Buildable<HttpLicenceFinder> {

    private String url;

    /**
     * This function sets the url variable to the value of the url parameter.
     *
     * @param url The URL of the licence file.
     * @return The builder object itself.
     */
    public HttpLicenceFinderBuilder setUrl(String url) {
        this.url = Validate.notNull(
                url,
                "url"
        );
        return this;
    }

    @Override
    public HttpLicenceFinder build() {
        return new HttpLicenceFinder(
                url
        );
    }
}
