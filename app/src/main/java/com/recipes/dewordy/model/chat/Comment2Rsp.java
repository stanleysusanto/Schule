package com.recipes.dewordy.model.chat;

/**
 * Created by Paulstanley on 3/7/16.
 */
public class Comment2Rsp {

    private String comment;
    private int id;
    private int lines_id;
    private String username;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLines_id() {
        return lines_id;
    }

    public void setLines_id(int lines_id) {
        this.lines_id = lines_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
