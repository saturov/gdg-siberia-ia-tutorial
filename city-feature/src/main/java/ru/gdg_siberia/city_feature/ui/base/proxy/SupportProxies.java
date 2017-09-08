package ru.gdg_siberia.city_feature.ui.base.proxy;

public interface SupportProxies {

    void registerActivityResultProxy(ActivityResultProxy activityResultProxy);

    void registerNewIntentProxy(NewIntentProxy newIntentProxy);

    void registerRequestPermissionProxy(RequestPermissionProxy requestPermissionProxy);
}