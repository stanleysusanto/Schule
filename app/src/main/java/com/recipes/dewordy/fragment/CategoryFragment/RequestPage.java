package com.recipes.dewordy.fragment.CategoryFragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.recipes.dewordy.MainActivity;
import com.recipes.dewordy.R;
import com.recipes.dewordy.SharedPreference;
import com.recipes.dewordy.model.category.Category;
import com.recipes.dewordy.model.request.Requestjson;
import com.recipes.dewordy.model.request.SendRequest;
import com.recipes.dewordy.rest.RestClient;

import java.util.List;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

//import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by Paulstanley on 3/9/16.
 */
public class RequestPage extends Fragment {

    ListView listRecipes;

    List<Category> categories;
//    ListAdapter la;

    static int[] id;
    static String[] RecipeName;
    static String[] Preview;
    static String[] CookTime;

    private Runnable mSliderThread;
    private Handler mSliderHandler;
    private int mPosition = 0;
    private ViewPager viewPager;

    TextView title, subtitle;
    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_signup)
    Button _signupButton;
//    private CirclePageIndicator mIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.request, container, false);
//        getActivity().getActionBar().hide();

//        String[] imageword = new String[]{""};
//        String[] imageurl = new String[]{Conf.imageurl + "welcome.jpg"};
//
        title = (TextView) view.findViewById(R.id.title);
        subtitle = (TextView) view.findViewById(R.id.sub_title);
        _signupButton = (Button) view.findViewById(R.id.btn_signup);
        _emailText = (EditText) view.findViewById(R.id.input_email);
        _passwordText = (EditText) view.findViewById(R.id.input_password);
//        PagerAdapter adapter = new ViewPagerAdapter(view.getContext(), imageurl, imageword);
//        viewPager.setAdapter(adapter);
//        enableSliding();
//        la = new ListAdapter(view.getContext());

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
//                Intent cd = new Intent(Request.this, MainActivity6.class);
//                startActivity(cd);
            }
        });
        return view;
    }

    public void signup() {
        Log.d("TAG", "Request");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

//        final ProgressDialog progressDialog = new ProgressDialog(RequestPage.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Sending Request .....");
//        progressDialog.show();

        String name = SharedPreference.getusername(MainActivity.getInstance().getApplicationContext());
        final String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        SendRequest request = new SendRequest();
        request.setUsername(SharedPreference.getusername(MainActivity.getInstance().getApplicationContext()));
        request.setEmail(email);
        request.setPassword(password);

        RestClient.get().getRequest(request, new Callback<Requestjson>() {
            @Override
            public void success(Requestjson requestjson, Response response) {
                String status = requestjson.getStatus();
//                progressDialog.dismiss();
                _signupButton.setEnabled(true);
                if (status.equals("sukses")) {
//                    LoginState.setUsername(email);
//                    LoginState.setUserid(requestjson.getUserid());
                    Bundle args = new Bundle();
                    MainActivity.getInstance().changeFragment(FragmentsAvailable.FIRST_FRAGMENT, args);
                } else {
//                    Toast.makeText(Request.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }



//    public void finish() {
//        finish(false);
//    }

    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
//        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(MainActivity.getInstance().getBaseContext(), "Request failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

//        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        return valid;
    }
}