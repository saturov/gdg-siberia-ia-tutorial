package ru.gdg_siberia.instant_app_tutorial.ui.common.message;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.ferro.mvp.component.scope.PerScreen;

import javax.inject.Inject;

import ru.gdg_siberia.instant_app_tutorial.R;

/**
 * Message show presenter
 */
@PerScreen
public class MessagePresenter {

    private final ActivityProvider activityProvider;

    @Inject
    public MessagePresenter(ActivityProvider activityProvider) {
        this.activityProvider = activityProvider;
    }

    public void show(@StringRes int stringId) {
        View v = getView();
        Snackbar snackbar = Snackbar.make(v, stringId, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    /**
     * Show snackbar with clickable action button. <p>
     * By default {@link Snackbar} length set as {@link Snackbar#LENGTH_INDEFINITE}.
     *
     * @param stringId        message text
     * @param actionStringId  action button text
     * @param onClickListener action button click listener
     */
    public void showWithAction(@StringRes int stringId,
                               @StringRes int actionStringId,
                               View.OnClickListener onClickListener) {
        View v = getView();
        Snackbar snackbar = Snackbar.make(v, stringId, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(actionStringId, onClickListener);
        snackbar.setActionTextColor(ContextCompat.getColor(getView().getContext(), R.color.colorPrimary));
        snackbar.show();
    }

    public void show(String message) {
        View v = getView();
        Snackbar snackbar = Snackbar.make(v, message, Snackbar.LENGTH_LONG);
        setMultilineSnackbar(snackbar);
        snackbar.show();
    }

    public void show(@IdRes int parentViewId, @StringRes int stringId) {
        View v = activityProvider.get().findViewById(parentViewId);
        Snackbar snackbar = Snackbar.make(v, stringId, Snackbar.LENGTH_LONG);
        setMultilineSnackbar(snackbar);
        snackbar.show();
    }

    private void setMultilineSnackbar(Snackbar snackbar) {
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(4);
    }

    /**
     * To activate {@link android.support.design.widget.CoordinatorLayout.Behavior} of {@link Snackbar}
     * it's necessary {@link android.support.design.widget.CoordinatorLayout} to be the root screens'
     * container and to has an ID = "coordinator_content". {@link Snackbar} would be tied to that view.<p>
     * If there is no such view on the screens' layout - {@link Snackbar} would be tied to the base
     * root activity container with ID = content.
     */
    private View getView() {
        View v = null/*activityProvider.get().findViewById(R.id.coordinator_content)*/;
        /*if (v == null) {
            v = activityProvider.get().findViewById(android.R.id.content);
        }*/
        return v;
    }
}