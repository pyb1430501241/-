package com.pdsu.banmeng.controller;

import com.pdsu.banmeng.manager.impl.UserManager;
import com.pdsu.banmeng.service.IEmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-24 18:07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private UserManager userManager;

}
