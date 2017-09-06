package ru.gdg_siberia.instant_app_tutorial.app.dagger;

import android.content.Context;

import com.agna.ferro.mvp.component.scope.PerApplication;

import dagger.Component;

@PerApplication
@Component(modules = {
        AppModule.class})
public interface AppComponent {
    Context context();
}