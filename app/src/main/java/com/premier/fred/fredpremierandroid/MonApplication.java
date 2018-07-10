package com.premier.fred.fredpremierandroid;

import android.app.Application;

import com.squareup.otto.Bus;

public class MonApplication extends Application {

    private static Bus monEventBus;

    @Override
    public void onCreate() {
        super.onCreate();
        monEventBus = new Bus();
    }

    public static Bus getBus() {
        return monEventBus;
    }
}
