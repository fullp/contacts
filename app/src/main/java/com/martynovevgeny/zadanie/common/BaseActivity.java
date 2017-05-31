package com.martynovevgeny.zadanie.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.martynovevgeny.zadanie.app.App;
import com.martynovevgeny.zadanie.injections.components.IAppComponent;

/**
 * Created by websu on 30.05.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(App.get(this).getAppComponent());
    }

    protected abstract void setupComponent(IAppComponent appComponent);
}

