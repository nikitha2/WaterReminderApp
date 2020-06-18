package com.example.android.background.sync;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.widget.Toast;

import com.example.android.background.MainActivity;

public class BroadcastReceiver extends android.content.BroadcastReceiver {
    Context context;
    MainActivity mainActivity=new MainActivity();
    public BroadcastReceiver(Context context) {
        this.context=context;
    }

    public BroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_POWER_CONNECTED.equalsIgnoreCase(intent.getAction())){
            Toast.makeText(context,"charging",Toast.LENGTH_SHORT).show();
            mainActivity.isCharging(true);
        }
        else  if(Intent.ACTION_POWER_DISCONNECTED.equalsIgnoreCase(intent.getAction())){
            Toast.makeText(context,"charging",Toast.LENGTH_SHORT).show();
            mainActivity.isCharging(false);
        }

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Toast.makeText(context, "Boot completed="+ intent.getComponent(), Toast.LENGTH_SHORT).show();
            intent.getComponent();

        }
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );
            if (noConnectivity) {
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            }
        }
        // connectivity cannot be heard by broadcastreciever. for battery efficiency.
        /*if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            Toast.makeText(context, "Connectivity changed", Toast.LENGTH_SHORT).show();
        }*/
    }
}
