package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pdsu.banmeng.entity.Email;
import com.pdsu.banmeng.mapper.EmailMapper;
import com.pdsu.banmeng.service.IEmailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
@Service
public class EmailServiceImpl extends ServiceImpl<EmailMapper, Email> implements IEmailService {

}
