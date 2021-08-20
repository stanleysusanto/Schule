package com.recipes.dewordy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.recipes.dewordy.model.request.Requestjson;
import com.recipes.dewordy.model.request.SendRequest;
import com.recipes.dewordy.rest.RestClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

//import android.widget.Toast;

public class Request extends AppCompatActivity {
    private static final String TAG = "RequestActivity";

//    @Bind(R.id.link_login) TextView _loginLink;
//    @Bind(R.id.input_name) EditText _nameText;
@Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_signup) Button _signupButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
//                Intent cd = new Intent(Request.this, MainActivity.class);
//                startActivity(cd);
            }
        });

//        _loginLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Finish the registration screen and return to the Login activity
//                finish();
//            }
//        });
    }

    public void signup() {
        Log.d(TAG, "Request");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Request.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Sending Request .....");
        progressDialog.show();

        String name = LoginState.getUsername();
        final String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        SendRequest request = new SendRequest();
        request.setUsername(LoginState.getUsername());
        request.setEmail(email);
        request.setPassword(password);

        RestClient.get().getRequest(request, new Callback<Requestjson>() {
            @Override
            public void success(Requestjson requestjson, Response response) {
                String status = requestjson.getStatus();
                progressDialog.dismiss();
                _signupButton.setEnabled(true);
                if(status.equals("sukses")){
//                    LoginState.setUsername(email);
//                    LoginState.setUserid(requestjson.getUserid());
                    Intent i = new Intent(Request.this, MainActivity.class);
                    startActivity(i);
                }else{
//                    Toast.makeText(Request.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });




//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onSignupSuccess or onSignupFailed
//                        // depending on success
//                        onSignupSuccess();
//                        // onSignupFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Reques failed", Toast.LENGTH_LONG).show();

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