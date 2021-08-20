package com.recipes.dewordy.model.edit_profile;

/**
 * Created by Paulstanley on 3/22/16.
 */
public class EditProfil {

    private String username;
    private String email;
    private String city;
    private String birthplace;
    private int userid;
    private String image;
    private String namasekolah;
    private String jenjangsekolah;

    public String getNamasekolah() {
        return namasekolah;
    }

    public void setNamasekolah(String namasekolah) {
        this.namasekolah = namasekolah;
    }

    public String getJenjangsekolah() {
        return jenjangsekolah;
    }

    public void setJenjangsekolah(String jenjangsekolah) {
        this.jenjangsekolah = jenjangsekolah;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }
}
