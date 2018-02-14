package com.ilkayaktas.projectname.views.activities.animations;


import com.ilkayaktas.projectname.views.activities.base.BasePresenter;

/**
 * Created by ilkay on 02/08/2017.
 */

public class AnimationPresenter<V extends AnimationMvpView> extends BasePresenter<V>
		implements AnimationMvpPresenter<V> {
	public AnimationPresenter(com.ilkayaktas.projectname.controller.IDataManager IDataManager) {
		super(IDataManager);
	}
}
