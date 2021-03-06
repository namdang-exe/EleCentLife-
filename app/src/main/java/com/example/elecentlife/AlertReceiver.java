package com.example.elecentlife;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Notification notificationHelper = new Notification(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.manager().notify(1, nb.build());
    }
}

