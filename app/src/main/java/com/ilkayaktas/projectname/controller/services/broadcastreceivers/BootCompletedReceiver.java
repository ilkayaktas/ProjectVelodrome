package com.ilkayaktas.projectname.controller.services.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.ilkayaktas.projectname.controller.alarms.dailynotification.DailyNotificationAlarm;
import com.ilkayaktas.projectname.controller.services.MobssPeriodicNotificationTimerService;

/**
 * Created by iaktas on 23.11.2017 at 08:13.
 */

public class BootCompletedReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent ıntent) {
        Log.i("APPLICATION TEMPLATE", "onReceive: Boot is completed ");

//        startPeriodicService(context);

//        setAlarm(context);
    }

    private void startPeriodicService(Context context){
        Intent intent = new Intent(context,MobssPeriodicNotificationTimerService.class);
        context.startService(intent);

    }

    private void setAlarm(Context context){
        new DailyNotificationAlarm(context).setAt12();
    }
}
