package com.example.server.service.impl;

import com.example.server.pojo.Role;
import com.example.server.mapper.RoleMapper;
import com.example.server.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JoeyWong
 * @since 2023-05-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
