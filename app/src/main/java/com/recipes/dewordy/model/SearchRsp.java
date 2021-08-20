package com.recipes.dewordy.model;

/**
 * Created by Paulstanley on 3/25/16.
 */
public class SearchRsp {

    private int frm_sub_category_id;
    private int sub_category_id;
    private int next_id;
    private int sub_next_id;
    private int topic_id;
    private String title;
    private String category_name;
    private String image;
    private String status;
    private String search;
    private String keyword;
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearch100() {
        return search;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getFrm_sub_category_id() {
        return frm_sub_category_id;
    }

    public void setFrm_sub_category_id(int frm_sub_category_id) {
        this.frm_sub_category_id = frm_sub_category_id;
    }

    public int getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(int sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public int getNext_id() {
        return next_id;
    }

    public void setNext_id(int next_id) {
        this.next_id = next_id;
    }

    public int getSub_next_id() {
        return sub_next_id;
    }

    public void setSub_next_id(int sub_next_id) {
        this.sub_next_id = sub_next_id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
