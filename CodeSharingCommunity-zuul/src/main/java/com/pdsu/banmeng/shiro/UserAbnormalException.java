package com.pdsu.banmeng.shiro;

import org.apache.shiro.authc.CredentialsException;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-20 20:29
 */
public class UserAbnormalException extends CredentialsException {

    public UserAbnormalException(String message) {
        super(message);
    }

}
