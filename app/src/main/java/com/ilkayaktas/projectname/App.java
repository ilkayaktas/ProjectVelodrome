package com.ilkayaktas.projectname;

import android.app.Application;
import com.google.android.gms.ads.MobileAds;
import com.ilkayaktas.projectname.controller.IDataManager;
import com.ilkayaktas.projectname.di.components.ApplicationComponent;
import com.ilkayaktas.projectname.di.components.DaggerApplicationComponent;
import com.ilkayaktas.projectname.di.modules.ApplicationModule;
import io.realm.Realm;

import javax.inject.Inject;

public class App extends Application {
	
	ApplicationComponent appComponent;
	
	@Inject
	IDataManager mIDataManager;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Realm.init(this);

		MobileAds.initialize(this, BuildConfig.ADMOB_APP_ID);

		initializeInjector();
		
	}
	
	private void initializeInjector(){
		appComponent = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.build();
		
		appComponent.inject(this);
		
	}
	
	public ApplicationComponent getAppComponent(){
		return appComponent;
	}
	
}