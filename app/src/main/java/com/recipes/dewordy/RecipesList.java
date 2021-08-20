//package com.recipes.app;
//
//import android.app.Fragment;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.database.SQLException;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.SystemClock;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.recipes.app.model.category.Category;
//import com.recipes.app.model.category.Json;
//import com.recipes.app.rest.RestClient;
//import com.shahroz.svlibrary.interfaces.onSearchListener;
//import com.shahroz.svlibrary.interfaces.onSimpleSearchActionsListener;
//import com.shahroz.svlibrary.utils.Util;
//import com.shahroz.svlibrary.widgets.MaterialSearchView;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//
//import retrofit.Callback;
//import retrofit.RetrofitError;
//import retrofit.client.Response;
//
//public class RecipesList extends AppCompatActivity implements onSimpleSearchActionsListener, onSearchListener {
//
//	ImageView imgAbout, imgSearchNav;
//	Button btnSearch;
//	EditText edtSearch;
//	LinearLayout lytSearchForm;
//	ListView listRecipes;
//	ProgressBar prgLoading;
//	TextView txtAlert;
//
//	private boolean mSearchViewAdded = false;
//	private MaterialSearchView mSearchView;
//	private WindowManager mWindowManager;
//	private Toolbar mToolbar;
//	private MenuItem searchItem;
//	private boolean searchActive = false;
//	private FloatingActionButton fab;
//
//	String RecipeNameKeyword = "";
//
//	static DBHelper dbhelper;
//	ArrayList<ArrayList<Object>> data;
//	ListAdapter la;
//
//	private Runnable mSliderThread;
//	private Handler mSliderHandler;
//	private int mPosition = 0;
//	private ViewPager viewPager;
//
//	List<Category> categories;
//	static int[] id;
//	static String[] RecipeName;
//	static String[] Preview;
//	static String[] CookTime;
//
////	private DrawerLayout mDrawerLayout;
//	// private ListView mDrawerList;
//
////	private ExpandableListView mDrawerList;
////
////	private LinearLayout navDrawerView;
////
////	CustomExpandAdapter customAdapter;
//
////	private ActionBarDrawerToggle mDrawerToggle;
//
//	private CharSequence mDrawerTitle;
//	private CharSequence mTitle;
//	private String[] mPlanetTitles;
//	private int selectedPosition;
//
//	List<SampleTO> listParent;
//
//	HashMap<String, List<String>> listDataChild;
//
//
//	@Override
//	public void onSearch(String query) {
//		Log.i("search", query);
//		List<Category> filterCategories = new ArrayList<>();
//		for (Category category : categories) {
//			String name = category.getCategory_name();
//			if (name.contains(query)) {
//				filterCategories.add(category);
//			}
//		}
//
//		id = new int[filterCategories.size()];
//		RecipeName = new String[filterCategories.size()];
//		Preview = new String[filterCategories.size()];
//		CookTime = new String[filterCategories.size()];
//
//		for (int i = 0; i < filterCategories.size(); i++) {
//			id[i] = Integer.parseInt(filterCategories.get(i).getCid());
//			RecipeName[i] = filterCategories.get(i).getCategory_name();
//			Preview[i] = filterCategories.get(i).getCategory_image();
//			CookTime[i] = filterCategories.get(i).getStatus();
//		}
//
//		la.notifyDataSetChanged();
//
////		listRecipes.invalidateViews();
////		listRecipes.setAdapter(null);
////		listRecipes.setAdapter(la);
//	}
//
//	@Override
//	public void searchViewOpened() {
//		Toast.makeText(RecipesList.this, "Search View Opened", Toast.LENGTH_SHORT).show();
//	}
//
//	@Override
//	public void searchViewClosed() {
//		Util.showSnackBarMessage(fab, "Search View Closed");
//	}
//
//	@Override
//	public void onItemClicked(String item) {
//		Toast.makeText(RecipesList.this, item + " clicked", Toast.LENGTH_SHORT).show();
//	}
//
//	@Override
//	public void onScroll() {
//
//	}
//
//	@Override
//	public void error(String localizedMessage) {
//
//	}
//
//	@Override
//	public void onCancelSearch() {
//		Util.showSnackBarMessage(fab, "Search View Cleared");
//		searchActive = false;
//		mSearchView.hide();
//	}
//
//	/**
//	 * This class is used to create custom listview
//	 */
//	static class ListAdapter extends BaseAdapter {
//		private LayoutInflater inflater;
//		private Context ctx;
//
//		public ListAdapter(Context context) {
//			inflater = LayoutInflater.from(context);
//			ctx = context;
//		}
//
//		public int getCount() {
//			// TODO Auto-generated method stub
//			return RecipeName.length;
//		}
//
//		public Object getItem(int position) {
//			// TODO Auto-generated method stub
//			return position;
//		}
//
//		public long getItemId(int position) {
//			// TODO Auto-generated method stub
//			return position;
//		}
//
//		public View getView(int position, View convertView, ViewGroup parent) {
//			// TODO Auto-generated method stub
//			ViewHolder holder;
//
//			if (convertView == null) {
//				convertView = inflater.inflate(R.layout.row, null);
//				holder = new ViewHolder();
//				holder.txtRecipeName = (TextView) convertView.findViewById(R.id.txtRecipeName);
//				holder.txtReadyIn = (TextView) convertView.findViewById(R.id.txtReadyIn);
//				holder.imgPreview = (ImageView) convertView.findViewById(R.id.imgPreview);
//
//				convertView.setTag(holder);
//			} else {
//				holder = (ViewHolder) convertView.getTag();
//			}
//
//
//			holder.txtRecipeName.setText(RecipeName[position]);
//			holder.txtReadyIn.setText("Cook time " + CookTime[position]);
//			int imagePreview = ctx.getResources().getIdentifier(Preview[position], "drawable", ctx.getPackageName());
//			holder.imgPreview.setImageResource(imagePreview);
//
//
//			return convertView;
//		}
//
//		static class ViewHolder {
//			TextView txtRecipeName, txtReadyIn;
//			ImageView imgPreview;
//		}
//
//	}
//
//
//	/**
//	 * Called when the activity is first created.
//	 */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.recipes_list);
//		mToolbar = (Toolbar) findViewById(R.id.toolbar);
//		setSupportActionBar(mToolbar);
//		mTitle = mDrawerTitle = getTitle();
//
////		navDrawerView = (LinearLayout) findViewById(R.id.navDrawerView);
//		mPlanetTitles = getResources().getStringArray(R.array.planets_array);
////		mDrawerLayout = (DrawerLayout) findViewById(R.id.lytContent);
//		// mDrawerList = (ListView) findViewById(R.id.left_drawer);
//
////		mDrawerList = (ExpandableListView) findViewById(R.id.nav_left_drawer);
//
//		// set a custom shadow that overlays the main content when the drawer
//		// opens
////		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
//
//		listParent = new ArrayList<SampleTO>();
//		listDataChild = new HashMap<String, List<String>>();
//
//		// Navigation Drawer of Flight starts
//		listParent.add(new SampleTO(getString(R.string.sun), R.drawable.sun));
//		listParent.add(new SampleTO(getString(R.string.moon), R.drawable.moon));
//		listParent.add(new SampleTO(getString(R.string.Planets), R.drawable.solar_system));
//
//		listDataChild.put(getString(R.string.sun), new ArrayList<String>());
//		listDataChild.put(getString(R.string.moon), new ArrayList<String>());
//
//		listDataChild.put(getString(R.string.Planets), Arrays.asList(mPlanetTitles));
//
////		customAdapter = new CustomExpandAdapter(this, listParent, listDataChild);
////		// setting list adapter
////		mDrawerList.setAdapter(customAdapter);
////		mDrawerList.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);
//		// // set up the drawer's list view with items and click listener
//		// mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
//		// mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
//
//		// enable ActionBar app icon to behave as action to toggle nav drawer
////		mToolbar.setDisplayHomeAsUpEnabled(true);
////		mToolbar.setHomeButtonEnabled(true);
//
//		// ActionBarDrawerToggle ties together the the proper interactions
//		// between the sliding drawer and the action bar app icon
////		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
////				mDrawerLayout, /* DrawerLayout object */
////				R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
////				R.string.drawer_open, /* "open drawer" description for accessibility */
////				R.string.drawer_close /* "close drawer" description for accessibility */
////		) {
////			public void onDrawerClosed(View view) {
////				mToolbar.setTitle(mTitle);
////				invalidateOptionsMenu(); // creates call to
////				// onPrepareOptionsMenu()
////			}
////
////			public void onDrawerOpened(View drawerView) {
////				mToolbar.setTitle(mDrawerTitle);
////				invalidateOptionsMenu(); // creates call to
////				// onPrepareOptionsMenu()
////			}
////		};
////		mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//		if (savedInstanceState == null) {
////			selectItem(0);
//		}
//
//		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//		mSearchView = new MaterialSearchView(this);
//		mSearchView.setOnSearchListener(this);
//		mSearchView.setSearchResultsListener(this);
//		mSearchView.setHintText("Search");
//
//		if (mToolbar != null) {
//			// Delay adding SearchView until Toolbar has finished loading
//			mToolbar.post(new Runnable() {
//				@Override
//				public void run() {
//					if (!mSearchViewAdded && mWindowManager != null) {
//						mWindowManager.addView(mSearchView,
//								MaterialSearchView.getSearchViewLayoutParams(RecipesList.this));
//						mSearchViewAdded = true;
//					}
//				}
//			});
//		}
////		fab.setOnClickListener(new View.OnClickListener() {
////			@Override
////			public void onClick(View view) {
////				Util.showSnackBarMessage(view,"Its just here to make screen look pretty :D");
////			}
////		});
//
//		dbhelper = new DBHelper(this);
//		la = new ListAdapter(this);
//
//		imgAbout = (ImageView) findViewById(R.id.imgAbout);
//		imgSearchNav = (ImageView) findViewById(R.id.imgSearchNav);
//		btnSearch = (Button) findViewById(R.id.btnSearch);
//		edtSearch = (EditText) findViewById(R.id.edtSearch);
//		lytSearchForm = (LinearLayout) findViewById(R.id.lytSearchForm);
//		listRecipes = (ListView) findViewById(R.id.listRecipes);
//		prgLoading = (ProgressBar) findViewById(R.id.prgLoading);
//		txtAlert = (TextView) findViewById(R.id.txtAlert);
//
//		/**
//		 * when this app's installed at the first time, code below will
//		 * copy database stored in assets to
//		 * /data/data/com.recipes.app/databases/
//		 */
//		try {
//			dbhelper.createDataBase();
//		} catch (IOException ioe) {
//			throw new Error("Unable to create database");
//		}
//
//		/** then, the database will be open to use */
//		try {
//			dbhelper.openDataBase();
//		} catch (SQLException sqle) {
//			throw sqle;
//		}
//
//		String[] imageword = new String[]{"aaaaaaa", "bbbbbbb", "ccccccc"};
//		String[] imageurl = new String[]{"https://upload.wikimedia.org/wikipedia/commons/f/f7/LeBron_James_2.jpg", "http://static.giantbomb.com/uploads/scale_small/1/17380/1459088-lebronjames550.jpg", "https://upload.wikimedia.org/wikipedia/commons/f/fd/LeBron_James_18112009_1.jpg"};
//
//		viewPager = (ViewPager) findViewById(R.id.slider_posts_viewpager);
//		PagerAdapter adapter = new ViewPagerAdapter(this, imageurl, imageword);
//		viewPager.setAdapter(adapter);
////		enableSliding();
////
//////		new getDataTask().execute();
//////		RestClient.get().getCategory(new Callback<Json>() {
//////			@Override
//////			public void success(Json json, Response response) {
//////				categories = json.getJson();
//////				id = new int[categories.size()];
//////				RecipeName = new String[categories.size()];
//////				Preview = new String[categories.size()];
//////				CookTime = new String[categories.size()];
//////
//////				for (int i = 0; i < categories.size(); i++) {
//////					id[i] = Integer.parseInt(categories.get(i).getCid());
//////					RecipeName[i] = categories.get(i).getCategory_name();
//////					Preview[i] = categories.get(i).getCategory_image();
//////					CookTime[i] = categories.get(i).getStatus();
//////				}
//////				listRecipes.setVisibility(View.VISIBLE);
//////				listRecipes.setAdapter(la);
//////			}
////
////			@Override
////			public void failure(RetrofitError error) {
////				Log.i("App", error.getMessage());
////			}
////		});
////
////
////		listRecipes.setOnItemClickListener(new OnItemClickListener() {
////
////			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
////									long arg3) {
////				// TODO Auto-generated method stub
////
////				/**
////				 * when one of item in the list is clicked, this app will access
////				 * RecipeDetail.class. it also send id value to that class
////				 */
////				Intent i = new Intent(RecipesList.this, RecipesList3.class);
////				i.putExtra("id_for_detail", id[position]);
////				startActivity(i);
////			}
////		});
////
////
////		imgSearchNav.setOnClickListener(new OnClickListener() {
////
////			public void onClick(View v) {
////				// TODO Auto-generated method stub
////
////				/** this code is used to hide and show the search form */
////				if (lytSearchForm.getVisibility() == 8) {
////					lytSearchForm.setVisibility(0);
////					imgSearchNav.setImageResource(R.drawable.nav_down);
////				} else {
////					lytSearchForm.setVisibility(8);
////					imgSearchNav.setImageResource(R.drawable.nav_up);
////				}
////			}
////		});
////
////		btnSearch.setOnClickListener(new OnClickListener() {
////
////			public void onClick(View v) {
////				// TODO Auto-generated method stub
////				RecipeNameKeyword = edtSearch.getText().toString();
////				try {
////					dbhelper.openDataBase();
////				} catch (SQLException sqle) {
////					throw sqle;
////				}
////				new getDataTask().execute();
////			}
////		});
////
////		imgAbout.setOnClickListener(new OnClickListener() {
////
////			public void onClick(View v) {
////				// TODO Auto-generated method stub
////
////				/** when about icon is clicked, it will access AboutApp.class */
////				Intent i = new Intent(RecipesList.this, AboutApp.class);
////				startActivity(i);
////			}
////		});
////	}
//
////	@Override
////	protected void onResume() {
////		super.onResume();
//////		mDrawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
////
////			@Override
////			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
////				int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupPosition));
////				parent.setItemChecked(index, true);
////
////				String parentTitle = ((SampleTO) customAdapter.getGroup(groupPosition)).getTitle();
////
////				if (parentTitle != getString(R.string.Planets)) {
////					mDrawerLayout.closeDrawer(navDrawerView);
////				}
////
////				return false;
////			}
////		});
////
////		mDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
////
////			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
////
////				Log.d("CHECK", "child click");
////
////				int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
////				parent.setItemChecked(index, true);
////
////				selectItem(childPosition);
////
////				return false;
////			}
////		});
//	}
//
//	/* Called whenever we call invalidateOptionsMenu() */
//	@Override
//	public boolean onPrepareOptionsMenu(Menu menu) {
//		// If the nav drawer is open, hide action items related to the content
//		// view
////		boolean drawerOpen = mDrawerLayout.isDrawerOpen(navDrawerView);
////		menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
//		return super.onPrepareOptionsMenu(menu);
//
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// The action bar home/up action should open or close the drawer.
//		// ActionBarDrawerToggle will take care of this.
////		if (mDrawerToggle.onOptionsItemSelected(item)) {
////			return true;
////		}
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//
////		noinspection SimplifiableIfStatement
//		if (id == R.id.action_settings) {
//			Intent i = new Intent(RecipesList.this, MainActivity6.class);
//			startActivity(i);
//			return true;
//		}
//		// Handle action buttons
//		switch (item.getItemId()) {
////			case R.id.action_websearch:
////				// create intent to perform web search for this planet
////				Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
////				intent.putExtra(SearchManager.QUERY, mToolbar.getTitle());
////				// catch event that there's no activity to handle intent
////				if (intent.resolveActivity(getPackageManager()) != null) {
////					startActivity(intent);
////				} else {
////					Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
////				}
////				return true;
//			default:
//				return super.onOptionsItemSelected(item);
//		}
//	}
//
//	//
//	// /* The click listner for ListView in the navigation drawer */
//	// private class DrawerItemClickListener implements ListView.OnItemClickListener {
//	// @Override
//	// public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//	// selectItem(position);
//	// }
//	// }
//
//	private void selectItem(int position) {
//		selectedPosition = position;
////		mDrawerLayout.closeDrawer(navDrawerView);
//
//		// update the main content by replacing fragments
////		Fragment fragment = new PlanetFragment();
////		Bundle args = new Bundle();
////		args.putInt(PlanetFragment.ARG_PLANET_NUMBER, selectedPosition);
////		fragment.setArguments(args);
////
////		FragmentManager fragmentManager = getFragmentManager();
////		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
//
//		// update selected item and title, then close the drawer
//		// mDrawerList.setItemChecked(selectedPosition, true);
//		setTitle(mPlanetTitles[selectedPosition]);
//
//	}
//
//	@Override
//	public void setTitle(CharSequence title) {
//		mTitle = title;
//		mToolbar.setTitle(mTitle);
//	}
//
//	/**
//	 * When using the ActionBarDrawerToggle, you must call it during onPostCreate() and onConfigurationChanged()...
//	 */
//
//	@Override
//	protected void onPostCreate(Bundle savedInstanceState) {
//		super.onPostCreate(savedInstanceState);
//		// Sync the toggle state after onRestoreInstanceState has occurred.
////		mDrawerToggle.syncState();
//	}
//
//	@Override
//	public void onConfigurationChanged(Configuration newConfig) {
//		super.onConfigurationChanged(newConfig);
//		// Pass any configuration change to the drawer toggls
////		mDrawerToggle.onConfigurationChanged(newConfig);
//	}
//
//	/**
//	 * Fragment that appears in the "content_frame", shows a planet
//	 */
//	public static class PlanetFragment extends Fragment {
//		public static final String ARG_PLANET_NUMBER = "planet_number";
//
//		public PlanetFragment() {
//			// Empty constructor required for fragment subclasses
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
//			int i = getArguments().getInt(ARG_PLANET_NUMBER);
//			String planet = getResources().getStringArray(R.array.planets_array)[i];
//
//			int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()), "drawable", getActivity().getPackageName());
//			((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
//			getActivity().setTitle(planet);
//			return rootView;
//		}
//	}
//
//	private void stopSliding() {
//		if (mSliderHandler != null) {
//			mSliderHandler.removeCallbacksAndMessages(null);
//		}
//	}
//
//	/**
//	 * Enables the slider animation
//	 */
//	private void enableSliding() {
//		stopSliding();
//		mSliderHandler = new Handler();
//		mSliderThread = new Runnable() {
//			public void run() {
//				if (viewPager != null) {
//					viewPager.setCurrentItem(mPosition++, true);
////					mIndicator.setCurrentItem(mPosition++);
//				}
//				if (mPosition > 3) {
//					mPosition = 0;
//				}
//				mSliderHandler.postDelayed(this, 3000);
//			}
//		};
//		mSliderThread.run();
//	}
//
//	/**
//	 * this class is used to handle thread
//	 */
//	public class getDataTask extends AsyncTask<Void, Void, Void> {
//
//		getDataTask() {
//			if (!prgLoading.isShown()) {
//				prgLoading.setVisibility(0);
//				txtAlert.setVisibility(8);
//			}
//		}
//
//		@Override
//		protected void onPreExecute() {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		protected Void doInBackground(Void... arg0) {
//			// TODO Auto-generated method stub
//			getDataFromDatabase(RecipeNameKeyword);
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//			// TODO Auto-generated method stub
//			prgLoading.setVisibility(8);
//			if (id.length > 0) {
//				listRecipes.setVisibility(0);
//				listRecipes.setAdapter(la);
//			} else {
//				txtAlert.setVisibility(0);
//			}
//			dbhelper.close();
//		}
//	}
//
//	/**
//	 * this code is used to get data from database and store them
//	 * to array attributes
//	 */
//	public void getDataFromDatabase(String RecipeNameKeyword) {
//		data = dbhelper.getAllData(RecipeNameKeyword);
//
//		id = new int[data.size()];
//		RecipeName = new String[data.size()];
//		Preview = new String[data.size()];
//		CookTime = new String[data.size()];
//
//		for (int i = 0; i < data.size(); i++) {
//			ArrayList<Object> row = data.get(i);
//
//			id[i] = Integer.parseInt(row.get(0).toString());
//			RecipeName[i] = row.get(1).toString();
//			Preview[i] = row.get(2).toString().trim();
//			CookTime[i] = row.get(3).toString();
//
//		}
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.menu_main, menu);
//		searchItem = menu.findItem(R.id.search);
//		searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//			@Override
//			public boolean onMenuItemClick(MenuItem item) {
//				mSearchView.display();
//				openKeyboard();
//				return true;
//			}
//		});
//		if (searchActive)
//			mSearchView.display();
//		return true;
//
//	}
//
//	private void openKeyboard() {
//		new Handler().postDelayed(new Runnable() {
//			public void run() {
//				mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
//				mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
//			}
//		}, 200);
//	}
//}
