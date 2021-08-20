package com.recipes.dewordy.rest;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Paulstanley on 11/21/15.x
 */
public class RestClient {

    private static Api REST_CLIENT;
    private static String ROOT =
            "http://www.schuleofficial.com/";
//            "http://192.168.1.101:80/";
//            "http://172.20.10.2:80/";
//              "http://192.168.1.104:80/";
    static {
        setupRestClient();
    }

    private RestClient() {}

    public static Api get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(Api.
                class);
    }
}
