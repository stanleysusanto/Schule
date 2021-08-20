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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.recipes.dewordy.model.JsonSekolah;
import com.recipes.dewordy.model.JsonSekolah2;
import com.recipes.dewordy.model.login_signup.Signup;
import com.recipes.dewordy.model.login_signup.Signupjson;
import com.recipes.dewordy.rest.RestClient;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "SignupActivity";

    @Bind(R.id.input_name) EditText _nameText;
    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.input_confirmpassword) EditText _confirmpasswordText;
    @Bind(R.id.input_student_id) EditText _studentidText;
    @Bind(R.id.btn_signup) Button _signupButton;
    @Bind(R.id.link_login) TextView _loginLink;
    @Bind(R.id.addschool) TextView _addschool;
    private Spinner spinner_school_name, spinner_class;
    private static final String[] paths = {"Kindergarten ( TK )", "Elementary ( SD )", "Secondary ( SMP )", "High School ( SMA )", "College ( Universitas )"};
    static String[] namasekolah2, jenjangsekolah;
    List<JsonSekolah2> categories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        spinner_school_name = (Spinner) findViewById(R.id.spinner_school_name);
        spinner_class = (Spinner) findViewById(R.id.spinner_class);
//        spinner_school_level = (Spinner) findViewById(R.id.spinner_school_level);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(SignupActivity.this,
                android.R.layout.simple_spinner_item,paths);

        RestClient.get().getNamaSekolah(new Callback<JsonSekolah>() {
            @Override
            public void success(JsonSekolah jsonSekolah, Response response) {
                categories = jsonSekolah.getJsonSekolah();
                namasekolah2 = new String[categories.size()];
                jenjangsekolah = new String[categories.size()];

                String[] paths2 = new String[categories.size()];

                for(int i = 0; i < categories.size(); i++){
                    paths2[i] = categories.get(i).getNama_sekolah();
                }

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(SignupActivity.this,
                        android.R.layout.simple_spinner_item, paths2);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_school_name.setAdapter(adapter2);
                spinner_school_name.setOnItemSelectedListener(SignupActivity.this);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        RestClient.get().getNamaKelas(new Callback<JsonSekolah>() {
            @Override
            public void success(JsonSekolah jsonSekolah, Response response) {
                categories = jsonSekolah.getJsonSekolah();
                namasekolah2 = new String[categories.size()];
                jenjangsekolah = new String[categories.size()];

                String[] paths3 = new String[categories.size()];

                for(int i = 0; i < categories.size(); i++){
                    paths3[i] = categories.get(i).getNama_kelas();
                }

                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(SignupActivity.this,
                        android.R.layout.simple_spinner_item, paths3);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_class.setAdapter(adapter3);
                spinner_class.setOnItemSelectedListener(SignupActivity.this);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_school_level.setAdapter(adapter);
//        spinner_school_level.setOnItemSelectedListener(this);

        _addschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SignupActivity.this);
                dialog.setContentView(R.layout.school_box);
                dialog.setTitle("  Enter Your School");
                dialog.show();

                final EditText addschool = (EditText) dialog.findViewById(R.id.editsekolah);
                Button add = (Button) dialog.findViewById(R.id.buttonsekolah);

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String namasekolah = addschool.getText().toString();
//                        final JsonSekolah2 addsekolah = new JsonSekolah2();
                        RestClient.get().getAddSekolah(namasekolah, new Callback<JsonSekolah2>() {
                            @Override
                            public void success(JsonSekolah2 jsonSekolah2, Response response) {
//                                addsekolah.setNama_sekolah(stringnamasekolah);
//                                String status = jsonSekolah2.getStatus();
//                                if(status.equals("aa")){
                                dialog.dismiss();
//                                }
                            }


                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });
                    }
                });
                dialog.show();
            }
        });

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String namaa = spinner_school_name.getSelectedItem().toString();
        String kelas = spinner_class.getSelectedItem().toString();
        String stu = _studentidText.getText().toString();
//        String jenjang = spinner_school_level.getSelectedItem().toString();
//        String city = _cityText.getText().toString();
//        String birthplace = _birthplaceText.getText().toString();

        Signup signup = new Signup();
        signup.setUsername(name);
        signup.setEmail(email);
        signup.setPassword(password);
        signup.setRegid(SharedPreference.getregid(MainActivity.getInstance().getApplicationContext()));
        signup.setNama_sekolah(namaa);
        signup.setNama_kelas(kelas);
        signup.setStudent_id(stu);
//        signup.setJenjang_sekolah(jenjang);
//        signup.setCity(city);
//        signup.setBirthplace(birthplace);

        RestClient.get().getSignup(signup, new Callback<Signupjson>() {
            @Override
            public void success(Signupjson signupjson, Response response) {
                String status = signupjson.getStatus();
                progressDialog.dismiss();
                _signupButton.setEnabled(true);
                if (status.equals("sukses")) {
                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("username", email);
                    editor.putInt("userid", signupjson.getUserid());
                    editor.commit();
                    Intent i = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(SignupActivity.this, "Username has been taken by another account", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String confirm_password = _confirmpasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (_passwordText != _confirmpasswordText) {
            _confirmpasswordText.setError(null);
        } else {
            _confirmpasswordText.setError("confirm password doesn't match with password");
            valid = false;
        }
        return valid;
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}