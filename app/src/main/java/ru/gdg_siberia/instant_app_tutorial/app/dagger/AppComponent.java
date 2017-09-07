package ru.gdg_siberia.instant_app_tutorial.app.dagger;

import android.content.Context;

import com.agna.ferro.mvp.component.scope.PerApplication;

import dagger.Component;
import ru.gdg_siberia.instant_app_tutorial.interactor.scheduler.SchedulerModule;
import ru.gdg_siberia.instant_app_tutorial.interactor.scheduler.SchedulersProvider;

@PerApplication
@Component(modules = {
        AppModule.class,
        SchedulerModule.class})
public interface AppComponent {
    Context context();

    SchedulersProvider schedulerProvider();
}