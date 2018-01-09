package com.ilkayaktas.projectname.controller.alarms.dailynotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.ilkayaktas.projectname.utils.DateUtils;

import java.util.Calendar;

/**
 * Created by iaktas on 3.12.2017 at 21:36.
 */

public class DailyNotificationAlarm {
    private static final String TAG = "DailyNotificationAlarm";
    public static final int REQUEST_CODE = 147411;
    private AlarmManager alarmManager = null;
    private PendingIntent pendingIntent = null;
    private Context context = null;
    private final Intent intent;

    public DailyNotificationAlarm(Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        intent = new Intent(context, MobssCustomNotificationService.class);

    }

    public void set(Calendar calendar){

        // alarm time is in past, set to next day
        if(calendar.getTime().compareTo(Calendar.getInstance().getTime()) < 0){
            calendar.setTimeInMillis( calendar.getTimeInMillis() + AlarmManager.INTERVAL_DAY);
        }

        if(PendingIntent.getService(context, REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE) == null){

            pendingIntent = PendingIntent.getService(context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Sets an alarm - note this alarm will be lost if the phone is turned off and on again
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

            Log.d(TAG, "Alarm is set: " + calendar.getTime().toString());
        } else{
            Log.d(TAG, "Alarm is already set!");
        }
    }

    public void setAt01(){
        set(DateUtils.getCalendar(1,0));
    }

    public void setAt02(){
        set(DateUtils.getCalendar(2,0));
    }

    public void setAt03(){
        set(DateUtils.getCalendar(3,0));
    }

    public void setAt04(){
        set(DateUtils.getCalendar(4,0));
    }

    public void setAt05(){
        set(DateUtils.getCalendar(5,0));
    }

    public void setAt06(){
        set(DateUtils.getCalendar(6,0));
    }

    public void setAt07(){
        set(DateUtils.getCalendar(7,0));
    }

    public void setAt08(){
        set(DateUtils.getCalendar(8,0));
    }

    public void setAt09(){
        set(DateUtils.getCalendar(9,0));
    }

    public void setAt10(){
        set(DateUtils.getCalendar(10,0));
    }

    public void setAt11(){
        set(DateUtils.getCalendar(11,0));
    }

    public void setAt12(){
        set(DateUtils.getCalendar(12,0));
    }

    public void setAt13(){
        set(DateUtils.getCalendar(13,0));
    }

    public void setAt14(){
        set(DateUtils.getCalendar(14,0));
    }

    public void setAt15(){
        set(DateUtils.getCalendar(15,0));
    }

    public void setAt16(){
        set(DateUtils.getCalendar(16,0));
    }

    public void setAt17(){
        set(DateUtils.getCalendar(17,0));
    }

    public void setAt18(){
        set(DateUtils.getCalendar(18,0));
    }

    public void setAt19(){
        set(DateUtils.getCalendar(19,0));
    }

    public void setAt20(){
        set(DateUtils.getCalendar(20,0));
    }

    public void setAt21(){
        set(DateUtils.getCalendar(21,0));
    }

    public void setAt22(){
        set(DateUtils.getCalendar(22,0));
    }

    public void setAt23(){
        set(DateUtils.getCalendar(23,0));
    }

    public void setAt24(){
        set(DateUtils.getCalendar(0,0));
    }

    public void cancel(){
        if (alarmManager != null && pendingIntent != null){
            alarmManager.cancel(pendingIntent);
        }
    }
}
