package com.recipes.dewordy.fragment.CategoryFragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.recipes.dewordy.MainActivity;
import com.recipes.dewordy.R;
import com.recipes.dewordy.SharedPreference;
import com.recipes.dewordy.helper.Conf;
import com.recipes.dewordy.model.category.Category3;
import com.recipes.dewordy.model.category.Json3;
import com.recipes.dewordy.rest.RestClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Paulstanley on 3/9/16.
 */
public class ThirdFragment2 extends Fragment {

    ListView listRecipes;

    List<Category3> categories;
    ListAdapter la;

    static int[] id;
    static int id2;
    static int id3;
    static String[] RecipeName;
    static String[] Preview;
    static String[] CookTime;
    static int[] frm_sub_category;
    static int[] next_id;

    private Runnable mSliderThread;
    private Handler mSliderHandler;
    private int mPosition = 0;
    private ViewPager viewPager;

    ImageView addtopic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_third, container, false);

        final ProgressDialog mProgressDialog = new ProgressDialog(MainActivity.getInstance());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        Bundle bundle = getArguments();
        id2 = bundle.getInt("id");
        id3 = bundle.getInt("id_sub_category");
        final String bree1 = bundle.getString("bre1");
        final String bree2 = bundle.getString("bre2");

//        String[] imageword = new String[]{"WELCOME TO DEWORDY", "Di bawah ini, pilih BAHAN DISKUSI yang kamu tuju", "DEWORDY FOR US"};
//        String[] imageurl = new String[]{"https://upload.wikimedia.org/wikipedia/commons/f/f7/LeBron_James_2.jpg", "http://static.giantbomb.com/uploads/scale_small/1/17380/1459088-lebronjames550.jpg", "https://upload.wikimedia.org/wikipedia/commons/f/fd/LeBron_James_18112009_1.jpg"};

        listRecipes = (ListView) view.findViewById(R.id.listRecipes);
//        viewPager = (ViewPager) view.findViewById(R.id.slider_posts_viewpager);
//        PagerAdapter adapter = new ViewPagerAdapter(view.getContext(), imageurl, imageword);
//        viewPager.setAdapter(adapter);
//        enableSliding();
        addtopic = (ImageView) view.findViewById(R.id.addtopic);
        TextView bre1 = (TextView) view.findViewById(R.id.bre1);
        TextView bre2 = (TextView) view.findViewById(R.id.bre2);
        TextView bre3 = (TextView) view.findViewById(R.id.bre3);
        TextView bre4 = (TextView) view.findViewById(R.id.bre4);

        bre1.setText(bree1);
        bre2.setText(" ::hhnhhhhnhnhhnhnhnhnhn " + bree2);

        addtopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPreference.getusername(MainActivity.getInstance().getApplicationContext()).length() == 0) {
//                    Bundle args = new Bundle();
//                    MainActivity.getInstance().changeFragment(FragmentsAvailable.FIRST_FRAGMENT, args);
                    final Dialog dialog = new Dialog(MainActivity.getInstance());
                    dialog.setContentView(R.layout.requestbox);
                    dialog.setTitle("         Want to request?");
                    dialog.show();
                }else{
                    Bundle args = new Bundle();
                    MainActivity.getInstance().changeFragment(FragmentsAvailable.RequestPage, args);
                }
            }
        });
        la = new ListAdapter(view.getContext());

        RestClient.get().getCategory3(id2, id3, new Callback<Json3>() {
            @Override
            public void success(Json3 json, Response response) {
                categories = json.getJson3();
                id = new int[categories.size()];
                RecipeName = new String[categories.size()];
                Preview = new String[categories.size()];
                CookTime = new String[categories.size()];
                frm_sub_category = new int[categories.size()];
                next_id = new int[categories.size()];

                for (int i = 0; i < categories.size(); i++) {
                    id[i] = Integer.parseInt(categories.get(i).getSub_next_id());
                    RecipeName[i] = categories.get(i).getTitle();
                    Preview[i] = categories.get(i).getImages();
                    CookTime[i] = categories.get(i).getDescription();
                    frm_sub_category[i] = categories.get(i).getFrm_sub_category_id();
                    next_id[i] = categories.get(i).getNext_id();
                }
                listRecipes.setVisibility(View.VISIBLE);
                listRecipes.setAdapter(la);
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("App", error.getMessage());
            }
        });


        listRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                Bundle args = new Bundle();
                args.putInt("id", id[position]);
                args.putString("bre1", bree1 );
                args.putString("bre2", bree2);
                args.putString("bre3", RecipeName[position]);
                args.putInt("frm_sub_category_id", frm_sub_category[position]);
                args.putInt("next_id", next_id[position]);
                MainActivity.getInstance().changeFragment(FragmentsAvailable.FORTH_FRAGMENT, args);
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
                convertView = inflater.inflate(R.layout.row3, null);
                holder = new ViewHolder();
                holder.txtRecipeName = (TextView) convertView.findViewById(R.id.txtRecipeName);
                holder.txtReadyIn = (TextView) convertView.findViewById(R.id.txtReadyIn);
                holder.imgPreview = (ImageView) convertView.findViewById(R.id.imgPreview);
//                holder.imgPreview.setMaxWidth(50);
//                holder.imgPreview.setMaxHeight(30);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.txtRecipeName.setText(RecipeName[position]);
            holder.txtReadyIn.setText(CookTime[position]);
//            int imagePreview = ctx.getResources().getIdentifier(Preview[position], "drawable", ctx.getPackageName());
//            holder.imgPreview.setImageResource(imagePreview);
            Picasso.with(MainActivity.getInstance()).load(Conf.imageurl + Preview[position] + ".jpg").into(holder.imgPreview);

            return convertView;
        }

        static class ViewHolder {
            TextView txtRecipeName, txtReadyIn;
            ImageView imgPreview;
        }

    }
    private void stopSliding() {
        if (mSliderHandler != null) {
            mSliderHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * Enables the slider animation
     */
    private void enableSliding() {
//        stopSliding();
        mSliderHandler = new Handler();
        mSliderThread = new Runnable() {
            public void run() {
                if (viewPager != null) {
                    viewPager.setCurrentItem(mPosition++, true);
//					mIndicator.setCurrentItem(mPosition++);
                }
                if (mPosition > 3) {
                    mPosition = 0;
                }
                mSliderHandler.postDelayed(this, 3000);
            }
        };
        mSliderThread.run();
    }


}