package ru.gdg_siberia.instant_app_tutorial.ui.screen.city;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.agna.ferro.mvp.component.ScreenComponent;

import javax.inject.Inject;

import ru.gdg_siberia.instant_app_tutorial.R;
import ru.gdg_siberia.instant_app_tutorial.domain.City;
import ru.gdg_siberia.instant_app_tutorial.ui.base.activity.BaseActivityView;
import ru.gdg_siberia.instant_app_tutorial.ui.screen.cities.CitiesPresenter;

/**
 * Экран конкретного города
 */
public class CityActivityView extends BaseActivityView {

    private static final String EXTRA_CITY_URL = "extra_city_url";

    @Inject
    CityPresenter presenter;

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView descriptionTv;
    private ImageView coverIv;

    public static void start(Context context, String cityUrl) {
        Intent i = new Intent(context, CityActivityView.class);
        i.putExtra(EXTRA_CITY_URL, cityUrl);
        context.startActivity(i);
    }

    @Override
    public String getName() {
        return CitiesPresenter.NAME;
    }

    @Override
    public CityPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_city;
    }

    @Override
    protected ScreenComponent<CityActivityView> createScreenComponent() {
        String cityUrl = getIntent().getStringExtra(EXTRA_CITY_URL);
        if (cityUrl == null) {
            cityUrl = "";
        }
        return DaggerCityComponent.builder()
                .activityModule(getActivityModule())
                .appComponent(getAppComponent())
                .cityScreenModule(new CityScreenModule(cityUrl))
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState, boolean viewRecreated) {
        super.onCreate(savedInstanceState, viewRecreated);
        initViews();
    }

    public void showData(City city) {
        collapsingToolbarLayout.setTitle(city.getName());
        descriptionTv.setText(city.getDescription());
        coverIv.setImageResource(city.getPictureId());
    }

    private void initViews() {
        this.collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        this.descriptionTv = findViewById(R.id.city_description_tv);
        this.coverIv = findViewById(R.id.city_cover_iv);
        initToolbar();
    }

    private void initToolbar() {
        this.toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

    }

}