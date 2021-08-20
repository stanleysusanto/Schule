package com.recipes.dewordy;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.recipes.dewordy.model.ForgotPassword;
import com.recipes.dewordy.model.login_signup.Login;
import com.recipes.dewordy.model.login_signup.Loginjson;
import com.recipes.dewordy.rest.RestClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

//import com.facebook.CallbackManager;
//import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    GoogleApiClient mGoogleApiClient;

//    LoginButton loginButton;
//    CallbackManager callbackManager;

    TextView Or, forget_password;
    EditText forgotpassword_edit;
    Button sendforgot;

    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.input_user) EditText _emailText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Or = (TextView) findViewById(R.id.or);
        forget_password = (TextView) findViewById(R.id.forget_password);

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.getInstance());
                dialog.setContentView(R.layout.forgot_password);
                dialog.setTitle("            How it works?");

                forgotpassword_edit = (EditText) findViewById(R.id.forget_password_edit);

                final ForgotPassword forgotpassword = new ForgotPassword();

                RestClient.get().getForgotPassword(forgotpassword, new Callback<ForgotPassword>() {
                    @Override
                    public void success(ForgotPassword forgotPassword, Response response) {
                        forgotpassword.setEmail(forgotpassword_edit.getText().toString());
                        sendforgot = (Button) findViewById(R.id.sendforgot);
                        sendforgot.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
                dialog.show();

            }
        });
//        loginButton = (LoginButton) findViewById(R.id.login_button);
//        loginButton.setReadPermissions("email");
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        callbackManager = CallbackManager.Factory.create();
//        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                GraphRequest request = GraphRequest.newMeRequest(
//                        loginResult.getAccessToken(),
//                        new GraphRequest.GraphJSONObjectCallback() {
//                            @Override
//                            public void onCompleted(JSONObject object, GraphResponse response) {
//                                Log.v("LoginActivity", response.toString());
//
//                                // Application code
//                                try {
//                                    LoginState.setUsername(object.getString("email"));
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                LoginState.setUserid(object.getString("userid"));
//                            }
//
//                Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(i);
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//            }
//        });

        // Other app specific specialization
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
        ButterKnife.bind(this);

//        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//        signInButton.setScopes(gso.getScopeArray());

//        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.sign_in_button:
//                        signIn();
//                        break;
//                }
//                }
//        });


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }


    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
//        String regid10 = SharedPreference.getregid(LoginActivity.this);

        final Login login = new Login();
        login.setUsername(email);
        login.setPassword(password);
        login.setRegid(SharedPreference.getregid(LoginActivity.this));


        RestClient.get().getLogin(login, new Callback<Loginjson>() {
            @Override
            public void success(Loginjson loginjson, Response response) {
                String status = loginjson.getStatus();
                progressDialog.dismiss();
                _loginButton.setEnabled(true);
                if(status.equals("sukses")){
                    LoginState.setUsername(email);
                    LoginState.setUserid(loginjson.getUserid());

//                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPref.edit();
//                    editor.putString("username", email);

                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("username", email);
                    editor.putInt("userid", loginjson.getUserid());
                    editor.putString("city", loginjson.getCity());
                    editor.putString("birthplace", loginjson.getBirthplace());
                    editor.putString("senin", loginjson.getSenin());
                    editor.putString("selasa", loginjson.getSelasa());
                    editor.putString("rabu", loginjson.getRabu());
                    editor.putString("kamis", loginjson.getKamis());
                    editor.putString("jumat", loginjson.getJumat());
                    editor.putString("nama_sekolah", loginjson.getNama_sekolah());
                    editor.putString("jenjang_sekolah", loginjson.getJenjang_sekolah());
                    editor.putString("regid", loginjson.getRegid());
                    editor.putString("image", loginjson.getImage());
                    editor.putString("email", loginjson.getEmail());
                    editor.putString("nama_kelas", loginjson.getNama_kelas());
                    editor.putInt("studentid", loginjson.getStudent_id());
                    editor.commit();

                    Intent i = new Intent(LoginActivity.this, MainActivity3.class);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this, "Username and Password don't match", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
            }
        });

//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onLoginSuccess or onLoginFailed
//                        onLoginSuccess();
//                        // onLoginFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
//        }
    }
//    @Override
//    public void onBackPressed() {
//        // Disable going back to the MainActivity
//        moveTaskToBack(true);
//    }

//    private void signIn() {
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }


//    private void handleSignInResult(GoogleSignInResult result) {
//        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
//        if (result.isSuccess()) {
//            // Signed in successfully, show authenticated UI.
//            GoogleSignInAccount acct = result.getSignInAccount();
//            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
//            updateUI(true);
//        } else {
//            // Signed out, show unauthenticated UI.
//            updateUI(false);
//        }
//    }
    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
