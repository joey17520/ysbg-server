package com.example.server.controller;


import com.example.server.pojo.Admin;
import com.example.server.pojo.ResultBean;
import com.example.server.pojo.Role;
import com.example.server.service.IAdminService;
import com.example.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JoeyWong
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取所有管理员")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keywords) {
        return adminService.getAllAdmins(keywords);
    }

    @ApiOperation(value = "更新管理员信息")
    @PutMapping("/")
    public ResultBean updateAdmin(@RequestBody Admin admin) {
        if (adminService.updateById(admin)) {
            return ResultBean.success("更新成功");
        }
        return ResultBean.fail("更新失败");
    }

    @ApiOperation(value = "删除管理员")
    @DeleteMapping("/{id}")
    public ResultBean deleteAdmin(@PathVariable Integer id) {
        if (adminService.removeById(id)) {
            return ResultBean.success("删除成功");
        }
        return ResultBean.fail("删除失败");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation(value = "更新管理员角色")
    @PutMapping("/role")
    public ResultBean updateAdminRole(Integer adminId, Integer[] rids) {
        return adminService.updateAdminRole(adminId, rids);
    }
}
