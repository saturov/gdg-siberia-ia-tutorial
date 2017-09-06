package ru.gdg_siberia.instant_app_tutorial.interactor.scheduler;

import dagger.Module;
import dagger.Provides;

@Module
public class SchedulerModule {

    @Provides
    public SchedulersProvider provideSchedulerProvider(){
        return new SchedulersProviderImpl();
    }
}