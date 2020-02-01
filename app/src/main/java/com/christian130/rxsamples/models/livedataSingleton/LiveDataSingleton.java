package com.christian130.rxsamples.models.livedataSingleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.christian130.rxsamples.models.SessionManagement.SessionManagement;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LiveDataSingleton {
    private static LiveDataSingleton liveDataSingleton;
    public static LiveDataSingleton getLiveDataSingleton(){
        if (liveDataSingleton==null){
            liveDataSingleton = new LiveDataSingleton();
        }
        return liveDataSingleton;
    }
    public LiveData<ResponseBody> retrieveRxRequest(){
        return LiveDataReactiveStreams.fromPublisher(SessionManagement.getImplSrvIntAll()
                .makeRequest()
                .subscribeOn(Schedulers.io())

        );
    }
}
