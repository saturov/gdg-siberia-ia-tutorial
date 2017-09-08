package ru.gdg_siberia.city_feature.ui.base.presenter;

import android.support.annotation.CallSuper;

import com.agna.ferro.mvp.presenter.MvpPresenter;
import com.agna.ferro.mvp.view.BaseView;
import com.agna.ferro.rx.ObservableOperatorFreeze;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Presenter with freeze logic.
 * If subscribe to {@link Observable} via one of {@link #subscribe(Observable, ResourceObserver)} method,
 * all rx events (onNext, onError, onComplete) would be frozen when view destroyed and unfrozen
 * when view recreated (see {@link ObservableOperatorFreeze}).
 * <p>
 * When screen finally destroyed, all subscriptions would be automatically unsubscribed.
 * <p>
 * When configuration changed, presenter isn't destroyed and reused for new view
 * <p>
 * If option freezeEventOnPause enabled (see {@link #setFreezeOnPauseEnabled(boolean)}, all events
 * would be also frozen when screen paused and unfrozen when screen resumed.
 * If option freezeEventOnPause disabled, screen may handle event when it invisible
 * (e.g. when Activity in back stack) and user can miss important information e.g. SnackBar
 */
public class MvpRxPresenter<V extends BaseView> extends MvpPresenter<V> {

    private final CompositeDisposable subscriptions = new CompositeDisposable();
    private final BehaviorSubject<Boolean> freezeSelector = BehaviorSubject.createDefault(false);
    private boolean freezeEventsOnPause = true;

    /**
     * This method is called, when view is ready
     *
     * @param viewRecreated - show whether view created in first time or recreated after
     *                      changing configuration
     */
    @Override
    public void onLoad(boolean viewRecreated) {
        super.onLoad(viewRecreated);
    }

    @CallSuper
    @Override
    public void onLoadFinished() {
        super.onLoadFinished();
        freezeSelector.onNext(false);
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        freezeSelector.onNext(false);
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();
        if (freezeEventsOnPause) {
            freezeSelector.onNext(true);
        }
    }


    @CallSuper
    @Override
    protected void onViewDetached() {
        super.onViewDetached();
        freezeSelector.onNext(true);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        subscriptions.dispose();
    }

    /**
     * If true, all rx event would be frozen when screen paused, and unfrozen when screen resumed,
     * otherwise event would be frozen when {@link #onViewDetached()} called.
     * Default enabled.
     *
     * @param enabled
     */
    public void setFreezeOnPauseEnabled(boolean enabled) {
        this.freezeEventsOnPause = enabled;
    }

    /**
     * Apply {@link ObservableOperatorFreeze} and subscribe subscriber to the observable.
     * When screen finally destroyed, all subscriptions would be automatically unsubscribed.
     * For more information see description of this class.
     *
     * @return subscription
     */
    private <T> Disposable subscribe(final Observable<T> observable,
                                     final ObservableOperatorFreeze<T> operator,
                                     final ResourceObserver<T> resourceObserver) {
        ResourceObserver<T> subscription = observable
                .lift(operator)
                .subscribeWith(resourceObserver);
        subscriptions.add(subscription);
        return subscription;
    }

    /**
     * @see #subscribe(Observable, ObservableOperatorFreeze, ResourceObserver)
     */
    private <T> Disposable subscribe(final Observable<T> observable,
                                     final ObservableOperatorFreeze<T> operator,
                                     final Consumer<T> onNext,
                                     final Consumer<Throwable> onError) {
        return subscribe(observable, operator,
                new ResourceObserver<T>() {

                    @Override
                    public void onError(Throwable e) {
                        try {
                            onError.accept(e);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {
                        // do nothing
                    }

                    @Override
                    public void onNext(T t) {
                        try {
                            onNext.accept(t);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * @param replaceFrozenEventPredicate - used for reduce num element in freeze buffer
     * @see #subscribe(Observable, ObservableOperatorFreeze, ResourceObserver)
     * @see ObservableOperatorFreeze
     */
    protected <T> Disposable subscribe(final Observable<T> observable,
                                       final BiFunction<T, T, Boolean> replaceFrozenEventPredicate,
                                       final ResourceObserver<T> subscriber) {

        return subscribe(observable, createOperatorFreeze(replaceFrozenEventPredicate), subscriber);
    }

    /**
     * @param replaceFrozenEventPredicate - used for reduce num element in freeze buffer
     * @see @link #subscribe(Observable, OperatorFreeze, ResourceObserver)
     * @see @link OperatorFreeze
     */
    protected <T> Disposable subscribe(final Observable<T> observable,
                                       final BiFunction<T, T, Boolean> replaceFrozenEventPredicate,
                                       final Consumer<T> onNext,
                                       final Consumer<Throwable> onError) {

        return subscribe(observable, createOperatorFreeze(replaceFrozenEventPredicate), onNext, onError);
    }

    /**
     * @see @link #subscribe(Observable, OperatorFreeze, ResourceObserver)
     */
    protected <T> Disposable subscribe(final Observable<T> observable,
                                       final ResourceObserver<T> subscriber) {

        return subscribe(observable, this.createOperatorFreeze(), subscriber);
    }

    /**
     * @see @link #subscribe(Observable, OperatorFreeze, ResourceObserver)
     */
    protected <T> Disposable subscribe(final Observable<T> observable,
                                       final Consumer<T> onNext,
                                       final Consumer<Throwable> onError) {

        return subscribe(observable, this.createOperatorFreeze(), onNext, onError);
    }

    /**
     * Subscribe subscriber to the observable without applying {@link ObservableOperatorFreeze}
     * When screen finally destroyed, all subscriptions would be automatically unsubscribed.
     *
     * @return subscription
     */
    protected <T> Disposable subscribeWithoutFreezing(final Observable<T> observable,
                                                      final ResourceObserver<T> subscriber) {

        ResourceObserver subscription = observable
                .subscribeWith(subscriber);
        subscriptions.add(subscription);
        return subscription;
    }

    /**
     * @see @link #subscribeWithoutFreezing(Observable, Subscriber)
     */
    protected <T> Disposable subscribeWithoutFreezing(final Observable<T> observable,
                                                      final Consumer<T> onNext,
                                                      final Consumer<Throwable> onError) {

        return subscribeWithoutFreezing(observable, new ResourceObserver<T>() {
            @Override
            public void onComplete() {
                // do nothing
            }

            @Override
            public void onError(Throwable e) {
                try {
                    onError.accept(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void onNext(T t) {
                try {
                    onNext.accept(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    protected <T> ObservableOperatorFreeze<T> createOperatorFreeze(BiFunction<T, T, Boolean> replaceFrozenEventPredicate) {
        return new ObservableOperatorFreeze<>(freezeSelector, replaceFrozenEventPredicate);
    }

    protected <T> ObservableOperatorFreeze<T> createOperatorFreeze() {
        return new ObservableOperatorFreeze<>(freezeSelector);
    }

    protected boolean isDisposableInactive(Disposable disposable) {
        return disposable == null || disposable.isDisposed();
    }
}