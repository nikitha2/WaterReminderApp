package com.example.android.background.sync;

import android.content.Context;

import com.example.android.background.utilities.NotificationUtils;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkerManagerScheduling extends Worker {
    Context context;
    public WorkerManagerScheduling(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context=context;
    }

    @NonNull
    @Override
    public Result doWork() {
        ReminderTasks.executeTask(context,ReminderTasks.CHARGING_COUNT);
        return Result.success();
    }
}
