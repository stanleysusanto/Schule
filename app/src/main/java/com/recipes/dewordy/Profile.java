package com.recipes.dewordy;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipes.dewordy.helper.Conf;
import com.recipes.dewordy.model.CategoryProfile;
import com.recipes.dewordy.model.edit_profile.EditProfil;
import com.recipes.dewordy.rest.RestClient;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;



public class Profile extends Fragment {

    TextView usernames, showreminder, reminderor, editreminder, email, city, birthplace, namasekolah, jenjangsekolah, textViewSenin, textViewSelasa, textViewRabu, textViewKamis, textViewJumat;
    ImageButton userprofilephoto;
    ImageView editprofile;
    String a, b, c, d, e, f, g, aa, bb, cc, dd, ee;
    private static final int PICK_IMAGE = 100;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private Uri filePath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.profile_design, container, false);

        userprofilephoto = (ImageButton) view.findViewById(R.id.user_profile_photo);
        usernames = (TextView) view.findViewById(R.id.usernames);
        showreminder = (TextView) view.findViewById(R.id.showreminder);
        editreminder = (TextView) view.findViewById(R.id.editreminder);
        email = (TextView) view.findViewById(R.id.email);
        city = (TextView) view.findViewById(R.id.city);
        birthplace = (TextView) view.findViewById(R.id.birthplace);
        namasekolah = (TextView) view.findViewById(R.id.namasekolah);
//        jenjangsekolah = (TextView) view.findViewById(R.id.jenjangsekolah);
        userprofilephoto = (ImageButton) view.findViewById(R.id.user_profile_photo);
        editprofile = (ImageView) view.findViewById(R.id.editprofile);
        final int userid = SharedPreference.getuserid(MainActivity3.getInstance().getApplicationContext());

        RestClient.get().getProfile(userid, new Callback<CategoryProfile>() {
            @Override
            public void success(CategoryProfile categoryprofile, Response response) {
                a = categoryprofile.getUsername();
                b = categoryprofile.getEmail();
                c = categoryprofile.getNama_sekolah();
                d = categoryprofile.getNama_kelas();
                e = categoryprofile.getImage();
                f = categoryprofile.getStudent_id();
                g = categoryprofile.getJenjang_sekolah();

                Picasso.with(MainActivity3.getInstance()).load(Conf.imageurl2 + categoryprofile.getUsername() + ".png").into(userprofilephoto);
                usernames.setText(categoryprofile.getUsername());
                email.setText("Email : " + categoryprofile.getEmail());
                city.setText("School Name : " + categoryprofile.getNama_sekolah());
                birthplace.setText("Class : " + categoryprofile.getNama_kelas());
                namasekolah.setText("Student id : " + categoryprofile.getStudent_id());
//                jenjangsekolah.setText("Jenjang sekolah : " + categoryprofile.getJenjang_sekolah());
//                Picasso.with(MainActivity3.getInstance()).load(Conf.imageurl2 + e).into(userprofilephoto);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


//        showreminder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog = new Dialog(MainActivity3.getInstance());
//                dialog.setContentView(R.layout.show_reminder);
//                dialog.setTitle("        Reminder List");
//
//                textViewSenin = (TextView) dialog.findViewById(R.id.textSenin);
//                textViewSelasa = (TextView) dialog.findViewById(R.id.textSelasa);
//                textViewRabu = (TextView) dialog.findViewById(R.id.textRabu);
//                textViewKamis = (TextView) dialog.findViewById(R.id.textKamis);
//                textViewJumat = (TextView) dialog.findViewById(R.id.textJumat);
//
//                RestClient.get().getShowReminder(SharedPreference.getuserid(MainActivity3.getInstance().getApplicationContext()), new Callback<EditReminder>() {
//                    @Override
//                    public void success(EditReminder editRReminder, Response response) {
////                        categories = editRReminder.get();
//                        textViewSenin.setText(editRReminder.getSenin());
//                        textViewSelasa.setText(editRReminder.getSelasa());
//                        textViewRabu.setText(editRReminder.getRabu());
//                        textViewKamis.setText(editRReminder.getKamis());
//                        textViewJumat.setText(editRReminder.getJumat());
////                        dialog.dismiss();
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//
//                    }
//                });
//                dialog.show();
//            }
//        });
//
//        editreminder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog = new Dialog(MainActivity3.getInstance());
//                dialog.setContentView(R.layout.edit_reminder);
//                dialog.setTitle("        Edit Reminder");
//                final EditText editHari1 = (EditText) dialog.findViewById(R.id.editSenin);
//                final EditText editHari2 = (EditText) dialog.findViewById(R.id.editSelasa);
//                final EditText editHari3 = (EditText) dialog.findViewById(R.id.editRabu);
//                final EditText editHari4 = (EditText) dialog.findViewById(R.id.editKamis);
//                final EditText editHari5 = (EditText) dialog.findViewById(R.id.editJumat);
//                final Button buttonReminder = (Button) dialog.findViewById(R.id.buttonEditReminder);
//                final EditReminder editreminder = new EditReminder();
//                RestClient.get().getShowReminder(SharedPreference.getuserid(MainActivity3.getInstance().getApplicationContext()), new Callback<EditReminder>() {
//                    @Override
//                    public void success(EditReminder editReminder, Response response) {
//                        editHari1.setText(editReminder.getSenin());
//                        editHari2.setText(editReminder.getSelasa());
//                        editHari3.setText(editReminder.getRabu());
//                        editHari4.setText(editReminder.getKamis());
//                        editHari5.setText(editReminder.getJumat());
//
//                        aa = editReminder.getSenin();
//                        bb = editReminder.getSelasa();
//                        cc = editReminder.getRabu();
//                        dd = editReminder.getKamis();
//                        ee = editReminder.getJumat();
//
//                        buttonReminder.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                                editreminder.setSenin(editHari1.getText().toString());
//                                editreminder.setSelasa(editHari2.getText().toString());
//                                editreminder.setRabu(editHari3.getText().toString());
//                                editreminder.setKamis(editHari4.getText().toString());
//                                editreminder.setJumat(editHari5.getText().toString());
//                                editreminder.setUserid(SharedPreference.getuserid(MainActivity3.getInstance().getApplicationContext()));
//                                dialog.dismiss();
//
//                                RestClient.get().getEditReminder(editreminder, new Callback<EditReminder>() {
//                                    @Override
//                                    public void success(EditReminder editReminder, Response response) {
//                                        editreminder.setSenin(editHari1.getText().toString());
//                                        editreminder.setSelasa(editHari2.getText().toString());
//                                        editreminder.setRabu(editHari3.getText().toString());
//                                        editreminder.setKamis(editHari4.getText().toString());
//                                        editreminder.setJumat(editHari5.getText().toString());
//                                        editreminder.setUserid(SharedPreference.getuserid(MainActivity3.getInstance().getApplicationContext()));
//                                        dialog.dismiss();
//                                    }
//
//                                    @Override
//                                    public void failure(RetrofitError error) {
//
//                                    }
//                                });
//
//                            }
//                        });
//
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//
//                    }
//                });
//
//                editHari1.setText(aa);
//                editHari2.setText(bb);
//                editHari3.setText(cc);
//                editHari4.setText(dd);
//                editHari5.setText(ee);
//                dialog.show();
//            }
//        });
//
//
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity3.getInstance());
                dialog.setContentView(R.layout.edit_profile);
                dialog.setTitle("   Edit Profile");

//                final EditText editText = (EditText) dialog.findViewById(R.id.editusername);
//                final EditText editText2 = (EditText) dialog.findViewById(R.id.editemail);
//                final EditText editText3 = (EditText) dialog.findViewById(R.id.editcity);
//                final EditText editText4 = (EditText) dialog.findViewById(R.id.editbirthplace);
//                final EditText editText5 = (EditText) dialog.findViewById(R.id.editnamasekolah);
//                final EditText editText6 = (EditText) dialog.findViewById(R.id.editjenjangsekolah);
                Button button = (Button) dialog.findViewById(R.id.buttonprofile);
                Button buttonImage = (Button) dialog.findViewById(R.id.buttonimage);

                buttonImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        startActivityForResult(gallery, PICK_IMAGE);
                    }
                });

                final EditProfil editProfil = new EditProfil();

//                editText.setText(a);
//                editText2.setText(b);
//                editText3.setText(c);
//                editText4.setText(d);
//                editText5.setText(f);
//                editText6.setText(g);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String imagesss = getStringImage(bitmap);

//                        editProfil.setUsername(SharedPreference.getusername(MainActivity3.getInstance().getApplicationContext()));
//                        editProfil.setEmail(editText2.getText().toString());
//                        editProfil.setCity(editText3.getText().toString());
//                        editProfil.setBirthplace(editText4.getText().toString());
//                        editProfil.setNamasekolah(editText5.getText().toString());
//                        editProfil.setJenjangsekolah(editText6.getText().toString());
//                        editProfil.setUserid(userid);
//                        if (!imagesss.equals("")) {
                            editProfil.setImage(getStringImage(bitmap));
                            editProfil.setUsername(SharedPreference.getusername(MainActivity3.getInstance()));
//                        }else{
//                            editProfil.setImage(null);
//                        }


                        RestClient.get().getEditProfile(editProfil, new Callback<EditProfil>() {
                            @Override
                            public void success(EditProfil editProfil, Response response) {
//                                textView3.setText(editProfil.getUsername());
//                                email.setText(editProfil.getEmail());
//                                city.setText(editProfil.getCity());
//                                birthplace.setText(editProfil.getBirthplace());
//                                namasekolah.setText(editProfil.getNamasekolah());
//                                jenjangsekolah.setText(editProfil.getJenjangsekolah());
                                Picasso.with(MainActivity3.getInstance()).load(Conf.imageurl2 + editProfil.getUsername() + ".png").into(userprofilephoto);

                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }

                        });
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return view;
    }
    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == MainActivity3.getInstance().RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(MainActivity3.getInstance().getContentResolver(), filePath);
                EditProfil editProfil = new EditProfil();
//                editProfil.setUsername(SharedPreference.getusername(MainActivity3.getInstance().getApplicationContext()));
//                editProfil.setUserid(SharedPreference.getuserid(MainActivity3.getInstance().getApplicationContext()));
//                editProfil.setCity(SharedPreference.getcity(MainActivity3.getInstance().getApplicationContext()));
//                editProfil.setBirthplace(SharedPreference.getbirthplace(MainActivity3.getInstance().getApplicationContext()));
                editProfil.setImage(getStringImage(bitmap));
                editProfil.setUsername(SharedPreference.getusername(MainActivity3.getInstance()));

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}