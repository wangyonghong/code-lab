package com.derry.rxjavastudy.login.bean;

// 请求服务器 的 结果Bean  总Bena
public class ResponseResult {

    // 登录成功
    private SuccessBean data; // 成功Bean
    private int code;
    private String message;

    public ResponseResult(SuccessBean data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public ResponseResult() {
    }

    public SuccessBean getData() {
        return data;
    }

    public void setData(SuccessBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
