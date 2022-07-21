package dev.alexisdev.balder.http.provider;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpConnectionProvider {

    private static CloseableHttpClient HTTP_CLIENT;

    public static CloseableHttpClient getHttpClient() {
        if (HTTP_CLIENT == null) {
            HTTP_CLIENT = HttpClients.createDefault();

            return getHttpClient();
        }

        return HTTP_CLIENT;
    }
}
