package com.ilkayaktas.projectname.views.activities.another;


import com.ilkayaktas.projectname.views.activities.base.BasePresenter;

/**
 * Created by ilkay on 02/08/2017.
 */

public class AnotherPresenter<V extends AnotherMvpView> extends BasePresenter<V>
		implements AnotherMvpPresenter<V> {
	public AnotherPresenter(com.ilkayaktas.projectname.controller.IDataManager IDataManager) {
		super(IDataManager);
	}
}
