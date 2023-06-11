package com.example.server.service;

import com.example.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.ResultBean;
import com.example.server.pojo.Role;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JoeyWong
 * @since 2023-05-29
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录之后返回TOKEN
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    ResultBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户id查询角色列表
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 获取所有管理员
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmins(String keywords);

    /**
     * 更新管理员角色
     * @param adminId
     * @param rids
     * @return
     */
    ResultBean updateAdminRole(Integer adminId, Integer[] rids);

    /**
     * 更新用户密码
     * @param oldPass
     * @param pass
     * @param adminId
     * @return
     */
    ResultBean updateAdminPassword(String oldPass, String pass, Integer adminId);

    /**
     * 更新用户头像
     * @param url
     * @param id
     * @param authentication
     * @return
     */
    ResultBean updateAdminUserFace(String url, Integer id, Authentication authentication);
}
