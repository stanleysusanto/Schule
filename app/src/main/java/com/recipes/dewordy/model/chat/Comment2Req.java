package com.recipes.dewordy.model.chat;

/**
 * Created by Paulstanley on 3/9/16.
 */
public class Comment2Req {
    String comment;
    int lines_id;
    int id;
    int username;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLines_id() {
        return lines_id;
    }

    public void setLines_id(int lines_id) {
        this.lines_id = lines_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }
}
