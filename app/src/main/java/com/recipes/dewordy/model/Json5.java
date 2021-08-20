package com.recipes.dewordy.model;

import com.recipes.dewordy.model.chat.ChatRsp;

import java.util.List;

/**
 * Created by Paulstanley on 12/1/15.
 */
public class Json5 {

    public List<ChatRsp> getChat() {
        return chat;
    }

    public void setChat(List<ChatRsp> chat) {
        this.chat = chat;
    }

    private List<ChatRsp> chat;

}


