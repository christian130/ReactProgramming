package com.christian130.rxsamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.christian130.rxsamples.model.DTO.DatosDummy;
import com.christian130.rxsamples.model.FuenteDeDatos;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable<DatosDummy> datosDummyObservable = Observable
                .fromIterable(FuenteDeDatos.llenarLista())
                .take(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        datosDummyObservable.subscribe(new Observer<DatosDummy>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DatosDummy datosDummy) {
                Log.d("imprimit",datosDummy.getDescripcion());
                Log.d("no repetido","item dummy no repetido");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
