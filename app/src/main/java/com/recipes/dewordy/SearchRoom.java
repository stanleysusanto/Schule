package com.recipes.dewordy;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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

import com.recipes.dewordy.fragment.CategoryFragment.FragmentsAvailable;
import com.recipes.dewordy.helper.Conf;
import com.recipes.dewordy.model.JsonSearch2;
import com.recipes.dewordy.model.SearchRsp;
import com.recipes.dewordy.rest.RestClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Paulstanley on 3/9/16.
 */
public class SearchRoom extends Fragment {

    ListView listRecipes;

    List<SearchRsp> categories;
    ListAdapter la;

    static int[] id;
    static String keyword;
    static String[] RecipeName;
    static String[] Preview;
    static String[] CookTime;
    static int[] frm_sub_category;
    static int[] frm_sub_category2;
    static int[] next_id;
    static int[] sub_next_id;
    static int[] topic_id;
    static String[] searcha;

    private Runnable mSliderThread;
    private Handler mSliderHandler;
    private int mPosition = 0;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.searchroom, container, false);

//        Intent i_get = getIntent();
//        keyword = i_get.getExtras().getString("keyword");

        Bundle bundle = getArguments();
        keyword = bundle.getString("keyword");


        listRecipes = (ListView) view.findViewById(R.id.listRecipes);
        la = new ListAdapter(MainActivity.getInstance());

//        SearchReq searchroom = new SearchReq();
//        searchroom.setKeyword(id2);

//        JsonSearch keyword = new JsonSearch();
//        keyword.setKeyword(id2);

        RestClient.get().getSearchRoom(keyword, new Callback<JsonSearch2>() {
            @Override
            public void success(JsonSearch2 jsonSearch, Response response) {
//                int sub_cat = jsonSearch.getFrm_sub_category_id();
//                int sub_cat2 = jsonSearch.getSub_category_id();
//                int next = jsonSearch.getNext_id();
//                int sub_next = jsonSearch.getSub_next_id();
//                int topic = jsonSearch.getTopic_id();

                categories = jsonSearch.getJsonSearch2();
                id = new int[categories.size()];
                RecipeName = new String[categories.size()];
                Preview = new String[categories.size()];
                CookTime = new String[categories.size()];
                frm_sub_category = new int[categories.size()];
                frm_sub_category2 = new int[categories.size()];
                next_id = new int[categories.size()];
                sub_next_id = new int[categories.size()];
                topic_id = new int[categories.size()];
                searcha = new String[categories.size()];

                for (int i = 0; i < categories.size(); i++) {
                    id[i] = categories.get(i).getId();
                    RecipeName[i] = categories.get(i).getName();
                    Preview[i] = categories.get(i).getImage();
                    CookTime[i] = categories.get(i).getStatus();
                    frm_sub_category[i] = categories.get(i).getFrm_sub_category_id();
                    next_id[i] = categories.get(i).getNext_id();
                    sub_next_id[i] = categories.get(i).getSub_next_id();
                    searcha[i] = categories.get(i).getSearch100();
//                    topic_id[i] = categories.get(i).getTopic_id();
                }
//                for (int i = 0; i < categories.size(); i++) {
//                    id[i] = categories.get(i).getSub_category_id();
//                    RecipeName[i] = categories.get(i).getTitle();
//                    Preview[i] = categories.get(i).getImage();
//                    CookTime[i] = categories.get(i).getStatus();
//                    frm_sub_category2[i] = categories.get(i).getSub_category_id();
//                }
                listRecipes.setVisibility(View.VISIBLE);
                listRecipes.setAdapter(la);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("App", error.getMessage());
            }
        });

        listRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
//                SearchRsp search1 = new SearchRsp();
//                String search = search1.getSearch100();
                Bundle args = new Bundle();
                if (searcha[position].equals("a1")) {
                    args.putInt("id", id[position]);
                    MainActivity.getInstance().changeFragment(FragmentsAvailable.FIRST_FRAGMENT, args);
                }
                ;
                if (searcha[position].equals("a2")) {
                    args.putInt("id", id[position]);
                    args.putInt("id_sub_category", frm_sub_category[position]);
                    MainActivity.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, args);
                }
                ;
                if (searcha[position].equals("a3")) {
                    args.putInt("id", id[position]);
                    args.putInt("id_sub_category", frm_sub_category[position]);
                    args.putInt("id_next", next_id[position]);
                    MainActivity.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, args);
                }
                ;
                if (searcha[position].equals("a4")) {
                    args.putInt("id", id[position]);
                    args.putInt("id_sub_category", frm_sub_category[position]);
                    args.putInt("id_next", next_id[position]);
                    args.putInt("id_sub_next", sub_next_id[position]);
                    MainActivity.getInstance().changeFragment(FragmentsAvailable.FORTH_FRAGMENT, args);
                }
                ;
                if (searcha[position].equals("a5")) {
                    MainActivity.getInstance().changeFragment(FragmentsAvailable.FIRST_FRAGMENT, args);
                }
                ;
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
}
