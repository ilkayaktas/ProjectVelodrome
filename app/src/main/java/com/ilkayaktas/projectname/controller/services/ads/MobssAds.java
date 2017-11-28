package com.ilkayaktas.projectname.controller.services.ads;

import android.view.ViewGroup;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.ilkayaktas.projectname.annotaionprocessing.annotations.BuilderPattern;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;

/**
 * Created by aselsan on 28.11.2017 at 22:10.
 */

@BuilderPattern
public class MobssAds {
    public String adUnitId = "/6499/example/banner";

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
    public void loadBannerAdd(BaseActivity activity){
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
}
