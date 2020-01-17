package com.christian130.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.christian130.rxjava.models.Task;
import com.christian130.rxjava.utils.DataSource;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private int progressinformation;
    private AtomicInteger counter;
    private float g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progress_circular);
        seekBar = findViewById(R.id.seekBar);
        counter = new AtomicInteger();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                textView.setText("" + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final List<Task> taskList = DataSource.createTaskList();

        Observable<Task> taskObservable = Observable
                .create(new ObservableOnSubscribe<Task>() {
                    @Override
                    public void subscribe(ObservableEmitter<Task> emitter) throws Exception {
                        for (int c = 0; c < taskList.size(); c++) {
                            Log.d("estoy",String.valueOf(c)+"vs"+String.valueOf(taskList.size()));
                            if (!emitter.isDisposed()) {
                                try {
                                    Thread.sleep(3000);
                                    if (taskList.get(c).isComplete()){
                                        emitter.onNext(taskList.get(c));
                                    }
                                    if ((taskList.size()-1)==c){
                                        try {
                                            Thread.sleep(3000);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Log.d("hostia","hostia he llegado al ultimo");
                                                    textView.setText("100 %");
                                                    progressBar.setProgress(100);
                                                }
                                            });
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }


                                    }

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }


                                //int x=counter.incrementAndGet();
                                //float s=(((float) x / 5) * 100);
                                //textView.setText(" "+s+" %");
                            }

                        }
                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.d("estoy en el onNext()", task.getDescription());
                int x=counter.incrementAndGet();
                Log.d("counter",String.valueOf(x));
                float s=(((float) x / taskList.size()) * 100);
                progressBar.setProgress((int)s);
                textView.setText(" "+(int)s+" %");

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
