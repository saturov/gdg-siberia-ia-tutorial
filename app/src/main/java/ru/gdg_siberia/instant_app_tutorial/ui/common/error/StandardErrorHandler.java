package ru.gdg_siberia.instant_app_tutorial.ui.common.error;

import com.agna.ferro.mvp.component.scope.PerScreen;

import javax.inject.Inject;

import ru.gdg_siberia.instant_app_tutorial.R;
import ru.gdg_siberia.instant_app_tutorial.ui.common.message.MessagePresenter;
import ru.gdg_siberia.instant_app_tutorial.ui.common.navigation.Navigator;

@PerScreen
public class StandardErrorHandler extends ErrorHandler {

    private final MessagePresenter messagePresenter;
    private Navigator navigator;

    @Inject
    public StandardErrorHandler(MessagePresenter messagePresenter,
                                Navigator navigator) {
        this.messagePresenter = messagePresenter;
        this.navigator = navigator;
    }

    @Override
    protected void handleOtherError(Throwable e) {
        messagePresenter.show(R.string.error_text_unexpected);
    }
}