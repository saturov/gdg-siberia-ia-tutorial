package ru.gdg_siberia.instant_app_tutorial.ui.base.proxy;

public interface SupportProxies {

    void registerActivityResultProxy(ActivityResultProxy activityResultProxy);

    void registerNewIntentProxy(NewIntentProxy newIntentProxy);

    void registerRequestPermissionProxy(RequestPermissionProxy requestPermissionProxy);
}