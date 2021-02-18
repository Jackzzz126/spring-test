package org.jack.rock.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;

/**
 * Result
 *
 * @author zhengzhe17
 * @date 2020/7/10
 */

@Setter
@Getter
@NoArgsConstructor
public class Result<T> {
    public int code = 0;
    public String msg = "success";
    public T data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(T data) {
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result();
    }

    public static <T> Result<T> success(T data) {
        return new Result(data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result(500, msg);
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result(code, msg);
    }

    public static <T> Result<T> error(int code, String msg, T data) {
        Result<T> result = new Result(code, msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> badRequest() {
        return new Result(400, "请求参数错误");
    }

    public static <T> Result<T> reject() {
        return new Result(403, "无权限访问");
    }
}
