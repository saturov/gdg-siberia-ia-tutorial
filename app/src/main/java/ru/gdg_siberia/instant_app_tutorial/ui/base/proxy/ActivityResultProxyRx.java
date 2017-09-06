package ru.gdg_siberia.instant_app_tutorial.ui.base.proxy;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class ActivityResultProxyRx<E> implements ActivityResultProxy {
    private final PublishSubject<E> eventSubject = PublishSubject.create();

    public Observable<E> observeResult(){
        return eventSubject;
    }

    protected void notifyListeners(E event){
        eventSubject.onNext(event);
    }
}