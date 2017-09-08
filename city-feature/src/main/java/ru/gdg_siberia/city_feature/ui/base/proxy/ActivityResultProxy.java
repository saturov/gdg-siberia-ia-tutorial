package ru.gdg_siberia.city_feature.ui.base.proxy;

import android.content.Intent;

public interface ActivityResultProxy {
    void handleIntent(int requestCode, int resultCode, Intent data);
}