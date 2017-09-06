package ru.gdg_siberia.instant_app_tutorial.interactor.scheduler;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Schedulers provider for RxJava
 */
public class SchedulersProviderImpl implements SchedulersProvider {

    @Override
    public Scheduler main(){
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler worker(){
        return Schedulers.io();
    }
}