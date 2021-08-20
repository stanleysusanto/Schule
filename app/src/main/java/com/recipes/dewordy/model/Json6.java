package com.recipes.dewordy.model;

import com.recipes.dewordy.model.chat.CommentRsp;

import java.util.List;

/**
 * Created by Paulstanley on 12/1/15.
 */
public class Json6 {

    public List<CommentRsp> getComment() {
        return comment;
    }

    public void setComment(List<CommentRsp> comment) {
        this.comment = comment;
    }

    private List<CommentRsp> comment;

}


