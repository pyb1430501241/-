package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pdsu.banmeng.bo.EmailBo;
import com.pdsu.banmeng.entity.Email;
import com.pdsu.banmeng.ibo.EmailSearchIbo;
import com.pdsu.banmeng.mapper.EmailMapper;
import com.pdsu.banmeng.service.IEmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean isExist(EmailSearchIbo searchIbo) {
        return count(new QueryWrapper<Email>().setEntity(Email.builder().email(searchIbo.getEmail()).build())) != 0;
    }

    @Override
    @Cacheable(value = "CodeSharingCommunity_EmailService_getEmail", key = "#emailSearchIbo.uid")
    public EmailBo getEmailByUid(EmailSearchIbo emailSearchIbo) {
        return modelMapper.map(getOne(new QueryWrapper<Email>().setEntity(Email.builder().uid(emailSearchIbo.getUid()).build())), EmailBo.class);
    }

}
