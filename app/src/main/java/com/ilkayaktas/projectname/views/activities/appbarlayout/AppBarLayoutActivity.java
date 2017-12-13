package com.ilkayaktas.projectname.views.activities.appbarlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.controller.services.ads.MobssAds;
import com.ilkayaktas.projectname.controller.services.ads.MobssAdsBuilder;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;
import com.ilkayaktas.projectname.views.activities.home.MainActivity;
import com.ilkayaktas.projectname.views.adapters.ViewPagerAdapter;
import com.ilkayaktas.projectname.views.widgets.dialogs.rateme.Config;
import com.ilkayaktas.projectname.views.widgets.dialogs.rateme.RateMe;

import javax.inject.Inject;

public class AppBarLayoutActivity extends BaseActivity implements AppBarLayoutMvpView {

	private static final String TAG = "MainActivity";
	private RewardedVideoAd rewardedVideoAd = null;
	private String[] btcMarkets;

	@Inject
	AppBarLayoutMvpPresenter<AppBarLayoutMvpView> mPresenter;

	@BindView(R.id.adView) AdView adView;
	@BindView(R.id.toolbar_main) Toolbar mToolbar;
	@BindView(R.id.tablayout_main) TabLayout mTabLayout;
	@BindView(R.id.vp_main) ViewPager mViewPager;
	@BindView(R.id.toolbar_title) TextView toolbarTitle;
	@BindView(R.id.toolbar_settings) ImageButton toolbarSettings;
	@BindView(R.id.toolbar_mainicon) ImageButton toolbarMainIcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setSupportActionBar(mToolbar);

		getActivityComponent().inject(this);

		setUnBinder(ButterKnife.bind(this));

		RateMe.init(new Config(5, 10)); // 5 gün ya da 10 defa uygulama başlattıktan sonra

		// Attach presenter
		mPresenter.onAttach(AppBarLayoutActivity.this);

		initUI();

		initTabs();

		loadAds();
	}

	private void initUI(){
		setFont();
		setOnFavoritesClickAction();
		setOnMainIconClickAction();
	}

	private void initTabs(){
		btcMarkets = getResources().getStringArray(R.array.tablist);
		ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), btcMarkets);
		mViewPager.setAdapter(viewPagerAdapter);
		mViewPager.setOffscreenPageLimit(btcMarkets.length);
		mTabLayout.setupWithViewPager(mViewPager);
		mViewPager.addOnPageChangeListener(pageChangeListener);

		mViewPager.post(() -> pageChangeListener.onPageSelected(mViewPager.getCurrentItem()));
	}

	ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

		}

		@Override
		public void onPageSelected(int position) {
			((ViewPagerAdapter)mViewPager.getAdapter()).updateVisibleFragment(position);
		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	};

	private void setOnFavoritesClickAction(){
		toolbarSettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
	}

	private void setOnMainIconClickAction(){
		toolbarMainIcon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
	}

	@Override
	protected int getActivityLayout() {
		return R.layout.activity_appbarlayout;
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
		Intent intent = new Intent(context, AppBarLayoutActivity.class);
		return intent;
	}

	private void setFont(){
		toolbarTitle.setTypeface(fontRobotoThin);
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

	private void loadAds(){
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		// Load interstatial ads
		final MobssAds mobssAds = MobssAdsBuilder.instance().build();
		InterstitialAd interstitialAd = mobssAds.loadInterstitialAds(this);

		interstitialAd.setAdListener(new AdListener(){
			@Override
			public void onAdLoaded() {
				super.onAdLoaded();
				mobssAds.showInterstitialAds(interstitialAd);
				Log.d(TAG, "onAdLoaded: InterstatitialAds Loaded");
			}
		});


		// Load rewarded video ads
		rewardedVideoAd = mobssAds.loadRewardedVideoAds(this, rewardItem -> Log.d(TAG, "Duded Rewarded: type:"+rewardItem.getType()+" amount:"+rewardItem.getAmount()));
		rewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
			@Override
			public void onRewardedVideoAdLoaded() {
				mobssAds.showRewardedVideoAds(rewardedVideoAd);
			}

			@Override
			public void onRewardedVideoAdOpened() {

			}

			@Override
			public void onRewardedVideoStarted() {

			}

			@Override
			public void onRewardedVideoAdClosed() {

			}

			@Override
			public void onRewarded(RewardItem rewardItem) {

			}

			@Override
			public void onRewardedVideoAdLeftApplication() {

			}

			@Override
			public void onRewardedVideoAdFailedToLoad(int i) {

			}
		});

	}
}