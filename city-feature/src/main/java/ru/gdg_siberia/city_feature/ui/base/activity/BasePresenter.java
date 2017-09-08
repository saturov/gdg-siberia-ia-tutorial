package ru.gdg_siberia.city_feature.ui.base.activity;

import com.agna.ferro.mvp.view.BaseView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.ResourceObserver;
import ru.gdg_siberia.city_feature.interactor.scheduler.SchedulersProvider;
import ru.gdg_siberia.city_feature.ui.base.presenter.MvpRxPresenter;
import ru.gdg_siberia.city_feature.ui.common.error.ErrorHandler;

/**
 * базовый класс презентера
 * Методы {@link #subscribe}, {@link #subscribeNetworkQuery} добавляют логику замораживания
 * Rx событий, см {@link MvpRxPresenter}
 * Подписки через все виды методов {@link #subscribe}, {@link #subscribeWithoutFreezing},
 * {@link #subscribeNetworkQuery} обрабатываются в главном потоке
 * При подписке с помощью методов {@link #subscribeNetworkQuery} observable источника переводится в
 * background поток.
 *
 * @param <V>
 */
public class BasePresenter<V extends BaseView> extends MvpRxPresenter<V> {

    private SchedulersProvider schedulersProvider;
    private ErrorHandler errorHandler;

    public BasePresenter(SchedulersProvider schedulersProvider, ErrorHandler errorHandler) {
        this.schedulersProvider = schedulersProvider;
        this.errorHandler = errorHandler;
    }

    @Override
    protected <T> Disposable subscribe(Observable<T> observable,
                                       Consumer<T> onNext,
                                       Consumer<Throwable> onError) {
        observable = observable.observeOn(schedulersProvider.main(), true);
        return super.subscribe(observable, onNext, onError);
    }

    @Override
    protected <T> Disposable subscribe(Observable<T> observable,
                                       BiFunction<T, T, Boolean> replaceFrozenEventPredicate,
                                       Consumer<T> onNext,
                                       Consumer<Throwable> onError) {
        observable = observable.observeOn(schedulersProvider.main(), true);
        return super.subscribe(observable, replaceFrozenEventPredicate, onNext, onError);
    }

    @Override
    protected <T> Disposable subscribe(Observable<T> observable,
                                       BiFunction<T, T, Boolean> replaceFrozenEventPredicate,
                                       ResourceObserver<T> subscriber) {
        observable = observable.observeOn(schedulersProvider.main(), true);
        return super.subscribe(observable, replaceFrozenEventPredicate, subscriber);
    }

    @Override
    protected <T> Disposable subscribe(Observable<T> observable,
                                       ResourceObserver<T> subscriber) {
        observable = observable.observeOn(schedulersProvider.main(), true);
        return super.subscribe(observable, subscriber);
    }

    @Override
    protected <T> Disposable subscribeWithoutFreezing(Observable<T> observable,
                                                      Consumer<T> onNext,
                                                      Consumer<Throwable> onError) {
        return super.subscribeWithoutFreezing(observable, onNext, onError);
    }

    @Override
    protected <T> Disposable subscribeWithoutFreezing(Observable<T> observable,
                                                      ResourceObserver<T> subscriber) {
        observable = observable.observeOn(schedulersProvider.main(), true);
        return super.subscribeWithoutFreezing(observable, subscriber);
    }

    /**
     * Работает также как {@link #subscribe}, кроме того предоставляет стандартную обработку
     * ошибок сетевых запросов
     */
    protected <T> Disposable subscribeNetworkQuery(Observable<T> observable,
                                                     final Consumer<T> onNext,
                                                     final Consumer<Throwable> onError) {
        return subscribeNetworkQuery(observable, onNext, onError, errorHandler);
    }

    /**
     * Работает также как {@link #subscribe}, кроме того предоставляет стандартную обработку
     * ошибок сетевых запросов
     */
    protected <T> Disposable subscribeNetworkQuery(Observable<T> observable,
                                                     final Consumer<T> onNext) {
        return subscribeNetworkQuery(observable, onNext, null, errorHandler);
    }

    /**
     * Работает также как {@link #subscribeNetworkQuery}, кроме того позволяет указать обработчик
     * ошибок сетевых запросов
     */
    protected <T> Disposable subscribeNetworkQuery(Observable<T> observable,
                                                     final Consumer<T> onNext,
                                                     ErrorHandler errorHandler) {
        return subscribeNetworkQuery(observable, onNext, null, errorHandler);
    }

    /**
     * Работает также как {@link #subscribeNetworkQuery}, кроме того позволяет указать обработчик
     * ошибок сетевых запросов
     */
    protected <T> Disposable subscribeNetworkQuery(Observable<T> observable,
                                                     final Consumer<T> onNext,
                                                     final Consumer<Throwable> onError,
                                                     ErrorHandler errorHandler) {
        observable = observable.subscribeOn(schedulersProvider.worker());
        return subscribe(observable, onNext, e -> onNetworkError(e, onError, errorHandler));
    }

    private void onNetworkError(Throwable e, Consumer<Throwable> onError, ErrorHandler errorHandler) {
        errorHandler.handleError(e);
        if (onError != null) {
            try {
                onError.accept(e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
