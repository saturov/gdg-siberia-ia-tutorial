package ru.gdg_siberia.instant_app_tutorial.ui.screen.cities;

import android.content.Context;
import android.content.Intent;

import com.agna.ferro.mvp.component.ScreenComponent;

import javax.inject.Inject;

import ru.gdg_siberia.instant_app_tutorial.R;
import ru.gdg_siberia.instant_app_tutorial.ui.base.activity.BaseActivityView;

/**
 * Экран со списком городов
 */
public class CitiesActivityView extends BaseActivityView {

    @Inject
    CitiesPresenter presenter;

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

}