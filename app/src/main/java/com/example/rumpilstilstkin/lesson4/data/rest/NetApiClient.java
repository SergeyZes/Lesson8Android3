package com.example.rumpilstilstkin.lesson4.data.rest;

import com.example.rumpilstilstkin.lesson4.data.Endpoints;
import com.example.rumpilstilstkin.lesson4.data.models.GithubUser;
import com.example.rumpilstilstkin.lesson4.data.models.RepsModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetApiClient implements NetApiClientInterface{

    private static NetApiClient ourInstance;

    public static NetApiClient getInstance() {
        if(ourInstance == null){
            ourInstance = new NetApiClient();
        }
        return ourInstance;
    }

    private Endpoints netApi = new ServiceGenerator().createService(Endpoints.class);

    private NetApiClient() {
    }

    @Override
    public Observable<GithubUser> getUser(String user) {
        return netApi.getUser(user);
            //    .observeOn(AndroidSchedulers.mainThread())
             //   .subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<RepsModel>> getReps() {
        return netApi.getRepos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
