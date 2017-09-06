package ru.gdg_siberia.instant_app_tutorial.ui.common.navigation;

import android.content.Intent;

import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.ferro.mvp.component.scope.PerScreen;

import javax.inject.Inject;

import ru.gdg_siberia.instant_app_tutorial.ui.base.proxy.ActivityResultProxy;

@PerScreen
public class Navigator implements ActivityResultProxy {

    private final ActivityProvider activityProvider;

    @Inject
    public Navigator(ActivityProvider activityProvider) {
        this.activityProvider = activityProvider;
    }

    @Override
    public void handleIntent(int requestCode, int resultCode, Intent data) {
    }

    public void finishCurrent() {
        activityProvider.get().finish();
    }
}