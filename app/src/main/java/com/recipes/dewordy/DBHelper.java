/**
 * Application name : Recipes App
 * Author			: Taufan Erfiyanto
 * Date				: March 2012
 */
package com.recipes.dewordy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.recipes.dewordy.fragment.CategoryFragment.FifthFragment;
import com.recipes.dewordy.fragment.CategoryFragment.FirstFragment;
import com.recipes.dewordy.fragment.CategoryFragment.ForthFragment;
import com.recipes.dewordy.fragment.CategoryFragment.FragmentsAvailable;
import com.recipes.dewordy.fragment.CategoryFragment.SecondFragment;
import com.recipes.dewordy.fragment.CategoryFragment.ThirdFragment;
import com.recipes.dewordy.model.search.SearchJson;
import com.shahroz.svlibrary.interfaces.onSearchListener;
import com.shahroz.svlibrary.interfaces.onSimpleSearchActionsListener;
import com.shahroz.svlibrary.widgets.MaterialSearchView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper{
	 
    private static String DB_PATH = "/data/data/com.recipes.app/databases/";
 
    private final static String DB_NAME = "db_recipes";
	public final static int DB_VERSION = 1;
    public static SQLiteDatabase db; 
 
    private final Context context;
    
	private final String TABLE_NAME = "tbl_recipes";
	private final String ID = "id";
	private final String RECIPE_NAME = "recipe_name";
	private final String IMAGE_PREVIEW = "image_preview";
	private final String PREP_TIME = "prepare_time";
	private final String COOK_TIME = "cook_time";
	private final String SERVES = "serves";
	private final String SUMMARY = "summary";
	private final String INGREDIENTS = "ingredients";
	private final String DIRECTIONS = "directions";
	
	
    public DBHelper(Context context) {
 
    	super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }	
 
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
    	SQLiteDatabase db_Read = null;

 
    	if(dbExist){
    		//do nothing - database already exist
    		deleteDataBase();
    		try {
    			copyDataBase();
    		} catch (IOException e) {
        		throw new Error("Error copying database");
        	}
    	}else{
    		db_Read = this.getReadableDatabase();
    		db_Read.close();
 
        	try {
    			copyDataBase();
    		} catch (IOException e) {
        		throw new Error("Error copying database");
        	}
    	}
 
    }
 
    private void deleteDataBase(){
    	File dbFile = new File(DB_PATH + DB_NAME);
    	
    	dbFile.delete();
    }
   
    private boolean checkDataBase(){
 
    	File dbFile = new File(DB_PATH + DB_NAME);

    	return dbFile.exists();
    	
    }
 
    
    private void copyDataBase() throws IOException{
    	
    	InputStream myInput = context.getAssets().open(DB_NAME);
 
    	String outFileName = DB_PATH + DB_NAME;
 
    	OutputStream myOutput = new FileOutputStream(outFileName);
    	
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDataBase() throws SQLException{
    	String myPath = DB_PATH + DB_NAME;
    	db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
 
    @Override
	public void close() {
    	db.close();
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}
	
	/** this code is used to get all data from database */
 	public ArrayList<ArrayList<Object>> getAllData(String RecipeNameKeyword){
		ArrayList<ArrayList<Object>> dataArrays = new ArrayList<ArrayList<Object>>();
 
		Cursor cursor = null;
 
		if(RecipeNameKeyword.equals("")){
			try{
				cursor = db.query(
						TABLE_NAME,
						new String[]{ID, RECIPE_NAME, IMAGE_PREVIEW, COOK_TIME},
						null, null, null, null, null);
				cursor.moveToFirst();
	
				if (!cursor.isAfterLast()){
					do{
						ArrayList<Object> dataList = new ArrayList<Object>();
						
						dataList.add(cursor.getLong(0));
						dataList.add(cursor.getString(1));
						dataList.add(cursor.getString(2));
						dataList.add(cursor.getString(3));
	 
						dataArrays.add(dataList);
					}
				
					while (cursor.moveToNext());
				}
				cursor.close();
			}catch (SQLException e){
				Log.e("DB Error", e.toString());
				e.printStackTrace();
			}
		}else{
			try{
				cursor = db.query(
						TABLE_NAME,
						new String[]{ID, RECIPE_NAME, IMAGE_PREVIEW, COOK_TIME},
						RECIPE_NAME +" LIKE '%"+RecipeNameKeyword+"%'",
						null, null, null, null);
				cursor.moveToFirst();
	
				if (!cursor.isAfterLast()){
					do{
						ArrayList<Object> dataList = new ArrayList<Object>();
	 
						dataList.add(cursor.getLong(0));
						dataList.add(cursor.getString(1));
						dataList.add(cursor.getString(2));
						dataList.add(cursor.getString(3));
	 
						dataArrays.add(dataList);
					}
				
					while (cursor.moveToNext());
				}
				cursor.close();
			}catch (SQLException e){
				Log.e("DB Error", e.toString());
				e.printStackTrace();
			}
		}
		return dataArrays;
	}
	
 	/** this code is used to get data from database base on id value */
 	public ArrayList<Object> getDetail(long id){
		
		ArrayList<Object> rowArray = new ArrayList<Object>();
		Cursor cursor;
 
		try{
			cursor = db.query(
					TABLE_NAME,
					new String[] {RECIPE_NAME, IMAGE_PREVIEW, PREP_TIME, COOK_TIME, SERVES, SUMMARY, INGREDIENTS, DIRECTIONS},
					ID + "=" + id,
					null, null, null, null, null);
 
			cursor.moveToFirst();
 
			if (!cursor.isAfterLast()){
				do{
					rowArray.add(cursor.getString(0));
					rowArray.add(cursor.getString(1));
					rowArray.add(cursor.getString(2));
					rowArray.add(cursor.getString(3));
					rowArray.add(cursor.getString(4));
					rowArray.add(cursor.getString(5));
					rowArray.add(cursor.getString(6));
					rowArray.add(cursor.getString(7));
				}
				while (cursor.moveToNext());
			}
 
			cursor.close();
		}
		catch (SQLException e) 
		{
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
 
		return rowArray;
	}

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
    public static class MainActivity2 extends AppCompatActivity implements onSimpleSearchActionsListener, onSearchListener {

        private DrawerLayout mDrawerLayout;
        // private ListView mDrawerList;

        private ExpandableListView mDrawerList;
        private static MainActivity2 instance = null;

        private LinearLayout navDrawerView;

        CustomExpandAdapter customAdapter;

        private ActionBarDrawerToggle mDrawerToggle;
        private Toolbar mToolbar;

        private boolean mSearchViewAdded = false;
        private MaterialSearchView mSearchView;
        private WindowManager mWindowManager;
        private MenuItem searchItem;
        private boolean searchActive = false;
        private FloatingActionButton fab;

        private CharSequence mDrawerTitle;
        private CharSequence mTitle;
    //    private String[] mPlanetTitles;
        private String[] mKindergartenTitles;
        private String[] mElementaryTitles;
        private String[] mSecondaryTitles;
        private String[] mHighschoolTitles;
        private String[] mCollegeTitles;
        private int selectedPosition;
        private FragmentsAvailable currentFragment, nextFragment;

        List<SampleTO> listParent;

        HashMap<String, List<String>> listDataChild;

        public static MainActivity2 getInstance() {
            if(instance == null) {
                instance = new MainActivity2();
            }

            return instance;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            instance = this;
            mTitle = mDrawerTitle = LoginState.getUsername();
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);

            navDrawerView = (LinearLayout) findViewById(R.id.navDrawerView);
    //        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
            mKindergartenTitles = getResources().getStringArray(R.array.kindergarten_array);
            mElementaryTitles = getResources().getStringArray(R.array.elementary_array);
            mSecondaryTitles = getResources().getStringArray(R.array.secondary_array);
            mHighschoolTitles = getResources().getStringArray(R.array.highschool_array);
            mCollegeTitles = getResources().getStringArray(R.array.college_array);

            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            // mDrawerList = (ListView) findViewById(R.id.left_drawer);

            mDrawerList = (ExpandableListView) findViewById(R.id.nav_left_drawer);

            // set a custom shadow that overlays the main content when the drawer
            // opens
            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

            listParent = new ArrayList<SampleTO>();
            listDataChild = new HashMap<String, List<String>>();

            // Navigation Drawer of Flight starts
            listParent.add(new SampleTO(getString(R.string.sun), R.drawable.sun));
            listParent.add(new SampleTO(getString(R.string.kindergarten), R.drawable.sun));
            listParent.add(new SampleTO(getString(R.string.elementary), R.drawable.moon));
            listParent.add(new SampleTO(getString(R.string.secondary), R.drawable.solar_system));
            listParent.add(new SampleTO(getString(R.string.highschool), R.drawable.solar_system));
            listParent.add(new SampleTO(getString(R.string.college), R.drawable.solar_system));

            listDataChild.put(getString(R.string.kindergarten), Arrays.asList(mKindergartenTitles));
            listDataChild.put(getString(R.string.elementary), Arrays.asList(mElementaryTitles));
            listDataChild.put(getString(R.string.secondary), Arrays.asList(mSecondaryTitles));
            listDataChild.put(getString(R.string.highschool), Arrays.asList(mHighschoolTitles));
            listDataChild.put(getString(R.string.college), Arrays.asList(mCollegeTitles));

            listDataChild.put(getString(R.string.sun), new ArrayList<String>());
            listDataChild.put(getString(R.string.moon), new ArrayList<String>());
    //
    //        listDataChild.put(getString(R.string.Planets), Arrays.asList(mPlanetTitles));

            customAdapter = new CustomExpandAdapter(this, listParent, listDataChild);
            // setting list adapter
            mDrawerList.setAdapter(customAdapter);
            mDrawerList.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);
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

            if (savedInstanceState == null) {
                currentFragment = FragmentsAvailable.RekapNilai;
                changeFragment(FragmentsAvailable.RekapNilai);
            }

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
                                    MaterialSearchView.getSearchViewLayoutParams(MainActivity2.this));
                            mSearchViewAdded = true;
                        }
                    }
                });
            }
        }

        @Override
        protected void onResume() {
            super.onResume();
            mDrawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupPosition));
                    parent.setItemChecked(index, true);

                    String parentTitle = ((SampleTO) customAdapter.getGroup(groupPosition)).getTitle();

                    if (parentTitle != getString(R.string.Planets)) {
                        mDrawerLayout.closeDrawer(navDrawerView);
                    }

                    return false;
                }
            });

            mDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                    Log.d("CHECK", "child click");

                    int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                    parent.setItemChecked(index, true);

    //                changeFragment(childPosition);

                    return false;
                }
            });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            searchItem = menu.findItem(R.id.search);
            searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    mSearchView.display();
                    openKeyboard();
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
    //    @Override
    //    public boolean onPrepareOptionsMenu(Menu menu) {
    //        // If the nav drawer is open, hide action items related to the content
    //        // view
    //        boolean drawerOpen = mDrawerLayout.isDrawerOpen(navDrawerView);
    //        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
    //        return super.onPrepareOptionsMenu(menu);
    //    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // The action bar home/up action should open or close the drawer.
            // ActionBarDrawerToggle will take care of this.
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
            // Handle action buttons
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
                case R.id.aboutus:
                    Intent i = new Intent(MainActivity2.this, AboutApp.class);
                    startActivity(i);
                    return true;
                case R.id.login:
                Intent w = new Intent(MainActivity2.this, LoginActivity.class);
                startActivity(w);
                return true;
                case R.id.signup:
                    Intent z = new Intent(MainActivity2.this, SignupActivity.class);
                    startActivity(z);
                    return true;
                case R.id.notification:
                    Intent q = new Intent(MainActivity2.this, LoginActivity.class);
                    startActivity(q);
                    return true;
                case R.id.settings:
                    Intent o = new Intent(MainActivity2.this, LoginActivity.class);
                    startActivity(o);
                    return true;
                case R.id.help:
                    Intent a = new Intent(MainActivity2.this, LoginActivity.class);
                    startActivity(a);
                    return true;
                case R.id.report:
                    Intent l = new Intent(MainActivity2.this, LoginActivity.class);
                    startActivity(l);
                    return true;

            default:
                return super.onOptionsItemSelected(item);
            }
        }

        @Override
        public void onSearch(String query) {
            Log.i("search", query);
            SearchJson search1 = new SearchJson();
    //        search1.setId(mSearchView.getText().toString());


        }

        @Override
        public void searchViewOpened() {
            Toast.makeText(MainActivity2.this, "Search View Opened", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void searchViewClosed() {
    //        Util.showSnackBarMessage(fab, "Search View Closed");
        }

        @Override
        public void onItemClicked(String item) {
            Toast.makeText(MainActivity2.this, item + " clicked", Toast.LENGTH_SHORT).show();
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
                case FIRST_FRAGMENT:
                    fragment = new FirstFragment();
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

    }
}