package com.recipes.dewordy.fragment.CategoryFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipes.dewordy.MainActivity;
import com.recipes.dewordy.MainActivity4;
import com.recipes.dewordy.R;

/**
 * Created by Paulstanley on 6/19/16.
 */
public class SchoolHomeFragment extends Fragment{

    ImageView image1, image2, image3, image4, image5, image6;
    TextView textViewSenin, textViewSelasa, textViewRabu, textViewKamis, textViewJumat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        image1 = (ImageView) view.findViewById(R.id.image1);
        image2 = (ImageView) view.findViewById(R.id.image2);
        image3 = (ImageView) view.findViewById(R.id.image3);
        image4 = (ImageView) view.findViewById(R.id.image4);
//        image5 = (ImageView) view.findViewById(R.id.image5);
//        image6 = (ImageView) view.findViewById(R.id.image6);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putInt("id", 1);
                args.putInt("id_sub_category", 4);
                MainActivity.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, args);
//                MainActivity3.getInstance().changeFragment(FragmentsAvailable.THIRD_FRAGMENT, args);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.getInstance().changeFragment(FragmentsAvailable.GeneralList);
//                MainActivity3.getInstance().changeFragment(FragmentsAvailable.GeneralList);
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("http://www.smakgadingserpong.bpkpenaburjakarta.or.id/"));
                startActivity(myWebLink);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().changeFragment(FragmentsAvailable.DailyDevotion);
//                MainActivity3.getInstance().changeFragment(FragmentsAvailable.DailyDevotion);
            }
        });

//        image5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.getInstance().changeFragment(FragmentsAvailable.RekapNilai);
//                MainActivity3.getInstance().changeFragment(FragmentsAvailable.RekapNilai);
//            }
//        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.getInstance().changeFragment(FragmentsAvailable.AbsenPage);
//                MainActivity3.getInstance().changeFragment(FragmentsAvailable.AbsenPage);
                Intent i = new Intent(MainActivity.getInstance(), MainActivity4.class);
//                Intent ia = new Intent(MainActivity3.getInstance(), MainActivity4.class);
                startActivity(i);
//                startActivity(ia);
            }
        });

//       image6.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent i = new Intent(MainActivity.getInstance(), MainActivity4.class);
//               Intent ia = new Intent(MainActivity3.getInstance(), MainActivity4.class);
//               startActivity(i);
//               startActivity(ia);
//           }
//       });
        return view;

    }
}
