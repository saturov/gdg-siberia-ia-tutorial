package ru.gdg_siberia.instant_app_tutorial.ui.common.error;

import com.agna.ferro.mvp.component.scope.PerScreen;

import dagger.Module;
import dagger.Provides;

@Module
public class ErrorHandlerModule {

    @Provides
    @PerScreen
    ErrorHandler provideNetworkErrorHandler(StandardErrorHandler standardErrorHandler){
        return standardErrorHandler;
    }
}