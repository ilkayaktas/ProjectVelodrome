package com.ilkayaktas.projectname.views.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RemoteViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.controller.services.MobssPeriodicNotificationService;
import com.ilkayaktas.projectname.controller.services.ads.MobssAdsBuilder;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;
import com.ilkayaktas.projectname.views.widgets.dialogs.rateme.Config;
import com.ilkayaktas.projectname.views.widgets.dialogs.rateme.RateMe;
import com.ilkayaktas.projectname.views.widgets.notification.MobssNotification;
import com.ilkayaktas.projectname.views.widgets.notification.MobssNotificationBuilder;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView {

	private static final String TAG = "MainActivity";
	@Inject
	MainMvpPresenter<MainMvpView> mPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActivityComponent().inject(this);

		setUnBinder(ButterKnife.bind(this));

		RateMe.init(new Config(5, 10)); // 5 gün ya da 10 defa uygulama başlattıktan sonra

		// Attach presenter
		mPresenter.onAttach(MainActivity.this);

		// Add AdView
		MobssAdsBuilder.instance().build().loadBannerAdd(this);

	}

	@Override
	protected int getActivityLayout() {
		return R.layout.activity_main;
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

	@OnClick(R.id.ib_main_sendnotification)
	public void onSendNotificationButton(View view){
		MobssNotification notification = MobssNotificationBuilder.instance()
				.context(MainActivity.this)
				.invocationActivity(MainActivity.class)
				.title(TAG)
				.message("Notification message: " + System.currentTimeMillis())
				.smallIcon(R.mipmap.ic_launcher)
				.build();

		notification.showNotification();

		notification.showNotification();
	}

	@OnClick(R.id.ib_main_sendcustomnotification)
	public void onSendCustomNotificationButton(View view){

		RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification_custom);
		contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
		contentView.setTextViewText(R.id.title, "Custom notification");
		contentView.setTextViewText(R.id.text, "This is a custom layout");

		MobssNotification notification = MobssNotificationBuilder.instance()
				.context(this)
				.invocationActivity(MainActivity.class)
				.remoteViews(contentView)
				.smallIcon(R.mipmap.ic_launcher)
				.build();

		notification.showNotification();
	}

	@OnClick(R.id.ib_main_startservice)
	public void onStartNotificationServiceButton(View view){
		startService(new Intent(MainActivity.this,
				MobssPeriodicNotificationService.class));
	}


}