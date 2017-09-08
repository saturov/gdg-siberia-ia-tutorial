package ru.gdg_siberia.city_feature.ui.base.proxy;

import android.content.Intent;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class NewIntentProxyRx<E> implements NewIntentProxy {
    protected final PublishSubject<E> eventSubject = PublishSubject.create();

    public abstract void handleIntent(Intent newIntent);

    public Observable<E> observeEvent(){
        return eventSubject.firstElement().toObservable();
    }

    protected void notifyListeners(E event){
        eventSubject.onNext(event);
    }
}
