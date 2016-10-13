package com.roix.testtaskinstagram;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by u5 on 10/10/16.
 */
public class ServiceGenerator {
    public static InstagramApiModel createApiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(InstagramApiModel.class);
    }
}
