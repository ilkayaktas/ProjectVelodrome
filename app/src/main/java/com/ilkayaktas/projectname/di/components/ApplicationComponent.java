package com.ilkayaktas.projectname.di.components;


import android.app.Application;
import android.content.Context;

import com.ilkayaktas.projectname.App;
import com.ilkayaktas.projectname.controller.IDataManager;
import com.ilkayaktas.projectname.di.annotations.ApplicationContext;
import com.ilkayaktas.projectname.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ilkay on 26/02/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);
    
    @ApplicationContext
    Context context();
    
    Application application();
    
    IDataManager getDataManager();
    
}
