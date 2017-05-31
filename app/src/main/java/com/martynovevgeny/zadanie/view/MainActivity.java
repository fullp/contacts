package com.martynovevgeny.zadanie.view;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.martynovevgeny.zadanie.R;
import com.martynovevgeny.zadanie.common.BaseActivity;
import com.martynovevgeny.zadanie.injections.components.DaggerIMainActivityComponent;
import com.martynovevgeny.zadanie.injections.components.IAppComponent;
import com.martynovevgeny.zadanie.injections.components.IMainActivityComponent;
import com.martynovevgeny.zadanie.injections.modules.MainActivityModule;
import com.martynovevgeny.zadanie.presenters.MainActivityPresenterImpl;
import com.martynovevgeny.zadanie.utils.MainActivityRecyclerViewAdapter;
import com.martynovevgeny.zadanie.utils.RxSearch;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity implements IMainActivityView {

    @Inject
    MainActivityPresenterImpl presenter;

    @BindView(R.id.rv_main_activity)
    RecyclerView recyclerView;
    @BindView(R.id.spinner_main_activity)
    Spinner spinner;
    private String[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        array = this.getResources().getStringArray(R.array.sort);
        initializeRecyclerView();
        spinnerSetup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_item_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);

        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)
                searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        ArrayList<String> autocompleteWords = presenter.getAutocompleteArray();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.list_item, autocompleteWords);
        searchAutoComplete.setAdapter(adapter);

        RxSearch.fromSearchView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> {
                    if (query.equals("")) {
                        presenter.setSearchName("");
                    } else {
                        presenter.setSearchName(query);
                    }
                });

        searchView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                presenter.setSearchName("");
            }
        });


        searchAutoComplete.setOnItemClickListener((parent, view, position, id)
                -> searchView.setQuery(parent.getItemAtPosition(position).toString(), true));
        return true;
    }

    private void initializeRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void setupComponent(IAppComponent appComponent) {
        IMainActivityComponent mainActivityComponent = DaggerIMainActivityComponent.builder()
                .iAppComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mainActivityComponent.inject(this);
    }

    @Override
    public void initializeAdapter(MainActivityRecyclerViewAdapter adapter) {
        recyclerView.setAdapter(null);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.init();
        presenter.fillDataBase();
        presenter.initializeAdapter();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    private void spinnerSetup() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.setSort(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}