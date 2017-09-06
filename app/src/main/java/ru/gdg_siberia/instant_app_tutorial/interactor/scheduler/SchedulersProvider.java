package ru.gdg_siberia.instant_app_tutorial.interactor.scheduler;

import io.reactivex.Scheduler;

public interface SchedulersProvider {
    Scheduler main();
    Scheduler worker();
}