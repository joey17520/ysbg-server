package com.example.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一全局返回结果信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultBean {

    private long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static ResultBean success(String message) {
        return new ResultBean(200, message, null);
    }

    /**
     * 成功返回结果
     * @param message
     * @param obj
     * @return
     */
    public static ResultBean success(String message, Object obj) {
        return new ResultBean(200, message, obj);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static ResultBean fail(String message) {
        return new ResultBean(500, message, null);
    }

    /**
     * 失败返回结果
     * @param message
     * @param obj
     * @return
     */
    public static ResultBean fail(String message, Object obj) {
        return new ResultBean(500, message, obj);
    }
}
