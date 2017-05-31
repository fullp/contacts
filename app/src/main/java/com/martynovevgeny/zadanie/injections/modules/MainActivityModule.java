package com.martynovevgeny.zadanie.injections.modules;

import com.martynovevgeny.zadanie.injections.ActivityScope;
import com.martynovevgeny.zadanie.presenters.MainActivityPresenterImpl;
import com.martynovevgeny.zadanie.view.IMainActivityView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by websu on 30.05.2017.
 */

@Module
public class MainActivityModule {

    private final IMainActivityView view;

    public MainActivityModule(IMainActivityView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public IMainActivityView provideView() {
        return view;
    }

    @ActivityScope
    @Provides
    public MainActivityPresenterImpl provideMainActivityPresenterImpl (IMainActivityView view){
        return  new MainActivityPresenterImpl(view);
    }
}
