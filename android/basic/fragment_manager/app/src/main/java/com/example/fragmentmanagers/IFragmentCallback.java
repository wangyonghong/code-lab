package com.example.fragmentmanagers;

public interface IFragmentCallback {
    void sendMsgToActivity(String msg);
    String getMsgFromActivity(String msg);
}
