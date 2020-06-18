/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.background.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.Executor;

import androidx.annotation.Nullable;

public class WaterReminderService extends Service {

    private Executor executor;
    //MyApplication myApplication;
    public WaterReminderService() {
        super();
    }
    public WaterReminderService(Executor executor) {
        super();
        this.executor=executor;
    }
    @Override
    public void onCreate() {
       executor= MyApplication.getInstance().executorService;
       super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String action = intent.getAction();
                ReminderTasks.executeTask(getBaseContext(), action);
            }
        });
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}