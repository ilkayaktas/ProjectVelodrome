package com.ilkayaktas.projectname.views.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RemoteViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.controller.alarms.dailynotification.DailyNotificationAlarm;
import com.ilkayaktas.projectname.controller.services.MobssPeriodicNotificationTimerService;
import com.ilkayaktas.projectname.controller.services.ads.MobssAds;
import com.ilkayaktas.projectname.controller.services.ads.MobssAdsBuilder;
import com.ilkayaktas.projectname.utils.AppConstants;
import com.ilkayaktas.projectname.utils.DateUtils;
import com.ilkayaktas.projectname.views.activities.another.AnotherActivity;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;
import com.ilkayaktas.projectname.views.widgets.dialogs.rateme.Config;
import com.ilkayaktas.projectname.views.widgets.dialogs.rateme.RateMe;
import com.ilkayaktas.projectname.views.widgets.notification.MobssNotification;
import com.ilkayaktas.projectname.views.widgets.notification.MobssNotificationBuilder;

import javax.inject.Inject;
import java.util.Calendar;

public class MainActivity extends BaseActivity implements MainMvpView {

	private static final String TAG = "MainActivity";
	private MobssAds mobssAds;
	private MobssAds mobssAds1;
	private MobssAds mobssAds2;
	private InterstitialAd interstitialAd = null;
	private RewardedVideoAd rewardedVideoAd = null;

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

		MobileAds.initialize(this, AppConstants.ADMOB_APP_ID);

		// Load banner ads
		MobssAdsBuilder.instance()./*adUnitId(AppConstants.ADMOB_AD_UNIT_ID).*/build().loadBannerAds(this);

		// Load interstatial ads
		mobssAds = MobssAdsBuilder.instance()./*adUnitId(AppConstants.ADMOB_INTERSTITIAL_AD_UNIT_ID).*/build();
		interstitialAd = mobssAds.loadInterstitialAds(this);

		mobssAds1 = MobssAdsBuilder.instance()./*adUnitId(AppConstants.ADMOB_REWARDEDVIDEO_AD_UNIT_ID).*/build();
		// Load rewarded video ads
		rewardedVideoAd = mobssAds1.loadRewardedVideoAds(this, rewardItem -> Log.d(TAG, "Duded Rewarded: type:"+rewardItem.getType()+" amount:"+rewardItem.getAmount()));

		mobssAds2 = MobssAdsBuilder.instance()./*adUnitId(AppConstants.ADMOB_AD_UNIT_ID).*/build();
		mobssAds2.loadNativeAdsContent(this, findViewById(R.id.fl_adplaceholder));

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
		rewardedVideoAd.destroy(this);
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		rewardedVideoAd.resume(this);
		super.onResume();
	}

	@Override
	protected void onPause() {
		rewardedVideoAd.pause(this);
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
				MobssPeriodicNotificationTimerService.class));
	}

	@OnClick(R.id.ib_main_showads)
	public void onShowInterstatialAds(View view){
		mobssAds.showInterstitialAds(interstitialAd);
	}

	@OnClick(R.id.ib_main_showrewadedads)
	public void onShowRewardedVideoAds(View view){
		mobssAds1.showRewardedVideoAds(rewardedVideoAd);
	}

	@OnClick(R.id.ib_main_setalarm)
	public void onSetAlarmButton(View view){
		Calendar calendar = DateUtils.getCalendar(12, 0);

		new DailyNotificationAlarm(this).set(calendar);

		Log.d(TAG, "onSetAlarmButton: Alarm is set to " + calendar.getTime().toString());
	}

	@OnClick(R.id.ib_main_openotheractivity)
	public void onOpenOtherACtivity(View view){
		startActivity(AnotherActivity.class);
	}
}