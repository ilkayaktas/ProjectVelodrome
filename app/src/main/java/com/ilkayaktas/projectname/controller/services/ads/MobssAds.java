package com.ilkayaktas.projectname.controller.services.ads;

import android.util.Log;
import android.view.ViewGroup;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.ilkayaktas.projectname.annotaionprocessing.annotations.BuilderPattern;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;

/**
 * Created by aselsan on 28.11.2017 at 22:10.
 */

@BuilderPattern
public class MobssAds {
    private static final String TAG = "MobssAds";
    public String adUnitId = null;

    /** If you want to add ads on layout, use below and inflate it.
         <com.google.android.gms.ads.AdView
             xmlns:ads="http://schemas.android.com/apk/res-auto"
             android:id="@+id/adView"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:layout_centerHorizontal="true"
             android:layout_alignParentBottom="true"
             ads:adSize="BANNER"
             ads:adUnitId="ca-app-pub-3940256099942544/6300978111">
         </com.google.android.gms.ads.AdView>
     * @param activity
     */
    public void loadBannerAds(BaseActivity activity){

        // Test ad
        if (adUnitId == null)adUnitId = "/6499/example/banner";

        // Create adView
        AdView adView = new AdView(activity);
        adView.setAdSize(AdSize.LARGE_BANNER);
        adView.setAdUnitId(adUnitId);

        // Ad into
        activity.addContentView(adView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                                    ViewGroup.LayoutParams.WRAP_CONTENT));
        // Load ads
        adView.loadAd(new AdRequest.Builder().build());

    }

    public InterstitialAd loadInterstitialAds(BaseActivity activity){
        // Test ad
        if (adUnitId == null)adUnitId = "/6499/example/interstitial";

        InterstitialAd mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(adUnitId);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdLoaded() {
                Log.d(TAG, "onAdLoaded: Ad is loaded.");
            }

        });

        return mInterstitialAd;
    }

    public void showInterstitialAds(InterstitialAd mInterstitialAd){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public RewardedVideoAd loadRewardedVideoAds(BaseActivity activity, OnRewardedListener onRewarded){
        if (adUnitId == null) adUnitId = "ca-app-pub-3940256099942544/5224354917";

        RewardedVideoAd  mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity);
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener(){
            @Override
            public void onRewardedVideoAdLoaded() {
                Log.d(TAG, "onRewardedVideoAdLoaded:");
            }

            @Override
            public void onRewardedVideoAdOpened() {
                Log.d(TAG, "onRewardedVideoAdOpened: ");
            }

            @Override
            public void onRewardedVideoStarted() {
                Log.d(TAG, "onRewardedVideoStarted: ");
            }

            @Override
            public void onRewardedVideoAdClosed() {
                Log.d(TAG, "onRewardedVideoAdClosed: ");
                mRewardedVideoAd.loadAd(adUnitId,
                        new AdRequest.Builder().build());
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                Log.d(TAG, "onRewarded: Vuhuuuuuu Rewarded");
                onRewarded.onRewarded(rewardItem);
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                Log.d(TAG, "onRewardedVideoAdLeftApplication: ");
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
                Log.d(TAG, "onRewardedVideoAdFailedToLoad: ");
            }
        });

        mRewardedVideoAd.loadAd(adUnitId,
                new AdRequest.Builder().build());

        return mRewardedVideoAd;
    }

    public void showRewardedVideoAds(RewardedVideoAd mRewardedVideoAd){
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }

    public interface OnRewardedListener{
        void onRewarded(RewardItem rewardItem);
    }
}
