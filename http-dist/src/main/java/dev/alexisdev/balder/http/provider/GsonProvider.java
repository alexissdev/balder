package dev.alexisdev.balder.http.provider;

import com.google.gson.Gson;

public class GsonProvider {

    private static Gson GSON;

    public static Gson getGson() {
        if (GSON == null) {
            GSON = new Gson();

            return getGson();
        }

        return GSON;
    }
}
