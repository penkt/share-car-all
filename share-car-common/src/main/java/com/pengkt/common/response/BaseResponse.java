package com.pengkt.common.response;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable{
    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 000000;

    public static final int FAIL = 999999;

    public static final int NO_PERMISSION = 444444;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public BaseResponse() {
        super();
    }

    public BaseResponse(T data) {
        super();
        this.data = data;
    }

    public BaseResponse(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL ;
    }

    public static int getSUCCESS() {
        return SUCCESS;
    }

    public static int getFAIL() {
        return FAIL;
    }

    public static int getNoPermission() {
        return NO_PERMISSION;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
