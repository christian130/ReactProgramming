package com.christian130.rxsamples05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.christian130.rxsamples05.DTO.Tarea;
import com.christian130.rxsamples05.model.OrigenDelRecurso;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable<Tarea> tareaObservable = Observable
                .fromIterable(OrigenDelRecurso.crearLista())
                .filter(new Predicate<Tarea>() {
                    @Override
                    public boolean test(Tarea tarea) throws Exception {
                        return tarea.isCompletadoBooleano();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        tareaObservable.subscribe(new Observer<Tarea>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Tarea tarea) {
                Log.d("tarea","esta tarea tiene el booleano en true");

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
