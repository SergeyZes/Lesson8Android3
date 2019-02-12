package com.example.rumpilstilstkin.lesson4;

import com.example.rumpilstilstkin.lesson4.data.rest.NetApiClient;

import dagger.Module;
import dagger.Provides;

@Module
public class DaggerNetModule {
    private NetApiClient netApiClient;

    public DaggerNetModule(NetApiClient netApiClient) {
        this.netApiClient = netApiClient;
    }

    @Provides
    NetApiClient getNetApiClient(){
        return netApiClient;
    }
}
