package com.recipes.dewordy.model.chat;

/**
 * Created by Paulstanley on 3/25/16.
 */
public class CommentReq {

    int id;
    int lines_id;
    String comment;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
