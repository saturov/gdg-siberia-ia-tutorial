package ru.gdg_siberia.city_feature.ui.screen.city;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.component.scope.PerScreen;

import dagger.Component;
import ru.gdg_siberia.city_feature.app.dagger.BaseAppComponent;
import ru.gdg_siberia.city_feature.ui.common.dagger.ActivityViewModule;

@PerScreen
@Component(dependencies = BaseAppComponent.class,
        modules = {
                ActivityViewModule.class,
                CityScreenModule.class
        })
interface CityComponent extends ScreenComponent<CityActivityView> {
    //empty
}