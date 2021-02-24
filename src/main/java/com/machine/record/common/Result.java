package com.machine.record.common;

public class Result<T> {
    String code;
    T data;

    public Result(){}
    public Result(String code,T data){
        this.code = code;
        this.data = data;
    }
    public Result(T data){
        code = "200";
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
