package com.pdsu.banmeng.service.impl;

import com.pdsu.banmeng.entity.Comment;
import com.pdsu.banmeng.mapper.CommentMapper;
import com.pdsu.banmeng.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
