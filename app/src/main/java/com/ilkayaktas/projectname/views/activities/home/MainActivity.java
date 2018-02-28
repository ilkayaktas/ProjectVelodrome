package com.ilkayaktas.projectname.views.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.model.app.Dummy;
import com.ilkayaktas.projectname.views.activities.adsshowcase.AdsShowcaseActivity;
import com.ilkayaktas.projectname.views.activities.animations.AnimationActivity;
import com.ilkayaktas.projectname.views.activities.another.AnotherActivity;
import com.ilkayaktas.projectname.views.activities.appbarlayout.AppBarLayoutActivity;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;
import com.ilkayaktas.projectname.views.activities.bottomnavigation.BottomNavigationActivity;
import com.ilkayaktas.projectname.views.activities.recyclershowcase.RecyclerViewActivity;
import com.ilkayaktas.projectname.views.adapters.CustomSpinnerAdapter;
import com.ilkayaktas.projectname.views.widgets.dialogs.rateme.Config;
import com.ilkayaktas.projectname.views.widgets.dialogs.rateme.RateMe;

import javax.inject.Inject;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainMvpView {

	private static final String TAG = "MainActivity";

	@Inject
	MainMvpPresenter<MainMvpView> mPresenter;

	@BindView(R.id.spinner) Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActivityComponent().inject(this);

		setUnBinder(ButterKnife.bind(this));

		RateMe.init(new Config(5, 10)); // 5 gün ya da 10 defa uygulama başlattıktan sonra

		// Attach presenter
		mPresenter.onAttach(MainActivity.this);

		initUI();
	}

	@Override
	protected int getActivityLayout() {
		return R.layout.activity_main;
	}

	@Override
	protected void initUI() {
		Log.d(TAG, "initUI: ");
		CustomSpinnerAdapter userAdapter = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, new ArrayList<>());
		userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		userAdapter.add(new Dummy("Michael","Jackson"));
		userAdapter.add(new Dummy("David","Gilmour"));
		userAdapter.add(new Dummy("Rick","Wright"));
		spinner.setAdapter(userAdapter);
	}

	@Override
	protected void onStart() {
		super.onStart();
		RateMe.onStart(this);
		RateMe.showRateDialogIfNeeded(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		mPresenter.onDetach();
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	public static Intent getStartIntent(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		return intent;
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

	@OnClick(R.id.button2)
	public void onButton2Clicked(View v){
		startActivity(AnotherActivity.class);
	}

	@OnClick(R.id.button3)
	public void onButton3Clicked(View v){
		startActivity(AdsShowcaseActivity.class);
	}

	@OnClick(R.id.button4)
	public void onButton4Clicked(View v){
		startActivity(AppBarLayoutActivity.class);
	}

	@OnClick(R.id.button5)
	public void onButton5Clicked(View v){
		startActivity(BottomNavigationActivity.class);
	}

	@OnClick(R.id.button6)
	public void onButton6Clicked(View v){
		startActivity(RecyclerViewActivity.class);
	}

	@OnClick(R.id.button7)
	public void onButton7Clicked(View v){
		startActivity(AnimationActivity.class);
	}

	@OnClick(R.id.button8)
	public void onButton8Cliked(View v){
		Toast.makeText(this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
	}
}