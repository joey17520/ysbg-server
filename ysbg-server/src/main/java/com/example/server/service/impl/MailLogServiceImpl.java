package com.example.server.service.impl;

import com.example.server.pojo.MailLog;
import com.example.server.mapper.MailLogMapper;
import com.example.server.service.IMailLogService;
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
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
