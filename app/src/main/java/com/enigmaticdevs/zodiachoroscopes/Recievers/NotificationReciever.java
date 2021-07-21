package com.enigmaticdevs.zodiachoroscopes.Recievers;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.enigmaticdevs.zodiachoroscopes.R;
import com.enigmaticdevs.zodiachoroscopes.SplashActivity;

public class NotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Ran","RAN");
        intent=new Intent(context, SplashActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,100,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelId")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Reminder")
                .setContentText("Have you read your today's Zodiac?")
                .setSmallIcon(R.drawable.ic_notification)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(100, builder.build());
    }
}
