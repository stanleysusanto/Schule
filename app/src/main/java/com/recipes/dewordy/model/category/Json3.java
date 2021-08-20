package com.recipes.dewordy.model.category;

import com.recipes.dewordy.model.Dets;

import java.util.List;

/**
 * Created by Paulstanley on 12/1/15.
 */
public class Json3 {

    private List<Category3> Json;

    public List<Dets> getDetsJson() {
        return detsJson;
    }

    public void setDetsJson(List<Dets> detsJson) {
        this.detsJson = detsJson;
    }

    private List<Dets> detsJson;

    public List<Category3> getJson3() {
        return Json;
    }

    public void setJson(List<Category3> json) {
        Json = json;
    }
}


