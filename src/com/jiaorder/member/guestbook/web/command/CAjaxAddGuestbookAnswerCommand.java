package com.jiaorder.member.guestbook.web.command;

import com.jiaorder.member.guestbook.busi.GuestbookAnswerBean;
import com.jiaorder.member.guestbook.vo.GuestbookAnswerVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 新增单位command
 */
public class CAjaxAddGuestbookAnswerCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        //日志记录
        LogUtil.operLog(LogType.OTHER,"新增回复",ID,request);

        //创建单位vo对象并且新增到数据库
        GuestbookAnswerVO vo = new GuestbookAnswerVO();
        vo.setCONTENT(ANSWER);
        vo.setGUESTBOOK_ID(ID);
        vo.setUSER_ID(USER_ID);
        vo.setSERVICE_ID(serviceId);
        try {
            GuestbookAnswerBean.newInstance().addGuestbookAnswer(vo);
            return JsonResultUtil.ok();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return JsonResultUtil.error();
    }

    int serviceId,ID,USER_ID;
    String ANSWER;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {
        //接收单位相关数据
        serviceId =  UserHelper.getServiceID(request);
        USER_ID =  UserHelper.getUserInfo(request).getUID();
        ANSWER = request.getParameter("ANSWER");
        ID = StrUtil.getNotNullIntValue(request.getParameter("ID"), 0);
    }

}
