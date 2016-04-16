/*
 * 创建于 2004-11-11 16:13:26
 * JCMS
 */
package com.pabula.fw.exception;

import javax.servlet.ServletException;

/**
 * 登录LOGIN异常
 */
public class LoginException extends ServletException {


    public LoginException(Throwable cause)
    {
        super(cause);
    }

    public LoginException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public LoginException(String ex)
    {
        super(ex);
    }

    public LoginException()
    {
    }


}
