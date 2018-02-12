package com.ilkayaktas.projectname.views.activities.bottomnavigation;


import com.ilkayaktas.projectname.views.activities.base.BasePresenter;

/**
 * Created by ilkay on 02/08/2017.
 */

public class BottomNavigationPresenter<V extends BottomNavigationMvpView> extends BasePresenter<V>
		implements BottomNavigationMvpPresenter<V> {
	public BottomNavigationPresenter(com.ilkayaktas.projectname.controller.IDataManager IDataManager) {
		super(IDataManager);
	}
}
