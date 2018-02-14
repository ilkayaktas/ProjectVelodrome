/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.ilkayaktas.projectname.di.components;


import com.ilkayaktas.projectname.di.annotations.PerActivity;
import com.ilkayaktas.projectname.di.modules.ActivityModule;
import com.ilkayaktas.projectname.views.activities.adsshowcase.AdsShowcaseActivity;
import com.ilkayaktas.projectname.views.activities.animations.AnimationActivity;
import com.ilkayaktas.projectname.views.activities.another.AnotherActivity;
import com.ilkayaktas.projectname.views.activities.appbarlayout.AppBarLayoutActivity;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;
import com.ilkayaktas.projectname.views.activities.bottomnavigation.BottomNavigationActivity;
import com.ilkayaktas.projectname.views.activities.home.MainActivity;
import com.ilkayaktas.projectname.views.activities.recyclershowcase.RecyclerViewActivity;
import com.ilkayaktas.projectname.views.activities.splash.SplashScreenActivity;
import com.ilkayaktas.projectname.views.fragments.another.AnotherFragment;
import dagger.Component;

/**
 * Created by iaktas on 24/04/17.
 */

@PerActivity
@Component(dependencies = com.ilkayaktas.projectname.di.components.ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);

    void inject(MainActivity activity);
    
    void inject(SplashScreenActivity activity);

    void inject(AnotherActivity activity);

    void inject(AnotherFragment fragment);

    void inject(AppBarLayoutActivity activity);

    void inject(RecyclerViewActivity activity);

    void inject(BottomNavigationActivity activity);

    void inject(AdsShowcaseActivity activity);

    void inject(AnimationActivity activity);
}
