package com.recipes.dewordy.model;

import java.util.List;

/**
 * Created by Paulstanley on 3/25/16.
 */
public class JsonSearch {

    public List<SearchRsp> getSearchRoom() {
        return searchroom;
    }

    public void setComment(List<SearchRsp> search) {
        this.searchroom = searchroom;
    }

    private List<SearchRsp> searchroom;

    private int frm_sub_category_id;
    private int sub_category_id;
    private int next_id;
    private int sub_next_id;
    private int topic_id;

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

    public void setSearch(List<SearchRsp> search) {
        this.searchroom = searchroom;
    }

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
