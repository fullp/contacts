package com.martynovevgeny.zadanie.injections.modules;

import android.app.Application;
import android.content.Context;

import com.martynovevgeny.zadanie.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by websu on 30.05.2017.
 */

@Module
public class AppModule {

    private final App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Singleton
    @Provides
    public Application providesApplication() {
        return mApp;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mApp;
    }
}