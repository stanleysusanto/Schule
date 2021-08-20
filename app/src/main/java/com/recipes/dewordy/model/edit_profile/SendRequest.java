package com.recipes.dewordy.model.edit_profile;

/**
 * Created by Paulstanley on 3/22/16.
 */
public class SendRequest {

    String editusername;
    String editemail;
    String editcity;
    String editbirthplace;
    int userid;

    public String getEditusername() {
        return editusername;
    }

    public void setEditusername(String editusername) {
        this.editusername = editusername;
    }

    public String getEditemail() {
        return editemail;
    }

    public void setEditemail(String editemail) {
        this.editemail = editemail;
    }

    public String getEditcity() {
        return editcity;
    }

    public void setEditcity(String editcity) {
        this.editcity = editcity;
    }

    public String getEditbirthplace() {
        return editbirthplace;
    }

    public void setEditbirthplace(String editbirthplace) {
        this.editbirthplace = editbirthplace;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
