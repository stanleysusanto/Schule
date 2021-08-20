package com.recipes.dewordy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.recipes.dewordy.model.category.Category2;
import com.recipes.dewordy.model.category.Json2;
import com.recipes.dewordy.rest.RestClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Paulstanley on 6/23/16.
 */
public class AbsenPage extends Fragment{

    TextView text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13, text14, text15, text16, text17, text18, text19;
    ListAdapter la;
    ListView listView;
    List<Category2> categories;
    static String[] Id;
    static String[] RecipeName;
    static String[] Preview;
    static String[] CookTime;
    static String[] CookTime1;
    static String[] CookTime2;

    private Spinner spinner_tanggal, spinner_bulan;
    private static final String[] paths = {"IPA", "Bahasa Indonesia"};
    private static final String[] paths2 = {"IPA", "Bahasa Indonesiafvfdvf"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.absen_page, container, false);

        text1 = (TextView) view.findViewById(R.id.text1);
        text2 = (TextView) view.findViewById(R.id.text2);
        text3 = (TextView) view.findViewById(R.id.text3);
        text4 = (TextView) view.findViewById(R.id.text4);
        text5 = (TextView) view.findViewById(R.id.text5);
        text6 = (TextView) view.findViewById(R.id.text6);
        text7 = (TextView) view.findViewById(R.id.text7);
        spinner_tanggal = (Spinner) view.findViewById(R.id.spinner_tanggal);
        spinner_bulan = (Spinner) view.findViewById(R.id.spinner_bulan);
        listView = (ListView) view.findViewById(R.id.listabsen);
        la = new ListAdapter(view.getContext());


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.getInstance(),
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tanggal.setAdapter(adapter);

        final String namakelas = SharedPreference.getnama_kelas(MainActivity.getInstance());

        spinner_tanggal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String namapel2 = spinner_tanggal.getSelectedItem().toString();
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                RestClient.get().getCategory2(4, namakelas, namapel2, new Callback<Json2>() {
//                    @Override
//                    public void success(Json2 json, Response response) {
//                        text1.setText(namapel2);
//                        categories = json.getJson2();
//                        Id = new String[categories.size()];
//                        RecipeName = new String[categories.size()];
//                        Preview = new String[categories.size()];
//                        CookTime = new String[categories.size()];
//                        CookTime1 = new String[categories.size()];
//                        CookTime2 = new String[categories.size()];
//
//                        for (int i = 0; i < categories.size(); i++) {
//                            Id[i] = categories.get(i).getStudent_id();
//                            RecipeName[i] = categories.get(i).getTitle();
//                            Preview[i] = categories.get(i).getUH1();
//                            CookTime[i] = categories.get(i).getUH2();
//                            CookTime1[i] = categories.get(i).getUH3();
//                            CookTime2[i] = categories.get(i).getUH4();
//                        }
//                        listView.setVisibility(View.VISIBLE);
//                        listView.setAdapter(la);
//
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.i("App", error.getMessage());
//                    }
//                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity.getInstance(),
                android.R.layout.simple_spinner_item,paths2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_bulan.setAdapter(adapter);

        final int rekapnilai = SharedPreference.getstudentid(MainActivity.getInstance().getApplicationContext());

//        String namakelas = "mipa 6";
        final String  namapel2 = spinner_tanggal.getSelectedItem().toString();
        final String namapel3 = spinner_bulan.getSelectedItem().toString();

        RestClient.get().getAbsen(4, namapel2, namapel3, new Callback<Json2>() {
            @Override
            public void success(Json2 json, Response response) {
                categories = json.getJson2();
                Id = new String[categories.size()];
                RecipeName = new String[categories.size()];
                Preview = new String[categories.size()];

                for (int i = 0; i < categories.size(); i++) {
                    Id[i] = categories.get(i).getStudent_id();
                    RecipeName[i] = categories.get(i).getNama();
                    Preview[i] = categories.get(i).getStatus();
                }
                listView.setVisibility(View.VISIBLE);
                listView.setAdapter(la);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("App", error.getMessage());
            }
        });

        return view;

    }

    static class ListAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private Context ctx;

        public ListAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            ctx = context;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return RecipeName.length;
//            return 0;
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.rowabsen, null);
                holder = new ViewHolder();
                holder.txtRecipeName = (TextView) convertView.findViewById(R.id.text11);
                holder.txtReadyIn = (TextView) convertView.findViewById(R.id.text12);
                holder.imgPreview = (TextView) convertView.findViewById(R.id.text13);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.txtRecipeName.setText(Id[position]);
            holder.txtReadyIn.setText(RecipeName[position]);
            holder.imgPreview.setText(Preview[position]);

            return convertView;
        }

        static class ViewHolder {
            TextView txtRecipeName, txtReadyIn, imgPreview, imgPreview1, imgPreview2, imgPreview3 ;
        }

    }
}