package com.christian130.rxjava.models;

import androidx.lifecycle.ViewModel;

import com.christian130.rxjava.ExecutorUser;

import java.util.concurrent.Future;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class GenericViewModelImportant extends ViewModel {
    private ExecutorUser executorUserRepo;
    public GenericViewModelImportant(){
        executorUserRepo = ExecutorUser.getGetInstance();
    }
    public Future<Observable<ResponseBody>> observableFuturePet(){
        return executorUserRepo.observableFuturePeticion();
    }
}
