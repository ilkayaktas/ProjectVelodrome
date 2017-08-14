package com.ilkayaktas.projectname.views.activities.splash;


import com.ilkayaktas.projectname.di.annotations.PerActivity;
import com.ilkayaktas.projectname.views.activities.base.MvpPresenter;

/**
 * Created by ilkay on 11/03/2017.
 */

@PerActivity
public interface SplashScreenMvpPresenter<V extends SplashScreenMvpView> extends MvpPresenter<V> {
}
