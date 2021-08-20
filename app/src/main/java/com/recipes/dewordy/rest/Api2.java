package com.recipes.dewordy.rest;

import com.recipes.dewordy.model.CategoryProfile;
import com.recipes.dewordy.model.EditReminder;
import com.recipes.dewordy.model.ForgotPassword;
import com.recipes.dewordy.model.Json5;
import com.recipes.dewordy.model.Json6;
import com.recipes.dewordy.model.JsonRekap;
import com.recipes.dewordy.model.JsonSearch2;
import com.recipes.dewordy.model.JsonSekolah;
import com.recipes.dewordy.model.JsonSekolah2;
import com.recipes.dewordy.model.Kursus;
import com.recipes.dewordy.model.Reskre;
import com.recipes.dewordy.model.Verification;
import com.recipes.dewordy.model.category.Category8;
import com.recipes.dewordy.model.category.CategoryD;
import com.recipes.dewordy.model.category.Json;
import com.recipes.dewordy.model.category.Json2;
import com.recipes.dewordy.model.category.Json3;
import com.recipes.dewordy.model.category.Json4;
import com.recipes.dewordy.model.chat.ChatReq;
import com.recipes.dewordy.model.chat.Comment2Req;
import com.recipes.dewordy.model.chat.Comment2Rsp;
import com.recipes.dewordy.model.chat.CommentReq;
import com.recipes.dewordy.model.edit_profile.EditProfil;
import com.recipes.dewordy.model.like.Like;
import com.recipes.dewordy.model.like.Likejson;
import com.recipes.dewordy.model.login_signup.Login;
import com.recipes.dewordy.model.login_signup.Loginjson;
import com.recipes.dewordy.model.login_signup.Signup;
import com.recipes.dewordy.model.login_signup.Signupjson;
import com.recipes.dewordy.model.request.Requestjson;
import com.recipes.dewordy.model.request.SendRequest;
import com.recipes.dewordy.model.search.Search;
import com.recipes.dewordy.model.search.SearchJson;
import com.recipes.dewordy.model.search.SearchJson2;
import com.recipes.dewordy.model.search.SearchJson3;
import com.recipes.dewordy.model.search.SearchJson4;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Paulstanley on 11/21/15.
 */
public interface Api2 {

    @GET("/service.php")
    void getService(Callback<Kursus> callback);

    @GET("/dewordyapps/profile.php")
    void getProfile(@Query("userid") Integer userid, Callback<CategoryProfile> callback);

    @GET("/dewordyapps/listfirstpage.php")
    void getCategory(Callback<Json> callback);

    @GET("/dewordyapps/listsecondpage.php")
    void getCategory2(@Query("frm_sub_category_id") Integer cid, @Query("namakelas") String namakelas, @Query("namapel") String namapel, Callback<Json2> callback);

    @GET("/dewordyapps/absen.php")
    void getAbsen(@Query("frm_sub_category_id") Integer cid, @Query("tanggal") String tanggal, @Query("bulan") String bulan, Callback<Json2> callback);

    @GET("/dewordyapps/daily_devotion.php")
    void getDailyDevotion(@Query("frm_sub_category_id") Integer cid, Callback<CategoryD> callback);

    @GET("/dewordyapps/reskre.php")
    void getReskre(@Query("userid") Integer userid, Callback<Reskre> callback);

//
//    @GET("/dewordyapps/rekap_nilai.php")
//    void getCategory2(@Query("nama_kelas") String nama_kelas, Callback<Json2> callback);

    @GET("/dewordyapps/listthirdpage.php")
    void getCategory3(@Query("next_id") Integer next_id, @Query("frm_sub_category_id") Integer frm_sub_category, Callback<Json3> callback);

    @GET("/dewordyapps/listforthpage.php")
    void getCategory4(@Query("sub_next_id") Integer sub_next_id, @Query("next_id") Integer next_id, @Query("frm_sub_category_id") Integer frm_sub_category, Callback<Json4> callback);

    @GET("/dewordyapps/nametab.php")
    void getNamaTab(@Query("topic_id") Integer topic_id, @Query("sub_next_id") Integer sub_next_id, @Query("next_id") Integer next_id, @Query("sub_category_id") Integer sub_category_id, Callback<Category8> callback);

    @GET("/detail.php")
    void getDetail(@Query("cid") Integer cid, Callback<Json> callback);

    @POST("/dewordyapps/discussion.php")
    void getDetail2(@Body ChatReq chat, Callback<Json5> callback);

    @POST("/dewordyapps/discussion_comment.php")
    void getDetailComment(@Body CommentReq comment, Callback<Json6> callback);
//    @GET("/detail_comment.php")
//    void getDetailComment(@Query("cid") Integer cid, Callback<Json> callback);
//
//    @POST("/dewordyapps/discussion.php")
//    void getDetail2(@Body ChatReq chat, Callback<Json5> callback);

    @GET("/dewordyapps/searchroom.php")
    void getSearchRoom(@Query("keyword") String keyword, Callback<JsonSearch2> callback);

    @POST("/dewordyapps/post_photo.php")
    void doPostPhoto(@Body ChatReq chat, Callback<Json5> callback);

    @POST("/dewordyapps/post_photoimage.php")
    void doPostPhotoImage(@Body EditProfil editprofil, Callback<EditProfil> callback);

    @POST("/dewordyapps/comment.php")
    void getComment2(@Body Comment2Req comment2Req, Callback<Comment2Rsp> callback);

    @GET("/dewordyapps/discussion2.php")
    void getChat(@Query("id") Integer id, Callback<Json5> callback);

    @GET("/dewordyapps/discussion2_comment.php")
    void getComment(@Query("id") Integer id, Callback<Json6> callback);

    @POST("/dewordyapps/like.php")
    void getLike(@Body Like like, Callback<Likejson> callback);

    @POST("/dewordyapps/login.php")
    void getLogin(@Body Login login, Callback<Loginjson> callback);

//    @GET("/dewordyapps/login.php")
//    void getLogin(@Query ("username") String username, @Query ("password") String password, @Query ("regid") String regid, Callback<Loginjson> callback);


    @POST("/dewordyapps/signup.php")
    void getSignup(@Body Signup signup, Callback<Signupjson> callback);

    @POST("/dewordyapps/request.php")
    void getRequest(@Body SendRequest request, Callback<Requestjson> callback);

    @POST("/dewordyapps/search.php")
    void getSearchSubCategory(@Body Search search, Callback<SearchJson> callback);

    @POST("/dewordyapps/search2.php")
    void getSearchUser(@Body Search search, Callback<SearchJson2> callback);

    @POST("/dewordyapps/search3.php")
    void getSearchSubNext(@Body Search search, Callback<SearchJson3> callback);

    @POST("/dewordyapps/search4.php")
    void getSearchTopic(@Body Search search, Callback<SearchJson4> callback);

    @POST("/dewordyapps/search5.php")
    void getSearchNext(@Body Search search, Callback<SearchJson4> callback);

    @POST("/dewordyapps/editprofile.php")
//    void getEditProfile(@Query("userid") int userid, Callback<EditProfil> callback);
    void getEditProfile(@Body EditProfil editprofil, Callback<EditProfil> callback);

    @POST("/dewordyapps/editreminder.php")
    void getEditReminder(@Body EditReminder editreminder, Callback<EditReminder> callback);

    @GET("/dewordyapps/showreminder.php")
    void getShowReminder(@Query("userid") Integer userid, Callback<EditReminder> callback);

    @GET("/dewordyapps/expandable.php")
    void getExpandable(Callback<Json2> callback);

    @POST("/dewordyapps/forgot_password.php")
    void getForgotPassword(@Body ForgotPassword forgotPassword, Callback<ForgotPassword> callback);

    @GET("/dewordyapps/namasekolah.php")
    void getNamaSekolah(Callback<JsonSekolah> callback);

    @GET("/dewordyapps/namakelas.php")
    void getNamaKelas(Callback<JsonSekolah> callback);

    @GET("/dewordyapps/verification.php")
    void getVerification(@Query("verification") String verification, @Query("id") Integer id, Callback<Verification> callback);

    @GET("/dewordyapps/addsekolah.php")
    void getAddSekolah(@Query("namasekolah") String namasekolah, Callback<JsonSekolah2> callback);
//    @POST("/dewordyapps/addsekolah.php")
//    void getAddSekolah(@Body JsonSekolah2 addsekolah,  Callback<JsonSekolah2> callback);

    @GET("/dewordyapps/rekap_nilai.php")
    void getRekapNilai(@Query("nama_kelas") String nama_kelas, Callback<JsonRekap> callback);

//    @GET("/dewordyapps/absen.php")
//    void getAbsen(@Query("nama_kelas") String absen, Callback<JsonAbsen> callback);

//    @POST("/dewordyapps/listsecondpage.php")
//    void getCategory2(@Body Category2 json2, Callback<Json2> callback);
}
