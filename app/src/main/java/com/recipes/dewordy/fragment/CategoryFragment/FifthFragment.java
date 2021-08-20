package com.recipes.dewordy.fragment.CategoryFragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipes.dewordy.MainActivity3;
import com.recipes.dewordy.R;
import com.recipes.dewordy.adapter.CustomPagerAdapter;

/**
 * Created by Paulstanley on 3/9/16.
 */
public class FifthFragment extends Fragment {

    public static int id2;
    public static int id3;
    public static int id4;
    public static int id5;
    public static String nama, namasekolah;
    public static String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.discussion, container, false);

//        final ProgressDialog mProgressDialog = new ProgressDialog(MainActivity36.getInstance());
//        mProgressDialog.setIndeterminate(true);
//        mProgressDialog.setMessage("Loading...");
//        mProgressDialog.show();

        Bundle bundle = getArguments();
        id2 = bundle.getInt("id");
        id3 = bundle.getInt("frm_sub_category_id");
        id4 = bundle.getInt("next_id");
        id5 = bundle.getInt("sub_next_id");
        nama = bundle.getString("nama");
        namasekolah = bundle.getString("namasekolah");
        title = bundle.getString("title");


        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
//        RestClient.get().getNamaTab(id2, id5, id4, id3, new Callback<Category8>() {
//            @Override
//            public void success(Category8 json, Response response) {
////                tabLayout.addTab(tabLayout.newTab().setText(json.getTitle()));
//                tabLayout.addTab(tabLayout.newTab().setText("dddsff"));
//            }
//            @Override
//            public void failure(RetrofitError error) {
//                Log.i("App", error.getMessage());
//            }
//        });

        tabLayout.addTab(tabLayout.newTab().setText(title));
//        tabLayout.addTab(tabLayout.newTab().setText("Chat List"));
//        tabLayout.addTab(tabLayout.newTab().setText("Chat Room"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        final CustomPagerAdapter adapter = new CustomPagerAdapter(MainActivity3.getInstance().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}
