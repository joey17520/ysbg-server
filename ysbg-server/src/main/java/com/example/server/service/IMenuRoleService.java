package com.example.server.service;

import com.example.server.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.ResultBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JoeyWong
 * @since 2023-05-29
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    ResultBean updateMenuRole(Integer rid, Integer[] mids);
}
