package com.pdsu.banmeng.controller;

import com.pdsu.banmeng.bo.AuthorBo;
import com.pdsu.banmeng.bo.FansInformationBo;
import com.pdsu.banmeng.bo.PageTemplateBo;
import com.pdsu.banmeng.business.Tourist;
import com.pdsu.banmeng.context.RequestContext;
import com.pdsu.banmeng.dto.SimpleResponse;
import com.pdsu.banmeng.ibo.FansSearchIbo;
import com.pdsu.banmeng.manager.IUserManager;
import com.pdsu.banmeng.manager.impl.UserManager;
import com.pdsu.banmeng.service.IEmailService;
import com.pdsu.banmeng.vo.FansSearchVo;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private IUserManager userManager;

    @GetMapping("/author")
    @ApiOperation("获取用户信息")
    @Tourist
    public SimpleResponse<AuthorBo> author(Integer uid) {
        return new SimpleResponse<>(userManager.getAuthor(uid, RequestContext.currentUser()));
    }

    @PostMapping("/fans")
    @ApiOperation("获取用户的粉丝")
    @Tourist
    public SimpleResponse<PageTemplateBo<FansInformationBo>> getFans(@RequestBody FansSearchVo vo) {
        return new SimpleResponse<>(userManager.getFans(modelMapper.map(vo, FansSearchIbo.class)));
    }

}
