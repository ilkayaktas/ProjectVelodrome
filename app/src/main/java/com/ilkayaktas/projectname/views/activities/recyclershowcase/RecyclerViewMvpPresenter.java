package com.ilkayaktas.projectname.views.activities.recyclershowcase;

import com.ilkayaktas.projectname.views.activities.base.MvpPresenter;

/**
 * Created by ilkay on 02/08/2017.
 */

public interface RecyclerViewMvpPresenter<V extends RecyclerViewMvpView> extends MvpPresenter<V> {
    void provideContent();
}
