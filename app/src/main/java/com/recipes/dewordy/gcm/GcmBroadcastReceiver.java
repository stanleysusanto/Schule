package com.recipes.dewordy.gcm;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.EditText;
import android.widget.Toast;

import com.recipes.dewordy.LoginState;
import com.recipes.dewordy.MainActivity;
import com.recipes.dewordy.MainActivity3;
import com.recipes.dewordy.R;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Paulstanley on 4/2/16.
 */
public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ComponentName comp = new ComponentName(context.getPackageName(),
                GcmIntentService.class.getName());
        String title = intent.getExtras().getString("title");
        String source = intent.getExtras().getString("source");
        String senin = intent.getExtras().getString("senin");
        String selasa = intent.getExtras().getString("selasa");
        String rabu = intent.getExtras().getString("rabu");
        String kamis = intent.getExtras().getString("kamis");
        String jumat = intent.getExtras().getString("jumat");
        String namapost = intent.getExtras().getString("namapost");

        if(source.equals("like")) {

//            Bundle to = new Bundle();
//            MainActivity3.getInstance().changeFragment(FragmentsAvailable.Profile, to);
            Intent to = new Intent(context, MainActivity3.class);
            to.putExtra("like", true);
            startWakefulService(context, (intent.setComponent(comp)));
            setResultCode(Activity.RESULT_OK);
            Toast.makeText(context, "new push notification", Toast.LENGTH_LONG).show();
            NotificationUtils notificationUtils = new NotificationUtils(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            notificationUtils.showNotificationMessage(title, "someone liked your post", new Timestamp(Calendar.getInstance().getTimeInMillis()).toString(), to);

        }

        if(source.equals("discussion")) {

            if(!namapost.equals(LoginState.getUsername())) {
//                Bundle to = new Bundle();
//                MainActivity3.getInstance().changeFragment(FragmentsAvailable.FIFTH_FRAGMENT, to);
                Intent to = new Intent(context, MainActivity3.class);
                to.putExtra("notif", true);
                startWakefulService(context, (intent.setComponent(comp)));
                setResultCode(Activity.RESULT_OK);
                Toast.makeText(context, "new push notification", Toast.LENGTH_LONG).show();
                NotificationUtils notificationUtils = new NotificationUtils(context);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                notificationUtils.showNotificationMessage(title, "someone posts in the same topic as you ", new Timestamp(Calendar.getInstance().getTimeInMillis()).toString(), to);
            }
        }

        if(source.equals("reminder")) {


            final Dialog dialog = new Dialog(MainActivity.getInstance());
            dialog.setContentView(R.layout.edit_reminder);
            dialog.setTitle("dcdcdscds");

            final EditText editHari1 = (EditText) dialog.findViewById(R.id.editSenin);
            final EditText editHari2 = (EditText) dialog.findViewById(R.id.editSelasa);
            final EditText editHari3 = (EditText) dialog.findViewById(R.id.editRabu);
            final EditText editHari4 = (EditText) dialog.findViewById(R.id.editKamis);
            final EditText editHari5 = (EditText) dialog.findViewById(R.id.editJumat);

            editHari1.setText(senin);
            editHari2.setText(selasa);
            editHari3.setText(rabu);
            editHari4.setText(kamis);
            editHari5.setText(jumat);

            dialog.show();


        }
    }
}