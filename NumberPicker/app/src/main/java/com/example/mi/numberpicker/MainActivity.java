package com.example.mi.numberpicker;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends Activity implements NumberPicker.OnValueChangeListener,NumberPicker.OnScrollListener,NumberPicker.Formatter {
    NumberPicker hourPicker;
    NumberPicker minutePicker;
    NumberPicker secondPicker;
    Button timeStart;
    Button timeStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hourPicker = findViewById(R.id.hourpicker);
        minutePicker = findViewById(R.id.minuteicker);
        secondPicker = findViewById(R.id.secondteicker);
        timeStart = findViewById(R.id.timeStart);
        timeStop = findViewById(R.id.timeStart);
        init();

        // https://www.cnblogs.com/zyw-205520/p/4040923.html
//        Intent intent2 = new Intent(this, Alarmreceiver.class);
//        PendingIntent pd = PendingIntent.getBroadcast(getApplicationContext(), 0, intent2, PendingIntent.FLAG_ONE_SHOT);
//        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
//        long triggerTime = SystemClock.elapsedRealtime() + 5*1000;
//        am.set(AlarmManager.ELAPSED_REALTIME, triggerTime, pd);
        // AlarmManager.RTC，硬件闹钟，不唤醒手机（也可能是其它设备）休眠；当手机休眠时不发射闹钟。
        // AlarmManager.RTC_WAKEUP，硬件闹钟，当闹钟发射时唤醒手机休眠；
        // AlarmManager.ELAPSED_REALTIME，真实时间流逝闹钟，不唤醒手机休眠；当手机休眠时不发射闹钟。
        // AlarmManager.ELAPSED_REALTIME_WAKEUP，真实时间流逝闹钟，当闹钟发射时唤醒手机休眠；

        Intent intent =new Intent(this, Alarmreceiver.class);
        intent.putExtra(Alarmreceiver.REPEAING_KEY, Alarmreceiver.REPEAING);
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
         //开始时间
        long firstime = SystemClock.elapsedRealtime();
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        //5秒一个周期，不停的发送广播
        // am.setRepeating(AlarmManager.RTC_WAKEUP, firstime, 4*1000, sender);
        // 设置精确时间
        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 4*1000, sender);

        // am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,firstime, 4*1000, sender);
        // am.cancel(sender);
    }

    @Override
    public String format(int value) {
        String tmpStr = String.valueOf(value);
        if (value < 10) {
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }

    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {
//        switch (scrollState) {
//            case NumberPicker.OnScrollListener.SCROLL_STATE_FLING:
//                Toast.makeText(this, "后续滑动(飞呀飞，根本停下来)", Toast.LENGTH_LONG)
//                        .show();
//                break;
//            case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE:
//                Toast.makeText(this, "不滑动", Toast.LENGTH_LONG).show();
//                break;
//            case NumberPicker.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
//                Toast.makeText(this, "滑动中", Toast.LENGTH_LONG)
//                        .show();
//                break;
//        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Toast.makeText(this,"原来的值 " + oldVal + "--新值: " + newVal, Toast.LENGTH_SHORT).show();
    }
    private void init() {
        hourPicker.setFormatter(this);
        hourPicker.setOnValueChangedListener(this);
        hourPicker.setOnScrollListener(this);
        hourPicker.setMaxValue(24);
        hourPicker.setMinValue(0);
        hourPicker.setValue(9);

        minutePicker.setFormatter(this);
        minutePicker.setOnValueChangedListener(this);
        minutePicker.setOnScrollListener(this);
        minutePicker.setMaxValue(60);
        minutePicker.setMinValue(0);
        minutePicker.setValue(49);

        secondPicker.setFormatter(this);
        secondPicker.setOnValueChangedListener(this);
        secondPicker.setOnScrollListener(this);
        secondPicker.setMaxValue(60);
        secondPicker.setMinValue(0);
        secondPicker.setValue(49);
    }
}
