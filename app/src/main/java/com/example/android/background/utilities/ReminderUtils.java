package com.example.android.background.utilities;

import android.content.Context;

import com.example.android.background.MainActivity;
import com.example.android.background.sync.WorkerManagerScheduling;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

public class ReminderUtils {
    public static final String CHARGING_REMAINDER = "remindUserBecauseCharging-count";
    private static final int REMINDER_FLEX_INTERVAL_MINUTES = 15;
    private static final int REMINDER_REPEAT_INTERVAL_MINUTES = 1/4;

    private static Context context;
    public ReminderUtils(Context context) {
        this.context=context;
    }

    public static void scheduleChargingReminder(MainActivity mainActivity) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)  // WIFI
                .setRequiresCharging(true)                      // REQUIRE CHARGING
                .build();
        WorkRequest remindUserBecauseCharging =new PeriodicWorkRequest.Builder(WorkerManagerScheduling.class,REMINDER_REPEAT_INTERVAL_MINUTES, TimeUnit.HOURS,REMINDER_FLEX_INTERVAL_MINUTES, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .addTag(CHARGING_REMAINDER)
                .build();

        WorkManager.getInstance(context).enqueue(remindUserBecauseCharging);

    }
}
