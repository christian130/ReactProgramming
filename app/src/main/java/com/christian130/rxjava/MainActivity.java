package com.christian130.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.christian130.rxjava.models.Task;
import com.christian130.rxjava.utils.DataSource;

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
        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Exception {
                        Log.d("filtro","a filter was added so the current task object is"+ Thread.currentThread().getName());
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return task.isComplete();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("obSubscribeMethod","called on subscribe with Disposable object");

            }

            @Override
            public void onNext(Task task) {
                Log.d("OnNext","called from OnNext()"+Thread.currentThread().getName());
                Log.d("OnNext: ","called from OnNext() with a POJO Task named task"+task.getDescription());

            }

            @Override
            public void onError(Throwable e) {
                Log.d("OnError()",Log.getStackTraceString(e));

            }

            @Override
            public void onComplete() {
                Log.d("everything","everything is completed");

            }
        });
    }
}
