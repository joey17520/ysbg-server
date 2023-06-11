package com.example.server.service.impl;

import com.example.server.pojo.Admin;
import com.example.server.pojo.Menu;
import com.example.server.mapper.MenuMapper;
import com.example.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.utils.AdminUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JoeyWong
 * @since 2023-05-29
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据用户Id查询菜单列表
     * 使用缓存时需要注意一个一致性问题，尽量为每个数据都添加有效期限
     * 并且保证每次数据库的修改都要重新清空一次缓存，避免数据不一致的问题。
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        Integer adminId = AdminUtils.getCurrentAdmin().getId();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 首先从Redis中查询数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        // 判断是否存在数据
        if(CollectionUtils.isEmpty(menus)) {
            // 数据不存在，从数据库中查询数据
            menus = menuMapper.getMenusByAdminId(adminId);
            // 将数据插入Redis中
            valueOperations.set("menu_" + adminId, menus);
            // 设置6小时的缓存有效期
            valueOperations.getAndExpire("menu_" + adminId, Duration.ofSeconds(21600));
        }
        return menus;
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
