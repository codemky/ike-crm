package com.ike.pojo.vo;

import com.ike.pojo.Message;

public class MessageVo extends Message {

    private String fromWhoName;

    private String toWhoName;

    public MessageVo() {
    }

    public String getFromWhoName() {
        return fromWhoName;
    }

    public void setFromWhoName(String fromWhoName) {
        this.fromWhoName = fromWhoName;
    }

    public String getToWhoName() {
        return toWhoName;
    }

    public void setToWhoName(String toWhoName) {
        this.toWhoName = toWhoName;
    }
}
