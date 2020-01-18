package com.christian130.rxjava.restclient;

import com.christian130.rxjava.iservices.ImplSrvIntAll;

import java.util.Observable;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImplementacionServiciado {
    public static final String BASE_URL="https://randomuser.me";
    private static Retrofit.Builder contructor = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = contructor.build();
    public static ImplSrvIntAll implSrvIntAll = retrofit.create(ImplSrvIntAll.class);
    public static ImplSrvIntAll getImplSrvIntAll(){
        return implSrvIntAll;
    }
}
