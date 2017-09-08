package ru.gdg_siberia.instant_app_tutorial.ui.screen.city;

import com.agna.ferro.mvp.component.scope.PerScreen;

import javax.inject.Inject;

import ru.gdg_siberia.instant_app_tutorial.interactor.city.CityRepository;
import ru.gdg_siberia.instant_app_tutorial.interactor.scheduler.SchedulersProvider;
import ru.gdg_siberia.instant_app_tutorial.ui.base.activity.BasePresenter;
import ru.gdg_siberia.instant_app_tutorial.ui.common.error.ErrorHandler;
import ru.gdg_siberia.instant_app_tutorial.ui.common.navigation.Navigator;

/**
 * Презентер экрана конкретного города
 */
@PerScreen
public class CityPresenter extends BasePresenter<CityActivityView> {

    public static final String NAME = "CitiesActivityView";

    private final CityRepository cityRepository;
    private final String cityUrl;

    @Inject
    CityPresenter(SchedulersProvider schedulersProvider,
                  ErrorHandler errorHandler,
                  Navigator navigator,
                  CityRepository cityRepository,
                  String cityUrl) {
        super(schedulersProvider, errorHandler);
        this.cityRepository = cityRepository;
        this.cityUrl = cityUrl;
    }

    @Override
    public void onLoad(boolean viewRecreated) {
        super.onLoad(viewRecreated);

        subscribeNetworkQuery(cityRepository.getCityByUrl(cityUrl), city -> {
            getView().showData(city);
        });
    }

}