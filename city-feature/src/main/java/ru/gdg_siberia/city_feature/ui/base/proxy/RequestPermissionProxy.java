package ru.gdg_siberia.city_feature.ui.base.proxy;

import android.support.annotation.NonNull;

public interface RequestPermissionProxy {
    void handlePermission(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}