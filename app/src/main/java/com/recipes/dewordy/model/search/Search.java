package com.recipes.dewordy.model.search;

/**
 * Created by Paulstanley on 12/1/15.
 */
public class Search {

    public String getFrm_sub_category_id() {
        return frm_sub_category_id;
    }

    public void setFrm_sub_category_id(String frm_sub_category_id) {
        this.frm_sub_category_id = frm_sub_category_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFrm_category_id() {
        return frm_category_id;
    }

    public void setFrm_category_id(String frm_category_id) {
        this.frm_category_id = frm_category_id;
    }

    private String frm_category_id;
    private String frm_sub_category_id;
    private String category_name;
    private String image;
    private String status;
    private String description;
    private String url;

}
