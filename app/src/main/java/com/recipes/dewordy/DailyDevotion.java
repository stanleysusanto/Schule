package com.recipes.dewordy;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recipes.dewordy.model.category.CategoryD;
import com.recipes.dewordy.rest.RestClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Paulstanley on 7/17/16.
 */
public class DailyDevotion extends Fragment{

    TextView judul, nats, bacaanemas, tanggal, isiparagraf1, isiparagraf2, isiparagraf3, isiparagraf4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.daily_devotion, container, false);

        final ProgressDialog mProgressDialog = new ProgressDialog(MainActivity3.getInstance());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();



        judul = (TextView) view.findViewById(R.id.judul);
            nats = (TextView) view.findViewById(R.id.nats);
            bacaanemas = (TextView) view.findViewById(R.id.bacaanemas);
            tanggal = (TextView) view.findViewById(R.id.tanggal);
            isiparagraf1 = (TextView) view.findViewById(R.id.isiparagraf1);
            isiparagraf2 = (TextView) view.findViewById(R.id.isiparagraf2);
            isiparagraf3 = (TextView) view.findViewById(R.id.isiparagraf3);
            isiparagraf4 = (TextView) view.findViewById(R.id.isiparagraf4);

        RestClient.get().getDailyDevotion(1, new Callback<CategoryD>() {
            @Override
            public void success(CategoryD categoryD, Response response) {
                judul.setText(categoryD.getJudul());
                nats.setText("Nats                   : " + categoryD.getNats());
                bacaanemas.setText("Bacaan Emas  : " + "\n\n" + "          " + "\"" + categoryD.getBacaan_emas() + "\"");
                tanggal.setText("           " + categoryD.getTanggal());
                isiparagraf1.setText("           " + categoryD.getPar1());
                isiparagraf2.setText("           " + categoryD.getPar2());
                isiparagraf3.setText("           " + categoryD.getPar3());
                isiparagraf4.setText("           " + categoryD.getPar4());

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
//                judul.setText("aaaaa");

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        return view;
    }
}
