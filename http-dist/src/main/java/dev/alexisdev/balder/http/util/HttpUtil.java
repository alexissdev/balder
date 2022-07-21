package dev.alexisdev.balder.http.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

    public HttpUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * If the response has an entity, return the entity as a string, otherwise return null.
     *
     * @return A lambda expression that takes a response and returns a string.
     */
    public static ResponseHandler<String> handleResponse() {
        return response -> {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        };
    }

}
