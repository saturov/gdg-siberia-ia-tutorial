package ru.gdg_siberia.instant_app_tutorial.ui.common.dagger;

import dagger.Module;
import ru.gdg_siberia.instant_app_tutorial.ui.base.activity.ActivityModule;
import ru.gdg_siberia.instant_app_tutorial.ui.common.error.ErrorHandlerModule;

@Module(includes = {
        ActivityModule.class,
        ErrorHandlerModule.class})
public class ActivityViewModule {
    //empty
}