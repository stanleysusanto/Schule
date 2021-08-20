package com.recipes.dewordy.model;

/**
 * Created by Paulstanley on 6/30/16.
 */
public class Absen {
    private String absen_id;
    private String student_id;
    private String nama;
    private String status;
    private String tanggal;
    private String bulan;

    public String getAbsen_id() {
        return absen_id;
    }

    public void setAbsen_id(String absen_id) {
        this.absen_id = absen_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }
}
