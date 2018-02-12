package com.ilkayaktas.projectname.views.activities.adsshowcase;


import com.ilkayaktas.projectname.views.activities.base.BasePresenter;

/**
 * Created by ilkay on 02/08/2017.
 */

public class AdsShowcasePresenter<V extends AdsShowcaseMvpView> extends BasePresenter<V>
		implements AdsShowcaseMvpPresenter<V> {
	public AdsShowcasePresenter(com.ilkayaktas.projectname.controller.IDataManager IDataManager) {
		super(IDataManager);
	}
}
