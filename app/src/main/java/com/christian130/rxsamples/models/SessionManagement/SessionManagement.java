package com.christian130.rxsamples.models.SessionManagement;

import com.christian130.rxsamples.models.interfaces.ImplSrvIntAll;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SessionManagement {
    public static final String BASE_URL = "https://randomuser.me";
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit plantillaRetrofit = builder.build();
    private static ImplSrvIntAll implSrvIntAll = plantillaRetrofit.create(ImplSrvIntAll.class);

    @org.jetbrains.annotations.Contract(pure = true)
    public static ImplSrvIntAll getImplSrvIntAll() {
        return implSrvIntAll;
    }

}
