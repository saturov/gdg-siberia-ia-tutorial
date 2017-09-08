package ru.gdg_siberia.instant_app_tutorial.app.dagger;

import android.content.Context;

import dagger.Component;
import ru.gdg_siberia.city_feature.app.dagger.BaseAppComponent;
import ru.gdg_siberia.city_feature.interactor.scheduler.SchedulersProvider;

@PerFullApplication
@Component(dependencies = BaseAppComponent.class)
public interface AppComponent {
    Context context();

    SchedulersProvider schedulerProvider();
}