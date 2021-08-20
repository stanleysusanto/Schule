package com.recipes.dewordy.model.search;

/**
 * Created by Paulstanley on 12/1/15.
 */
public class Search5 {

    private String next_id;//nama table dari db nya
    private String frm_category_id; //nama table dari db nya
    private String frm_sub_category_id; //nama table dari db nya
    private String title; //nama table dari db nya
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

    public String getNext_id() {
        return next_id;
    }

    public void setNext_id(String next_id) {
        this.next_id = next_id;
    }

    public String getFrm_category_id() {
        return frm_category_id;
    }

    public void setFrm_category_id(String frm_category_id) {
        this.frm_category_id = frm_category_id;
    }

    public String getFrm_sub_category_id() {
        return frm_sub_category_id;
    }

    public void setFrm_sub_category_id(String frm_sub_category_id) {
        this.frm_sub_category_id = frm_sub_category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
