package ru.gdg_siberia.instant_app_tutorial.ui.screen.cities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.agna.ferro.mvp.component.ScreenComponent;

import java.util.List;

import javax.inject.Inject;

import ru.gdg_siberia.instant_app_tutorial.R;
import ru.gdg_siberia.instant_app_tutorial.domain.City;
import ru.gdg_siberia.instant_app_tutorial.ui.base.activity.BaseActivityView;

/**
 * Экран со списком городов
 */
public class CitiesActivityView extends BaseActivityView {

    @Inject
    CitiesPresenter presenter;

    private RecyclerView citiesRv;

    private CitiesAdapter adapter;

    public static void start(Context context) {
        Intent i = new Intent(context, CitiesActivityView.class);
        context.startActivity(i);
    }

    @Override
    public String getName() {
        return CitiesPresenter.NAME;
    }

    @Override
    public CitiesPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected ScreenComponent<CitiesActivityView> createScreenComponent() {
        return DaggerCitiesComponent.builder()
                .activityModule(getActivityModule())
                .appComponent(getAppComponent())
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState, boolean viewRecreated) {
        super.onCreate(savedInstanceState, viewRecreated);
        initViews();
    }

    public void showData(List<City> cities) {
        adapter.clear();
        adapter.addAll(cities);
    }

    private void initViews() {
        initToolbar();
        this.citiesRv = findViewById(R.id.cities_rv);
        this.citiesRv.setLayoutManager(new LinearLayoutManager(this));
        this.citiesRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        this.adapter = new CitiesAdapter(this);
        this.adapter.setOnClickListener(url -> presenter.openCityScreen(url));
        this.citiesRv.setAdapter(adapter);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
        }

    }

}