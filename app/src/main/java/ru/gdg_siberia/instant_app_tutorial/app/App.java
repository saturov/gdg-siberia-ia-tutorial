package ru.gdg_siberia.instant_app_tutorial.app;

import android.app.Application;

import ru.gdg_siberia.instant_app_tutorial.app.dagger.AppComponent;
import ru.gdg_siberia.instant_app_tutorial.app.dagger.AppModule;
import ru.gdg_siberia.instant_app_tutorial.app.dagger.DaggerAppComponent;

/**
 * Application class
 */
public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }

    private void initInjector() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}