package com.example.rumpilstilstkin.lesson4.presenters.home;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.rumpilstilstkin.lesson4.data.models.GithubUser;
import com.example.rumpilstilstkin.lesson4.data.rest.NetApiClient;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class UserPresenter extends MvpPresenter<UserView>
        implements Observer<GithubUser>{

    @Override
    public void attachView(UserView view) {
        super.attachView(view);
        loadDate();
    }

    public void loadDate() {
        getViewState().startLoad();
        NetApiClient.getInstance().getUser("rumpilstilstkin")
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        //nope
    }

    @Override
    public void onNext(GithubUser githubUser) {
        getViewState().setImage(githubUser.getAvatar());
        getViewState().setName(githubUser.getLogin());
    }

    @Override
    public void onError(Throwable e) {
        getViewState().showError(e);
    }

    @Override
    public void onComplete() {
        getViewState().finishLoad();
    }
}
