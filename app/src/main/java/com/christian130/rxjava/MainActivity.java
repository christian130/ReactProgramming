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


import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
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
        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Exception {
                        Log.d("filtro", "a filter was added so the current task object is" + Thread.currentThread().getName());
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (task.isComplete()) {


                        }
                        return task.isComplete();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public synchronized void onSubscribe(Disposable d) {
                Log.d("obSubscribeMethod", "called on subscribe with Disposable object");

            }

            @Override
            public synchronized void onNext(Task task) {
                //progressBar.setProgress(progress);
                int n = counter.incrementAndGet();
                g = (((float) n / 5) * 100);


                Log.d("OnNext", "called from OnNext()" + Thread.currentThread().getName());
                Log.d("Counting", "this is what i've counted" + n);
                Log.d("percentage", "this is the percentage" + (int) g);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress((int) g);
                        textView.setText("" + (int) g + "%");
                    }
                });



                Log.d("OnNext: ", "called from OnNext() with a POJO Task named task" + task.getDescription());


            }

            @Override
            public synchronized void onError(Throwable e) {
                Log.d("OnError()", Log.getStackTraceString(e));

            }

            @Override
            public synchronized void onComplete() {

               /* runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // Stuff that updates the UI
                       // progressBar.setProgress(100);
                       // textView.setText("" + 100 + "%");

                    }
                });
*/
                //textView.setText("" + 100 + "%");
                launch();
                Log.d("OnComplete()", "everything is completed");

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private synchronized void launch() {


        Integer integer = progressBar.getProgress();
        Log.d("integer", String.valueOf(integer));
        /*if (integer < 100) {
            try {
                Thread.sleep(6000);
                textView.setText("" + 100 + "%");
                progressBar.setProgress(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }*/
        //
    }
}
