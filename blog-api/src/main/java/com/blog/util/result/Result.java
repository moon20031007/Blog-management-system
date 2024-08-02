package com.blog.util.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: Result
 * Package: com.blog.util.result
 * Description:全局统一返回结果类
 *
 * @Author: Yixiao
 * @Create: 2024.7.30 - 14:24
 * @Version: v1.0
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -4762928619495260423L;
    // 返回码
    private Integer code;
    // 返回消息
    private String message;
    // 返回数据
    private T data;

    public Result(){}

    public static<T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static<T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static<T> Result<T> error(ResultCode resultCode) {
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        return result;
    }

    public static<T> Result<T> error(ResultCode resultCode, T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}