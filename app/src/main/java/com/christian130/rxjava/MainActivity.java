package com.christian130.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.christian130.rxjava.models.GenericViewModelImportant;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GenericViewModelImportant viewModelmain = ViewModelProviders.of(this).get(GenericViewModelImportant.class);
        try {
            viewModelmain.observableFuturePet().get()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d("data is subscribed","the data is subcribed");

                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {
                            Log.d("onNext Called","the method onNext() is already called");
                            try {
                                Log.d("onNext",responseBody.string());
                            } catch (IOException e) {
                                Log.getStackTraceString(e.fillInStackTrace());

                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("onError Called","the method onError() have been called");
                        }

                        @Override
                        public void onComplete() {
                            Log.d("onComplete Called","the method onComplete() have been called");
                        }
                    });
        } catch (ExecutionException e) {
            Log.d("there was an Execution error",Log.getStackTraceString(e));

        } catch (InterruptedException e) {
            Log.d("There was an Interrupted",Log.getStackTraceString(e));

        }

    }
}
