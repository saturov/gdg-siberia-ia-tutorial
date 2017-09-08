package ru.gdg_siberia.instant_app_tutorial.interactor.common.cache;

import ru.gdg_siberia.instant_app_tutorial.interactor.scheduler.SchedulersProvider;

public abstract class BaseRepository {

    private SchedulersProvider schedulersProvider;

    public BaseRepository(SchedulersProvider schedulersProvider) {
        this.schedulersProvider = schedulersProvider;
    }

}