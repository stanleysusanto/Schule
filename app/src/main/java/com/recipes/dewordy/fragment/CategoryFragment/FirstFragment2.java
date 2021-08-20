package com.recipes.dewordy.fragment.CategoryFragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.recipes.dewordy.MainActivity;
import com.recipes.dewordy.MyInterface;
import com.recipes.dewordy.R;
import com.recipes.dewordy.model.Verification;
import com.recipes.dewordy.rest.RestClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

//import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by Paulstanley on 3/9/16.
 */
public class FirstFragment2 extends Fragment {

    ImageView image1;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private MyInterface myInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_front, container, false);
        image1 = (ImageView) view.findViewById(R.id.image1);
//        mDrawerToggle.setDrawerIndicatorEnabled(false);
//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.LEFT);
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
//        myInterface.lockDrawer();
//        TextView dis1 = (TextView) view.findViewById(R.id.dis1);
//        dis1.setText("")

//        dis1.setClickable(false);
//        dis1.setEnabled(false);
//        dis1.setVisibility(view.getVisibility());

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.getInstance());
                dialog.setContentView(R.layout.verification_box);
                dialog.setTitle("     Verification");

                final EditText editText1 = (EditText) dialog.findViewById(R.id.verif);
                Button button = (Button) dialog.findViewById(R.id.buttonverification);
                final String verification = editText1.getText().toString();

                dialog.show();
//                Bundle args = new Bundle();
//                args.putInt("id", 1);
//                MainActivity6.getInstance().changeFragment(FragmentsAvailable.SECOND_FRAGMENT, args);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final EditText editText1 = (EditText) dialog.findViewById(R.id.verif);
                        Button button = (Button) dialog.findViewById(R.id.buttonverification);
                        final String verification = editText1.getText().toString();

                        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.getInstance().getApplicationContext());
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("verification", verification);
                        editor.commit();

                        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.getInstance(),
                                R.style.AppTheme_Dark_Dialog);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage("Authenticating...");
                        progressDialog.show();

                        RestClient.get().getVerification(verification, 1, new Callback<Verification>() {
                            @Override
                            public void success(Verification verification, Response response) {
                                String hasil = verification.getHasil();
                                if (hasil.equals("sukses")) {
                                    Bundle args = new Bundle();
                                    args.putInt("id", 1);
                                    MainActivity.getInstance().changeFragment(FragmentsAvailable.SchoolHomeFragment3, args);

                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                        dialog.dismiss();
                                    }

                                } else {
                                    final Dialog dialog = new Dialog(MainActivity.getInstance());
                                    dialog.setContentView(R.layout.wrongverif);
                                    dialog.show();

                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
//                                        dialog.dismiss();
                                    }

                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });
                    }
                });
            }
        });

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            myInterface = (MyInterface) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + " must implement MyInterface");
//        }
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        myInterface.unlockDrawer();
//    }
//
//
//    public void enableDisableDrawer(int mode) {
//        if (mDrawerLayout != null) {
//            mDrawerLayout.setDrawerLockMode(mode);
//        }
//    }

}
