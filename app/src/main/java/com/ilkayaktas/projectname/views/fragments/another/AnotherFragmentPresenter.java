package com.ilkayaktas.projectname.views.fragments.another;


import com.ilkayaktas.projectname.views.activities.base.BasePresenter;

/**
 * Created by iaktas on 14.03.2017.
 */

public class AnotherFragmentPresenter<V extends AnotherFragmentMvpView> extends BasePresenter<V> implements AnotherFragmentMvpPresenter<V> {
    public AnotherFragmentPresenter(com.ilkayaktas.projectname.controller.IDataManager dataManager) {
        super(dataManager);
    }

}
