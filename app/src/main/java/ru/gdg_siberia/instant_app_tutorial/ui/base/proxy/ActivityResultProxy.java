package ru.gdg_siberia.instant_app_tutorial.ui.base.proxy;

import android.content.Intent;

public interface ActivityResultProxy {
    void handleIntent(int requestCode, int resultCode, Intent data);
}