package com.ilkayaktas.projectname.di.components;


import android.app.Application;
import android.content.Context;
import com.ilkayaktas.projectname.App;
import com.ilkayaktas.projectname.controller.IDataManager;
import com.ilkayaktas.projectname.controller.alarms.dailynotification.MobssCustomNotificationService;
import com.ilkayaktas.projectname.di.annotations.ApplicationContext;
import com.ilkayaktas.projectname.di.modules.ApplicationModule;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by ilkay on 26/02/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);

    void inject(MobssCustomNotificationService service);

    @ApplicationContext
    Context context();
    
    Application application();
    
    IDataManager getDataManager();
    
}
