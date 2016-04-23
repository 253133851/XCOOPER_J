package com.xcooper.sys.user.user.web.command;

import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.exception.SysException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;
import com.xcooper.list.busi.ListBean;
import com.xcooper.list.vo.ListVO;
import com.xcooper.member.member.busi.MemberBean;
import com.xcooper.member.member.vo.MemberVO;
import com.xcooper.sys.user.user.busi.UserBean;
import com.xcooper.sys.user.user.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxAddUserCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        UserBean userBean = new UserBean();

        UserVO userVO = new UserVO();

        MemberBean memberBean = new MemberBean();

        MemberVO memberVO = new MemberVO();

        userVO.setUID(SeqNumHelper.getNewSeqNum("user"));

        //插入登录id loginId
        userVO.setLOGIN_ID(request.getParameter("loginId"));

        //插入密码 pwd
        userVO.setPWD(request.getParameter("pwd"));

        //插入用户状态 state
        userVO.setSTATE(StrUtil.getNotNullIntValue(request.getParameter("state"),0));

        //插入e-mail email
        userVO.setEMAIL(request.getParameter("email"));

        //插入头像url faceUrl
        userVO.setFACE_URL(request.getParameter("faceUrl"));

        //插入个人说明 userRemark
        userVO.setUSER_REMARK(request.getParameter("userRemark"));

        //插入手机验证码 regVilidateCode
        userVO.setREG_VILIDATE_CODE(request.getParameter("regVilidateCode"));

        //插入手机号 phone
        userVO.setPHONE(request.getParameter("phone"));

        //向member表中插入memberId
        memberVO.setMEMBER_ID(SeqNumHelper.getNewSeqNum("member"));

        //插入uid
        memberVO.setUID(userVO.getUID());

        //插如用户姓名 name
        memberVO.setNAME(request.getParameter("name"));

        //插入职位 job
        memberVO.setJOB(request.getParameter("job"));

        //插入手机号 phone
        memberVO.setMOBILE(userVO.getPHONE());

        //插入e-mail email
        memberVO.setEMAIL(userVO.getEMAIL());

        //插入QQ qq
        memberVO.setQQ(request.getParameter("qq"));


        try {
            userBean.addUser(userVO);
            memberBean.addMember(memberVO);
            return JsonResultUtil.instance().ok();
        } catch (DataAccessException e) {
            return JsonResultUtil.instance().
                    addMsg(e.getMessage())
                    .addCode(JsonResultUtil.ERROR).json();
        }

//        返回error
//        JsonResultUtil.instance().error();
//        返回ok
//        JsonResultUtil.instance().ok();
//        返回带参数的json
//        JsonResultUtil.instance()
//                .addMsg(e.getMessage()).
//                addCode(JsonResultUtil.OK)
//                .addData("xxx")
//                .json();
    }

    //项目id
    int projectId;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}
