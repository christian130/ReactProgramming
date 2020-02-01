package com.christian130.rxsamples.models.interfaces;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface ImplSrvIntAll {
    @GET("/api")
    Flowable<ResponseBody> makeRequest();
}
