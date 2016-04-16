package com.jiaorder.sys.login.web;

import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.StrUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出
 * Created by Pabula on 2015/7/4.
 */
public class logoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    /**
     * 退出处理
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        //日志记录
        LogUtil.operLog(LogType.LOGIN,"用户退出系统", UserHelper.getUserInfo(request).getUID(),request);

        String logOutPage = StrUtil.getNotNullStringValue(request.getParameter("LogOutReturnPage"),"/");

        // 清除客户端的 Cookie
        Cookie killUidCookie=new Cookie("lid", null);
        Cookie killDateTimeCookie=new Cookie("dateTime", null);
        Cookie killEncryptCookie = new Cookie("hhpd",null);

        killUidCookie.setPath("/");
        killDateTimeCookie.setPath("/");
        killEncryptCookie.setPath("/");

        killUidCookie.setMaxAge(0); // 0M
        killDateTimeCookie.setMaxAge(0);
        killEncryptCookie.setMaxAge(0);

        response.addCookie(killUidCookie); // write
        response.addCookie(killDateTimeCookie);
        response.addCookie(killEncryptCookie);

        request.getSession().invalidate();

        response.sendRedirect(logOutPage);
    }

}
