////package com.recipes.app;
////
////import java.io.IOException;
////import java.util.ArrayList;
////import java.util.List;
////
////import android.app.Activity;
////import android.content.Context;
////import android.content.Intent;
////import android.content.res.Configuration;
////import android.database.SQLException;
////import android.os.AsyncTask;
////import android.os.Bundle;
////import android.util.Log;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.View.OnClickListener;
////import android.view.ViewGroup;
////import android.widget.AdapterView;
////import android.widget.AdapterView.OnItemClickListener;
////import android.widget.BaseAdapter;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.ImageView;
////import android.widget.LinearLayout;
////import android.widget.ListView;
////import android.widget.ProgressBar;
////import android.widget.TextView;
////
////import com.recipes.app.model.category.Category;
////import com.recipes.app.model.category.Json;
////import com.recipes.app.model.Kursus;
////import com.recipes.app.rest.RestClient;
////
////import retrofit.Callback;
////import retrofit.RetrofitError;
////import retrofit.client.Response;
////
////public class RecipesList extends Activity {
////
////	ImageView imgAbout, imgSearchNav;
////	Button btnSearch;
////	EditText edtSearch;
////	LinearLayout lytSearchForm;
////	ListView listRecipes;
////	ProgressBar prgLoading;
////	TextView txtAlert;
////	TextView lytContent;
////
////	String RecipeNameKeyword = "";
////
////	static DBHelper dbhelper;
////	ArrayList<ArrayList<Object>> data;
////	ListAdapter la;
////
////	static int[] id;
////	static String[] RecipeName;
////	static String[] Preview;
////	static String[] CookTime;
////
////
////	/** This class is used to create custom listview */
////	static class ListAdapter extends BaseAdapter {
////		private LayoutInflater inflater;
////		private Context ctx;
////		ViewHolder holder;
////
////		public ListAdapter(Context context) {
////			inflater = LayoutInflater.from(context);
////			ctx = context;
////		}
////
////		public int getCount() {
////			// TODO Auto-generated method stub
////			return RecipeName.length;
////		}
////
////		public Object getItem(int position) {
////			// TODO Auto-generated method stub
////			return position;
////		}
////
////		public long getItemId(int position) {
////			// TODO Auto-generated method stub
////			return position;
////		}
////
////		public View getView(int position, View convertView, ViewGroup parent) {
////			// TODO Auto-generated method stub
////
////
////			if(convertView == null){
////				convertView = inflater.inflate(R.layout.row, null);
////				holder = new ViewHolder();
////				holder.txtRecipeName = (TextView) convertView.findViewById(R.id.txtRecipeName);
////				holder.txtReadyIn = (TextView) convertView.findViewById(R.id.txtReadyIn);
////				holder.imgPreview = (ImageView) convertView.findViewById(R.id.imgPreview);
////
////				convertView.setTag(holder);
////			}else{
////				holder = (ViewHolder) convertView.getTag();
////			}
////
////
////			holder.txtRecipeName.setText(RecipeName[position]);
////			holder.txtReadyIn.setText("Cook time "+CookTime[position]);
////			int imagePreview = ctx.getResources().getIdentifier(Preview[position], "drawable", ctx.getPackageName());
////			holder.imgPreview.setImageResource(imagePreview);
////
//////			RestClient.get().getService(new Callback<Kursus>() {
//////				@Override
//////				public void success(Kursus result, Response response) {
//////					// success!
//////					Log.i("App", result.getName());
//////					holder.txtRecipeName.setText(result.getName());
////////					lytContent.setText(result);
//////					// you get the point...
//////				}
//////
//////				@Override
//////				public void failure(RetrofitError error) {
//////					Log.i("App", error.getMessage());
//////				}
//////			});
////
////
////			return convertView;
////		}
////
////		static class ViewHolder {
////			TextView txtRecipeName, txtReadyIn;
////			ImageView imgPreview;
////		}
////
////	}
////
////
////    /** Called when the activity is first created. */
////    @Override
////    public void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.recipes_list);
////
////        dbhelper = new DBHelper(this);
////        la = new ListAdapter(this);
////
////        imgAbout = (ImageView) findViewById(R.id.imgAbout);
////        imgSearchNav = (ImageView) findViewById(R.id.imgSearchNav);
////        btnSearch = (Button) findViewById(R.id.btnSearch);
////        edtSearch = (EditText) findViewById(R.id.edtSearch);
////        lytSearchForm = (LinearLayout) findViewById(R.id.lytSearchForm);
////        listRecipes = (ListView) findViewById(R.id.listRecipes);
////        prgLoading = (ProgressBar) findViewById(R.id.prgLoading);
////        txtAlert = (TextView) findViewById(R.id.txtAlert);
////		lytContent = (TextView) findViewById(R.id.textView);
////
////
////
////        /**
////         * when this app's installed at the first time, code below will
////         * copy database stored in assets to
////         * /data/data/com.recipes.app/databases/
////         */
////        try {
////			dbhelper.createDataBase();
////		}catch(IOException ioe){
////			throw new Error("Unable to create database");
////		}
////
////        /** then, the database will be open to use */
////		try{
////			dbhelper.openDataBase();
////		}catch(SQLException sqle){
////			throw sqle;
////		}
////
//////		new getDataTask().execute();
////		RestClient.get().getCategory(new Callback<Json>() {
////			@Override
////			public void success(Json json, Response response) {
////				List<Category> categories = json.getJson();
////				id = new int[categories.size()];
////				RecipeName = new String[categories.size()];
////				Preview = new String[categories.size()];
////				CookTime = new String[categories.size()];
////
////				for(int i=0 ; i<categories.size() ; i++) {
////					id[i] = Integer.parseInt(categories.get(i).getCid());
////					RecipeName[i] = categories.get(i).getCategory_name();
////					Preview[i] = categories.get(i).getCategory_image();
////					CookTime[i] = categories.get(i).getStatus();
////				}
////				listRecipes.setVisibility(View.VISIBLE);
////				listRecipes.setAdapter(la);
////			}
////
////			@Override
////			public void failure(RetrofitError error) {
////				Log.i("App", error.getMessage());
////			}
////		});
////
////		listRecipes.setOnItemClickListener(new OnItemClickListener() {
////
////			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
////					long arg3) {
////				// TODO Auto-generated method stub
////
////				/**
////				 * when one of item in the list is clicked, this app will access
////				 * RecipeDetail.class. it also send id value to that class
////				 */
////				Intent i = new Intent(RecipesList.this, RecipeDetail.class);
////				i.putExtra("id_for_detail", id[position]);
////				startActivity(i);
////			}
////		});
////
////
////        imgSearchNav.setOnClickListener(new OnClickListener() {
////
////			public void onClick(View v) {
////				// TODO Auto-generated method stub
////
////				/** this code is used to hide and show the search form */
////				if(lytSearchForm.getVisibility() == 8){
////					lytSearchForm.setVisibility(0);
////					imgSearchNav.setImageResource(R.drawable.nav_down);
////				}else{
////					lytSearchForm.setVisibility(8);
////					imgSearchNav.setImageResource(R.drawable.nav_up);
////				}
////			}
////		});
////
////        btnSearch.setOnClickListener(new OnClickListener() {
////
////			public void onClick(View v) {
////				// TODO Auto-generated method stub
////				RecipeNameKeyword = edtSearch.getText().toString();
////				try{
////					dbhelper.openDataBase();
////				}catch(SQLException sqle){
////					throw sqle;
////				}
////				new getDataTask().execute();
////			}
////		});
////
////        imgAbout.setOnClickListener(new OnClickListener() {
////
////			public void onClick(View v) {
////				// TODO Auto-generated method stub
////
////				/** when about icon is clicked, it will access AboutApp.class */
////				Intent i = new Intent(RecipesList.this, AboutApp.class);
////				startActivity(i);
////			}
////		});
////    }
////
////    /** this class is used to handle thread */
////    public class getDataTask extends AsyncTask<Void, Void, Void>{
////
////    	getDataTask(){
////    		if(!prgLoading.isShown()){
////    			prgLoading.setVisibility(0);
////				txtAlert.setVisibility(8);
////    		}
////    	}
////
////    	@Override
////		 protected void onPreExecute() {
////		  // TODO Auto-generated method stub
////
////    	}
////
////		@Override
////		protected Void doInBackground(Void... arg0) {
////			// TODO Auto-generated method stub
////			getDataFromDatabase(RecipeNameKeyword);
////			return null;
////		}
////
////		@Override
////		protected void onPostExecute(Void result) {
////			// TODO Auto-generated method stub
////			prgLoading.setVisibility(View.GONE);
////			if(id.length > 0){
////				listRecipes.setVisibility(View.VISIBLE);
////				listRecipes.setAdapter(la);
////			}else{
////				txtAlert.setVisibility(View.VISIBLE);
////			}
////			dbhelper.close();
////		}
////    }
////
////    /**
////     * this code is used to get data from database and store them
////     * to array attributes
////     */
////    public void getDataFromDatabase(String RecipeNameKeyword){
////    	data = dbhelper.getAllData(RecipeNameKeyword);
////
////    	id = new int[data.size()];
////    	RecipeName = new String[data.size()];
////    	Preview = new String[data.size()];
////    	CookTime = new String[data.size()];
////
////    	for(int i=0;i<data.size();i++){
////    		ArrayList<Object> row = data.get(i);
////
////    		id[i] = Integer.parseInt(row.get(0).toString());
////    		RecipeName[i] = row.get(1).toString();
////    		Preview[i] = row.get(2).toString().trim();
////    		CookTime[i] = row.get(3).toString();
////
////    	}
////    }
////
////
////    @Override
////	public void onConfigurationChanged(final Configuration newConfig)
////	{
////	    // Ignore orientation change to keep activity from restarting
////	    super.onConfigurationChanged(newConfig);
////	}
////
////
////}
//
//package com.recipes.app;
//
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
//import com.recipes.app.model.category.Category2;
//import com.recipes.app.model.category.Json;
//import com.recipes.app.model.category.Json2;
//import com.recipes.app.rest.RestClient;
//import com.shahroz.svlibrary.interfaces.onSearchListener;
//import com.shahroz.svlibrary.interfaces.onSimpleSearchActionsListener;
//import com.shahroz.svlibrary.utils.Util;
//import com.shahroz.svlibrary.widgets.MaterialSearchView;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit.Callback;
//import retrofit.RetrofitError;
//import retrofit.client.Response;
//
//public class RecipesList3 extends AppCompatActivity implements onSimpleSearchActionsListener, onSearchListener{
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
//	static int[] id;
//	static int id2;
//	static String[] RecipeName;
//	static String[] Preview;
//	static String[] CookTime;
//
//	@Override
//	public void onSearch(String query) {
//
//	}
//
//	@Override
//	public void searchViewOpened() {
//		Toast.makeText(RecipesList3.this,"Search View Opened",Toast.LENGTH_SHORT).show();
//	}
//
//	@Override
//	public void searchViewClosed() {
//		Util.showSnackBarMessage(fab,"Search View Closed");
//	}
//
//	@Override
//	public void onItemClicked(String item) {
//		Toast.makeText(RecipesList3.this,item + " clicked",Toast.LENGTH_SHORT).show();
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
//		Util.showSnackBarMessage(fab,"Search View Cleared");
//		searchActive = false;
//		mSearchView.hide();
//	}
//
//	/** This class is used to create custom listview */
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
//			if(convertView == null){
//				convertView = inflater.inflate(R.layout.row, null);
//				holder = new ViewHolder();
//				holder.txtRecipeName = (TextView) convertView.findViewById(R.id.txtRecipeName);
//				holder.txtReadyIn = (TextView) convertView.findViewById(R.id.txtReadyIn);
//				holder.imgPreview = (ImageView) convertView.findViewById(R.id.imgPreview);
//
//				convertView.setTag(holder);
//			}else{
//				holder = (ViewHolder) convertView.getTag();
//			}
//
//
//			holder.txtRecipeName.setText(RecipeName[position]);
//			holder.txtReadyIn.setText("Cook time "+CookTime[position]);
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
//	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.recipes_list);
//		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//		setSupportActionBar(toolbar);
//
//		Intent i_get = getIntent();
//		id2 = i_get.getIntExtra("id_for_detail", 0);
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
//								MaterialSearchView.getSearchViewLayoutParams(RecipesList3.this));
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
//		}catch(IOException ioe){
//			throw new Error("Unable to create database");
//		}
//
//		/** then, the database will be open to use */
//		try{
//			dbhelper.openDataBase();
//		}catch(SQLException sqle){
//			throw sqle;
//		}
//
//		String[] imageword =  new String[]{"aaaaaaa", "bbbbbbb", "ccccccc"};
//		String[] imageurl = new String[]{"https://upload.wikimedia.org/wikipedia/commons/f/f7/LeBron_James_2.jpg", "http://static.giantbomb.com/uploads/scale_small/1/17380/1459088-lebronjames550.jpg", "https://upload.wikimedia.org/wikipedia/commons/f/fd/LeBron_James_18112009_1.jpg"};
//
//		ViewPager viewPager = (ViewPager) findViewById(R.id.slider_posts_viewpager);
//		PagerAdapter adapter = new ViewPagerAdapter(this, imageurl, imageword);
//		viewPager.setAdapter(adapter);
//
////		new getDataTask().execute();
//		RestClient.get().getCategory2(id2, new Callback<Json2>() {
//			@Override
//			public void success(Json2 json, Response response) {
//				List<Category2> categories = json.getJson2();
//				id = new int[categories.size()];
//				RecipeName = new String[categories.size()];
//				Preview = new String[categories.size()];
//				CookTime = new String[categories.size()];
//
//				for(int i=0 ; i<categories.size() ; i++) {
//					id[i] = Integer.parseInt(categories.get(i).getNid());
//					RecipeName[i] = categories.get(i).getNews_heading();
//					Preview[i] = categories.get(i).getNews_image();
//					CookTime[i] = categories.get(i).getNews_status();
//				}
//				listRecipes.setVisibility(View.VISIBLE);
//				listRecipes.setAdapter(la);
//			}
//
//			@Override
//			public void failure(RetrofitError error) {
//				Log.i("App", error.getMessage());
//			}
//		});
//
//
//		listRecipes.setOnItemClickListener(new OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
//									long arg3) {
//				// TODO Auto-generated method stub
//
//				/**
//				 * when one of item in the list is clicked, this app will access
//				 * RecipeDetail.class. it also send id value to that class
//				 */
//				Intent i = new Intent(RecipesList3.this, Discussion.class);
//				i.putExtra("id_for_detail", id[position]);
//				startActivity(i);
//			}
//		});
//
//
//		imgSearchNav.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//				/** this code is used to hide and show the search form */
//				if(lytSearchForm.getVisibility() == 8){
//					lytSearchForm.setVisibility(0);
//					imgSearchNav.setImageResource(R.drawable.nav_down);
//				}else{
//					lytSearchForm.setVisibility(8);
//					imgSearchNav.setImageResource(R.drawable.nav_up);
//				}
//			}
//		});
//
//		btnSearch.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				RecipeNameKeyword = edtSearch.getText().toString();
//				try{
//					dbhelper.openDataBase();
//				}catch(SQLException sqle){
//					throw sqle;
//				}
//				new getDataTask().execute();
//			}
//		});
//
//		imgAbout.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//				/** when about icon is clicked, it will access AboutApp.class */
//				Intent i = new Intent(RecipesList3.this, AboutApp.class);
//				startActivity(i);
//			}
//		});
//	}
//
//	/** this class is used to handle thread */
//	public class getDataTask extends AsyncTask<Void, Void, Void>{
//
//		getDataTask(){
//			if(!prgLoading.isShown()){
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
//			if(id.length > 0){
//				listRecipes.setVisibility(0);
//				listRecipes.setAdapter(la);
//			}else{
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
//	public void getDataFromDatabase(String RecipeNameKeyword){
//		data = dbhelper.getAllData(RecipeNameKeyword);
//
//		id = new int[data.size()];
//		RecipeName = new String[data.size()];
//		Preview = new String[data.size()];
//		CookTime = new String[data.size()];
//
//		for(int i=0;i<data.size();i++){
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
//
//	@Override
//	public void onConfigurationChanged(final Configuration newConfig)
//	{
//		// Ignore orientation change to keep activity from restarting
//		super.onConfigurationChanged(newConfig);
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
//		if(searchActive)
//			mSearchView.display();
//		return true;
//
//	}
//
//	private void openKeyboard(){
//		new Handler().postDelayed(new Runnable() {
//			public void run() {
//				mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
//				mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
//			}
//		}, 200);
//	}
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//
//		//noinspection SimplifiableIfStatement
////		if (id == R.id.action_settings) {
////			return true;
////		}
//
//		return super.onOptionsItemSelected(item);
//	}
//}
