package com.recipes.dewordy.model;

/**
 * Created by Paulstanley on 5/24/16.
 */
public class JsonSekolah2 {

    private String nama_sekolah;
    private String jenjang_sekolah;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    private String nama_kelas;
    private int kelas_id;

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public int getKelas_id() {
        return kelas_id;
    }

    public void setKelas_id(int kelas_id) {
        this.kelas_id = kelas_id;
    }
}
