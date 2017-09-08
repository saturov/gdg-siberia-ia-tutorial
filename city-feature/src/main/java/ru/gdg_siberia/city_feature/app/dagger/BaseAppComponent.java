package ru.gdg_siberia.city_feature.app.dagger;

import android.content.Context;

import com.agna.ferro.mvp.component.scope.PerApplication;

import dagger.Component;
import ru.gdg_siberia.city_feature.interactor.scheduler.SchedulerModule;
import ru.gdg_siberia.city_feature.interactor.scheduler.SchedulersProvider;

@PerApplication
@Component(modules = {
        AppModule.class,
        SchedulerModule.class})
public interface BaseAppComponent {
    Context context();

    SchedulersProvider schedulerProvider();
}