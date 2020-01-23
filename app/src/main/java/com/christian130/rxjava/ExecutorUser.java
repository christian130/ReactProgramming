package com.christian130.rxjava;



import com.christian130.rxjava.restclient.ImplementacionServiciado;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import okhttp3.ResponseBody;


public class ExecutorUser {
    private static ExecutorUser executorUserInstance; //me preparo para crear un singleton
    public static ExecutorUser getGetInstance(){
        if(executorUserInstance == null){
            executorUserInstance = new ExecutorUser();
        }
        return executorUserInstance;
    }
    public Future<Observable<ResponseBody>> observableFuturePeticion(){
        final ExecutorService ejecutor = Executors.newSingleThreadExecutor();
        final Callable<Observable<ResponseBody>> myExecutorUserCallable = new Callable<Observable<ResponseBody>>() {
            @Override
            public Observable<ResponseBody> call() throws Exception {
                return ImplementacionServiciado.getImplSrvIntAll().getQuery();
            }

        };
        final Future<Observable<ResponseBody>> observableFuture = new Future<Observable<ResponseBody>>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                if (mayInterruptIfRunning){
                    ejecutor.shutdown();
                }
                return false;
            }

            @Override
            public boolean isCancelled() {
                return ejecutor.isShutdown();
            }

            @Override
            public boolean isDone() {
                return ejecutor.isTerminated();
            }

            @Override
            public Observable<ResponseBody> get() throws ExecutionException, InterruptedException {
                return ejecutor.submit(myExecutorUserCallable).get();
            }

            @Override
            public Observable<ResponseBody> get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
                return ejecutor.submit(myExecutorUserCallable).get(timeout,unit);
            }

        };

        return observableFuture;
    }
}
