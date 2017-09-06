package ru.gdg_siberia.instant_app_tutorial.ui.common.error;

public abstract class ErrorHandler {

    public void handleError(Throwable err) {
        handleOtherError(err);
    }

    protected abstract void handleOtherError(Throwable e);

}