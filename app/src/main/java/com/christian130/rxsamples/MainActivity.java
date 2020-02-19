package com.christian130.rxsamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity {
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compositeDisposable = new CompositeDisposable();
        Observable<Unit> integerObservable = RxView.clicks(findViewById(R.id.button2));
        Observable<List<Integer>> listObservable = integerObservable
                .map(new Function<Unit, Integer>() { // convert the detected clicks to an integer
                    @Override
                    public Integer apply(Unit unit) throws Exception {
                        return 1;
                    }
                })//you are not allowed to subcribeOn any method to any Schedulers (computational or whatsoever)
                .buffer(1, TimeUnit.SECONDS) // capture all the clicks during a 4 second interval
                .observeOn(AndroidSchedulers.mainThread());
        listObservable
                .subscribe(new subscribtion());

// do not subscribeOn to anything using the library suplied to detects clicks


    }


    public class subscribtion implements Observer<List<Integer>> {

        @Override
        public void onSubscribe(Disposable d) {
            compositeDisposable.add(d);
        }

        @Override
        public void onNext(List<Integer> integers) {
            Log.d("clicks", "clicks " + String.valueOf(integers.size()));

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
