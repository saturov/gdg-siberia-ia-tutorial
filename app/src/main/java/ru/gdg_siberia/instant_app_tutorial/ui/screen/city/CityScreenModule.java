package ru.gdg_siberia.instant_app_tutorial.ui.screen.city;

import com.agna.ferro.mvp.component.scope.PerScreen;

import dagger.Module;
import dagger.Provides;

@Module
public class CityScreenModule {

    private String cityUrl;

    public CityScreenModule(String cityUrl) {
        this.cityUrl = cityUrl;
    }

    @Provides
    @PerScreen
    public String provideCityUrl() {
        return cityUrl;
    }
}