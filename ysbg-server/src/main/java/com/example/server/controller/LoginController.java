package com.example.server.controller;


import com.example.server.pojo.Admin;
import com.example.server.pojo.AdminLoginParam;
import com.example.server.pojo.ResultBean;
import com.example.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    IAdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户登录接口
     * @param adminiLoginParma
     * @param request
     * @return
     */
    @ApiOperation(value = "登录之后返回TOKEN")
    @PostMapping("/login")
    public ResultBean login(@RequestBody AdminLoginParam adminiLoginParma, HttpServletRequest request) {
        return adminService.login(adminiLoginParma.getUsername(),
                adminiLoginParma.getPassword(),
                adminiLoginParma.getCode(),
                request);
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (principal == null) {
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

    /**
     * 退出登录接口
     * @return
     */
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public ResultBean logout() {
        return ResultBean.success("注销成功");
    }
}
