package ru.gdg_siberia.instant_app_tutorial.ui.screen.cities;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.component.scope.PerScreen;

import dagger.Component;
import ru.gdg_siberia.instant_app_tutorial.app.dagger.AppComponent;
import ru.gdg_siberia.instant_app_tutorial.ui.common.dagger.ActivityViewModule;

@PerScreen
@Component(dependencies = AppComponent.class, modules = {
        ActivityViewModule.class
})
interface CitiesComponent extends ScreenComponent<CitiesActivityView> {
    //empty
}