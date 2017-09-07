package ru.gdg_siberia.instant_app_tutorial.ui.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.view.activity.MvpActivityView;

import java.util.HashSet;
import java.util.Set;

import ru.gdg_siberia.instant_app_tutorial.app.App;
import ru.gdg_siberia.instant_app_tutorial.app.dagger.AppComponent;
import ru.gdg_siberia.instant_app_tutorial.ui.base.proxy.ActivityResultProxy;
import ru.gdg_siberia.instant_app_tutorial.ui.base.proxy.NewIntentProxy;
import ru.gdg_siberia.instant_app_tutorial.ui.base.proxy.RequestPermissionProxy;
import ru.gdg_siberia.instant_app_tutorial.ui.base.proxy.SupportProxies;

/**
 * Базовый класс для View основанной на Activity
 */
public abstract class BaseActivityView extends MvpActivityView implements SupportProxies {

    private static final int DEFAULT_DELAY = 150; //ms
    private Handler handler = new Handler();

    private final Set<ActivityResultProxy> activityResultProxies = new HashSet<>();
    private final Set<NewIntentProxy> newIntentProxies = new HashSet<>();
    private final Set<RequestPermissionProxy> requestPermissionProxies = new HashSet<>();

    public abstract BasePresenter getPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState, boolean viewRecreated) {
        super.onCreate(savedInstanceState, viewRecreated);
    }

    @Override
    protected final void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (ActivityResultProxy activityResultProxy : activityResultProxies) {
            activityResultProxy.handleIntent(requestCode, resultCode, data);
        }
    }

    @Override
    protected final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        for (NewIntentProxy newIntentProxy : newIntentProxies) {
            newIntentProxy.handleIntent(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (RequestPermissionProxy requestPermissionProxy : requestPermissionProxies) {
            requestPermissionProxy.handlePermission(requestCode, permissions, grantResults);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }

    public void runDelayed(Runnable runnable, int delayMs) {
        handler.postDelayed(runnable, delayMs);
    }

    /**
     * Выполняет действие в главном потоке после истечения указанной задержки
     */
    public void runDelayed(Runnable runnable) {
        runDelayed(runnable, DEFAULT_DELAY);
    }

    /**
     * Выполняет действия в главном потоке
     */
    public void run(Runnable runnable) {
        handler.post(runnable);
    }

    /**
     * То же, что и {@link #runDelayed(Runnable, int)}, только с задержкой по умолчанию
     */
    public ActivityModule getActivityModule() {
        return new ActivityModule(getPersistentScreenScope());
    }

    /**
     * @return компонент экрана
     */
    public ScreenComponent getScreenComponent() {
        return getPersistentScreenScope().getObject(ScreenComponent.class);
    }

    /**
     * регистрирует прокси на события onActivityResult
     */
    @Override
    public void registerActivityResultProxy(ActivityResultProxy activityResultProxy) {
        this.activityResultProxies.add(activityResultProxy);
    }

    /**
     * регистрирует прокси на события onNewIntent
     */
    @Override
    public void registerNewIntentProxy(NewIntentProxy newIntentProxy) {
        this.newIntentProxies.add(newIntentProxy);
    }

    /**
     * регистрирует прокси на события onRequestPermissionsResult
     */
    @Override
    public void registerRequestPermissionProxy(RequestPermissionProxy requestPermissionProxy) {
        this.requestPermissionProxies.add(requestPermissionProxy);
    }

}