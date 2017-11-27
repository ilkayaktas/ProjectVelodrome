package com.ilkayaktas.projectname.views.widgets.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.annotaionprocessing.annotations.BuilderPattern;

import java.util.Random;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by aselsan on 27.11.2017 at 16:29.
 */

@BuilderPattern
public class MobssNotification {
    public Context context = null;
    public String message = "Notification message";
    public String title = "Notification Title";
    public Class invocationActivity;
    public int smallIcon = R.mipmap.ic_launcher;
    public boolean onGoing = false;
    public int notificationId = -1;
    private Notification notification;

    public MobssNotification(){
        Intent intent = new Intent(context, invocationActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("message", message);
        PendingIntent contentIntent = PendingIntent.getActivity(context, generateRequestID(), intent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(smallIcon)
                .setContentText(message)
                .setContentTitle(title)
                .setContentIntent(contentIntent)
                .setOngoing(onGoing);
        notification = notificationBuilder.build();
    }

    public void showNotification() {
        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);


        nm.notify(getNotificationId(), notification);
    }

    private int generateRequestID(){
        return (int) System.currentTimeMillis();
    }

    private int getNotificationId(){
        if(notificationId == -1){
            notificationId = new Random().nextInt(9999 - 1000) + 1000;
        }

        return notificationId;
    }
}
