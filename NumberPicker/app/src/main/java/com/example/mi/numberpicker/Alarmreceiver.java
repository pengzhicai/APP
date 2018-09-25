package com.example.mi.numberpicker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.Toast;

public class Alarmreceiver extends BroadcastReceiver {
    public static String REPEAING_KEY = "REPEAING";
    public static String REPEAING = "1";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String mRepeaing = intent.getStringExtra(REPEAING_KEY);
        if(mRepeaing.equals(REPEAING)) {
            Intent mintent =new Intent(context, Alarmreceiver.class);
            mintent.putExtra(Alarmreceiver.REPEAING_KEY, Alarmreceiver.REPEAING);
            PendingIntent sender = PendingIntent.getBroadcast(context, 0, mintent, 0);
            AlarmManager am = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
            //5秒一个周期，不停的发送广播
            // am.setRepeating(AlarmManager.RTC_WAKEUP, firstime, 4*1000, sender);
            // 设置精确时间
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 4*1000, sender);
            Toast.makeText(context, "这是重复闹钟 ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "这是不重复闹钟 ", Toast.LENGTH_SHORT).show();
        }
    }
}
