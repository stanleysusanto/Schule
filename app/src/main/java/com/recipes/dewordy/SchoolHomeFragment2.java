package com.recipes.dewordy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Paulstanley on 6/19/16.
 */
public class SchoolHomeFragment2 extends AppCompatActivity{

    ImageView image1, image2, image3, image4, image5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);



    }
}
