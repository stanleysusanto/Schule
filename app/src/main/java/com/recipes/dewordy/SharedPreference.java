package com.recipes.dewordy;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Paulstanley on 6/1/16.
 */
public class SharedPreference {

    static final String PREF_USER_NAME= "username";
    static final String PREF_USER_CITY= "city";
    static final String PREF_USER_BIRTHPLACE= "birthplace";
    static final String PREF_USER_SENIN= "senin";
    static final String PREF_USER_SELASA= "selasa";
    static final String PREF_USER_RABU= "rabu";
    static final String PREF_USER_KAMIS= "kamis";
    static final String PREF_USER_JUMAT= "jumat";
    static final String PREF_USER_NAMA_SEKOLAH= "nama_sekolah";
    static final String PREF_USER_JENJANG_SEKOLAH= "jenjang_sekolah";
    static final String PREF_USER_IMAGE= "image";
    static final String PREF_USER_REGID= "regid";
    static final String PREF_USER_EMAIL= "email";
    static final String PREF_USER_USERID= "userid";
    static final String PREF_USER_VERIFICATION= "verification";
    static final String PREF_USER_STUDENTID= "studentid";
    static final String PREF_USER_NAMA_KELAS= "nama_kelas";
    

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setusername(Context ctx, String username)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, username);
        editor.commit();
    }

    public static String getusername(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static void setsenin(Context ctx, String senin)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_SENIN, senin);
        editor.commit();
    }

    public static String getsenin(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_SENIN, "");
    }

    public static void setselasa(Context ctx, String selasa)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_SELASA, selasa);
        editor.commit();
    }

    public static String getselasa(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_SELASA, "");
    }

    public static void setrabu(Context ctx, String rabu)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_RABU, rabu);
        editor.commit();
    }

    public static String getrabu(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_RABU, "");
    }

    public static void setkamis(Context ctx, String kamis)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_KAMIS, kamis);
        editor.commit();
    }

    public static String getkamis(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_KAMIS, "");
    }

    public static void setjumat(Context ctx, String jumat)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_JUMAT, jumat);
        editor.commit();
    }

    public static String getjumat(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_JUMAT, "");
    }

    public static void setnama_sekolah(Context ctx, String nama_sekolah)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAMA_SEKOLAH, nama_sekolah);
        editor.commit();
    }

    public static String getnama_sekolah(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAMA_SEKOLAH, "");
    }

    public static void setjenjang_sekolah(Context ctx, String jenjang_sekolah)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_JENJANG_SEKOLAH, jenjang_sekolah);
        editor.commit();
    }

    public static String getjenjang_sekolah(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_JENJANG_SEKOLAH, "");
    }

    public static void setregid(Context ctx, String regid)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_REGID, regid);
        editor.commit();
    }

    public static String getregid(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_REGID, "");
    }

    public static void setimage(Context ctx, String image)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_IMAGE, image);
        editor.commit();
    }

    public static String getimage(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_IMAGE, "");
    }

    public static void setbirthplace(Context ctx, String birthplace)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_BIRTHPLACE, birthplace);
        editor.commit();
    }

    public static String getbirthplace(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_BIRTHPLACE, "");
    }

    public static void setcity(Context ctx, String city)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_CITY, city);
        editor.commit();
    }

    public static String getcity(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_CITY, "");
    }
    
    public static void setemail(Context ctx, String email)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EMAIL, email);
        editor.commit();
    }

    public static String getemail(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_EMAIL, "");
    }

    public static void setuserid(Context ctx, int userid)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_USER_USERID, userid);
        editor.commit();
    }

    public static int getuserid(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_USER_USERID, 0);
    }

    public static void setstudentid(Context ctx, int studentid)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_USER_STUDENTID, studentid);
        editor.commit();
    }

    public static int getstudentid(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_USER_STUDENTID, 0);
    }

    public static void setverification(Context ctx, String verification)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_VERIFICATION, verification);
        editor.commit();
    }

    public static String getverification(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_VERIFICATION, "");
    }

    public static void setnama_kelas(Context ctx, String nama_kelas)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAMA_KELAS, nama_kelas);
        editor.commit();
    }

    public static String getnama_kelas(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAMA_KELAS, "");
    }


}
