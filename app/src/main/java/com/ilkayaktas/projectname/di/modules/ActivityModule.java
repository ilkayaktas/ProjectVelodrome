package com.ilkayaktas.projectname.di.modules;

import android.app.Activity;
import android.graphics.Typeface;

import com.ilkayaktas.projectname.controller.IDataManager;
import com.ilkayaktas.projectname.controller.services.MobssAsyncTask;
import com.ilkayaktas.projectname.controller.strategy.Strategy;
import com.ilkayaktas.projectname.di.annotations.ActivityContext;
import com.ilkayaktas.projectname.di.annotations.PerActivity;
import com.ilkayaktas.projectname.views.activities.another.AnotherMvpPresenter;
import com.ilkayaktas.projectname.views.activities.another.AnotherMvpView;
import com.ilkayaktas.projectname.views.activities.another.AnotherPresenter;
import com.ilkayaktas.projectname.views.activities.home.MainMvpPresenter;
import com.ilkayaktas.projectname.views.activities.home.MainMvpView;
import com.ilkayaktas.projectname.views.activities.home.MainPresenter;
import com.ilkayaktas.projectname.views.activities.splash.SplashScreenMvpPresenter;
import com.ilkayaktas.projectname.views.activities.splash.SplashScreenMvpView;
import com.ilkayaktas.projectname.views.activities.splash.SplashScreenPresenter;
import com.ilkayaktas.projectname.views.fragments.another.AnotherFragmentMvpPresenter;
import com.ilkayaktas.projectname.views.fragments.another.AnotherFragmentMvpView;
import com.ilkayaktas.projectname.views.fragments.another.AnotherFragmentPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ilkay on 10/03/2017.
 */

@Module
public class ActivityModule {
    Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    Typeface provideTypeface() {
        return Typeface.createFromAsset(activity.getAssets(), "fonts/Sketch.ttf");
    }

    @Provides
    @PerActivity
    @ActivityContext
    Typeface provideTypefaceForActivity() {
        return Typeface.createFromAsset(activity.getAssets(), "fonts/gothic.TTF");
    }

    @Provides
    @PerActivity
    SplashScreenMvpPresenter<SplashScreenMvpView> provideSplashScreenPresenter(IDataManager IDataManager) {
        return new SplashScreenPresenter<>(IDataManager);
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> providesMainPresenter(IDataManager IDataManager) {
        return new MainPresenter<>(IDataManager);
    }

    @Provides
    @PerActivity
    AnotherMvpPresenter<AnotherMvpView> providesSlideUpPanelPresenter(IDataManager IDataManager) {
        return new AnotherPresenter<>(IDataManager);
    }

    @Provides
    @PerActivity
    AnotherFragmentMvpPresenter<AnotherFragmentMvpView> providesAnotherMvpPresenter(IDataManager IDataManager) {
        return new AnotherFragmentPresenter<>(IDataManager);
    }

    @Provides
    @PerActivity
    MobssAsyncTask providesMobssAsyncTask(Activity activity, Strategy strategy) {
        return new MobssAsyncTask(activity, strategy);
    }

}
