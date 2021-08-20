/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.recipes.dewordy;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.recipes.dewordy.fragment.CategoryFragment.FifthFragment;
import com.recipes.dewordy.fragment.CategoryFragment.FirstFragment;
import com.recipes.dewordy.fragment.CategoryFragment.ForthFragment;
import com.recipes.dewordy.fragment.CategoryFragment.FragmentsAvailable;
import com.recipes.dewordy.fragment.CategoryFragment.RequestPage;
import com.recipes.dewordy.fragment.CategoryFragment.SchoolHomeFragment;
import com.recipes.dewordy.fragment.CategoryFragment.SchoolHomeFragment3;
import com.recipes.dewordy.fragment.CategoryFragment.SecondFragment;
import com.recipes.dewordy.fragment.CategoryFragment.ThirdFragment;
import com.recipes.dewordy.helper.Conf;
import com.recipes.dewordy.model.category.Category2;
import com.recipes.dewordy.model.category.Json2;
import com.recipes.dewordy.rest.RestClient;
import com.shahroz.svlibrary.interfaces.onSearchListener;
import com.shahroz.svlibrary.interfaces.onSimpleSearchActionsListener;
import com.shahroz.svlibrary.widgets.MaterialSearchView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

//import com.recipes.app.fragment.CategoryFragment.ThirdFragment;

/**
 * This example illustrates a common usage of the DrawerLayout widget in the Android support library.
 * <p/>
 * <p>
 * When a navigation (left) drawer is present, the host activity should detect presses of the action bar's Up affordance as a signal to open and close the navigation drawer. The ActionBarDrawerToggle
 * facilitates this behavior. Items within the drawer should fall into one of two categories:
 * </p>
 * <p/>
 * <ul>
 * <li><strong>View switches</strong>. A view switch follows the same basic policies as list or tab navigation in that a view switch does not create navigation history. This pattern should only be
 * used at the root activity of a task, leaving some form of Up navigation active for activities further down the navigation hierarchy.</li>
 * <li><strong>Selective Up</strong>. The drawer allows the user to choose an alternate parent for Up navigation. This allows a user to jump across an app's navigation hierarchy at will. The
 * application should treat this as it treats Up navigation from a different task, replacing the current task stack using TaskStackBuilder or similar. This is the only form of navigation drawer that
 * should be used outside of the root activity of a task.</li>
 * </ul>
 * <p/>
 * <p>
 * Right side drawers should be used for actions, not navigation. This follows the pattern established by the Action Bar that navigation should be to the left and actions to the right. An action
 * should be an operation performed on the current contents of the window, for example enabling or disabling a data overlay on top of the current content.
 * </p>
 */
public class MainActivity3 extends AppCompatActivity implements onSimpleSearchActionsListener, onSearchListener {


    private DrawerLayout mDrawerLayout;
    // private ListView mDrawerList;

    private ExpandableListView mDrawerList;
    private static MainActivity3 instance = null;

    private LinearLayout navDrawerView;

    CustomExpandAdapter customAdapter;

    ImageButton login_drawer, logout_drawer;

    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;

    private ImageView plusminus;
    private boolean mSearchViewAdded = false;
    private MaterialSearchView mSearchView;
    private WindowManager mWindowManager;
    private MenuItem searchItem, profileItem, settings, login, signup, logout, settings2;
    private boolean searchActive = false;
//    private FloatingActionButton fab;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    private String[] mKindergartenTitles;
    private String[] mElementaryTitles;
    private String[] mSecondaryTitles;
    private String[] mHighschoolTitles;
    private String[] mCollegeTitles;
    private String[] title;
    private int selectedPosition;
    private FragmentsAvailable currentFragment, nextFragment;

    AlarmManager alarmManager;
    String ALARM_SERVICE = "alarm";

    List<Category2>categories;
    TextView dis1,dis2,dis3,dis4,dis5,dis6;
    //    private EditText mSearchText;
    static boolean id2, id3;

    List<SampleTO> listParent;

    HashMap<String, List<String>> listDataChild;

    public static MainActivity3 getInstance() {
        if(instance == null) {
            instance = new MainActivity3();
        }

        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invalidateOptionsMenu();
        setContentView(R.layout.activity_main);
//        alarmManager = (AlarmManager) MainActivity3.getInstance().getSystemService(getApplicationContext().ALARM_SERVICE);


//        Config.context = this;

        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        scheduleNotification(0, alarmManager);



        String Usernamess = LoginState.getUsername();
        instance = this;

        Intent i_get = getIntent();
        id2 = i_get.getBooleanExtra("notif", false);
        Intent i_get2 = getIntent();
        id3 = i_get.getBooleanExtra("like", false);

        if(id2){
            Bundle args = new Bundle();
            args.putInt("id", 1);
            args.putInt("frm_sub_category_id", 4);
            args.putInt("next_id", 1);
            args.putInt("sub_next_id", 1);
            MainActivity3.getInstance().changeFragment(FragmentsAvailable.FIFTH_FRAGMENT, args);
        }

        if(id3){
            Bundle args = new Bundle();
            MainActivity3.getInstance().changeFragment(FragmentsAvailable.Profile, args);
        }

//        FacebookSdk.sdkInitialize(getApplicationContext());
//        mTitle = mD'rawerTitle = LoginState.getUsername();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        dis1 = (TextView) findViewById(R.id.dis1);
//        dis2 = (TextView) findViewById(R.id.dis2);
//        dis3 = (TextView) findViewById(R.id.dis3);
//        dis4 = (TextView) findViewById(R.id.dis4);
//        dis5 = (TextView) findViewById(R.id.dis5);
//        dis6 = (TextView) findViewById(R.id.dis6);
//        dis1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle args = new Bundle();
//                args.putInt("id", 1);
//                args.putInt("id_sub_category", 4);
//                MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, args);
//            }
//        });
//        dis2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity3.getInstance().changeFragment(FragmentsAvailable.GeneralList);
//            }
//        });
//        dis3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity3.getInstance().changeFragment(FragmentsAvailable.DailyDevotion);
//            }
//        });
//        dis4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity3.getInstance().changeFragment(FragmentsAvailable.AbsenPage);
//            }
//        });
//        dis5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity3.getInstance().changeFragment(FragmentsAvailable.RekapNilai);
//            }
//        });
//        dis6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity6.getInstance(), MainActivity4.class);
//                startActivity(i);
//            }
//        });
        final ImageView mTitle2 = (ImageView) findViewById(R.id.toolbar_title);
        mTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putInt("id", 0);
                MainActivity3.getInstance().changeFragment(FragmentsAvailable.SchoolHomeFragment3);
            }
        });
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        mToolbar.postDelayed(new Runnable()
//        {
//            @Override
//            public void run ()
//            {
//                int maxWidth = mToolbar.getWidth();
//                int titleWidth = mTitle2.getWidth();
//                int iconWidth = maxWidth - titleWidth;
//
//                if (iconWidth > 0)
//                {
//                    //icons (drawer, menu) are on left and right side
//                    int width = maxWidth - iconWidth * 2;
//                    mTitle2.setMinimumWidth(width);
//                    mTitle2.getLayoutParams().width = width;
//                }
//            }
//        }, 0);

//        navDrawerView = (LinearLayout) findViewById(R.id.navDrawerView);
//        mPlanetTitles = getResources().getStringArray(R.array.planets_array);

        RestClient.get().getExpandable(new Callback<Json2>() {
            @Override
            public void success(Json2 json2, Response response) {

                List<String> kindergartens = new ArrayList<String>();
                List<String> elementaries = new ArrayList<String>();
                List<String> secondaries = new ArrayList<String>();
                List<String> highSchools = new ArrayList<String>();
                List<String> colleges = new ArrayList<String>();

//                categories = json2.getJson2();
//
//                for(Category2 cat2 : categories) {
//                    Integer frm = cat2.getFrm_sub_category_id();
//
//                    if(frm == 1) {
//                        kindergartens.add(cat2.getTitle());
//                    }
//                    if(frm == 2) {
//                        elementaries.add(cat2.getTitle());
//                    }
//                    if(frm == 3) {
//                        secondaries.add(cat2.getTitle());
//                    }
//                    if(frm == 4) {
//                        highSchools.add(cat2.getTitle());
//                    }
//                    if(frm == 5) {
//                        colleges.add(cat2.getTitle());
//                    }
//                }

                listDataChild.put(getString(R.string.kindergarten), kindergartens);
                listDataChild.put(getString(R.string.elementary), elementaries);
                listDataChild.put(getString(R.string.secondary), secondaries);
                listDataChild.put(getString(R.string.highschool), highSchools);
                listDataChild.put(getString(R.string.college),colleges);

                customAdapter = new CustomExpandAdapter(MainActivity3.this, listParent, listDataChild);
                customAdapter.notifyDataSetChanged();
                // setting list adapter
//                mDrawerList.setAdapter(customAdapter);
//                mDrawerList.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // mDrawerList = (ListView) findViewById(R.id.left_drawer);
//        mSearchText = (EditText) findViewById(R.id.search_text);

        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            //drawer is open
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
        }

        mDrawerList = (ExpandableListView) findViewById(R.id.nav_left_drawer);
//        login_drawer = (ImageButton) findViewById(R.id.login_drawer);
//        logout_drawer = (ImageButton) findViewById(R.id.logout_drawer);

        // set a custom shadow that overlays the main content when the drawer
        // opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        listParent = new ArrayList<SampleTO>();
        listDataChild = new HashMap<String, List<String>>();

        // Navigation Drawer of Flight starts
//        listParent.add(new SampleTO("HOME", R.drawable.homeicon));
//        listParent.add(new SampleTO(getString(R.string.loginsignup), R.color.white));
//        listParent.add(new SampleTO(getString(R.string.kindergarten), R.drawable.kindergartenicon));
//        listParent.add(new SampleTO(getString(R.string.elementary), R.drawable.elementaryicon));
//        listParent.add(new SampleTO(getString(R.string.secondary), R.drawable.secondaryicon));
//        listParent.add(new SampleTO(getString(R.string.highschool), R.drawable.highschoolicon));
//        listParent.add(new SampleTO(getString(R.string.college), R.drawable.collegeicon));

//        listDataChild.put(getString(R.string.kindergarten), Arrays.asList(mKindergartenTitles));
//        listDataChild.put(getString(R.string.elementary), Arrays.asList(mElementaryTitles));
//        listDataChild.put(getString(R.string.secondary), Arrays.asList(mSecondaryTitles));
//        listDataChild.put(getString(R.string.highschool), Arrays.asList(mHighschoolTitles));
//        listDataChild.put(getString(R.string.college), Arrays.asList(mCollegeTitles));

        listDataChild.put(getString(R.string.sun), new ArrayList<String>());
        listDataChild.put(getString(R.string.moon), new ArrayList<String>());
//
//        listDataChild.put(getString(R.string.Planets), Arrays.asList(mPlanetTitles));

        customAdapter = new CustomExpandAdapter(MainActivity3.this, listParent, listDataChild);
        // setting list adapter
//        mDrawerList.setAdapter(customAdapter);
//        mDrawerList.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);

//        if(SharedPreference.getusername(MainActivity6.this).length() == 0) {
//            logout_drawer.setVisibility(View.GONE);
//        }else{
//            login_drawer.setVisibility(View.GONE);
//        }

//        login_drawer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent zz = new Intent(MainActivity6.this, LoginActivity.class);
//                startActivity(zz);
//            }
//        });
//        logout_drawer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoginState.setUsername(null);
//                LoginState.setUserid(0);
//                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = settings.edit();
//                editor.remove("username");
//                editor.remove("city");
//                editor.remove("birthplace");
//                editor.remove("senin");
//                editor.remove("selasa");
//                editor.remove("rabu");
//                editor.remove("kamis");
//                editor.remove("jumat");
//                editor.remove("nama_sekolah");
//                editor.remove("jenjang_sekolah");
//                editor.remove("regid");
//                editor.remove("email");
//                editor.remove("image");
//                editor.remove("userid");
//                editor.remove("studentid");
//                editor.remove("nama_kelas");
//                editor.clear();
//                editor.commit();
//                Intent intent = new Intent(MainActivity6.this, MainActivity6.class);
//                mDrawerLayout.closeDrawer(navDrawerView);
//            }
//        });

//        plusminus = (ImageView) findViewById(R.id.plusminus);
//
//        plusminus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent login = new Intent(MainActivity6.this, LoginActivity.class);
//                startActivity(login);
//            }
//        });

        // // set up the drawer's list view with items and click listener
        // mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
        // mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
//        R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description for accessibility */
                R.string.drawer_close /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

//        if (savedInstanceState == null) {
//            currentFragment = FragmentsAvailable.SchoolHomeFragment;
//            changeFragment(FragmentsAvailable.SchoolHomeFragment);
//        }

//        if (SharedPreference.getverification(MainActivity3.this).length() == 0) {
//            if (savedInstanceState == null) {
//                currentFragment = FragmentsAvailable.FIRST_FRAGMENT;
//                changeFragment(FragmentsAvailable.FIRST_FRAGMENT);
//            }
//        }else{
            if (savedInstanceState == null) {
                currentFragment = FragmentsAvailable.SchoolHomeFragment3;
                changeFragment(FragmentsAvailable.SchoolHomeFragment3);
            }
//        }
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mSearchView = new MaterialSearchView(this);
        mSearchView.setOnSearchListener(this);
        mSearchView.setSearchResultsListener(this);
        mSearchView.setHintText("Search");

        if (mToolbar != null) {
            // Delay adding SearchView until Toolbar has finished loading
            mToolbar.post(new Runnable() {
                @Override
                public void run() {
                    if (!mSearchViewAdded && mWindowManager != null) {
                        mWindowManager.addView(mSearchView,
                                MaterialSearchView.getSearchViewLayoutParams(MainActivity3.this));
                        mSearchViewAdded = true;
                    }
                }
            });
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mDrawerList.setOnGroupClickListener(new OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupPosition));
//                parent.setItemChecked(index, true);
//
//                String parentTitle = ((SampleTO) customAdapter.getGroup(groupPosition)).getTitle();
//
//                if(groupPosition == 0){
////                    Intent a = new Intent(MainActivity6.this, SecondFragment.class);
////                    a.putExtra("id", 1);
////                    startActivity(a);
////                    Bundle args = new Bundle();
////                    args.putInt("id", 0);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.FIRST_FRAGMENT);
//                }
////                    else if (parentTitle == getString(R.string.loginsignup)) {
////                    Intent login = new Intent(MainActivity6.this, LoginActivity.class);
////                    startActivity(login);
////                }
//                else if (groupPosition == 1) {
////                    Intent a = new Intent(MainActivity6.this, SecondFragment.class);
////                    a.putExtra("id", 1);
////                    startActivity(a);
//                    Bundle args = new Bundle();
//                    args.putInt("id", 1);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, args);
//                }else if (groupPosition == 2) {
//                    Bundle a = new Bundle();
//                    a.putInt("id", 2);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, a);
//                }else if (groupPosition == 3) {
//                    Bundle a = new Bundle();
//                    a.putInt("id", 3);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, a);
//                }else if (groupPosition == 4) {
//                    Bundle a = new Bundle();
//                    a.putInt("id", 4);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, a);
//                } else if (groupPosition == 5) {
//                    Bundle a = new Bundle();
//                    a.putInt("id", 5);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, a);
//                }
//
//
//                return false;
//
//            }
//        });

//        mDrawerList.setOnChildClickListener(new OnChildClickListener() {
//
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//
//                Log.d("CHECK", "child click");
//
//                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
//                parent.setItemChecked(index, true);
//
////                changeFragment(childPosition);
//                if(groupPosition==1 && childPosition ==0  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 15);
//                    a.putInt("id_sub_category", 1);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==1 && childPosition ==1  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 14);
//                    a.putInt("id_sub_category", 1);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==1 && childPosition ==2  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 16);
//                    a.putInt("id_sub_category", 1);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==1 && childPosition ==3  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 17);
//                    a.putInt("id_sub_category", 1);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==1 && childPosition ==4  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 8);
//                    a.putInt("id_sub_category", 1);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==2 && childPosition ==0  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 19);
//                    a.putInt("id_sub_category", 2);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==2 && childPosition ==1  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 18);
//                    a.putInt("id_sub_category", 2);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==2 && childPosition ==2  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 21);
//                    a.putInt("id_sub_category", 2);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==2 && childPosition ==3  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 20);
//                    a.putInt("id_sub_category", 2);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==2 && childPosition ==4  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 7);
//                    a.putInt("id_sub_category", 2);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==3 && childPosition ==0  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 3);
//                    a.putInt("id_sub_category", 3);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==3 && childPosition ==1  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 22);
//                    a.putInt("id_sub_category", 3);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==3 && childPosition ==2  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 6);
//                    a.putInt("id_sub_category", 3);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==3 && childPosition ==3  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 23);
//                    a.putInt("id_sub_category", 3);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==3 && childPosition ==4  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 2);
//                    a.putInt("id_sub_category", 3);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==4 && childPosition ==0  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 25);
//                    a.putInt("id_sub_category", 4);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==4 && childPosition ==1  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 5);
//                    a.putInt("id_sub_category", 4);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==4 && childPosition ==2  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 24);
//                    a.putInt("id_sub_category", 4);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==4 && childPosition ==3  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 26);
//                    a.putInt("id_sub_category", 4);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==4 && childPosition ==4  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 1);
//                    a.putInt("id_sub_category", 4);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==4 && childPosition ==5  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 4);
//                    a.putInt("id_sub_category", 4);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==5 && childPosition ==0  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 10);
//                    a.putInt("id_sub_category", 5);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==5 && childPosition ==1  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 13);
//                    a.putInt("id_sub_category", 4);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==5 && childPosition ==2  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 12);
//                    a.putInt("id_sub_category", 5);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==5 && childPosition ==3  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 11);
//                    a.putInt("id_sub_category", 5);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==5 && childPosition ==4  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 9);
//                    a.putInt("id_sub_category", 5);
//                    MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                return false;
//            }
//        });
//    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

//        if(LoginState.getUsername()!= null) {
//            profileItem = menu.findItem(R.id.profpic);
//            profileItem.setVisible(true);
//            invalidateOptionsMenu();
//            ImageView profpic = new ImageView(this);
//            Picasso.with(this).load("https://cdn2.iconfinder.com/data/icons/users-6/100/USER10-128.png").into(profpic);
//            profileItem.setActionView(profpic);
//            profileItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    Toast.makeText(getApplicationContext(),"sd",Toast.LENGTH_LONG).show();
//                    Bundle args = new Bundle();
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT);
//                    return true;
//                }
//            });
//        }else{
//            profileItem = menu.findItem(R.id.profpic);
//            profileItem.setVisible(false);
//            invalidateOptionsMenu();
//        }
//

        searchItem = menu.findItem(R.id.search);
        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mSearchView.display();
                openKeyboard();
//                Intent search = new Intent("search", searchItem[position]);
                return true;
            }
        });
        if (searchActive)
            mSearchView.display();
        return true;

    }

    private void openKeyboard() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
                mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
            }
        }, 200);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        String Usernamess = LoginState.getUsername();

//        View settings = findViewById(R.id.settings);
//        settings.setMinimumWidth(200);
        // If the nav drawer is open, hide action items related to the content
        // view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(navDrawerView);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        if(SharedPreference.getusername(MainActivity3.this).length() != 0) {
            ImageView profpic = new ImageView(this);
            Picasso.with(this).load(Conf.imageurl + SharedPreference.getimage(this)).into(profpic);
//            menu.findItem(R.id.profpic).setActionView(profpic);
            menu.findItem(R.id.profpic).setVisible(false);
            menu.findItem(R.id.login).setVisible(false);
            menu.findItem(R.id.signup).setVisible(false);
            menu.findItem(R.id.logout).setVisible(false);
//            menu.findItem(R.id.settings).setVisible(false);
//            menu.findItem(R.id.settings2).setVisible(true);
            menu.findItem(R.id.profile).setVisible(true);
            menu.findItem(R.id.namefor_menu).setVisible(false);
            menu.findItem(R.id.namefor_menu).setTitle(SharedPreference.getusername(MainActivity3.this));
        } else {
            menu.findItem(R.id.profpic).setVisible(false);
            menu.findItem(R.id.login).setVisible(false);
            menu.findItem(R.id.signup).setVisible(false);
            menu.findItem(R.id.logout).setVisible(false);
//            menu.findItem(R.id.settings).setVisible(true);
            menu.findItem(R.id.profile).setVisible(false);
//            menu.findItem(R.id.settings2).setVisible(true);
            menu.findItem(R.id.namefor_menu).setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.

        String Usernamess = LoginState.getUsername();

//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
        // lHandle action buttons
        switch (item.getItemId()) {
//        case R.id.action_websearch:
//            // create intent to perform web search for this planet
//            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
//            intent.putExtra(SearchManager.QUERY, getSupportActionBar().getTitle());
//            // catch event that there's no activity to handle intent
//            if (intent.resolveActivity(getPackageManager()) != null) {
//                startActivity(intent);
//            } else {
//                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
//            }
//            return true;
            case R.id.profpic:
//                Toast.makeText(getApplicationContext(),"sd",Toast.LENGTH_LONG).show();
                Bundle args = new Bundle();
                MainActivity3.getInstance().changeFragment(FragmentsAvailable.Profile, args);
//                Intent profipic = new Intent(MainActivity6.this, Profile.class);
//                startActivity(profipic);
                return true;
//                return true;
//            case R.id.aboutus:
////                Intent i = new Intent(MainActivity6.this, AboutApp.class);
////                startActivity(i);
//                args = new Bundle();
//                MainActivity3.getInstance().changeFragment(FragmentsAvailable.AboutApp, args);
//                return true;
            case R.id.login:
//                if(LoginState.getUsername() == null) {
//                    login.setVisible(false);
//                }
                Intent w = new Intent(MainActivity3.this, LoginActivity.class);
                startActivity(w);
                return true;
            case R.id.profile:
//                Intent profile = new Intent(MainActivity6.this, Profile.class);
//                startActivity(profile);
                args = new Bundle();
                MainActivity3.getInstance().changeFragment(FragmentsAvailable.Profile, args);
                return true;
            case R.id.namefor_menu:
//                Intent profile = new Intent(MainActivity6.this, Profile.class);
//                startActivity(profile);
                args = new Bundle();
                MainActivity3.getInstance().changeFragment(FragmentsAvailable.Profile, args);
                return true;
            case R.id.logout:
//                if(LoginState.getUsername() != null) {
//                    logout.setVisible(false);
//                }
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = settings.edit();
                editor.remove("username");
                editor.remove("city");
                editor.remove("birthplace");
                editor.remove("senin");
                editor.remove("selasa");
                editor.remove("rabu");
                editor.remove("kamis");
                editor.remove("jumat");
                editor.remove("nama_sekolah");
                editor.remove("jenjang_sekolah");
                editor.remove("regid");
                editor.remove("email");
                editor.remove("image");
                editor.remove("userid");
                editor.remove("studentid");
                editor.remove("nama_kelas");
                editor.clear();
                editor.commit();
                Intent intent = new Intent(MainActivity3.this, LoginActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                return true;
//            case R.id.settings:
//                Intent o = new Intent(MainActivity6.this, LoginActivity.class);
//                startActivity(o);
//                return true;
//            case R.id.settings2:
//                LoginState.setUsername(null);
//                LoginState.setUserid(0);
//                Intent p = new Intent(MainActivity6.this, MainActivity6.class);
//                startActivity(p);
//                return true;
            case R.id.signup:
//                if(LoginState.getUsername() == null) {
//                    signup.setVisible(false);
//                }
                Intent z = new Intent(MainActivity3.this, SignupActivity.class);
                startActivity(z);
                return true;
            case R.id.notification:
                Intent q = new Intent(MainActivity3.this, LoginActivity.class);
                startActivity(q);
                return true;
            case R.id.request:
                if(SharedPreference.getusername(MainActivity3.this).length() == 0) {
                    Intent e = new Intent(MainActivity3.this, LoginActivity.class);
                    startActivity(e);
                }else{
                    Intent e = new Intent(MainActivity3.this, Request.class);
                    startActivity(e);
                }
                return true;
//            case R.id.howto:
//                args = new Bundle();
//                MainActivity6.getInstance().changeFragment(FragmentsAvailable.Howto, args);
////                Intent h = new Intent(MainActivity6.this, Howto.class);
////                startActivity(h);
//                return true;
            case R.id.help:
                args = new Bundle();
                MainActivity3.getInstance().changeFragment(FragmentsAvailable.Help, args);
//                Intent a = new Intent(MainActivity6.this, Help.class);
//                startActivity(a);
                return true;
//            case R.id.report:
//                Intent l = new Intent(MainActivity6.this, LoginActivity.class);
//                startActivity(l);
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSearch(String query) {
//        Log.i("search", query);
//        View view = this.getCurrentFocus();
//        SearchJson search1 = new SearchJson();
//        search1.setId(i.getText().toString());
//        Intent search = new Intent(MainActivity6.this, SearchRoom.class);
//        search.putExtra("keyword", query);
//        startActivity(search);
//        Bundle args = new Bundle();
//        args.putString("keyword", query);
//        MainActivity6.getInstance().changeFragment(FragmentsAvailable.Searchroom, args);
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void searchViewOpened() {
        Toast.makeText(MainActivity3.this, "Search View Opened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchViewClosed() {
//        Util.showSnackBarMessage(fab, "Search View Closed");
    }

    @Override
    public void onItemClicked(String item) {
        Toast.makeText(MainActivity3.this, item, Toast.LENGTH_SHORT).show();
        View view = this.getCurrentFocus();
//        Intent search = new Intent(MainActivity6.this, SearchRoom.class);
//        search.putExtra("keyword", item);
//        startActivity(search);
        Bundle args = new Bundle();
        args.putString("keyword", item);
        MainActivity3.getInstance().changeFragment(FragmentsAvailable.Searchroom, args);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        mSearchView.hide();

    }

    @Override
    public void onScroll() {

    }

    @Override
    public void error(String localizedMessage) {

    }

    @Override
    public void onCancelSearch() {
//        Util.showSnackBarMessage(fab, "Search View Cleared");
        searchActive = false;
        mSearchView.hide();
    }

    public void changeFragment(FragmentsAvailable newFragment, Bundle args) {
//        selectedPosition = position;
//        mDrawerLayout.closeDrawer(navDrawerView);
        Fragment fragment = null;

        // update the main content by replacing fragments
        switch (newFragment) {
            case SchoolHomeFragment:
                fragment = new SchoolHomeFragment();
                break;
            case SchoolHomeFragment3:
                fragment = new SchoolHomeFragment3();
                break;
            case RekapNilai:
                fragment = new RekapNilai();
                break;
            case AbsenPage:
                fragment = new AbsenPage();
                break;
            case FIRST_FRAGMENT:
                fragment = new FirstFragment();
//                mDrawerToggle.setDrawerIndicatorEnabled(false);
//                mDrawerLayout.setEnabled(false);
//                mDrawerList.setEnabled(false);
//                mDrawerLayout.setClickable(false);
//                mDrawerList.setClickable(false);
//                dis1.setText("");
//                dis2.setText("");
//                dis3.setText("");
//                dis4.setText("");
//                dis5.setText("");
//                dis6.setText("");
                break;
            case SECOND_FRAGMENT:
                fragment = new SecondFragment();
                break;
            case THIRD_FRAGMENT:
                fragment = new ThirdFragment();
                break;
            case FORTH_FRAGMENT:
                fragment = new ForthFragment();
                break;
            case FIFTH_FRAGMENT:
                fragment = new FifthFragment();
                break;
            case Searchroom:
                fragment = new SearchRoom();
                break;
            case AboutApp:
                fragment = new AboutApp();
                break;
            case Profile:
                fragment = new Profile();
                break;
            case Help:
                fragment = new Help();
                break;
            case Howto:
                fragment = new Howto();
                break;
//            case Login:
//                fragment = new LoginActivity();
//                break;
//            case Signup:
//                fragment = new SignupActivity();
//                break;
            case RequestPage:
                fragment = new RequestPage();
                break;
            case DailyDevotion:
                fragment = new DailyDevotion();
                break;
            case GeneralList:
                fragment = new GeneralList();
                break;
        }


        if(args != null) {
            fragment.setArguments(args);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(currentFragment.toString());
        transaction.replace(R.id.content_frame, fragment, newFragment.toString());


        // Commit the transaction
        transaction.commitAllowingStateLoss();
        getFragmentManager().executePendingTransactions();
        currentFragment = newFragment;

        // update selected item and title, then close the drawer
        // mDrawerList.setItemChecked(selectedPosition, true);
//        setTitle(mPlanetTitles[selectedPosition]);

    }

    public void changeFragment(FragmentsAvailable newFragment) {
        changeFragment(newFragment, null);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void scheduleNotification(int week, AlarmManager alarmManager){
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra("notifId", 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, 0);

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, week);
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);



//        super.onCreate(savedInstanceState);
//        this.alarmManager = (AlarmManager) MainActivity3.getInstance().getSystemService(ALARM_SERVICE);
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.alarmManager = (AlarmManager) this.getSystemService(getApplicationContext().ALARM_SERVICE);
        this.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), this.alarmManager.ELAPSED_REALTIME, pendingIntent);
    }

}