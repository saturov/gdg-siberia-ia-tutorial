package ru.gdg_siberia.instant_app_tutorial.app.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * App-level dagger scope
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFullApplication {
}