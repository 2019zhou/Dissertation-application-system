package com.example.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author TangZT
 * 公共响应类，统一前后端的数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer Code;  //状态码
    private String message;  //状态码的说明
    private T data;   //返回的数据

    //Success
    public static<T> Result<T> success(){
        return new Result<>(200, "success", null);
    }

    public static<T> Result<T> success(T data){
        return new Result<>(200, "success", data);
    }

    public static<T> Result<T> success(String messge){
        return new Result<>(200, messge, null);
    }

    public static<T> Result<T> success(T data, String messge){
        return new Result<>(200, messge, data);
    }

    // Fail
    public static<T> Result<T> fail(){
        return new Result<>(201, "fail", null);
    }

    public static<T> Result<T> fail(Integer code){
        return new Result<>(code, "fail", null);
    }

    public static<T> Result<T> fail(String messge){
        return new Result<>(201, messge, null);
    }

    public static<T> Result<T> fail(Integer code, String messge){
        return new Result<>(code, messge, null);
    }
}
