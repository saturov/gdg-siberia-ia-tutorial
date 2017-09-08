package ru.gdg_siberia.instant_app_tutorial.ui.screen.cities;

import com.agna.ferro.mvp.component.scope.PerScreen;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import ru.gdg_siberia.instant_app_tutorial.interactor.city.CityRepository;
import ru.gdg_siberia.instant_app_tutorial.interactor.scheduler.SchedulersProvider;
import ru.gdg_siberia.instant_app_tutorial.ui.base.activity.BasePresenter;
import ru.gdg_siberia.instant_app_tutorial.ui.common.error.ErrorHandler;
import ru.gdg_siberia.instant_app_tutorial.ui.common.navigation.Navigator;

/**
 * Презентер экрана списка городов
 */
@PerScreen
public class CitiesPresenter extends BasePresenter<CitiesActivityView> {

    public static final String NAME = "CitiesActivityView";

    private final SchedulersProvider schedulersProvider;
    private final Navigator navigator;
    private final CityRepository cityRepository;

    private Disposable loadScreenDataDisposable;

    @Inject
    CitiesPresenter(SchedulersProvider schedulersProvider,
                    ErrorHandler errorHandler,
                    Navigator navigator,
                    CityRepository cityRepository) {
        super(schedulersProvider, errorHandler);
        this.schedulersProvider = schedulersProvider;
        this.navigator = navigator;
        this.cityRepository = cityRepository;
    }

    @Override
    public void onLoad(boolean viewRecreated) {
        super.onLoad(viewRecreated);

        subscribeNetworkQuery(cityRepository.getCities(), cities ->
                getView().showData(cities));
    }

}