package ru.gdg_siberia.city_feature.interactor.scheduler;

import dagger.Module;
import dagger.Provides;

@Module
public class SchedulerModule {

    @Provides
    public SchedulersProvider provideSchedulerProvider(){
        return new SchedulersProviderImpl();
    }
}