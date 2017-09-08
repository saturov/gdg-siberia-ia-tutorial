package ru.gdg_siberia.city_feature.app;

import android.app.Application;
import android.content.Context;

import ru.gdg_siberia.city_feature.app.dagger.AppModule;
import ru.gdg_siberia.city_feature.app.dagger.BaseAppComponent;
import ru.gdg_siberia.city_feature.app.dagger.DaggerBaseAppComponent;


public class BaseApp extends Application {

    private BaseAppComponent baseAppComponent;

    public static BaseAppComponent getBaseAppComponent(Context context) {
        return ((BaseApp) context.getApplicationContext()).getBaseAppComponent();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }

    public BaseAppComponent getBaseAppComponent() {
        return this.baseAppComponent;
    }

    private void initInjector() {
        baseAppComponent = DaggerBaseAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
