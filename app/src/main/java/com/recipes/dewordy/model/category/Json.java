package com.recipes.dewordy.model.category;

import com.recipes.dewordy.model.Dets;

import java.util.List;

/**
 * Created by Paulstanley on 12/1/15.
 */
public class Json {

    private List<Category> Json;

    public List<Dets> getDetsJson() {
        return detsJson;
    }

    public void setDetsJson(List<Dets> detsJson) {
        this.detsJson = detsJson;
    }

    private List<Dets> detsJson;

    public List<Category> getJson() {
        return Json;
    }

    public void setJson(List<Category> json) {
        Json = json;
    }
}


