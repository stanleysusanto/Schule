package com.recipes.dewordy.model;

/**
 * Created by Paulstanley on 12/12/15.
 */
public class Dets {

    private String detail_id;//nama table dari db nya
    private String cid; //nama table dari db nya
    private String detail_name; //nama table dari db nya
    private String description; //nama table dari db nya

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDetail_name() {
        return detail_name;
    }

    public void setDetail_name(String detail_name) {
        this.detail_name = detail_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
