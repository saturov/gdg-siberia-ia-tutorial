package ru.gdg_siberia.instant_app_tutorial.app;

import android.app.Application;
import android.content.Context;

import ru.gdg_siberia.city_feature.app.BaseApp;
import ru.gdg_siberia.city_feature.app.dagger.AppModule;
import ru.gdg_siberia.instant_app_tutorial.app.dagger.AppComponent;
import ru.gdg_siberia.instant_app_tutorial.app.dagger.DaggerAppComponent;

/**
 * Application class
 */
public class App extends BaseApp {

    private AppComponent appComponent;

    public static AppComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).getAppComponent();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }

    private void initInjector() {
        appComponent = DaggerAppComponent.builder()
                .baseAppComponent(getBaseAppComponent())
                .build();
    }
}