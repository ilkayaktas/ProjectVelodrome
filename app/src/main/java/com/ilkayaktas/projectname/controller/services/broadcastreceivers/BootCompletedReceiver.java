package com.ilkayaktas.projectname.controller.services.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.ilkayaktas.projectname.controller.services.MobssPeriodicNotificationService;

/**
 * Created by iaktas on 23.11.2017 at 08:13.
 */

public class BootCompletedReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent ıntent) {
        Intent intent = new Intent(context,MobssPeriodicNotificationService.class);
        context.startService(intent);
        Log.i("Autostart", "started. başladı ellam");

    }
}
