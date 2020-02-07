package com.christian130.rxsamples05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.christian130.rxsamples05.DTO.Tarea;
import com.christian130.rxsamples05.model.OrigenDelRecurso;

import org.w3c.dom.Text;

import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private Context ctx;
    private ConstraintSet constraintSet;
    private ConstraintLayout constraintLayout;
    private AtomicInteger counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = (ConstraintLayout) findViewById(R.id.myconstrain);

        constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        ctx=this;
        counter = new AtomicInteger(60);

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
                int numberphile=counter.addAndGet(60);
                /*TextView nova = new TextView(ctx);
                nova.setText("this is the text");
                nova.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
                nova.setId(View.generateViewId());
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.applyTo(constraintLayout);*/

                ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.myconstrain);
                ConstraintSet set = new ConstraintSet();

                TextView view = new TextView(ctx);
                view.setText(tarea.getDescripcion()+ " this TextView has been created  dinamically");
                view.setId(View.generateViewId());
                layout.addView(view,0);
                set.clone(layout);
                set.connect(view.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, numberphile);
                set.applyTo(layout);

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
