package com.ilkayaktas.projectname.controller.alarms.dailynotification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.views.activities.home.MainActivity;
import com.ilkayaktas.projectname.views.widgets.notification.MobssNotification;
import com.ilkayaktas.projectname.views.widgets.notification.MobssNotificationBuilder;

/**
 * Created by iaktas on 23.11.2017 at 14:41.
 */

public class MobssCustomNotificationService extends Service {
    private static final String TAG = "NotificationService";
    private MobssNotification notification;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: _________________");
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification_custom);
        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        contentView.setTextViewText(R.id.title, "Custom notification");
        contentView.setTextViewText(R.id.text, "This is a custom layout");

        notification = MobssNotificationBuilder.instance()
                .context(this)
                .invocationActivity(MainActivity.class)
                .remoteViews(contentView)
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

        Log.d(TAG, "onStartCommand: Custom notification is sent on time!");

        notification.showNotification();

        return Service.START_STICKY;

    }
}
