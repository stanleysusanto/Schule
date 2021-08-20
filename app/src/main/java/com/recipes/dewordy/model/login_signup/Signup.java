package com.recipes.dewordy.model.login_signup;

/**
 * Created by Paulstanley on 3/9/16.
 */
public class Signup {


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String username;
    String password;
    String regid;
    String nama_sekolah;
    String jenjang_sekolah;
    String city;
    String birthplace;
    String nama_kelas;
    String student_id;

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
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

    public String getNama_sekolah() {
        return nama_sekolah;
    }

    public void setNama_sekolah(String nama_sekolah) {
        this.nama_sekolah = nama_sekolah;
    }

    public String getJenjang_sekolah() {
        return jenjang_sekolah;
    }

    public void setJenjang_sekolah(String jenjang_sekolah) {
        this.jenjang_sekolah = jenjang_sekolah;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;
}
