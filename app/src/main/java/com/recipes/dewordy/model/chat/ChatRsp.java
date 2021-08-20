package com.recipes.dewordy.model.chat;

/**
 * Created by Paulstanley on 3/7/16.
 */
public class ChatRsp {

    public int getLine_id() {
        return line_id;
    }

    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getLiness() {
        return liness;
    }

    public void setLiness(String liness) {
        this.liness = liness;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private int line_id;
    private String topic_id;
    private String liness;
    private String images;
    private String username;
    private int like;
    private String created_post;
    private String nama_sekolah;
    private String jenjangsekolah;

    public String getNamasekolah() {
        return nama_sekolah;
    }

    public void setNamasekolah(String nama_sekolah) {
        this.nama_sekolah = nama_sekolah;
    }

    public String getJenjangsekolah() {
        return jenjangsekolah;
    }

    public void setJenjangsekolah(String jenjangsekolah) {
        this.jenjangsekolah = jenjangsekolah;
    }

    public String getcreated_post() {
        return created_post;
    }

    public void setcreated_post(String created_post) {
        this.created_post = created_post;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
