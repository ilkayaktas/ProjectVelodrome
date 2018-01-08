package com.ilkayaktas.projectname.views.activities.recyclershowcase;


import com.ilkayaktas.projectname.views.activities.base.BasePresenter;

import java.util.Random;

/**
 * Created by ilkay on 02/08/2017.
 */

public class RecyclerViewPresenter<V extends RecyclerViewMvpView> extends BasePresenter<V>
		implements RecyclerViewMvpPresenter<V> {
	public RecyclerViewPresenter(com.ilkayaktas.projectname.controller.IDataManager IDataManager) {
		super(IDataManager);
	}

	@Override
	public void provideContent() {
		String str = String.valueOf(new Random().nextDouble());
		getMvpView().updateUI(str);
	}
}
