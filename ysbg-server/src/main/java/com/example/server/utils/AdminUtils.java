package com.example.server.utils;

import com.example.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Admin工具类
 */
public class AdminUtils {

    /**
     * 获取当前登录的管理员
     * @return
     */
    public static Admin getCurrentAdmin() {
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
