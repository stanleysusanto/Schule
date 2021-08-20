package com.recipes.dewordy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.recipes.dewordy.model.Reskre;
import com.recipes.dewordy.rest.RestClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Paulstanley on 8/27/16.
 */
public class NotificationAlarmService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flag, int startId)
    {
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, MainActivity3.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, intent.getIntExtra("notifId", 0), mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Reminder");

        RestClient.get().getReskre(SharedPreference.getuserid(MainActivity3.getInstance().getApplicationContext()), new Callback<Reskre>() {
            @Override
            public void success(Reskre reskre, Response response) {
                String a = "adfdfds";
//                String senin = reskre.getSenin();
//                String selasa = reskre.getSelasa();
//                String rabu = reskre.getRabu();
//                String kamis = reskre.getKamis();
//                String jumat = reskre.getJumat();
//                builder.setContentText(senin + "\n" + selasa + "\n" + rabu + "\n" + kamis + "\n" + jumat + "\n" );
                builder.setContentText("aaaa");
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
//        builder.setContentText("Notify Me \n  cdscdscdscds");
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.about);
        builder.setContentIntent(pendingIntent);

        notificationManager.notify(intent.getIntExtra("notifId", 0), builder.build());

        return super.onStartCommand(intent, flag, startId);
    }




}
