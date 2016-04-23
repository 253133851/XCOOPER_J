package com.xcooper.member.merbertask.web.command;

import com.pabula.common.util.JsonResultUtil;
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
import com.xcooper.member.merbertask.busi.MemberTaskBean;
import com.xcooper.member.merbertask.vo.MemberTaskVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxUpdateMemberTaskCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        MemberTaskBean memberTaskBean = new MemberTaskBean();

        int id = StrUtil.getNotNullIntValue(request.getParameter("id"), 0);

        MemberTaskVO memberTaskVO = memberTaskBean.getMemberTaskByID(id);

        //修改 memberId
        memberTaskVO.setMEMBER_ID(StrUtil.getNotNullIntValue(request.getParameter("memberId"),0));

        //修改 taskId
        memberTaskVO.setTASK_ID(StrUtil.getNotNullIntValue(request.getParameter("taskId"),0));

        //修改是否关注 isFocus
        memberTaskVO.setIS_FOCUS(StrUtil.getNotNullIntValue(request.getParameter("isFocus"),0));


        memberTaskBean.modifyMemberTask(memberTaskVO);

        //返回ok

        return JsonResultUtil.instance().ok();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}
