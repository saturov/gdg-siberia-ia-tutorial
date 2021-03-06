package ru.gdg_siberia.instant_app_tutorial.app.dagger;

import android.content.Context;

import com.agna.ferro.mvp.component.scope.PerApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @PerApplication
    Context provideContext() {
        return appContext;
    }
}