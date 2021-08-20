package com.recipes.dewordy.model;

/**
 * Created by Paulstanley on 12/1/15.
 */
public class CategoryRekap {

    private String student_id;//nama table dari db nya
    private String frm_category_id; //nama table dari db nya
    private int frm_sub_category_id; //nama table dari db nya
    private String nama; //nama table dari db nya
    private String description;
    private String image;
    private String alamat;
    private String city;
    private String website;
    private String nomor_telepon;
    private String url;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getstudent_id() {
        return student_id;
    }

    public void setstudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getFrm_category_id() {
        return frm_category_id;
    }

    public void setFrm_category_id(String frm_category_id) {
        this.frm_category_id = frm_category_id;
    }

    public int getFrm_sub_category_id() {
        return frm_sub_category_id;
    }

    public void setFrm_sub_category_id(int frm_sub_category_id) {
        this.frm_sub_category_id = frm_sub_category_id;
    }

    public String getnama() {
        return nama;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNomor_telepon() {
        return nomor_telepon;
    }

    public void setNomor_telepon(String nomor_telepon) {
        this.nomor_telepon = nomor_telepon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
