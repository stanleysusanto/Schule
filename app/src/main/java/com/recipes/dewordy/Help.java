package com.recipes.dewordy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class Help extends Fragment{

	TextView txtAbout, txtAbout2, txtAbout3, txtAbout4, txtAbout5;
	
	/**
	 * the value of this attribute is about application description. 
	 * you can change it with your own. it also accept html tag.
	 */
//	String aboutContent = "Contact us: ";
//	String aboutContent2 = "Email : ";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.help, container, false);

		View rootView = inflater.inflate(R.layout.help, container, false);

		((TextView) rootView.findViewById(R.id.sample6).findViewById(R.id.title)).setText("Hubungi Kami :");
//		((TextView) rootView.findViewById(R.id.sample1).findViewById(R.id.title)).setText("Cara Masuk ke Akun ( login )");
//		((TextView) rootView.findViewById(R.id.sample2).findViewById(R.id.title)).setText("Cara Membuat Akun ( Sign Up )");
		((TextView) rootView.findViewById(R.id.sample3).findViewById(R.id.title)).setText("(Di bagian diskusi )Cara Memesan ( Nama sekolah, nama bahan diskusi, topik diskusi )");
		((TextView) rootView.findViewById(R.id.sample4).findViewById(R.id.title)).setText("Cara Berdiskusi");
		((TextView) rootView.findViewById(R.id.sample5).findViewById(R.id.title)).setText("Cara Kembali ke Halaman Utama dengan lebih Cepat");

		ExpandableTextView expTv6 = (ExpandableTextView) rootView.findViewById(R.id.sample6)
				.findViewById(R.id.expand_text_view);
//		ExpandableTextView expTv1 = (ExpandableTextView) rootView.findViewById(R.id.sample1)
//				.findViewById(R.id.expand_text_view);
//		ExpandableTextView expTv2 = (ExpandableTextView) rootView.findViewById(R.id.sample2)
//				.findViewById(R.id.expand_text_view);
		ExpandableTextView expTv3 = (ExpandableTextView) rootView.findViewById(R.id.sample3)
				.findViewById(R.id.expand_text_view);
		ExpandableTextView expTv4 = (ExpandableTextView) rootView.findViewById(R.id.sample4)
				.findViewById(R.id.expand_text_view);
		ExpandableTextView expTv5 = (ExpandableTextView) rootView.findViewById(R.id.sample5)
				.findViewById(R.id.expand_text_view);

//		expTv3.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
//			@Override
//			public void onExpandStateChanged(TextView textView, boolean isExpanded) {
//				Toast.makeText(getActivity(), isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
//			}
//		});

//		expTv1.setText(getString(R.string.dummy_text1));
//		expTv2.setText(getString(R.string.dummy_text2));
		expTv3.setText(getString(R.string.dummy_text3));
		expTv4.setText(getString(R.string.dummy_text4));
		expTv5.setText(getString(R.string.dummy_text5));
		expTv6.setText(getString(R.string.dummy_text6));

		return rootView;
//		txtAbout = (TextView) view.findViewById(R.id.txtAbout);
//		txtAbout.setText(Html.fromHtml(aboutContent));
//		txtAbout2 = (TextView) view.findViewById(R.id.txtAbout2);
//		txtAbout2.setText(Html.fromHtml(aboutContent2));

	}
}
