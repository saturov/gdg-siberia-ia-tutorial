package ru.gdg_siberia.city_feature.ui.common.dagger;

import dagger.Module;
import ru.gdg_siberia.city_feature.ui.base.activity.ActivityModule;
import ru.gdg_siberia.city_feature.ui.common.error.ErrorHandlerModule;

@Module(includes = {
        ActivityModule.class,
        ErrorHandlerModule.class})
public class ActivityViewModule {
    //empty
}