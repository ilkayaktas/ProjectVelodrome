package com.ilkayaktas.projectname.views.activities.recyclershowcase;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;
import com.ilkayaktas.projectname.views.adapters.RecyclerViewAdapter;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * Created by ilkay on 02/08/2017.
 */

public class RecyclerViewActivity extends BaseActivity implements RecyclerViewMvpView {

	@Inject
	RecyclerViewMvpPresenter<RecyclerViewMvpView> mPresenter;

	@BindView(R.id.toolbar_title) TextView toolbarTitle;
	@BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
	@BindView(R.id.rv_recycler) RecyclerView recyclerView;
	@BindView(R.id.adView) AdView adView;
	private RecyclerViewAdapter recyclerViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActivityComponent().inject(this);

		setUnBinder(ButterKnife.bind(this));

		// Attach presenter
		mPresenter.onAttach(RecyclerViewActivity.this);

		initUI();
	}

	@Override
	protected void initUI() {
		setFont();

		initRecylerView();

		swipeRefreshLayout.setOnRefreshListener(() -> {
			mPresenter.provideContent();
		});
	}

	private void initRecylerView(){

		recyclerViewAdapter = new RecyclerViewAdapter(this, Arrays.asList("One", "Two", "Three"), recyclerView);

		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(recyclerViewAdapter);
	}

	@Override
	protected int getActivityLayout() {
		return R.layout.activity_recycler;
	}

	@Override
	protected void onDestroy() {
		mPresenter.onDetach();
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			finish();

			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	private void setFont(){
		toolbarTitle.setTypeface(fontGothic);
	}

	private void loadAdsBanner() {
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}

	@Override
	public void updateUI(String text) {
		((RecyclerViewAdapter)recyclerView.getAdapter()).addNewItem(text);
		swipeRefreshLayout.setRefreshing(false);
	}
}
