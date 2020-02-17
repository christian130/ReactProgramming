package com.christian130.rxsamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.christian130.rxsamples.DTO.local.SimplePOJO;
import com.christian130.rxsamples.models.FunctionExtendidoPOJO;
import com.christian130.rxsamples.models.FunctionExtendidoString;
import com.christian130.rxsamples.models.FuenteDeDatos;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private void cambiarString() {
        Observable<String> simplePOJOObservable = Observable
                .fromIterable(FuenteDeDatos.getAllDatosCableados())
                .subscribeOn(Schedulers.io())
                .map(new FunctionExtendidoString())
                .observeOn(AndroidSchedulers.mainThread());
        simplePOJOObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d("hilo", "estoy en el hilo de ejecucion" + Thread.currentThread().getName());
                Log.d("el string filtrado es", s);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void cambiarObjetos() {
        Observable<SimplePOJO> simplePOJOObservable = Observable
                .fromIterable(FuenteDeDatos.getAllDatosCableados())
                .subscribeOn(Schedulers.io())
                .map(new FunctionExtendidoPOJO())
                .observeOn(AndroidSchedulers.mainThread());
        simplePOJOObservable.subscribe(new Observer<SimplePOJO>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SimplePOJO simplePOJO) {
                Log.d("hilo ejecutor",Thread.currentThread().getName());
                Log.d("simplePOJO",String.valueOf(simplePOJO.isConsumaton()));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarString();
            }
        });
        Button cambiarObjetos = (Button) findViewById(R.id.button2);
        cambiarObjetos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarObjetos();
            }
        });

    }
}
