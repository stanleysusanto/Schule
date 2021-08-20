///*
// * Copyright 2013 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.recipes.dewordy;
//
//import android.app.Fragment;
//import android.app.FragmentTransaction;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.SystemClock;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.WindowManager;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.ExpandableListView;
//import android.widget.ExpandableListView.OnChildClickListener;
//import android.widget.ExpandableListView.OnGroupClickListener;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import com.facebook.FacebookSdk;
//import com.recipes.dewordy.fragment.CategoryFragment.FifthFragment;
//import com.recipes.dewordy.fragment.CategoryFragment.FirstFragment;
//import com.recipes.dewordy.fragment.CategoryFragment.ForthFragment;
//import com.recipes.dewordy.fragment.CategoryFragment.FragmentsAvailable;
//import com.recipes.dewordy.fragment.CategoryFragment.SecondFragment;
//import com.recipes.dewordy.fragment.CategoryFragment.ThirdFragment;
//import com.shahroz.svlibrary.interfaces.onSearchListener;
//import com.shahroz.svlibrary.interfaces.onSimpleSearchActionsListener;
//import com.shahroz.svlibrary.widgets.MaterialSearchView;
//import com.squareup.picasso.Picasso;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
////import com.recipes.dewordy.fragment.CategoryFragment.ThirdFragment;
//
///**
// * This example illustrates a common usage of the DrawerLayout widget in the Android support library.
// * <p/>
// * <p>
// * When a navigation (left) drawer is present, the host activity should detect presses of the action bar's Up affordance as a signal to open and close the navigation drawer. The ActionBarDrawerToggle
// * facilitates this behavior. Items within the drawer should fall into one of two categories:
// * </p>
// * <p/>
// * <ul>
// * <li><strong>View switches</strong>. A view switch follows the same basic policies as list or tab navigation in that a view switch does not create navigation history. This pattern should only be
// * used at the root activity of a task, leaving some form of Up navigation active for activities further down the navigation hierarchy.</li>
// * <li><strong>Selective Up</strong>. The drawer allows the user to choose an alternate parent for Up navigation. This allows a user to jump across an app's navigation hierarchy at will. The
// * application should treat this as it treats Up navigation from a different task, replacing the current task stack using TaskStackBuilder or similar. This is the only form of navigation drawer that
// * should be used outside of the root activity of a task.</li>
// * </ul>
// * <p/>
// * <p>
// * Right side drawers should be used for actions, not navigation. This follows the pattern established by the Action Bar that navigation should be to the left and actions to the right. An action
// * should be an operation performed on the current contents of the window, for example enabling or disabling a data overlay on top of the current content.
// * </p>
// */
//public class MainActivity6 extends AppCompatActivity implements onSimpleSearchActionsListener, onSearchListener {
//
//    private DrawerLayout mDrawerLayout;
//    // private ListView mDrawerList;
//
//    private ExpandableListView mDrawerList;
//    private static MainActivity6 instance = null;
//
//    private LinearLayout navDrawerView;
//
//    CustomExpandAdapter customAdapter;
//
//    private ActionBarDrawerToggle mDrawerToggle;
//    private Toolbar mToolbar;
//
//    private boolean mSearchViewAdded = false;
//    private MaterialSearchView mSearchView;
//    private WindowManager mWindowManager;
//    private MenuItem searchItem, profileItem, settings, login, signup, logout;
//    private boolean searchActive = false;
////    private FloatingActionButton fab;
//
//    private CharSequence mDrawerTitle;
//    private CharSequence mTitle;
//    //    private String[] mPlanetTitles;
//    private String[] mKindergartenTitles;
//    private String[] mElementaryTitles;
//    private String[] mSecondaryTitles;
//    private String[] mHighschoolTitles;
//    private String[] mCollegeTitles;
//    private int selectedPosition;
//    private FragmentsAvailable currentFragment, nextFragment;
//
//    static boolean id2;
//
//    List<SampleTO> listParent;
//
//    HashMap<String, List<String>> listDataChild;
//
//    public static MainActivity6 getInstance() {
//        if(instance == null) {
//            instance = new MainActivity6();
//        }
//
//        return instance;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        invalidateOptionsMenu();
//        setContentView(R.layout.activity_main);
//
//        instance = this;
//
//        Intent i_get = getIntent();
//        id2 = i_get.getBooleanExtra("notif", false);
//
//        if(id2){
//            Bundle args = new Bundle();
//            args.putInt("id", 0);
//            args.putInt("frm_sub_category_id", 0);
//            args.putInt("next_id", 0);
//            args.putInt("sub_next_id", 0);
//            MainActivity6.getInstance().changeFragment(FragmentsAvailable.FIFTH_FRAGMENT, args);
//        }
//
//        FacebookSdk.sdkInitialize(getApplicationContext());
////        mTitle = mDrawerTitle = LoginState.getUsername();
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
//
//        navDrawerView = (LinearLayout) findViewById(R.id.navDrawerView);
////        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
//        mKindergartenTitles = getResources().getStringArray(R.array.kindergarten_array);
//        mElementaryTitles = getResources().getStringArray(R.array.elementary_array);
//        mSecondaryTitles = getResources().getStringArray(R.array.secondary_array);
//        mHighschoolTitles = getResources().getStringArray(R.array.highschool_array);
//        mCollegeTitles = getResources().getStringArray(R.array.college_array);
//
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        // mDrawerList = (ListView) findViewById(R.id.left_drawer);
//
//        mDrawerList = (ExpandableListView) findViewById(R.id.nav_left_drawer);
//
//        // set a custom shadow that overlays the main content when the drawer
//        // opens
//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
//
//        listParent = new ArrayList<SampleTO>();
//        listDataChild = new HashMap<String, List<String>>();
//
//        // Navigation Drawer of Flight starts
//        listParent.add(new SampleTO("HOME", R.drawable.sun));
//        listParent.add(new SampleTO(getString(R.string.kindergarten), R.drawable.sun));
//        listParent.add(new SampleTO(getString(R.string.elementary), R.drawable.sun));
//        listParent.add(new SampleTO(getString(R.string.secondary), R.drawable.sun));
//        listParent.add(new SampleTO(getString(R.string.highschool), R.drawable.sun));
//        listParent.add(new SampleTO(getString(R.string.college), R.drawable.sun));
//
//        listDataChild.put(getString(R.string.kindergarten), Arrays.asList(mKindergartenTitles));
//        listDataChild.put(getString(R.string.elementary), Arrays.asList(mElementaryTitles));
//        listDataChild.put(getString(R.string.secondary), Arrays.asList(mSecondaryTitles));
//        listDataChild.put(getString(R.string.highschool), Arrays.asList(mHighschoolTitles));
//        listDataChild.put(getString(R.string.college), Arrays.asList(mCollegeTitles));
//
//        listDataChild.put(getString(R.string.sun), new ArrayList<String>());
//        listDataChild.put(getString(R.string.moon), new ArrayList<String>());
////
////        listDataChild.put(getString(R.string.Planets), Arrays.asList(mPlanetTitles));
//
//        customAdapter = new CustomExpandAdapter(this, listParent, listDataChild);
//        // setting list adapter
//        mDrawerList.setAdapter(customAdapter);
//        mDrawerList.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);
//        // // set up the drawer's list view with items and click listener
//        // mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
//        // mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
//
//        // enable ActionBar app icon to behave as action to toggle nav drawer
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//
//        // ActionBarDrawerToggle ties together the the proper interactions
//        // between the sliding drawer and the action bar app icon
//        mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
//                mDrawerLayout, /* DrawerLayout object */
////        R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
//                R.string.drawer_open, /* "open drawer" description for accessibility */
//                R.string.drawer_close /* "close drawer" description for accessibility */
//        ) {
//            public void onDrawerClosed(View view) {
//                getSupportActionBar().setTitle(mTitle);
//                invalidateOptionsMenu(); // creates call to
//                // onPrepareOptionsMenu()
//            }
//
//            public void onDrawerOpened(View drawerView) {
//                getSupportActionBar().setTitle(mDrawerTitle);
//                invalidateOptionsMenu(); // creates call to
//                // onPrepareOptionsMenu()
//            }
//        };
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//        if (savedInstanceState == null) {
//            currentFragment = FragmentsAvailable.FIRST_FRAGMENT;
//            changeFragment(FragmentsAvailable.FIRST_FRAGMENT);
//        }
//
//        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        mSearchView = new MaterialSearchView(this);
//        mSearchView.setOnSearchListener(this);
//        mSearchView.setSearchResultsListener(this);
//        mSearchView.setHintText("Search");
//
//        if (mToolbar != null) {
//            // Delay adding SearchView until Toolbar has finished loading
//            mToolbar.post(new Runnable() {
//                @Override
//                public void run() {
//                    if (!mSearchViewAdded && mWindowManager != null) {
//                        mWindowManager.addView(mSearchView,
//                                MaterialSearchView.getSearchViewLayoutParams(MainActivity6.this));
//                        mSearchViewAdded = true;
//                    }
//                }
//            });
//        }
//    }
//
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
//                if(parentTitle == getString(R.string.home)){
////                    Intent a = new Intent(MainActivity6.this, SecondFragment.class);
////                    a.putExtra("id", 1);
////                    startActivity(a);
////                    Bundle args = new Bundle();
////                    args.putInt("id", 0);
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.FIRST_FRAGMENT);
//                } else if (parentTitle == getString(R.string.kindergarten)) {
////                    Intent a = new Intent(MainActivity6.this, SecondFragment.class);
////                    a.putExtra("id", 1);
////                    startActivity(a);
//                    Bundle args = new Bundle();
//                    args.putInt("id", 1);
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, args);
//                }else if (parentTitle == getString(R.string.elementary)) {
//                    Bundle a = new Bundle();
//                    a.putInt("id", 2);
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, a);
//                }else if (parentTitle == getString(R.string.secondary)) {
//                    Bundle a = new Bundle();
//                    a.putInt("id", 3);
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, a);
//                }else if (parentTitle == getString(R.string.highschool)) {
//                    Bundle a = new Bundle();
//                    a.putInt("id", 4);
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, a);
//                } else if (parentTitle == getString(R.string.college)) {
//                    Bundle a = new Bundle();
//                    a.putInt("id", 5);
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, a);
//                }
//
//
//                return false;
//
//            }
//        });
//
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
//                if(groupPosition==1 && childPosition ==1  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 1);
//                    a.putInt("id_sub_category", 1);
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==2 && childPosition ==1  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 2);
//                    a.putInt("id_sub_category", 1);
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==3 && childPosition ==1  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 3);
//                    a.putInt("id_sub_category", 1);
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==4 && childPosition ==1  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 4);
//                    a.putInt("id_sub_category", 1);
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//                if(groupPosition==5 && childPosition ==1  ){
//                    Bundle a = new Bundle();
//                    a.putInt("id", 5);
//                    a.putInt("id_sub_category", 1);
//                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, a);
//                }
//
//                return false;
//            }
//        });
//    }
//
//    public static Drawable drawableFromUrl(String url) throws IOException {
//        Bitmap x;
//
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.connect();
//        InputStream input = connection.getInputStream();
//
//        x = BitmapFactory.decodeStream(input);
//        return new BitmapDrawable(x);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
////        if(LoginState.getUsername()!= null) {
////            profileItem = menu.findItem(R.id.profpic);
////            profileItem.setVisible(true);
////            invalidateOptionsMenu();
////            ImageView profpic = new ImageView(this);
////            Picasso.with(this).load("https://cdn2.iconfinder.com/data/icons/users-6/100/USER10-128.png").into(profpic);
////            profileItem.setActionView(profpic);
////            profileItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
////                @Override
////                public boolean onMenuItemClick(MenuItem item) {
////                    Toast.makeText(getApplicationContext(),"sd",Toast.LENGTH_LONG).show();
////                    Bundle args = new Bundle();
////                    MainActivity6.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT);
////                    return true;
////                }
////            });
////        }else{
////            profileItem = menu.findItem(R.id.profpic);
////            profileItem.setVisible(false);
////            invalidateOptionsMenu();
////        }
////
//
//        searchItem = menu.findItem(R.id.search);
//        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                mSearchView.display();
//                openKeyboard();
////                Intent search = new Intent("search", searchItem[position]);
//                return true;
//            }
//        });
//        if (searchActive)
//            mSearchView.display();
//        return true;
//
//    }
//
//    private void openKeyboard() {
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
//                mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
//            }
//        }, 200);
//    }
//
//    /* Called whenever we call invalidateOptionsMenu() */
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//
////        View settings = findViewById(R.id.settings);
////        settings.setMinimumWidth(200);
//        // If the nav drawer is open, hide action items related to the content
//        // view
////        boolean drawerOpen = mDrawerLayout.isDrawerOpen(navDrawerView);
////        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
//        if(LoginState.getUsername()!= null) {
//            ImageView profpic = new ImageView(this);
//            Picasso.with(this).load("https://cdn2.iconfinder.com/data/icons/users-6/100/USER10-128.png").into(profpic);
//            menu.findItem(R.id.profpic).setActionView(profpic);
//            menu.findItem(R.id.profpic).setVisible(true);
//            menu.findItem(R.id.login).setVisible(false);
//            menu.findItem(R.id.signup).setVisible(false);
//            menu.findItem(R.id.logout).setVisible(true);
//            menu.findItem(R.id.settings).setVisible(false);
//            menu.findItem(R.id.profile).setVisible(true);
//        } else {
//            menu.findItem(R.id.profpic).setVisible(false);
//            menu.findItem(R.id.login).setVisible(true);
//            menu.findItem(R.id.signup).setVisible(true);
//            menu.findItem(R.id.logout).setVisible(false);
//            menu.findItem(R.id.settings).setVisible(true);
//            menu.findItem(R.id.profile).setVisible(false);
//        }
//
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // The action bar home/up action should open or close the drawer.
//        // ActionBarDrawerToggle will take care of this.
//
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        // Handle action buttons
//        switch (item.getItemId()) {
////        case R.id.action_websearch:
////            // create intent to perform web search for this planet
////            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
////            intent.putExtra(SearchManager.QUERY, getSupportActionBar().getTitle());
////            // catch event that there's no activity to handle intent
////            if (intent.resolveActivity(getPackageManager()) != null) {
////                startActivity(intent);
////            } else {
////                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
////            }
////            return true;
//            case R.id.profpic:
//                Toast.makeText(getApplicationContext(),"sd",Toast.LENGTH_LONG).show();
////                Bundle args = new Bundle();
////                MainActivity6.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, args);
//                Intent profipic = new Intent(MainActivity6.this, Profile.class);
//                startActivity(profipic);
//                return true;
////                return true;
//            case R.id.aboutus:
//                Intent i = new Intent(MainActivity6.this, AboutApp.class);
//                startActivity(i);
//                return true;
//            case R.id.login:
////                if(LoginState.getUsername() == null) {
////                    login.setVisible(false);
////                }
//                Intent w = new Intent(MainActivity6.this, LoginActivity.class);
//                startActivity(w);
//                return true;
//            case R.id.profile:
//                Intent profile = new Intent(MainActivity6.this, Profile.class);
//                startActivity(profile);
//                return true;
//            case R.id.logout:
////                if(LoginState.getUsername() != null) {
////                    logout.setVisible(false);
////                }
//                LoginState.setUsername(null);
//                LoginState.setUserid(0);
//                Intent intent = new Intent(MainActivity6.this, MainActivity6.class);
//
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                startActivity(intent);
//                return true;
//            case R.id.settings:
////                if(LoginState.getUsername() == null) {
////                    settings.setVisible(true);
////                }
////                View settings = findViewById(R.id.settings);
////                settings.setMinimumWidth(200);
//                Intent o = new Intent(MainActivity6.this, LoginActivity.class);
//                startActivity(o);
//
//
//                return true;
//            case R.id.signup:
////                if(LoginState.getUsername() == null) {
////                    signup.setVisible(false);
////                }
//                Intent z = new Intent(MainActivity6.this, SignupActivity.class);
//                startActivity(z);
//                return true;
//            case R.id.notification:
//                Intent q = new Intent(MainActivity6.this, LoginActivity.class);
//                startActivity(q);
//                return true;
//            case R.id.request:
//                if(LoginState.getUsername() == null) {
//                    Intent e = new Intent(MainActivity6.this, LoginActivity.class);
//                    startActivity(e);
//                }else{
//                    Intent e = new Intent(MainActivity6.this, Request.class);
//                    startActivity(e);
//                }
//                return true;
////            case R.id.howto:
////                Intent h = new Intent(MainActivity6.this, Howto.class);
////                startActivity(h);
////                return true;
//            case R.id.help:
//                Intent a = new Intent(MainActivity6.this, Help.class);
//                startActivity(a);
//                return true;
////            case R.id.report:
////                Intent l = new Intent(MainActivity6.this, LoginActivity.class);
////                startActivity(l);
////                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public void onSearch(String query) {
////        Log.i("search", query);
////        View view = this.getCurrentFocus();
////        SearchJson search1 = new SearchJson();
////        search1.setId(i.getText().toString());
////        Intent search = new Intent(MainActivity6.this, SearchRoom.class);
////        search.putExtra("keyword", query);
////        startActivity(search);
////        Bundle args = new Bundle();
////        args.putString("keyword", query);
////        MainActivity6.getInstance().changeFragment(FragmentsAvailable.Searchroom, args);
////        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
////        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
//
//    @Override
//    public void searchViewOpened() {
//        Toast.makeText(MainActivity6.this, "Search View Opened", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void searchViewClosed() {
////        Util.showSnackBarMessage(fab, "Search View Closed");
//    }
//
//    @Override
//    public void onItemClicked(String item) {
//        Toast.makeText(MainActivity6.this, item, Toast.LENGTH_SHORT).show();
//        View view = this.getCurrentFocus();
////        Intent search = new Intent(MainActivity6.this, SearchRoom.class);
////        search.putExtra("keyword", item);
////        startActivity(search);
//        Bundle args = new Bundle();
//        args.putString("keyword", item);
//        MainActivity6.getInstance().changeFragment(FragmentsAvailable.Searchroom, args);
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
//
//    @Override
//    public void onScroll() {
//
//    }
//
//    @Override
//    public void error(String localizedMessage) {
//
//    }
//
//    @Override
//    public void onCancelSearch() {
////        Util.showSnackBarMessage(fab, "Search View Cleared");
//        searchActive = false;
//        mSearchView.hide();
//    }
//
//    public void changeFragment(FragmentsAvailable newFragment, Bundle args) {
////        selectedPosition = position;
////        mDrawerLayout.closeDrawer(navDrawerView);
//        Fragment fragment = null;
//
//        // update the main content by replacing fragments
//        switch (newFragment) {
//            case FIRST_FRAGMENT:
//                fragment = new FirstFragment();
//                break;
//            case SECOND_FRAGMENT:
//                fragment = new SecondFragment();
//                break;
//            case THIRD_FRAGMENT:
//                fragment = new ThirdFragment();
//                break;
//            case FORTH_FRAGMENT:
//                fragment = new ForthFragment();
//                break;
//            case FIFTH_FRAGMENT:
//                fragment = new FifthFragment();
//                break;
//            case Searchroom:
//                fragment = new SearchRoom();
//                break;
////            case PROFILE:
////                fragment = new Profile();
////                break;
//        }
//
//
//        if(args != null) {
//            fragment.setArguments(args);
//        }
//
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.addToBackStack(currentFragment.toString());
//        transaction.replace(R.id.content_frame, fragment, newFragment.toString());
//
//
//        // Commit the transaction
//        transaction.commitAllowingStateLoss();
//        getFragmentManager().executePendingTransactions();
//        currentFragment = newFragment;
//
//        // update selected item and title, then close the drawer
//        // mDrawerList.setItemChecked(selectedPosition, true);
////        setTitle(mPlanetTitles[selectedPosition]);
//
//    }
//
//    public void changeFragment(FragmentsAvailable newFragment) {
//        changeFragment(newFragment, null);
//    }
//
//    @Override
//    public void setTitle(CharSequence title) {
//        mTitle = title;
//        getSupportActionBar().setTitle(mTitle);
//    }
//
//    /**
//     * When using the ActionBarDrawerToggle, you must call it during onPostCreate() and onConfigurationChanged()...
//     */
//
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        // Pass any configuration change to the drawer toggls
//        mDrawerToggle.onConfigurationChanged(newConfig);
//    }
//
//    @Override
//    public void onBackPressed() {
//        if(getFragmentManager().getBackStackEntryCount() > 0) {
//            getFragmentManager().popBackStack();
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//}
//
