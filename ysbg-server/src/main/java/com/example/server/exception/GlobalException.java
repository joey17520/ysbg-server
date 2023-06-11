package com.example.server.exception;

import com.example.server.pojo.ResultBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    public ResultBean mySQLException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return ResultBean.fail("操作失败：该数据存在关联数据");
        }
        return ResultBean.fail("操作失败：数据库异常");
    }
}
