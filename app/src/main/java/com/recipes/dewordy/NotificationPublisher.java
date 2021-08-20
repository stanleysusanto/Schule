package com.recipes.dewordy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Paulstanley on 8/27/16.
 */
public class NotificationPublisher extends BroadcastReceiver {

    final String TAG = "NotificationPublisher";

    public void onReceive(Context context, Intent intent) {

        Intent service = new Intent(context, NotificationAlarmService.class);
        service.putExtra("notifId", intent.getIntExtra("notifId", 0));
        context.startService(service);
    }
}
