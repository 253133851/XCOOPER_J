package com.pabula.common.util;

import javax.servlet.http.HttpServletRequest;


/**
 * IP位置助手
 * @author dekn
 * www.cms4j.com 专业的 java /jsp 版网站内容管理系统
 * 2009-9-5 下午04:16:35
 */
public class IPUtil{

    /**
     * 获得客户IP
     * @param request
     * @return
     */
    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

}
