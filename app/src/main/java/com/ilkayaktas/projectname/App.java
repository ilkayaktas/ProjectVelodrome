package com.ilkayaktas.projectname;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
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

		// Setup handler for uncaught exceptions.
		Thread.setDefaultUncaughtExceptionHandler (this::handleUncaughtException);
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

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	public void handleUncaughtException (Thread thread, Throwable e)
	{
		Thread.UncaughtExceptionHandler uch = Thread.getDefaultUncaughtExceptionHandler();
		e.printStackTrace(); // not all Android versions will print the stack trace automatically

		System.out.println("UncaughtException is handled!");

		System.exit(-1);
	}
}