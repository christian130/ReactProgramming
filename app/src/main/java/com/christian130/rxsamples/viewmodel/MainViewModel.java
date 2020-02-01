package com.christian130.rxsamples.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.christian130.rxsamples.models.SessionManagement.SessionManagement;
import com.christian130.rxsamples.models.livedataSingleton.LiveDataSingleton;

import okhttp3.ResponseBody;

public class MainViewModel extends ViewModel {
    private LiveDataSingleton liveDataSingleton;
    public MainViewModel(){
        liveDataSingleton=LiveDataSingleton.getLiveDataSingleton();
    }
    public LiveData<ResponseBody> hacerPeticion(){
        return liveDataSingleton.retrieveRxRequest();
    }
}
