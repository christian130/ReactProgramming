package com.christian130.rxsamples.models;

import android.util.Log;

import com.christian130.rxsamples.DTO.local.SimplePOJO;

import io.reactivex.functions.Function;

public class FunctionExtendidoPOJO implements Function<SimplePOJO,SimplePOJO> {
    @Override
    public SimplePOJO apply(SimplePOJO simplePOJO) throws Exception {
        Log.d("etiqueta", "apply: doing work on thread: " + Thread.currentThread().getName());
        simplePOJO.setConsumaton(false);
        return simplePOJO;
    }
}
