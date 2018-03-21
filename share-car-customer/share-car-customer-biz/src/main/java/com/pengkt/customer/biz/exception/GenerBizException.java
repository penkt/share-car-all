package com.pengkt.customer.biz.exception;

public class GenerBizException extends Exception{
    private int code;
    private String msg;
    private boolean language;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isLanguage() {
        return language;
    }

    public void setLanguage(boolean language) {
        this.language = language;
    }

    public GenerBizException() {
        language=false;
    }

    public GenerBizException(String msg) {
        this.msg = msg;
    }

    public GenerBizException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
