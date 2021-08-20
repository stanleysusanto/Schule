package com.recipes.dewordy.model;

import java.util.List;

/**
 * Created by Paulstanley on 12/1/15.
 */
public class JsonSekolah {

    private List<JsonSekolah2> Json;

//    public List<Dets> getDetsJson() {
//        return detsJson;
//    }
//
//    public void setDetsJson(List<Dets> detsJson) {
//        this.detsJson = detsJson;
//    }
//
//    private List<Dets> detsJson;

    public List<JsonSekolah2> getJsonSekolah() {
        return Json;
    }

    public void setJsonSekolah(List<JsonSekolah2> json) {
        Json = json;
    }
}


