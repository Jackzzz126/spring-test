package org.jack.rock.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * GlobalExceptionHandle
 *
 * @author zhengzhe17
 * @date 2020/7/14
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<String> exHandle(Exception ex) {
        log.error(ex.toString());
        return Result.error(500, "未知错误:", ex.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = IOException.class)
    public Result<String> ioExHandle(IOException ex) {
        log.error(ex.toString());
        return Result.error(500, "未知错误:", ex.toString());
    }
}
