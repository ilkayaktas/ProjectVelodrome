package com.ilkayaktas.projectname.views.activities.adsshowcase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RemoteViews;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.controller.alarms.dailynotification.DailyNotificationAlarm;
import com.ilkayaktas.projectname.controller.services.MobssPeriodicNotificationTimerService;
import com.ilkayaktas.projectname.controller.services.ads.MobssAds;
import com.ilkayaktas.projectname.controller.services.ads.MobssAdsBuilder;
import com.ilkayaktas.projectname.utils.DateUtils;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;
import com.ilkayaktas.projectname.views.widgets.notification.MobssNotification;
import com.ilkayaktas.projectname.views.widgets.notification.MobssNotificationBuilder;

import javax.inject.Inject;
import java.util.Calendar;

/**
 * Created by ilkay on 02/08/2017.
 */

public class AdsShowcaseActivity extends BaseActivity implements AdsShowcaseMvpView {

	private static final String TAG = "AdsShowcaseActivity";
	private MobssAds mobssAds;
	private MobssAds mobssAds1;
	private MobssAds mobssAds2;
	private InterstitialAd interstitialAd = null;
	private RewardedVideoAd rewardedVideoAd = null;

	@Inject
	AdsShowcaseMvpPresenter<AdsShowcaseMvpView> mPresenter;

	@BindView(R.id.adView) AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActivityComponent().inject(this);
		
		setUnBinder(ButterKnife.bind(this));
		
		// Attach presenter
		mPresenter.onAttach(AdsShowcaseActivity.this);

		initUI();
	}

	@Override
	protected int getActivityLayout() {
		return R.layout.activity_showcase;
	}

	@Override
	protected void initUI() {
		Log.d(TAG, "initUI: ");

		loadAds();
	}

	@Override
	protected void onDestroy() {
		mPresenter.onDetach();
		if(rewardedVideoAd != null){
			rewardedVideoAd.destroy(this);
		}
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		if(rewardedVideoAd != null) {
			rewardedVideoAd.resume(this);
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		if(rewardedVideoAd != null) {
			rewardedVideoAd.pause(this);
		}
		super.onPause();
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
				.context(AdsShowcaseActivity.this)
				.invocationActivity(AdsShowcaseActivity.class)
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
				.invocationActivity(AdsShowcaseActivity.class)
				.remoteViews(contentView)
				.smallIcon(R.mipmap.ic_launcher)
				.build();

		notification.showNotification();
	}

	@OnClick(R.id.ib_main_startservice)
	public void onStartNotificationServiceButton(View view){
		startService(new Intent(AdsShowcaseActivity.this,
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
		Calendar calendar = DateUtils.getCalendar(14, 15);

		new DailyNotificationAlarm(this).set(calendar);

	}

	private void loadAds(){
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		// Load banner ads
		MobssAdsBuilder.instance().build().loadBannerAds(this);

		// Load interstatial ads
		mobssAds = MobssAdsBuilder.instance().build();
		interstitialAd = mobssAds.loadInterstitialAds(this);

		mobssAds1 = MobssAdsBuilder.instance().build();
		// Load rewarded video ads
		rewardedVideoAd = mobssAds1.loadRewardedVideoAds(this, rewardItem -> Log.d(TAG, "Duded Rewarded: type:"+rewardItem.getType()+" amount:"+rewardItem.getAmount()));

		mobssAds2 = MobssAdsBuilder.instance().build();
		mobssAds2.loadNativeAdsContent(this, findViewById(R.id.fl_adplaceholder));


	}
}
