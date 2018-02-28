package com.ilkayaktas.projectname.views.activities.another;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by ilkay on 02/08/2017.
 * TODO: DO NOT EDIT THIS CLASS SINCE THIS IS A TEMPLATE CLASS.
 */

public class AnotherActivity extends BaseActivity implements AnotherMvpView {
	
	@Inject
	AnotherMvpPresenter<AnotherMvpView> mPresenter;

	@BindView(R.id.toolbar_title) TextView toolbarTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActivityComponent().inject(this);
		
		setUnBinder(ButterKnife.bind(this));
		
		// Attach presenter
		mPresenter.onAttach(AnotherActivity.this);

		initUI();
	}

	@Override
	protected int getActivityLayout() {
		return R.layout.activity_another;
	}

	@Override
	protected void initUI() {
		setFont();
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
}
