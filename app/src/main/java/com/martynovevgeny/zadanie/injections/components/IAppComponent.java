package com.martynovevgeny.zadanie.injections.components;

import android.app.Application;

import com.martynovevgeny.zadanie.injections.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by websu on 30.05.2017.
 */

@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface IAppComponent {
    void inject(Application app);
}