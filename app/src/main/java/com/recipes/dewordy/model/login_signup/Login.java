package com.recipes.dewordy.model.login_signup;

/**
 * Created by Paulstanley on 3/9/16.
 */
public class Login {


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String username;
    String password;
    String regid;

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }
}
