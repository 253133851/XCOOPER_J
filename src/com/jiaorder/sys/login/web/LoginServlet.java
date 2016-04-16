package com.jiaorder.sys.login.web;

import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.user.busi.UserBean;
import com.jiaorder.sys.user.vo.UserVO;
import com.pabula.common.util.MD5;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 会员登录
 * Created by Pabula on 2015/7/3.
 */
public class LoginServlet extends HttpServlet {

    Logger log = Logger.getLogger(LoginServlet.class);

    String uid;
    String pwd;

    String loginPage;   //登录成功的页面
    String loginErrpage;    //登录失败的页面

    UserBean userBean=new UserBean();
    UserVO uservo  = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }


    /**
     * 处理登录逻辑
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        //用户名与密码
        uid = StrUtil.replaceAllSpace(request.getParameter("UID").trim().toLowerCase());
        pwd = request.getParameter("PWD");

        //登录成功与失败页
        loginPage = StrUtil.getNotNullStringValue(request.getParameter("loginPage"),"/");
        loginErrpage = StrUtil.getNotNullStringValue(request.getParameter("LoginErrpage"),"/common/member_login_err.jsp");

//        //是否在登录后，返回原登录位置
//        int isReturnRequetURL = StrUtil.getNotNullIntValue(request.getParameter("isReturnThisPage"),0);
//        if(isReturnRequetURL == 1){
//            loginPage = request.getHeader("Referer");
//        }

        log.error("安全事件: [" + uid + "] 正在试图登录! ");

        //检验是否登陆成功
        String isLogin = check();

        //登陆后的处理
        if(isLogin.equals("") || isLogin.equalsIgnoreCase("ok")){
            //登陆成功
            UserHelper.setLoginInfo(request, response, uservo);

            //页面跳转
            response.sendRedirect(loginPage);
        }else{
            //登陆失败
            loginErr(request, response, isLogin);
        }

    }


    /**
     * 检测用户登录的合法性
     * @return
     */
    public String check() {
        if(!StrUtil.checkStringRule(uid)){
            return "录入的数据非法";
        }

        try {
            //获取用户信息
            uservo  = userBean.getUserForLoginID(this.uid);

            String dbPwd = uservo.getPWD();	//密码
//            state = uservo.getSTATE();	//状态

//            if(!(state==1)){
//                return "对不起，用户暂未通过管理员审核，或者已经被停用！";
//            }

            //如果数据库中取得的PWD与算出的PWD相同，则认证通过
            String md5Pwd = MD5.MD5Encode(dbPwd);
            if(dbPwd.equals(md5Pwd)){//已经移位的MD5编码
                return "ok";  //登录成功
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return "用户名或密码错误";
    }





    /**
     * 登陆错误处理
     * @param request
     * @param response
     * @param errMsg
     * @throws IOException
     * @throws ServletException
     */
    public void loginErr(HttpServletRequest request,HttpServletResponse response,String errMsg) throws IOException, ServletException{
        request.setAttribute("err", errMsg);
        request.getRequestDispatcher(this.loginErrpage).forward(request, response);
    }


}
