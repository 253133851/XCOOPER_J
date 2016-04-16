package com.pabula.fw.filter;

import com.pabula.common.util.StrUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by sunsai on 2015/9/17.
 */
public class ShopAuthFilter implements Filter {
    private static final long CHECK_OPERATOR_TIME = 60*60*1000;//登录60分钟无操作，则清除登录信息,重新登录

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse)response;
        HttpServletRequest req = (HttpServletRequest)request;

        HttpSession session = req.getSession();
        long now = new Date().getTime();


        if(null==session.getAttribute("username")|| StrUtil.isNull(session.getAttribute("username").toString())){
            res.sendRedirect("../../ui/login.jsp");
            return;
        }

        long last = StrUtil.getNotNullLongValue("" + session.getAttribute("last_operator"));

        String type= (String)session.getAttribute("type");
        if((now - last) > CHECK_OPERATOR_TIME) {
            res.sendRedirect("../../ui/login.jsp");
            return;
        }else if(StrUtil.isNull(type) || !("shopadmin".equals(type) || "admin".equals(type))){
            res.sendRedirect("../../ui/error.jsp");
            return;
        } else {
            session.setAttribute("last_operator", now);
        }
        chain.doFilter(request,response);
    }

}
