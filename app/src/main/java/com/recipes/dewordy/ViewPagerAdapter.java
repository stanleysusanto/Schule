package com.recipes.dewordy;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Paulstanley on 2/15/16.
 */
public class ViewPagerAdapter extends PagerAdapter{

    Context context;
    String[] imageurl;
    String[] imageword;

    @Override
    public int getCount() {
        return imageurl.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    public ViewPagerAdapter(Context context, String[]imageurl, String[]imageword){
        this.context = context;
        this.imageurl = imageurl;
        this.imageword = imageword;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){

        ImageView slidingimage;
        TextView imagewordtext;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = inflater.inflate(R.layout.viewpager, container, false);

        slidingimage = (ImageView) itemview.findViewById(R.id.slidingimage);
        imagewordtext = (TextView) itemview.findViewById(R.id.imageword);

        Picasso.with(context)
                .load(imageurl[position])
                .into(slidingimage);

        imagewordtext.setText(imageword[position]);

        ((ViewPager) container).addView(itemview);

        return itemview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
