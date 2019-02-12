package com.example.rumpilstilstkin.lesson4;

import com.example.rumpilstilstkin.lesson4.presenters.home.UserPresenter;

import dagger.Component;

@Component(modules = {DaggerNetModule.class})
public interface NetModuleComponent {
    void injects(UserPresenter userPresenter);
}
