package com.recipes.dewordy.fragment.CategoryFragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.recipes.dewordy.MainActivity;
import com.recipes.dewordy.R;
import com.recipes.dewordy.SharedPreference;
import com.recipes.dewordy.helper.Conf;
import com.recipes.dewordy.model.category.Category2;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Paulstanley on 3/9/16.
 */
public class SecondFragment extends Fragment {

    ListView listRecipes;

    List<Category2> categories;
    ListAdapter la;

    int cid;
    static int[] id;
    static int id2;
    static String[] RecipeName;
    static String[] Preview;
    static String[] CookTime;
    static int[] frm_sub_category;

    private Runnable mSliderThread;
    private Handler mSliderHandler;
    private int mPosition = 0;
    private ViewPager viewPager;

    ImageView addtopic;
    TextView bre1, bre2, bre3, bre4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_third, container, false);

        final ProgressDialog mProgressDialog = new ProgressDialog(MainActivity.getInstance());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        Bundle bundle = getArguments();
        int id2 = bundle.getInt("id");
        final String bree1 = bundle.getString("bre1");

//        String[] imageword = new String[]{"WELCOME TO DEWORDY", "Di bawah ini, pilih NAMA SEKOLAH yang kamu tuju", "DEWORDY FOR US"};
//        String[] imageurl = new String[]{"https://upload.wikimedia.org/wikipedia/commons/f/f7/LeBron_James_2.jpg", "http://static.giantbomb.com/uploads/scale_small/1/17380/1459088-lebronjames550.jpg", "https://upload.wikimedia.org/wikipedia/commons/f/fd/LeBron_James_18112009_1.jpg"};

        listRecipes = (ListView) view.findViewById(R.id.listRecipes);
        addtopic = (ImageView) view.findViewById(R.id.addtopic);
        bre1 = (TextView) view.findViewById(R.id.bre1);
        bre2 = (TextView) view.findViewById(R.id.bre2);
        bre3 = (TextView) view.findViewById(R.id.bre3);
        bre4 = (TextView) view.findViewById(R.id.bre4);

        bre1.setText(bree1);

        addtopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPreference.getusername(MainActivity.getInstance().getApplicationContext()).length() == 0) {
//                    Bundle args = new Bundle();
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.FIRST_FRAGMENT, args);
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
//        viewPager = (ViewPager) view.findViewById(R.id.slider_posts_viewpager);
//        PagerAdapter adapter = new ViewPagerAdapter(view.getContext(), imageurl, imageword);
//        viewPager.setAdapter(adapter);
//        enableSliding();
        la = new ListAdapter(view.getContext());

//        Category2 jsonn = new Category2();
//        jsonn.setFrm_sub_category_id(1);

//        RestClient.get().getCategory2(1, new Callback<Json2>() {
//            @Override
//            public void success(Json2 json, Response response) {
//                categories = json.getJson2();
//                id = new int[categories.size()];
//                RecipeName = new String[categories.size()];
//                Preview = new String[categories.size()];
//                CookTime = new String[categories.size()];
//                frm_sub_category = new int[categories.size()];
//
//                for (int i = 0; i < categories.size(); i++) {
//                    id[i] = Integer.parseInt(categories.get(i).getNext_id());
//                    RecipeName[i] = categories.get(i).getTitle();
//                    Preview[i] = categories.get(i).getImage();
//                    CookTime[i] = categories.get(i).getDescription();
//                    frm_sub_category[i] = categories.get(i).getFrm_sub_category_id();
//                }
//                listRecipes.setVisibility(View.VISIBLE);
//                listRecipes.setAdapter(la);
//                if (mProgressDialog.isShowing())
//                    mProgressDialog.dismiss();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.i("App", error.getMessage());
//            }
//        });


//        listRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
//                                    long arg3) {
//                Bundle args = new Bundle();
//                args.putInt("id", id[position]);
//                args.putString("bre1", bree1);
//                args.putString("bre2", RecipeName[position]);
//                args.putInt("id_sub_category", frm_sub_category[position]);
//                MainActivity6.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, args);
//            }
//        });

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
                convertView = inflater.inflate(R.layout.row, null);
                holder = new ViewHolder();
                holder.txtRecipeName = (TextView) convertView.findViewById(R.id.txtRecipeName);
                holder.txtReadyIn = (TextView) convertView.findViewById(R.id.txtReadyIn);
                holder.imgPreview = (ImageView) convertView.findViewById(R.id.imgPreview);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.txtRecipeName.setText(RecipeName[position]);
            holder.txtReadyIn.setText(CookTime[position]);
//            int imagePreview = ctx.getResources().getIdentifier(Preview[position], "drawable", ctx.getPackageName());
//            holder.imgPreview.setImageResource(imagePreview);
            Picasso.with(MainActivity.getInstance()).load(Conf.imageurl + Preview[position]).into(holder.imgPreview);

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