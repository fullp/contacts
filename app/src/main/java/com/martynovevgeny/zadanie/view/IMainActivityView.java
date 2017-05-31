package com.martynovevgeny.zadanie.view;

import android.content.Context;

import com.martynovevgeny.zadanie.utils.MainActivityRecyclerViewAdapter;

/**
 * Created by websu on 30.05.2017.
 */

public interface IMainActivityView {

    void initializeAdapter(MainActivityRecyclerViewAdapter adapter);
    Context getContext();
}
