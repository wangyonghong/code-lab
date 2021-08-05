package com.derry.project;

public interface ActionUtils {

    // 广播注册时 与 发送广播时 的 唯一标识，必须要保持一致 (给动态注册用)
    String ACTION_EQUES_UPDATE_IP = "com.derry.receiver_study_";

    // 广播注册时 与 发送广播时 的 唯一标识，必须要保持一致（给静态注册用）
    String ACTION_FLAG = "com.derry.receiver_flag_";
}
