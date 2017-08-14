package com.ilkayaktas.projectname.views.activities.home;


import com.ilkayaktas.projectname.views.activities.base.BasePresenter;

/**
 * Created by ilkay on 12/03/2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
		implements MainMvpPresenter<V>{
	
	public MainPresenter(com.ilkayaktas.projectname.controller.IDataManager IDataManager) {
		super(IDataManager);
	}
	
}
