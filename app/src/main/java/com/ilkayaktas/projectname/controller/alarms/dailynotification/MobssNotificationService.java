package com.ilkayaktas.projectname.controller.alarms.dailynotification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.views.activities.home.MainActivity;
import com.ilkayaktas.projectname.views.widgets.notification.MobssNotification;
import com.ilkayaktas.projectname.views.widgets.notification.MobssNotificationBuilder;

/**
 * Created by iaktas on 23.11.2017 at 14:41.
 */

public class MobssNotificationService extends Service {
    private static final String TAG = "NotificationService";
    private MobssNotification notification;

    @Override
    public void onCreate() {
        super.onCreate();

        notification = MobssNotificationBuilder.instance()
                .context(MobssNotificationService.this)
                .invocationActivity(MainActivity.class)
                .title(TAG)
                .message("Notification message: " + System.currentTimeMillis())
                .smallIcon(R.mipmap.ic_launcher)
                .build();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand: MobssPeriodicNotificationService started!");

        notification.showNotification();

        return Service.START_STICKY;

    }
}
