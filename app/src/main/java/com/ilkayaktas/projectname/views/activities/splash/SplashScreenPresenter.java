package com.ilkayaktas.projectname.views.activities.splash;


import com.ilkayaktas.projectname.views.activities.base.BasePresenter;

/**
 * Created by ilkay on 11/03/2017.
 */

public class SplashScreenPresenter <V extends SplashScreenMvpView> extends BasePresenter<V>
		implements SplashScreenMvpPresenter<V>{
	
	public SplashScreenPresenter(com.ilkayaktas.projectname.controller.IDataManager IDataManager) {
		super(IDataManager);
	}
	
}
