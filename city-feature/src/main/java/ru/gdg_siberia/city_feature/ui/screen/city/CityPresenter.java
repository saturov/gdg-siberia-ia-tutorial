package ru.gdg_siberia.city_feature.ui.screen.city;

import com.agna.ferro.mvp.component.scope.PerScreen;

import javax.inject.Inject;

import ru.gdg_siberia.city_feature.interactor.city.CityRepository;
import ru.gdg_siberia.city_feature.interactor.scheduler.SchedulersProvider;
import ru.gdg_siberia.city_feature.ui.base.activity.BasePresenter;
import ru.gdg_siberia.city_feature.ui.common.error.ErrorHandler;

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
                  /*Navigator navigator,*/
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