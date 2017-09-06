package ru.gdg_siberia.instant_app_tutorial.ui.base.proxy;

import android.support.annotation.NonNull;

public interface RequestPermissionProxy {
    void handlePermission(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}