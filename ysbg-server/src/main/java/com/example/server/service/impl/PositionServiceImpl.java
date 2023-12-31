package com.example.server.service.impl;

import com.example.server.pojo.Position;
import com.example.server.mapper.PositionMapper;
import com.example.server.service.IPositionService;
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
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

}
