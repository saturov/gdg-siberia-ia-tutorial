package ru.gdg_siberia.city_feature.interactor.common.cache;

import ru.gdg_siberia.city_feature.interactor.scheduler.SchedulersProvider;

public abstract class BaseRepository {

    private SchedulersProvider schedulersProvider;

    public BaseRepository(SchedulersProvider schedulersProvider) {
        this.schedulersProvider = schedulersProvider;
    }

}