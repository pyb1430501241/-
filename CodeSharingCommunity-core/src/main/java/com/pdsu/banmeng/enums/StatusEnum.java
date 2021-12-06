package com.pdsu.banmeng.enums;

import lombok.*;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 16:22
 */
@AllArgsConstructor
@Getter
public enum StatusEnum {

    OK(200, "成功"),
    ERROR(500, "未定义类型错误"),
    NOT_LOGIN(444, "未登录"),
    NO_PERMISSION(301, "权限不足"),
    EMAIL_EXIST(445, "邮箱已被使用"),
    EMAIL_ERROR(446, "发送邮件时发生未知错误, 请稍后重试"),
    EMAIL_CODE_TIME_OUT(447, "验证码已过期"),
    EMAIL_CODE_ERROR(448, "验证码错误"),
    USER_ADD_ERROR(554, "用户信息添加失败"),
    USER_UID_EXIST(555, "用户名已被使用"),
    USER_NAME_EXIST(556, "昵称已被使用"),
    USER_IMAGE_ADD_ERROR(557, "添加头像信息失败"),
    USER_ROLE_ADD_ERROR(558, "分配用户权限失败"),
    USER_EMAIL_ADD_ERROR(559, "添加用户邮箱失败"),
    BLOB_ADD_ERROR(600, "发布博客失败"),
    BLOB_LABEL_ADD_ERROR(601, "更新博客标签失败"),
    UNKNOWN_REVERSAL_TYPE(700, "未知的反转类型"),
    REVERSAL_ERROR(801, "请稍后重试"),
    FILE_INIT_ERROR(901, "文件初始化错误"),
    FILE_UPLOAD_ERROR(902, "文件上传失败"),
    FILE_DOWNLOAD_ERROR(903, "文件下载失败"),
    FILE_SIZE_TOO_LARGE(904, "文件超出限制, 请自行压缩后上传"),
    FILE_ALREADY_EXIST(905, "不可上传同名文件, 请更换名称"),
    NOT_FOUND(404, "未找到");

    private Integer code;

    private String msg;


}
