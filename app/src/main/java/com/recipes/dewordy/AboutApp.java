package com.recipes.dewordy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.shahroz.svlibrary.widgets.MaterialSearchView;

public class AboutApp extends Fragment{

	private Toolbar mToolbar;
	private boolean mSearchViewAdded = false;
	private MaterialSearchView mSearchView;
	private WindowManager mWindowManager;
	private CharSequence mTitle;

	TextView txtAbout, txtAbout2, txtAbout3, txtAbout4, txtAbout5;
	
	/**
	 * the value of this attribute is about application description. 
	 * you can change it with your own. it also accept html tag.
	 */
	String aboutContent = " Dewordy adalah sarana untuk mencari informasi yang sederhana, yang dapat membantu anda, mencari segala sesuatu lebih cepat dan lebih akurat terutama di lingkungan sekolah\n";

	String aboutContent2 =	"karena kami menghubungkan anda dengan orang yang anda butuhkan.";

	String aboutContent3 =	"Jadi, bila anda mau bertanya dan mengetahui sesuatu, anda dapat mencari kategori hal yang anda ingin cari, dan bertanya di dalamnya, dan orang lain akan menjawabnya\n";

	String aboutContent4 =	"SAAT INI, dewordy hanya untuk Murid dan Mahasiswa. ";

	String aboutContent5 =	"Karena DEWORDY adalah tempat kita";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.about_app, container, false);

//		mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
//		setSupportActionBar(mToolbar);
		
		txtAbout = (TextView) view.findViewById(R.id.txtAbout);
		txtAbout.setText(Html.fromHtml(aboutContent));
		txtAbout2 = (TextView) view.findViewById(R.id.txtAbout2);
		txtAbout2.setText(Html.fromHtml(aboutContent2));
		txtAbout3 = (TextView) view.findViewById(R.id.txtAbout3);
		txtAbout3.setText(Html.fromHtml(aboutContent3));
		txtAbout4 = (TextView) view.findViewById(R.id.txtAbout4);
		txtAbout4.setText(Html.fromHtml(aboutContent4));
		txtAbout5 = (TextView) view.findViewById(R.id.txtAbout5);
		txtAbout5.setText(Html.fromHtml(aboutContent5));

		return view;
	}

//	@Override
//	public void setTitle(CharSequence title) {
//		mTitle = title;
//		mToolbar.setTitle(mTitle);
//	}

}
