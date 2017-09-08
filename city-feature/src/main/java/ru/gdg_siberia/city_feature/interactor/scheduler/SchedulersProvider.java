package ru.gdg_siberia.city_feature.interactor.scheduler;

import io.reactivex.Scheduler;

public interface SchedulersProvider {
    Scheduler main();
    Scheduler worker();
}