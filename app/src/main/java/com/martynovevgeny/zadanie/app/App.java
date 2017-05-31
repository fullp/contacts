package com.martynovevgeny.zadanie.app;

import android.app.Application;
import android.content.Context;

import com.martynovevgeny.zadanie.injections.components.DaggerIAppComponent;
import com.martynovevgeny.zadanie.injections.components.IAppComponent;
import com.martynovevgeny.zadanie.injections.modules.AppModule;

/**
 * Created by websu on 30.05.2017.
 */

public class App extends Application {

    private IAppComponent mAppComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraphAndInject();
    }

    public IAppComponent getAppComponent() {
        return mAppComponent;
    }

    private void buildGraphAndInject() {
        mAppComponent = DaggerIAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }
}
