package com.martynovevgeny.zadanie.injections.components;

import com.martynovevgeny.zadanie.injections.ActivityScope;
import com.martynovevgeny.zadanie.injections.modules.MainActivityModule;
import com.martynovevgeny.zadanie.view.MainActivity;

import dagger.Component;


@Component(
        dependencies = IAppComponent.class,
        modules = MainActivityModule.class
)
@ActivityScope
public interface IMainActivityComponent {

    void inject(MainActivity activity);
}