package com.christian130.rxjava.iservices;

import io.reactivex.Observable;


import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface ImplSrvIntAll {
    @GET("api/")
    Observable<ResponseBody> getQuery();
}
