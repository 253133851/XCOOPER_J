package com.jiaorder.sys.login;

import com.xcooper.ENV;
import com.jiaorder.sys.user.busi.UserBean;
import com.jiaorder.sys.user.vo.UserVO;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.MD5;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 会员助手
 * Created by Pabula on 2015/7/4.
 */
public class UserHelper {

    /**
     * 登录成功后设置相关信息
     * @param req
     * @param resp
     * @param uservo
     */
    public static void setLoginInfo(HttpServletRequest req,HttpServletResponse resp,UserVO uservo) {
        //设置SESSION的生命周期
        req.getSession().setMaxInactiveInterval(600*60);    //10小时

        //设置用户登陆信息
        UserHelper.setSessionForUser(req, uservo);

        //Cookie 加密方法
        String encrypt = MD5.MD5Encode(uservo.getUID()  + ENV.MEMBER_COOKIE_PWD_CHECK);

        Cookie uidCookie = new Cookie("lid", URLEncoder.encode(String.valueOf(uservo.getUID()))); //MID放至cookie中
        Cookie dateTime = new Cookie("dateTime", DateUtil.getCurrentDay("yyyy-MM-dd")); //登录的日期
        Cookie encryptCookie = new Cookie("hhpd",encrypt);  //加密码 唤换pwd

        uidCookie.setMaxAge(60*60*24*30*24);  //2year
        uidCookie.setPath("/");
        dateTime.setMaxAge(60*60*24*30*24);
        dateTime.setPath("/");
        encryptCookie.setMaxAge(60*60*24*30*24);
        encryptCookie.setPath("/");

        //写入cookie
        resp.addCookie(uidCookie);
        resp.addCookie(dateTime);
        resp.addCookie(encryptCookie);

        //Cookie[] cookie = ((HttpServletRequest)req).getCookies();

    }

    /**
     * 判断是否为登录状态
     * @param request
     * @return
     */
    public static boolean isLogin(HttpServletRequest request){
        UserVO userInfo = new UserVO();
        UserBean bean = new UserBean();

        boolean isHaveCookie = false;

        Object memObj = request.getSession().getAttribute("UserInfo");

        //先判断session，如果没有再找Cookie
        if(null != memObj && ((UserVO)memObj).getUID()>0){
            return true;
        }else{
            Cookie[] cookie = request.getCookies();
            String uidCookie = "";
            String dateTime="";
            String encrypt="";
            boolean check = false;
            //判断有没有Cookie
            if(null == cookie || cookie.length ==0){
                return false;  //没有/客户端不支持 Cookie
            }else{
                try{
                    //枚举Cookie
                    for(int i=0;i<cookie.length; i++){
                        // 获得用户UID
                        if(cookie[i].getName().equals("lid")){
                            uidCookie = URLDecoder.decode(cookie[i].getValue());
                        }
                        //获得最后一次自动登陆的时间
                        if(cookie[i].getName().equals("dateTime")){
                            dateTime = cookie[i].getValue();
                        }

                        //获得加密验证串
                        if(cookie[i].getName().equals("hhpd")){
                            encrypt = cookie[i].getValue();
                        }
                    }

                    //验证加密密码
                    if(StrUtil.isNotNull(uidCookie) && StrUtil.isNotNull(encrypt)){
                        String encryptCheck = MD5.MD5Encode(uidCookie + ENV.MEMBER_COOKIE_PWD_CHECK);
                        if(encryptCheck.equals(encrypt)){
                            check = true;
                        }
                    }
                    //设置登陆成功后的用户 session
                    if(StrUtil.isNotNull(uidCookie) && check){
                        userInfo = bean.getUserByID(Integer.parseInt(uidCookie));

                        //设置用户登陆信息
                        setSessionForUser(request, userInfo);

                        isHaveCookie = true;

                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        return isHaveCookie;
    }

    /**
     * 设置用户会话信息
     * @param request
     * @param userInfo
     * @author lw 2007-9-25 下午05:47:46
     */
    public static void setSessionForUser(HttpServletRequest request, UserVO userInfo){
        request.getSession().setAttribute("UserInfo",userInfo);
        request.getSession().setAttribute("UID",String.valueOf(userInfo.getUID()));
    }

    /**
     * 取得当前登陆的用户VO
     * @param request
     * @return
     */
    public static UserVO getUserInfo(HttpServletRequest request){
        UserVO user = null;

        if(isLogin(request)){
            user = (UserVO)request.getSession().getAttribute("UserInfo");
        }

        return user;
    }


    /**
     * 取得 SERVICE_ID
     * @param request
     * @return
     */
    public static int getServiceID(HttpServletRequest request){
        int serviceID = -1;

        UserVO userVO = getUserInfo(request);
        if(null != userVO){
            serviceID = userVO.getSERVICE_ID();
        }

        return serviceID;
    }


    /**
     * 取得 UID
     * @param request
     * @return
     */
    public static int getUID(HttpServletRequest request){
        int UID = -1;

        UserVO userVO = getUserInfo(request);
        if(null != userVO){
            UID = userVO.getUID();
        }

        return UID;
    }


    /**
     * 更新用户的session
     * @param request
     * @author dekn   2008-11-13 上午11:47:03
     */
    public static void updateUserSession(HttpServletRequest request){
        int mid = 0;

        UserVO memberVO = getUserInfo(request);
        if(null != memberVO){
            mid = memberVO.getUID();
        }else{
            return;
        }

        try {
            UserVO userInfo = new UserBean().getUserByID(mid);
            setSessionForUser(request, userInfo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

}

