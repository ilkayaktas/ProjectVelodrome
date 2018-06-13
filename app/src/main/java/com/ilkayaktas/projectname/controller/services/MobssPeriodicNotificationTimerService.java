package com.ilkayaktas.projectname.controller.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.views.activities.home.MainActivity;
import com.ilkayaktas.projectname.views.widgets.notification.MobssNotification;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by iaktas on 23.11.2017 at 14:41.
 */

public class MobssPeriodicNotificationTimerService extends Service {
    private static final String TAG = "MobssPeriodicNotificationService";
    private Timer alarmTimer;
    public TimerTask timerTask;
    public
    int ilkay;

    @Override
    public void onCreate() {
        super.onCreate();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                MobssNotification notification = MobssNotification.builder()
                        .context(MobssPeriodicNotificationTimerService.this)
                        .invocationActivity(MainActivity.class)
                        .title(TAG)
                        .message("Notification message: " + System.currentTimeMillis())
                        .smallIcon(R.mipmap.ic_launcher)
                        .build();

                notification.showNotification();
            }
        };
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand: MobssPeriodicNotificationService started!");
        if (alarmTimer != null) {
            alarmTimer.cancel();
        }
        alarmTimer = new Timer();
        alarmTimer.schedule(timerTask, 0l, 10000);

        return Service.START_STICKY;

    }
}
