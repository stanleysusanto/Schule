package com.recipes.dewordy.rest;

import com.recipes.dewordy.model.CategoryProfile;
import com.recipes.dewordy.model.EditReminder;
import com.recipes.dewordy.model.ForgotPassword;
import com.recipes.dewordy.model.Json5;
import com.recipes.dewordy.model.Json6;
import com.recipes.dewordy.model.JsonSearch2;
import com.recipes.dewordy.model.JsonSekolah;
import com.recipes.dewordy.model.JsonSekolah2;
import com.recipes.dewordy.model.Kursus;
import com.recipes.dewordy.model.RekapNilai2;
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
public interface Api {

    @GET("/service.php")
    void getService(Callback<Kursus> callback);

    @GET("/appsfilecollection/devo.php")
    void getDailyDevotion(@Query("frm_sub_category_id") Integer cid, Callback<CategoryD> callback);

    @GET("/appsfilecollection/listsecondpage.php")
    void getCategory2(@Query("frm_sub_category_id") Integer cid, @Query("namakelas") String namakelas, @Query("namapel") String namapel, Callback<Json2> callback);


    @GET("/appsfilecollection/absen.php")
    void getAbsen(@Query("frm_sub_category_id") Integer cid, @Query("tanggal") String tanggal, @Query("bulan") String bulan, Callback<Json2> callback);

    @GET("/appsfilecollection/profile.php")
    void getProfile(@Query("userid") Integer userid, Callback<CategoryProfile> callback);

    @GET("/appsfilecollection/listfirstpage.php")
    void getCategory(Callback<Json> callback);

//    @GET("/appsfilecollection/listsecondpage.php")
//    void getCategory2(@Query("frm_sub_category_id") Integer cid, Callback<Json2> callback);

    @GET("/appsfilecollection/listthirdpage.php")
    void getCategory3(@Query("next_id") Integer next_id, @Query("frm_sub_category_id") Integer frm_sub_category, Callback<Json3> callback);

//    @GET("/dewordyapps/listthirdpage.php")
//    void getCategory3(@Query("next_id") Integer next_id, @Query("frm_sub_category_id") Integer frm_sub_category, Callback<Json3> callback);


    @GET("/appsfilecollection/listforthpage.php")
    void getCategory4(@Query("sub_next_id") Integer sub_next_id, @Query("next_id") Integer next_id, @Query("frm_sub_category_id") Integer frm_sub_category, Callback<Json4> callback);

    @GET("/appsfilecollection/nametab.php")
    void getNamaTab(@Query("topic_id") Integer topic_id, @Query("sub_next_id") Integer sub_next_id, @Query("next_id") Integer next_id, @Query("sub_category_id") Integer sub_category_id, Callback<Category8> callback);

    @GET("/detail.php")
    void getDetail(@Query("cid") Integer cid, Callback<Json> callback);

    @POST("/appsfilecollection/discussion.php")
    void getDetail2(@Body ChatReq chat, Callback<Json5> callback);

    @POST("/appsfilecollection/discussion_comment.php")
    void getDetailComment(@Body CommentReq comment, Callback<Json6> callback);
//    @GET("/detail_comment.php")
//    void getDetailComment(@Query("cid") Integer cid, Callback<Json> callback);
//
//    @POST("/dewordyapps/discussion.php")
//    void getDetail2(@Body ChatReq chat, Callback<Json5> callback);

    @GET("/appsfilecollection/searchroom.php")
    void getSearchRoom(@Query("keyword") String keyword, Callback<JsonSearch2> callback);

    @POST("/appsfilecollection/puimg.php")
    void doPostPhoto(@Body ChatReq chat, Callback<Json5> callback);

    @POST("/appsfilecollection/post_photoimage.php")
    void doPostPhotoImage(@Body EditProfil editprofil, Callback<EditProfil> callback);

    @POST("/appsfilecollection/comment.php")
    void getComment2(@Body Comment2Req comment2Req, Callback<Comment2Rsp> callback);

    @GET("/appsfilecollection/discussion2.php")
    void getChat(@Query("id") Integer id, Callback<Json5> callback);

    @GET("/appsfilecollection/discussion2_comment.php")
    void getComment(@Query("id") Integer id, Callback<Json6> callback);

    @POST("/appsfilecollection/like.php")
    void getLike(@Body Like like, Callback<Likejson> callback);

    @POST("/appsfilecollection/login.php")
    void getLogin(@Body Login login, Callback<Loginjson> callback);

    @POST("/appsfilecollection/signup.php")
    void getSignup(@Body Signup signup, Callback<Signupjson> callback);

    @POST("/appsfilecollection/request.php")
    void getRequest(@Body SendRequest request, Callback<Requestjson> callback);

    @POST("/appsfilecollection/search.php")
    void getSearchSubCategory(@Body Search search, Callback<SearchJson> callback);

    @POST("/appsfilecollection/search2.php")
    void getSearchUser(@Body Search search, Callback<SearchJson2> callback);

    @POST("/appsfilecollection/search3.php")
    void getSearchSubNext(@Body Search search, Callback<SearchJson3> callback);

    @POST("/appsfilecollection/search4.php")
    void getSearchTopic(@Body Search search, Callback<SearchJson4> callback);

    @POST("/appsfilecollection/search5.php")
    void getSearchNext(@Body Search search, Callback<SearchJson4> callback);

    @POST("/appsfilecollection/puso.php")
//    void getEditProfile(@Query("userid") int userid, Callback<EditProfil> callback);
    void getEditProfile(@Body EditProfil editprofil, Callback<EditProfil> callback);

    @POST("/appsfilecollection/editreminder.php")
    void getEditReminder(@Body EditReminder editreminder, Callback<EditReminder> callback);

    @GET("/appsfilecollection/showreminder.php")
    void getShowReminder(@Query("userid") Integer userid, Callback<EditReminder> callback);

    @GET("/appsfilecollection/expandable.php")
    void getExpandable(Callback<Json2> callback);

    @POST("/appsfilecollection/forgot_password.php")
    void getForgotPassword(@Body ForgotPassword forgotPassword, Callback<ForgotPassword> callback);

    @GET("/appsfilecollection/namasekolah.php")
    void getNamaSekolah(Callback<JsonSekolah> callback);

    @GET("/appsfilecollection/namakelas.php")
    void getNamaKelas(Callback<JsonSekolah> callback);

    @GET("/appsfilecollection/verification.php")
    void getVerification(@Query("verification") String verification, @Query("id") Integer id, Callback<Verification> callback);

    @GET("/appsfilecollection/addsekolah.php")
    void getAddSekolah(@Query("namasekolah") String namasekolah, Callback<JsonSekolah2> callback);

    @POST("/appsfilecollection/rekap_nilai.php")
    void getRekapNilai(@Body RekapNilai2 rekapNilai2, Callback<RekapNilai2> callback);

    @GET("/appsfilecollection/reskre.php")
    void getReskre(@Query("userid") Integer userid, Callback<Reskre> callback);

}
