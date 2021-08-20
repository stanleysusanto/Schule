//package com.recipes.app.fragment;
//
//import android.app.Fragment;
//import android.content.Context;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.recipes.app.MainActivity;
//import com.recipes.app.R;
//import com.recipes.app.fragment.CategoryFragment.FragmentsAvailable;
//import com.recipes.app.model.category.Category;
//import com.recipes.app.model.category.Json;
//import com.recipes.app.model.search.SearchJson;
//import com.recipes.app.model.search.SearchJson2;
//import com.recipes.app.model.search.SearchJson3;
//import com.recipes.app.model.search.SearchJson4;
//import com.recipes.app.model.search.SearchJson5;
//import com.recipes.app.rest.RestClient;
//
//import java.util.List;
//
//import retrofit.Callback;
//import retrofit.RetrofitError;
//import retrofit.client.Response;
//
///**
// * Created by Paulstanley on 3/14/16.
// */
//public class SearchFragment extends Fragment {
//    ListView listRecipes;
//
//    List<Category> categories;
//    ListAdapter la;
//
//    static int[] id;
//    static String[] RecipeName;
//    static String[] Preview;
//    static String[] CookTime;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        final View view = inflater.inflate(R.layout.fragment_front, container, false);
//
//        la = new ListAdapter(view.getContext());
//
//        RestClient.get().getSearchSubCategory(new Callback<SearchJson>() {
//            @Override
//            public void success(SearchJson json, Response response) {
//                categories = json.getId();
//                id = new int[categories.size()];
//                RecipeName = new String[categories.size()];
//                Preview = new String[categories.size()];
//                CookTime = new String[categories.size()];
//
//                for (int i = 0; i < categories.size(); i++) {
//                    id[i] = Integer.parseInt(categories.get(i).getFrm_sub_category_id());
//                    RecipeName[i] = categories.get(i).getCategory_name();
//                    Preview[i] = categories.get(i).getImage();
//                    CookTime[i] = categories.get(i).getStatus();
//                }
//                listRecipes.setVisibility(View.VISIBLE);
//                listRecipes.setAdapter(la);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.i("App", error.getMessage());
//            }
//        });
//
//
//        listRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
//                                    long arg3) {
//                Bundle args = new Bundle();
//                args.putInt("id", id[position]);
//                MainActivity.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, args);
//            }
//        });
//
//        RestClient.get().getSearchNext(new Callback<SearchJson5>() {
//            @Override
//            public void success(SearchJson5 json, Response response) {
//                categories = json.getId();
//                id = new int[categories.size()];
//                RecipeName = new String[categories.size()];
//                Preview = new String[categories.size()];
//                CookTime = new String[categories.size()];
//
//                for (int i = 0; i < categories.size(); i++) {
//                    id[i] = Integer.parseInt(categories.get(i).getFrm_sub_category_id());
//                    RecipeName[i] = categories.get(i).getCategory_name();
//                    Preview[i] = categories.get(i).getImage();
//                    CookTime[i] = categories.get(i).getStatus();
//                }
//                listRecipes.setVisibility(View.VISIBLE);
//                listRecipes.setAdapter(la);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.i("App", error.getMessage());
//            }
//        });
//
//
//        listRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
//                                    long arg3) {
//                Bundle args = new Bundle();
//                args.putInt("id", id[position]);
//                MainActivity.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, args);
//            }
//        });
//
//        RestClient.get().getSearchSubNext(new Callback<SearchJson3>() {
//            @Override
//            public void success(SearchJson3 json, Response response) {
//                categories = json.getId();
//                id = new int[categories.size()];
//                RecipeName = new String[categories.size()];
//                Preview = new String[categories.size()];
//                CookTime = new String[categories.size()];
//
//                for (int i = 0; i < categories.size(); i++) {
//                    id[i] = Integer.parseInt(categories.get(i).getFrm_sub_category_id());
//                    RecipeName[i] = categories.get(i).getCategory_name();
//                    Preview[i] = categories.get(i).getImage();
//                    CookTime[i] = categories.get(i).getStatus();
//                }
//                listRecipes.setVisibility(View.VISIBLE);
//                listRecipes.setAdapter(la);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.i("App", error.getMessage());
//            }
//        });
//
//
//        listRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
//                                    long arg3) {
//                Bundle args = new Bundle();
//                args.putInt("id", id[position]);
//                MainActivity.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, args);
//            }
//        });
//
//        RestClient.get().getSearchTopic(new Callback<SearchJson4>() {
//            @Override
//            public void success(SearchJson4 json, Response response) {
//                categories = json.getId();
//                id = new int[categories.size()];
//                RecipeName = new String[categories.size()];
//                Preview = new String[categories.size()];
//                CookTime = new String[categories.size()];
//
//                for (int i = 0; i < categories.size(); i++) {
//                    id[i] = Integer.parseInt(categories.get(i).getFrm_sub_category_id());
//                    RecipeName[i] = categories.get(i).getCategory_name();
//                    Preview[i] = categories.get(i).getImage();
//                    CookTime[i] = categories.get(i).getStatus();
//                }
//                listRecipes.setVisibility(View.VISIBLE);
//                listRecipes.setAdapter(la);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.i("App", error.getMessage());
//            }
//        });
//
//
//        listRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
//                                    long arg3) {
//                Bundle args = new Bundle();
//                args.putInt("id", id[position]);
//                MainActivity.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, args);
//            }
//        });
//
//        RestClient.get().getSearchUser(new Callback<SearchJson2>() {
//            @Override
//            public void success(SearchJson2 json, Response response) {
//                categories = json.getId();
//                id = new int[categories.size()];
//                RecipeName = new String[categories.size()];
//                Preview = new String[categories.size()];
//                CookTime = new String[categories.size()];
//
//                for (int i = 0; i < categories.size(); i++) {
//                    id[i] = Integer.parseInt(categories.get(i).getFrm_sub_category_id());
//                    RecipeName[i] = categories.get(i).getCategory_name();
//                    Preview[i] = categories.get(i).getImage();
//                    CookTime[i] = categories.get(i).getStatus();
//                }
//                listRecipes.setVisibility(View.VISIBLE);
//                listRecipes.setAdapter(la);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.i("App", error.getMessage());
//            }
//        });
//
//
//        listRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
//                                    long arg3) {
//                Bundle args = new Bundle();
//                args.putInt("id", id[position]);
//                MainActivity.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, args);
//            }
//        });
//
//
//        return view;
//    }
//    static class ListAdapter extends BaseAdapter {
//        private LayoutInflater inflater;
//        private Context ctx;
//
//        public ListAdapter(Context context) {
//            inflater = LayoutInflater.from(context);
//            ctx = context;
//        }
//
//        public int getCount() {
//            // TODO Auto-generated method stub
//            return RecipeName.length;
//        }
//
//        public Object getItem(int position) {
//            // TODO Auto-generated method stub
//            return position;
//        }
//
//        public long getItemId(int position) {
//            // TODO Auto-generated method stub
//            return position;
//        }
//
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // TODO Auto-generated method stub
//            ViewHolder holder;
//
//            if (convertView == null) {
//                convertView = inflater.inflate(R.layout.row, null);
//                holder = new ViewHolder();
//                holder.txtRecipeName = (TextView) convertView.findViewById(R.id.txtRecipeName);
//                holder.txtReadyIn = (TextView) convertView.findViewById(R.id.txtReadyIn);
//                holder.imgPreview = (ImageView) convertView.findViewById(R.id.imgPreview);
//
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//
//
//            holder.txtRecipeName.setText(RecipeName[position]);
//            holder.txtReadyIn.setText(CookTime[position]);
//            int imagePreview = ctx.getResources().getIdentifier(Preview[position], "drawable", ctx.getPackageName());
//            holder.imgPreview.setImageResource(imagePreview);
//
//
//            return convertView;
//        }
//
//        static class ViewHolder {
//            TextView txtRecipeName, txtReadyIn;
//            ImageView imgPreview;
//        }
//
//    }
//}