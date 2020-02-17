package com.christian130.rxsamples.models;

import android.util.Log;

import com.christian130.rxsamples.DTO.local.SimplePOJO;

import io.reactivex.functions.Function;

public class FunctionExtendidoString implements Function<SimplePOJO,String> {

    @Override
    public String apply(SimplePOJO simplePOJO) throws Exception {
        Log.d("TAG", "apply: doing work on thread: " + Thread.currentThread().getName());
        return simplePOJO.getDescripcion();
    }
}
