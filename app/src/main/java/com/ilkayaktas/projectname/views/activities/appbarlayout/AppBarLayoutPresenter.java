package com.ilkayaktas.projectname.views.activities.appbarlayout;


import com.ilkayaktas.projectname.views.activities.base.BasePresenter;

/**
 * Created by ilkay on 12/03/2017.
 */

public class AppBarLayoutPresenter<V extends AppBarLayoutMvpView> extends BasePresenter<V>
		implements AppBarLayoutMvpPresenter<V> {

	public AppBarLayoutPresenter(com.ilkayaktas.projectname.controller.IDataManager IDataManager) {
		super(IDataManager);
	}
	
}
