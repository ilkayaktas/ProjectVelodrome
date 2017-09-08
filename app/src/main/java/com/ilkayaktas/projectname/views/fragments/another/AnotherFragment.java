package com.ilkayaktas.projectname.views.fragments.another;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.views.activities.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by iaktas on 14.03.2017.
 */

public class AnotherFragment extends BaseFragment implements AnotherFragmentMvpView {

    @Inject
    AnotherFragmentMvpPresenter<AnotherFragmentMvpView> mPresenter;

    public static AnotherFragment newInstance(){
        Bundle args = new Bundle();
        AnotherFragment fragment = new AnotherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);

        return view;
    }

    @Override
    protected int getTitle() {
        return 0;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_horizontal_item;
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

}
