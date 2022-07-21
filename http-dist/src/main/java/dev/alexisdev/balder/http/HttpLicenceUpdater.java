package dev.alexisdev.balder.http;

import dev.alexisdev.balder.api.licence.Licence;
import dev.alexisdev.balder.api.updater.LicenceUpdater;
import dev.alexisdev.balder.http.provider.GsonProvider;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

import static dev.alexisdev.balder.http.provider.HttpConnectionProvider.getHttpClient;

public class HttpLicenceUpdater
        implements LicenceUpdater {

    private static final String ERROR_FORMAT =
            "%s of Licence id= %s";

    protected final String url;

    protected HttpLicenceUpdater(
            String url
    ) {
        this.url = url;
    }

    @Override
    public void update(
            Licence licence
    ) {
        try {
            HttpPut put = new HttpPut(url);
            put.setEntity(
                    new StringEntity(
                            GsonProvider.getGson().toJson(licence),
                            ContentType.APPLICATION_JSON
                    )
            );
            HttpResponse httpResponse = getHttpClient().execute(put);

            switch (httpResponse.getStatusLine().getStatusCode()) {
                default: break;
                case 400: throw new IllegalArgumentException(
                        String.format(
                                ERROR_FORMAT,
                                "Bad Request",
                                licence.getId()
                        )
                );
                case 403: throw new IllegalArgumentException(
                        String.format(
                                ERROR_FORMAT,
                                "Unauthorized",
                                licence.getId()
                        )
                );
                case 404: throw new IllegalArgumentException(
                        String.format(
                                ERROR_FORMAT,
                                "Not Found",
                                licence.getId()
                        )
                );
                case 500: throw new IllegalArgumentException(
                        String.format(
                                ERROR_FORMAT,
                                "Internal Server Error",
                                licence.getId()
                        )
                );
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
