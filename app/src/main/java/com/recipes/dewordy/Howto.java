package com.recipes.dewordy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Paulstanley on 3/28/16.
 */
public class Howto extends Fragment{

    TextView point1, point2, point3, point4;

    String pointa1 = "";

    String pointa2 = "";

    String pointa3 = "";

    String pointa4 = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.howto, container, false);

        point1 = (TextView) view.findViewById(R.id.point1);
        point1.setText(Html.fromHtml(pointa1));
        point2 = (TextView) view.findViewById(R.id.point2);
        point2.setText(Html.fromHtml(pointa2));
        point3 = (TextView) view.findViewById(R.id.point3);
        point3.setText(Html.fromHtml(pointa3));
        point4 = (TextView) view.findViewById(R.id.point4);
        point4.setText(Html.fromHtml(pointa4));

        return view;
    }
}
